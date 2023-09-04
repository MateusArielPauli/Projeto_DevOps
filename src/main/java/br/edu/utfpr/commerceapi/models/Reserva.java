package br.edu.utfpr.commerceapi.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_reserva")
@Setter
@Getter
public class Reserva extends BaseEntity{
    //Quando o passeio sera realizado
    private LocalDateTime data;
    
    @ManyToOne
    @JoinColumn(name = "pacote_id")
    private Pacote pacote;

    //cliente fez reserva
    @ManyToOne
    private Person person;
}
