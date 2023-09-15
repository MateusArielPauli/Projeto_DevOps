package br.edu.utfpr.commerceapi.dto;

import br.edu.utfpr.commerceapi.models.Person;
import br.edu.utfpr.commerceapi.models.Reserva;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
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
public class PagamentoDTO {

  @NotBlank(message = "Favor digitar valor")
  private double valor;

  @NotBlank(message = "Favor digitar a data do pagamento")
  private LocalDateTime dataPagamento;

  @NotBlank(message = "Favor digitar o status")
  private String status;

  @NotBlank(message = "Favor digitar a forma de pagamento")
  private String formaPagamento;

  @NotBlank(message = "Favor digitar a reserva")
  private Reserva reserva;

  @NotBlank(message = "Favor digitar a pessoa")
  private Person pessoa;
}