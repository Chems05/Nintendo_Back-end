package Chems.NintendoTournaments.controllers;

import Chems.NintendoTournaments.entities.Squadra;
import Chems.NintendoTournaments.payloads.SquadraDTO;
import Chems.NintendoTournaments.services.SquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/squadre")
public class SquadraController {

    @Autowired
    private SquadraService squadraService;

    // Creazione di una nuova squadra
    @PostMapping
    public ResponseEntity<Squadra> createSquadra(@RequestBody @Validated SquadraDTO squadraDTO) {
        Squadra nuovaSquadra = squadraService.saveSquadra(squadraDTO);
        return ResponseEntity.created(URI.create("/api/squadre/" + nuovaSquadra.getId())).body(nuovaSquadra);
    }

    // Trova una squadra per ID
    @GetMapping("/{id}")
    public ResponseEntity<Squadra> getSquadra(@PathVariable UUID id) {
        Squadra squadra = squadraService.findById(id);
        return ResponseEntity.ok(squadra);
    }

    // Trova tutte le squadre
    @GetMapping
    public ResponseEntity<List<Squadra>> getAllSquadre() {
        List<Squadra> squadre = squadraService.findAll();
        return ResponseEntity.ok(squadre);
    }

    // Aggiorna una squadra
    @PutMapping("/{id}")
    public ResponseEntity<Squadra> updateSquadra(@PathVariable UUID id, @RequestBody @Validated SquadraDTO squadraDTO) {
        Squadra squadraAggiornata = squadraService.updateSquadra(id, squadraDTO);
        return ResponseEntity.ok(squadraAggiornata);
    }

    // Elimina una squadra
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSquadra(@PathVariable UUID id) {
        squadraService.deleteSquadra(id);
        return ResponseEntity.noContent().build();
    }
}

