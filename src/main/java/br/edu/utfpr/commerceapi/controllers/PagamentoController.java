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

import br.edu.utfpr.commerceapi.dto.PagamentoDTO;
import br.edu.utfpr.commerceapi.models.Pagamento;
import br.edu.utfpr.commerceapi.repositories.PagamentoRepository;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @GetMapping("")
    public ResponseEntity<List<Pagamento>> getAll() {
        try{
            List<Pagamento> pagamento = pagamentoRepository.findAll();
      
            return new ResponseEntity<>(pagamento, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getById(@PathVariable String id) {
        try {
            Optional<Pagamento> pagamento = pagamentoRepository.findById(UUID.fromString(id));
      
            if (pagamento.isPresent()) {
              return new ResponseEntity<>(pagamento.get(), HttpStatus.OK);
            } else {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Pagamento> criarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        try {
        var pagamento = new Pagamento();
        BeanUtils.copyProperties(pagamentoDTO, pagamento);

        Pagamento salvarPagamento = pagamentoRepository.save(pagamento);

        return new ResponseEntity<>(salvarPagamento, HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizaPagamento(@PathVariable UUID id, @RequestBody Pagamento updatedPagamento) {
        try {
            Optional<Pagamento> pagamentoExistente = pagamentoRepository.findById(id);
      
            if (pagamentoExistente.isPresent()) {
              Pagamento pagamento = pagamentoExistente.get();
      
              pagamentoRepository.save(pagamento);

              return new ResponseEntity<>(pagamento, HttpStatus.OK);
            } else {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
          } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaPagamento(@PathVariable UUID id) {
        try {
            Optional<Pagamento> pagamentoExistente = pagamentoRepository.findById(id);
      
            if (pagamentoExistente.isPresent()) {
              pagamentoRepository.delete(pagamentoExistente.get());

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