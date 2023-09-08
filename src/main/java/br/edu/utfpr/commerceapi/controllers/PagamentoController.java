package br.edu.utfpr.commerceapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.utfpr.commerceapi.models.Pagamento;
import br.edu.utfpr.commerceapi.repositories.PagamentoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @GetMapping("")
    public ResponseEntity<List<Pagamento>> getAll() {
        List<Pagamento> pagamento = pagamentoRepository.findAll();

        return new ResponseEntity<>(pagamento, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getById(@PathVariable UUID id) {
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);

        if (pagamento.isPresent()) {
            return new ResponseEntity<>(pagamento.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pagamento) {
        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);

        return new ResponseEntity<>(pagamentoSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable UUID id, @RequestBody Pagamento updatedPagamento) {
        Optional<Pagamento> pagamentoExistente = pagamentoRepository.findById(id);

        if (pagamentoExistente.isPresent()) {
            Pagamento pagamento = pagamentoExistente.get();
            // Atualize os campos do pagamento conforme necessário
            // pagamento.setCampo(updatedPagamento.getCampo());
            pagamentoRepository.save(pagamento);
            return new ResponseEntity<>(pagamento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePagamento(@PathVariable UUID id) {
        Optional<Pagamento> pagamentoExistente = pagamentoRepository.findById(id);
        
        if (pagamentoExistente.isPresent()) {
            pagamentoRepository.delete(pagamentoExistente.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}