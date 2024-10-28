package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Utente;
import Chems.NintendoTournaments.enums.RuoloUtente;
import Chems.NintendoTournaments.exceptions.BadRequestException;
import Chems.NintendoTournaments.exceptions.NotFoundException;
import Chems.NintendoTournaments.payloads.UtenteDTO;
import Chems.NintendoTournaments.repositories.UtenteRepository;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary cloudinary;

    // Creazione di un nuovo utente
    public Utente saveUtente(UtenteDTO utenteDTO) {
        if (utenteRepository.findByEmail(utenteDTO.email()).isPresent()) {
            throw new BadRequestException("Email già in uso!");
        }

        Utente nuovoUtente = new Utente();
        nuovoUtente.setUsername(utenteDTO.username());
        nuovoUtente.setEmail(utenteDTO.email());
        nuovoUtente.setPassword(passwordEncoder.encode(utenteDTO.password()));
        nuovoUtente.setRuolo(RuoloUtente.ORGANIZZATORE); // Ruolo predefinito
        nuovoUtente.setAvatar("/assets/default.jpg"); // Imposta l'avatar di default

        return utenteRepository.save(nuovoUtente);
    }

    // Trova un utente per ID
    public Utente findById(UUID id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente con ID " + id + " non trovato."));
    }

    // Trova tutti gli utenti
    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    // Aggiorna un utente
    public Utente updateUtente(UUID id, UtenteDTO utenteDTO) {
        Utente utente = findById(id);

        Optional<Utente> utenteEsistente = utenteRepository.findByEmail(utenteDTO.email());
        if (utenteEsistente.isPresent() && !utenteEsistente.get().getId().equals(id)) {
            throw new BadRequestException("Email già in uso!");
        }

        utente.setEmail(utenteDTO.email());

        if (utenteDTO.username() != null && !utenteDTO.username().isEmpty()) {
            utente.setUsername(utenteDTO.username());
        }

        if (utenteDTO.password() != null && !utenteDTO.password().isEmpty()) {
            utente.setPassword(passwordEncoder.encode(utenteDTO.password()));
        }

        return utenteRepository.save(utente);
    }

    // Carica l'avatar di un utente
    public Utente uploadAvatar(UUID utenteId, MultipartFile file) {
        try {
            Utente utente = findById(utenteId);

            // Verifica che il file sia un'immagine
            String contentType = file.getContentType();
            if (!contentType.startsWith("image/")) {
                throw new BadRequestException("Il file caricato non è un'immagine valida!");
            }

            String url = (String) cloudinary.uploader()
                    .upload(file.getBytes(), ObjectUtils.emptyMap())
                    .get("url");

            utente.setAvatar(url);
            return utenteRepository.save(utente);

        } catch (IOException e) {
            throw new BadRequestException("Errore nel caricamento del file, verifica il formato o le dimensioni!");
        }
    }

    // Elimina un utente
    public void deleteUtente(UUID id) {
        Utente utente = findById(id);
        utenteRepository.delete(utente);
    }

    // Trova un utente per email
    public Utente trovaUtentePerEmail(String email) {
        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato."));
    }
}
