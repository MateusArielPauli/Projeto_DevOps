package br.edu.utfpr.commerceapi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.commerceapi.dto.PacoteDTO;
import br.edu.utfpr.commerceapi.models.Pacote;
import br.edu.utfpr.commerceapi.repositories.PacoteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Pacote")
public class PacoteController {

    @Autowired
    private PacoteRepository pacoteRepository;

    @GetMapping("")
    public ResponseEntity<List<Pacote>> getAll() {
        try{
        List<Pacote> pacote = pacoteRepository.findAll();
        return new ResponseEntity<>(pacote, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pacote> getById(@PathVariable String id) {
        try{
        Optional<Pacote> pacote = pacoteRepository.findById(UUID.fromString(id));

        if (pacote.isPresent()) {
            return new ResponseEntity<>(pacote.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Pacote> createPacote(@Valid @RequestBody PacoteDTO PacoteDTO) {
        try {
            var pacote = new Pacote();
            BeanUtils.copyProperties(PacoteDTO, pacote);
            Pacote pacoteSalvo = pacoteRepository.save(pacote);

            return new ResponseEntity<>(pacoteSalvo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pacote> updatePacote(@PathVariable UUID id, @RequestBody Pacote updatedPacote) {
        try {
            Optional<Pacote> pacoteExistente = pacoteRepository.findById(id);

            if (pacoteExistente.isPresent()) {
                Pacote pacote = pacoteExistente.get();
                // Atualize os campos do Pacote conforme necessário
                // Pacote.setCampo(updatedPacote.getCampo());
                pacoteRepository.save(pacote);
                return new ResponseEntity<>(pacote, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacote(@PathVariable UUID id) {
        try {
            Optional<Pacote> pacoteExistente = pacoteRepository.findById(id);
            if (pacoteExistente.isPresent()) {
                pacoteRepository.delete(pacoteExistente.get());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {String fieldName = ((FieldError) error).getField();
                                                                String errorMessage = error.getDefaultMessage();
                                                                errors.put(fieldName, errorMessage);});

        return errors;
    }
}