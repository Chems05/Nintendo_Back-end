package Chems.NintendoTournaments.repositories;

import Chems.NintendoTournaments.entities.Gioco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GiocoRepository extends JpaRepository<Gioco, UUID> {
}
