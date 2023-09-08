package br.edu.utfpr.commerceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.utfpr.commerceapi.models.Reserva;
import java.util.UUID;

//@Repository
public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
    
}