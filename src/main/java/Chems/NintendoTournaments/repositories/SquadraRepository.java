package Chems.NintendoTournaments.repositories;

import Chems.NintendoTournaments.entities.Squadra;
import Chems.NintendoTournaments.entities.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SquadraRepository extends JpaRepository<Squadra, UUID> {
    List<Squadra> findByTorneo(Torneo torneo);
}
