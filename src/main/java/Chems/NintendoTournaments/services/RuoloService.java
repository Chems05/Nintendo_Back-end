package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Utente;
import Chems.NintendoTournaments.enums.RuoloUtente;
import Chems.NintendoTournaments.exceptions.BadRequestException;
import Chems.NintendoTournaments.exceptions.NotFoundException;
import Chems.NintendoTournaments.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RuoloService {

    @Autowired
    private UtenteRepository utenteRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public Utente assegnaRuolo(UUID idUtente, RuoloUtente ruolo) {
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new NotFoundException("Utente con ID " + idUtente + " non trovato."));
        utente.setRuolo(ruolo);
        return utenteRepository.save(utente);
    }

    public boolean verificaRuolo(UUID idUtente, RuoloUtente ruolo) {
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new NotFoundException("Utente con ID " + idUtente + " non trovato."));
        return utente.getRuolo() == ruolo;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void aggiungiRuolo(RuoloUtente nuovoRuolo) {
        throw new BadRequestException("I ruoli sono definiti come enum e non possono essere aggiunti dinamicamente.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Utente rimuoviRuolo(UUID idUtente) {
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new NotFoundException("Utente con ID " + idUtente + " non trovato."));
        utente.setRuolo(null);
        return utenteRepository.save(utente);
    }
}
