package br.edu.utfpr.commerceapi.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import br.edu.utfpr.commerceapi.models.Person;


//@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    public boolean existsByIdAndEmail(UUID id, String email);

    public boolean existsByEmail(String email);

    public boolean existsByEmailAndPassword(String email, String password);

    public Optional<Person> findByEmail(String email);

    public Optional<Person> findByEmailAndPassword(String email, String password);

    public List<Person> findByNome(String nome);
    
}
