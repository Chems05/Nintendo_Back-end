package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Squadra;
import Chems.NintendoTournaments.entities.Torneo;
import Chems.NintendoTournaments.entities.Utente;
import Chems.NintendoTournaments.exceptions.BadRequestException;
import Chems.NintendoTournaments.exceptions.NotFoundException;
import Chems.NintendoTournaments.payloads.SquadraDTO;
import Chems.NintendoTournaments.payloads.UtenteDTO;
import Chems.NintendoTournaments.repositories.SquadraRepository;
import Chems.NintendoTournaments.repositories.TorneoRepository;
import Chems.NintendoTournaments.repositories.UtenteRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SquadraService {

    @Autowired
    private SquadraRepository squadraRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private TorneoRepository torneoRepository;

    // Creazione di una nuova squadra
    public Squadra saveSquadra(SquadraDTO squadraDTO) {
        Squadra nuovaSquadra = new Squadra();
        nuovaSquadra.setNome(squadraDTO.nome());

        // Aggiungere il torneo associato
        if (squadraDTO.torneoId() != null) {
            Torneo torneo = torneoRepository.findById(squadraDTO.torneoId())
                    .orElseThrow(() -> new NotFoundException("Torneo non trovato con ID: " + squadraDTO.torneoId()));
            nuovaSquadra.setTorneo(torneo); // Associa il torneo alla squadra
        }

        // Aggiungere i giocatori alla squadra
        if (squadraDTO.giocatori() != null) {
            List<Utente> giocatori = utenteRepository.findAllById(
                    squadraDTO.giocatori().stream()
                            .map(UtenteDTO::id)
                            .toList()
            );

            // Controlla se ci sono utenti non trovati
            if (giocatori.size() != squadraDTO.giocatori().size()) {
                throw new BadRequestException("Uno o più utenti non esistono!");
            }
            nuovaSquadra.setGiocatori(giocatori);
        }

        return squadraRepository.save(nuovaSquadra);
    }

    // Trova una squadra per ID
    public Squadra findById(@NotNull UUID id) {
        return squadraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Squadra con ID " + id + " non trovata."));
    }

    // Trova tutte le squadre
    public List<Squadra> findAll() {
        return squadraRepository.findAll();
    }

    // Aggiorna una squadra
    public Squadra updateSquadra(UUID id, SquadraDTO squadraDTO) {
        Squadra squadra = findById(id);
        squadra.setNome(squadraDTO.nome());

        // Aggiungere/aggiornare il torneo associato
        if (squadraDTO.torneoId() != null) {
            Torneo torneo = torneoRepository.findById(squadraDTO.torneoId())
                    .orElseThrow(() -> new NotFoundException("Torneo non trovato con ID: " + squadraDTO.torneoId()));
            squadra.setTorneo(torneo); // Aggiorna il torneo associato alla squadra
        }

        // Aggiungere/aggiornare i giocatori nella squadra
        if (squadraDTO.giocatori() != null) {
            List<Utente> giocatori = utenteRepository.findAllById(
                    squadraDTO.giocatori().stream() // Chiamato su un'istanza di squadraDTO
                            .map(UtenteDTO::id)
                            .toList()
            );

            // Controlla se ci sono utenti non trovati
            if (giocatori.size() != squadraDTO.giocatori().size()) {
                throw new BadRequestException("Uno o più utenti non esistono!");
            }
            squadra.setGiocatori(giocatori);
        }

        return squadraRepository.save(squadra);
    }

    // Elimina una squadra
    public void deleteSquadra(UUID id) {
        Squadra squadra = findById(id);
        squadraRepository.delete(squadra);
    }
}
