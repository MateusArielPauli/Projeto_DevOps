package br.edu.utfpr.commerceapi.dto;

import java.time.LocalDate;
import java.util.List;

import br.edu.utfpr.commerceapi.models.Reserva;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class PersonDTO{
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private LocalDate nascimento;
    private String cpf;
    private Boolean cliente;
    private List<Reserva> reservas;
}