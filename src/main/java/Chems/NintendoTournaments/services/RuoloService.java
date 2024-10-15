package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Utente;
import Chems.NintendoTournaments.enums.RuoloUtente;
import Chems.NintendoTournaments.exceptions.BadRequestException;
import Chems.NintendoTournaments.exceptions.NotFoundException;
import Chems.NintendoTournaments.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RuoloService {

    @Autowired
    private UtenteRepository utenteRepository;

    // Assegna un ruolo a un utente esistente
    public Utente assegnaRuolo(UUID idUtente, RuoloUtente ruolo) {
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new NotFoundException("Utente con ID " + idUtente + " non trovato."));

        //Imposta il ruolo all'utente
        utente.setRuolo(ruolo);

        return utenteRepository.save(utente);
    }

    // Verifica se un utente ha un certo ruolo
    public boolean verificaRuolo(UUID idUtente, RuoloUtente ruolo) {
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new NotFoundException("Utente con ID " + idUtente + " non trovato."));

        return utente.getRuolo() == ruolo;
    }

    // Aggiungi un nuovo ruolo
    public void aggiungiRuolo(RuoloUtente nuovoRuolo) {
        throw new BadRequestException("I ruoli sono definiti come enum e non possono essere aggiunti dinamicamente.");
    }

    // Rimuovi un ruolo da un utente
    public Utente rimuoviRuolo(UUID idUtente) {
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new NotFoundException("Utente con ID " + idUtente + " non trovato."));

        // Rimuovi il ruolo impostandolo su null o su un ruolo predefinito
        utente.setRuolo(null); // O puoi impostare un ruolo predefinito

        return utenteRepository.save(utente);
    }
}
