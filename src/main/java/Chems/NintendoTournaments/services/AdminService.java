package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Utente;
import Chems.NintendoTournaments.exceptions.NotFoundException;
import Chems.NintendoTournaments.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private UtenteRepository utenteRepository;

    public void assegnaRuolo(UUID idUtente, String nuovoRuolo) {
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new NotFoundException("Utente con ID " + idUtente + " non trovato."));
        // Logica per cambiare il ruolo dell'utente
        // Assicurati di implementare il cambiamento di ruolo
    }

    public void rimuoviUtente(UUID idUtente) {
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new NotFoundException("Utente con ID " + idUtente + " non trovato."));
        utenteRepository.delete(utente);
    }
}