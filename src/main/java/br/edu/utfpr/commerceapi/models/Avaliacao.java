package br.edu.utfpr.commerceapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "tb_avaliacao")
public class Avaliacao extends BasicEntity {

  @Column(name = "titulo", length = 150, nullable = false)
  private String titulo;

  @Column(name = "classificacao", nullable = false)
  private int classificacao; // De 1 a 5 estrelas

  @Column(name = "data_avaliacao", nullable = false)
  private LocalDate dataAvaliacao;

  @Column(name = "comentario")
  private String comentario;

  // Relacionamento Many-to-One com Passeio: Várias avaliações estão associadas a
  // um passeio.
  @ManyToOne
  @JoinColumn(name = "passeio_id")
  private Pacote passeio;

  // Relacionamento Many-to-One com Pessoa (cliente): Várias avaliações estão
  // associadas a um cliente.
  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Person cliente;
}