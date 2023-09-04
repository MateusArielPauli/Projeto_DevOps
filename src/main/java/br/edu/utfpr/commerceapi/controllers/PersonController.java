package br.edu.utfpr.commerceapi.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.commerceapi.models.Person;
import br.edu.utfpr.commerceapi.repositories.PersonRepository;

@RestController
@RequestMapping("/pessoa")

public class PersonController {
    @Autowired
    PersonRepository personRepository;

    /*Obter todas as pessoas do banco */
    @GetMapping(value = {"", "/"})
    public String getAll() {
        return "Aqui estão as pessoas...";
    }

    /*Obter pessoa pelo ID */
    @GetMapping("/{id}")
    public String getById(@PathVariable Long id) {
        return "Aqui está a pessoa: " + id;
    }

    /*Atualizar 1 pessoa pelo ID */
    @PostMapping("")
    public Person create() {
        var pes = new Person();
        pes.setName("Joaozinho da Silva");
        pes.setEmail("joazinho@gmail.com");
        pes.setNascimento(LocalDateTime.now());

        personRepository.save(pes);

        return pes;
    }

    /*Atualizar 1 pessoa pelo ID */
    @PutMapping("/{id}")
    public String update(@PathVariable Long id) {
        return "Pessoa " + id + "atualizada";
    }

    /*Deletar 1 pessoa pelo ID */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return "Pessoa " + id + "deletada";
    }
}
