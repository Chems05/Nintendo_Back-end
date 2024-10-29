package Chems.NintendoTournaments.controllers;

import Chems.NintendoTournaments.entities.Squadra;
import Chems.NintendoTournaments.payloads.SquadraDTO;
import Chems.NintendoTournaments.services.SquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/squadre")
public class SquadraController {

    @Autowired
    private SquadraService squadraService;

    // Crea una nuova squadra
    @PostMapping
    public ResponseEntity<Squadra> createSquadra(@RequestBody SquadraDTO squadraDTO) {
        Squadra nuovaSquadra = squadraService.saveSquadra(squadraDTO);
        return ResponseEntity.status(201).body(nuovaSquadra);
    }

    // Ottieni tutte le squadre
    @GetMapping
    public ResponseEntity<List<Squadra>> getAllSquadre() {
        List<Squadra> squadre = squadraService.findAll();
        return ResponseEntity.ok(squadre);
    }

    // Ottieni una squadra per ID
    @GetMapping("/{id}")
    public ResponseEntity<Squadra> getSquadraById(@PathVariable UUID id) {
        Squadra squadra = squadraService.findById(id);
        return ResponseEntity.ok(squadra);
    }

    // Elimina una squadra per ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSquadra(@PathVariable UUID id) {
        squadraService.deleteSquadra(id);
        return ResponseEntity.noContent().build();
    }

    // Ottieni tutte le squadre per un torneo specifico
    @GetMapping("/tornei/{torneoId}")
    public ResponseEntity<List<Squadra>> getSquadreByTorneo(@PathVariable UUID torneoId) {
        List<Squadra> squadre = squadraService.findAllByTorneo(torneoId);
        return ResponseEntity.ok(squadre);
    }
}
