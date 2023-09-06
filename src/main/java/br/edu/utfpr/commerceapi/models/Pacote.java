package br.edu.utfpr.commerceapi.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity

public class Pacote extends BaseEntity{
    
    @Column(name = "destino", length = 150)
    public String destino;

    @Column(name = "itinerario", length = 150)
    public String itinerario;

    @Column(name = "preco")
    public double preco;

    @Column(name = "qtdDias")
    public double qtdDias;

    @Column(name = "refeicao", length = 150)
    public String refeicao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pacote")
    private List<Reserva> reservas = new ArrayList<>();

}
