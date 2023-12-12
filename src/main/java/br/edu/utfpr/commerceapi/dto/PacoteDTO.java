package br.edu.utfpr.commerceapi.dto;

import br.edu.utfpr.commerceapi.models.Avaliacao;
import br.edu.utfpr.commerceapi.models.Reserva;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
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
public class PacoteDTO {

  @NotBlank(message = "Favor digitar o nome")
  private String nome;

  @NotBlank(message = "Favor digitar a descrição")
  private String descricao;

  private String imagem;

  @NotBlank(message = "Favor digitar o status")
  private boolean ativo;

  @NotBlank(message = "Favor digitar o valor")
  private double valor;

  @NotBlank(message = "Favor digitar a origem")
  private String origem;

  @NotBlank(message = "Favor digitar o destino")
  private String destino;

  private String itinerario;

  @NotBlank(message = "Favor digitar a data e hora de saída")
  private String dataHoraSaida;

  @NotBlank(message = "Favor digitar a data e hora de retorno")
  private String dataHoraRetorno;

  @NotBlank(message = "Favor digitar a quantidade máxima de pessoas")
  private int quantidadeMaximaPessoas;

  private String observacao;

  private List<Reserva> reservas;
  
  private List<Avaliacao> avaliacoes;
}