package br.edu.utfpr.commerceapi.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

@Entity

public class Pacote extends BaseEntity{
    //atributos....

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pacote")

    private List<Reserva> reservas = new ArrayList<>();
}
