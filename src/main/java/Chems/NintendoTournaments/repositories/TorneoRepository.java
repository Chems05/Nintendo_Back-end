package Chems.NintendoTournaments.repositories;

import Chems.NintendoTournaments.entities.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TorneoRepository extends JpaRepository<Torneo, UUID> {
}

