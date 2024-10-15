package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Partecipante;
import Chems.NintendoTournaments.entities.Torneo;
import Chems.NintendoTournaments.entities.Utente;
import Chems.NintendoTournaments.exceptions.BadRequestException;
import Chems.NintendoTournaments.exceptions.NotFoundException;
import Chems.NintendoTournaments.payloads.PartecipanteDTO;
import Chems.NintendoTournaments.repositories.PartecipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PartecipanteService {

    @Autowired
    private PartecipanteRepository partecipanteRepository;

    @Autowired
    private TorneoService torneoService;

    @Autowired
    private UtenteService utenteService;

    // Crea un nuovo partecipante
    public Partecipante savePartecipante(PartecipanteDTO partecipanteDTO) {
        if (partecipanteDTO == null) {
            throw new BadRequestException("Il DTO del partecipante non può essere nullo.");
        }

        Torneo torneo = torneoService.findById(partecipanteDTO.torneoId());
        Utente giocatore = utenteService.findById(partecipanteDTO.giocatoreId());

        Partecipante partecipante = new Partecipante(torneo, giocatore);
        return partecipanteRepository.save(partecipante);
    }

    // Trova un partecipante per ID
    public Partecipante findById(UUID id) {
        return partecipanteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Partecipante con ID " + id + " non trovato."));
    }

    // Trova tutti i partecipanti
    public List<Partecipante> findAll() {
        return partecipanteRepository.findAll();
    }

    // Aggiorna un partecipante
    public Partecipante updatePartecipante(UUID id, PartecipanteDTO partecipanteDTO) {
        Partecipante partecipante = findById(id);

        Torneo torneo = torneoService.findById(partecipanteDTO.torneoId());
        Utente giocatore = utenteService.findById(partecipanteDTO.giocatoreId());

        partecipante.setTorneo(torneo);
        partecipante.setGiocatore(giocatore);

        return partecipanteRepository.save(partecipante);
    }

    // Elimina un partecipante
    public void deletePartecipante(UUID id) {
        Partecipante partecipante = findById(id);
        partecipanteRepository.delete(partecipante);
    }
}
