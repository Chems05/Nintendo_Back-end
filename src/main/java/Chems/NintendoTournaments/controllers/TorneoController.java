package Chems.NintendoTournaments.controllers;

import Chems.NintendoTournaments.entities.Torneo;
import Chems.NintendoTournaments.payloads.TorneoDTO;
import Chems.NintendoTournaments.services.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tornei")
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

    @PostMapping
    public ResponseEntity<Torneo> createTorneo(@RequestBody TorneoDTO torneoDTO) {
        Torneo createdTorneo = torneoService.saveTorneo(torneoDTO);
        return ResponseEntity.status(201).body(createdTorneo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Torneo> getTorneoById(@PathVariable UUID id) {
        Torneo torneo = torneoService.findById(id);
        return ResponseEntity.ok(torneo);
    }

    @GetMapping
    public ResponseEntity<List<Torneo>> getAllTornei() {
        List<Torneo> tornei = torneoService.findAll();
        return ResponseEntity.ok(tornei);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Torneo> updateTorneo(@PathVariable UUID id, @RequestBody TorneoDTO torneoDTO) {
        Torneo updatedTorneo = torneoService.updateTorneo(id, torneoDTO);
        return ResponseEntity.ok(updatedTorneo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTorneo(@PathVariable UUID id) {
        torneoService.deleteTorneo(id);
        return ResponseEntity.noContent().build();
    }
}

