package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Utente;
import Chems.NintendoTournaments.enums.RuoloUtente;
import Chems.NintendoTournaments.exceptions.BadRequestException;
import Chems.NintendoTournaments.exceptions.NotFoundException;
import Chems.NintendoTournaments.payloads.UtenteDTO;
import Chems.NintendoTournaments.repositories.UtenteRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Creazione di un nuovo utente
    public Utente saveUtente(UtenteDTO utenteDTO) {
        if (utenteRepository.findByEmail(utenteDTO.email()).isPresent()) {
            throw new BadRequestException("Email già in uso!");
        }

        Utente nuovoUtente = new Utente();
        nuovoUtente.setUsername(utenteDTO.username());
        nuovoUtente.setEmail(utenteDTO.email());
        nuovoUtente.setPassword(passwordEncoder.encode(utenteDTO.password()));
        nuovoUtente.setRuolo(RuoloUtente.GIOCATORE); // Ruolo predefinito

        return utenteRepository.save(nuovoUtente);
    }

    // Trova un utente per ID
    public Utente findById(@NotNull UUID id) { // Corretto per accettare un singolo UUID
        return utenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente con ID " + id + " non trovato."));
    }

    // Trova tutti gli utenti
    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    // Aggiorna un utente
    public Utente updateUtente(UUID id, UtenteDTO utenteDTO) {
        Utente utente = findById(id); // Trova l'utente per ID

        // Controllo se l'email esiste già per un altro utente
        Optional<Utente> utenteEsistente = utenteRepository.findByEmail(utenteDTO.email());
        if (utenteEsistente.isPresent() && !utenteEsistente.get().getId().equals(id)) {
            throw new BadRequestException("Email già in uso!");
        }

        // Aggiorna solo l'email
        utente.setEmail(utenteDTO.email());

        // Aggiorna lo username solo se viene fornito
        if (utenteDTO.username() != null && !utenteDTO.username().isEmpty()) {
            utente.setUsername(utenteDTO.username());
        }

        // Cambia solo la password se non è nulla o vuota
        if (utenteDTO.password() != null && !utenteDTO.password().isEmpty()) {
            utente.setPassword(passwordEncoder.encode(utenteDTO.password()));
        }

        return utenteRepository.save(utente);
    }

    // Elimina un utente
    public void deleteUtente(UUID id) {
        Utente utente = findById(id); // Corretto per accettare un singolo UUID
        utenteRepository.delete(utente);
    }

    // Trova un utente per email
    public Utente trovaUtentePerEmail(String email) {
        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato."));
    }
}
