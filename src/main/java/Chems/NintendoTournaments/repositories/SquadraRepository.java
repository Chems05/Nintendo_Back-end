package Chems.NintendoTournaments.repositories;

import Chems.NintendoTournaments.entities.Squadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SquadraRepository extends JpaRepository<Squadra, UUID> {
}
