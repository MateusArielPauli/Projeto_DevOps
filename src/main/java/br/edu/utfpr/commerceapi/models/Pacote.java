package br.edu.utfpr.commerceapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
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
@Entity
@Table(name = "tb_passeio")
public class Pacote extends BasicEntity {

  @Column(name = "nome", length = 150, nullable = false)
  private String nome;

  @Column(name = "descricao", length = 300)
  private String descricao;

  @Column(name = "imagem")
  private String imagem;

  @Column(name = "ativo", nullable = false)
  private boolean ativo;

  @Column(name = "valor", nullable = false)
  private double valor;

  @Column(name = "origem", length = 150, nullable = false)
  private String origem;

  @Column(name = "destino", length = 150, nullable = false)
  private String destino;

  @Column(name = "itinerario", length = 300)
  private String itinerario;

  @Column(name = "data_hora_saida", nullable = false)
  private String dataHoraSaida;

  @Column(name = "data_hora_retorno", nullable = false)
  private String dataHoraRetorno;

  @Column(name = "quantidade_maxima_pessoas", nullable = false)
  private int quantidadeMaximaPessoas;

  @Column(name = "observacao")
  private String observacao;

  // Relacionamento One-to-Many com Reserva: Um passeio pode ter muitas reservas.
  @OneToMany
  @JoinColumn(name = "passeio_id")
  private List<Reserva> reservas = new ArrayList<>();

  // Relacionamento One-to-Many com Avaliacao: Um passeio pode ter muitas
  // avaliações.
  @OneToMany
  @JoinColumn(name = "avaliacao_id")
  private List<Avaliacao> avaliacoes = new ArrayList<>();
}