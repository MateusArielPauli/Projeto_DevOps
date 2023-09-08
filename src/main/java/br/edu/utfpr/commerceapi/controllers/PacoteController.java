package br.edu.utfpr.commerceapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.utfpr.commerceapi.models.Pacote;
import br.edu.utfpr.commerceapi.repositories.PacoteRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/Pacote")
public class PacoteController {

    @Autowired
    private PacoteRepository PacoteRepository;

    @GetMapping("")
    public ResponseEntity<List<Pacote>> getAll() {
        List<Pacote> pacote = PacoteRepository.findAll();
        return new ResponseEntity<>(pacote, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pacote> getById(@PathVariable UUID id) {
        Optional<Pacote> pacote = PacoteRepository.findById(id);
        if (pacote.isPresent()) {
            return new ResponseEntity<>(pacote.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Pacote> createPacote(@RequestBody Pacote pacote) {
        Pacote pacoteSalvo = PacoteRepository.save(pacote);
        return new ResponseEntity<>(pacoteSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pacote> updatePacote(@PathVariable UUID id, @RequestBody Pacote updatedPacote) {
        Optional<Pacote> pacoteExistente = PacoteRepository.findById(id);
        if (pacoteExistente.isPresent()) {
            Pacote pacote = pacoteExistente.get();
            // Atualize os campos do Pacote conforme necessário
            // Pacote.setCampo(updatedPacote.getCampo());
            PacoteRepository.save(pacote);
            return new ResponseEntity<>(pacote, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacote(@PathVariable UUID id) {
        Optional<Pacote> pacoteExistente = PacoteRepository.findById(id);
        if (pacoteExistente.isPresent()) {
            PacoteRepository.delete(pacoteExistente.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}