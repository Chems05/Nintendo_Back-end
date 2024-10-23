package Chems.NintendoTournaments.controllers;

import Chems.NintendoTournaments.entities.Utente;
import Chems.NintendoTournaments.payloads.UtenteDTO;
import Chems.NintendoTournaments.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @PostMapping
    public ResponseEntity<Utente> createUtente(@RequestBody UtenteDTO utenteDTO) {
        Utente createdUtente = utenteService.saveUtente(utenteDTO);
        return ResponseEntity.status(201).body(createdUtente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUtenteById(@PathVariable UUID id) {
        Utente utente = utenteService.findById(id);
        return ResponseEntity.ok(utente);
    }

    @GetMapping
    public ResponseEntity<List<Utente>> getAllUtenti() {
        List<Utente> utenti = utenteService.findAll();
        return ResponseEntity.ok(utenti);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utente> updateUtente(@PathVariable UUID id, @RequestBody UtenteDTO utenteDTO) {
        Utente updatedUtente = utenteService.updateUtente(id, utenteDTO);
        return ResponseEntity.ok(updatedUtente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtente(@PathVariable UUID id) {
        utenteService.deleteUtente(id);
        return ResponseEntity.noContent().build();
    }
}

