package Chems.NintendoTournaments.repositories;

import Chems.NintendoTournaments.entities.Messaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessaggioRepository extends JpaRepository<Messaggio, UUID> {

}
