package br.edu.utfpr.commerceapi.models;

import java.time.LocalDateTime;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tb_pagamento")

public class Pagamento extends BaseEntity{
    @Column(name = "valor", nullable = false)
    private double valor;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    @Column(name = "status", length = 150)
    private String status;

    @Column(name = "forma_pagamento", length = 150)
    private String formaPagamento;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;
}
