package br.edu.utfpr.commerceapi.models;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "tb_reserva")

public class Reserva extends BaseEntity{
    //cliente que fez reserva
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Person person;
    
    @ManyToOne
    @JoinColumn(name = "pacote_id")
    private Pacote pacote;

    //Quando o passeio sera realizado
    @Column(name = "data_reserva")
    private LocalDate dataReserva;

    @Column(name = "qtd_pessoas")
    private int qtdPessoas;
}
