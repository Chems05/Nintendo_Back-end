package Chems.NintendoTournaments.controllers;

import Chems.NintendoTournaments.entities.Squadra;
import Chems.NintendoTournaments.payloads.SquadraDTO;
import Chems.NintendoTournaments.services.SquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/squadre")
public class SquadraController {

    @Autowired
    private SquadraService squadraService;

    @PostMapping
    public ResponseEntity<Squadra> createSquadra(@RequestBody SquadraDTO squadraDTO) {
        Squadra createdSquadra = squadraService.saveSquadra(squadraDTO);
        return ResponseEntity.status(201).body(createdSquadra);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Squadra> getSquadraById(@PathVariable UUID id) {
        Squadra squadra = squadraService.findById(id);
        return ResponseEntity.ok(squadra);
    }

    @GetMapping
    public ResponseEntity<List<Squadra>> getAllSquadre() {
        List<Squadra> squadre = squadraService.findAll();
        return ResponseEntity.ok(squadre);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('ORGANIZZATORE') and @squadraService.isOwner(#id, #username))")
    @PutMapping("/{id}")
    public ResponseEntity<Squadra> updateSquadra(@PathVariable UUID id, @RequestBody SquadraDTO squadraDTO, @AuthenticationPrincipal String username) {
        Squadra updatedSquadra = squadraService.updateSquadra(id, squadraDTO);
        return ResponseEntity.ok(updatedSquadra);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('ORGANIZZATORE') and @squadraService.isOwner(#id, #username))")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSquadra(@PathVariable UUID id, @AuthenticationPrincipal String username) {
        squadraService.deleteSquadra(id);
        return ResponseEntity.noContent().build();
    }
}
