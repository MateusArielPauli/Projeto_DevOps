package br.edu.utfpr.commerceapi.dto;

import br.edu.utfpr.commerceapi.models.Pacote;
import br.edu.utfpr.commerceapi.models.Person;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
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
public class ReservaDTO {

  @NotBlank(message = "O data da reserva não pode estar em branco")
  private LocalDate dataReserva;

  @NotBlank(message = "O quantidade de pessoas não pode estar em branco")
  private int quantidadePessoas;

  private String observacao;

  @NotBlank(message = "O id do passeio não pode estar em branco")
  private Pacote pacote;

  @NotBlank(message = "O id do cliente não pode estar em branco")
  private Person pessoa;
}