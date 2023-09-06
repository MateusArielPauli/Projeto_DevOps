package br.edu.utfpr.commerceapi.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@Table(name = "tb_person")

public class Person extends BaseEntity{
    
    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "telefone", length = 150, nullable = false)
    private String telefone;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "senha", length = 150, nullable = false)
    private String senha;

    @Column(name = "nascimento")
    private LocalDate nascimento;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "type")//cliente ou agencia de viagens deixar padrao cliente
    private Boolean cliente;

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;
}
