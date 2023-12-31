package br.edu.utfpr.commerceapi.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
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
public class Person extends BasicEntity implements UserDetails {

    // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 150, nullable = false)
    private String password;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "dataNascimento")
    private LocalDateTime dataNascimento;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cliente", columnDefinition = "boolean default true")
    private Boolean cliente; //pessoa fisica ou juridica

    @ManyToMany(mappedBy = "person")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "cliente")
    private List<Avaliacao> avaliacoes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Role> roles = new ArrayList<>();

    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Override
    @JsonIgnore
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }


}