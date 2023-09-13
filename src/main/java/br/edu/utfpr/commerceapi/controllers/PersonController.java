package br.edu.utfpr.commerceapi.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.commerceapi.dto.PersonDTO;
import br.edu.utfpr.commerceapi.models.Person;
import br.edu.utfpr.commerceapi.repositories.PersonRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")

public class PersonController {
    @Autowired
    PersonRepository personRepository;

    /* Obter todas as pessoas do banco */
    @GetMapping("/pages")
    public ResponseEntity<Page<Person>> getAll(
            @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(personRepository.findAll(pageable));
    }

    /**
     * Obter todas as pessoas do banco.
     */
    @GetMapping(value = {"", "/"})
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    /* Obter pessoa pelo ID */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        Optional<Person> personOpt = personRepository.findById(UUID.fromString(id));

        return personOpt.isPresent() ? ResponseEntity.ok(personOpt.get()) : ResponseEntity.notFound().build();
    }

    /* Atualizar 1 pessoa pelo ID */
    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody PersonDTO personDTO) {
        var pes = new Person(); // pessoa para persistir no BD
        BeanUtils.copyProperties(personDTO, pes);

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(personRepository.save(pes));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao criar pessoa.");
        }
    }

    /* Atualizar 1 pessoa pelo ID */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody PersonDTO personDTO) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Formato de UUID inválido");
        }

        // Buscando a pessoa no banco de dados
        var person = personRepository.findById(uuid);

        // Verifica se ela existe
        if (person.isEmpty())
            return ResponseEntity.notFound().build();

        var personToUpdate = person.get();
        BeanUtils.copyProperties(personDTO, personToUpdate);
        personToUpdate.setUpdatedAt(LocalDateTime.now());

        try {
            return ResponseEntity.ok().body(personRepository.save(personToUpdate));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao atualizar pessoa.");
        }
    }

    /* Deletar 1 pessoa pelo ID */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {

        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Formato de UUID inválido");
        }

        var person = personRepository.findById(uuid);

        if (person.isEmpty())
            return ResponseEntity.notFound().build();

        try {
            personRepository.delete(person.get());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
 
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
 
        return errors;
    }
}
