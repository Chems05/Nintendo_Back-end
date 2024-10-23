package Chems.NintendoTournaments.controllers;

import Chems.NintendoTournaments.entities.Partecipante;
import Chems.NintendoTournaments.payloads.PartecipanteDTO;
import Chems.NintendoTournaments.services.PartecipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/partecipanti")
public class PartecipanteController {

    @Autowired
    private PartecipanteService partecipanteService;

    @PostMapping
    public ResponseEntity<Partecipante> createPartecipante(@RequestBody PartecipanteDTO partecipanteDTO) {
        Partecipante createdPartecipante = partecipanteService.savePartecipante(partecipanteDTO);
        return ResponseEntity.status(201).body(createdPartecipante);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partecipante> getPartecipanteById(@PathVariable UUID id) {
        Partecipante partecipante = partecipanteService.findById(id);
        return ResponseEntity.ok(partecipante);
    }

    @GetMapping
    public ResponseEntity<List<Partecipante>> getAllPartecipanti() {
        List<Partecipante> partecipanti = partecipanteService.findAll();
        return ResponseEntity.ok(partecipanti);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partecipante> updatePartecipante(@PathVariable UUID id, @RequestBody PartecipanteDTO partecipanteDTO) {
        Partecipante updatedPartecipante = partecipanteService.updatePartecipante(id, partecipanteDTO);
        return ResponseEntity.ok(updatedPartecipante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartecipante(@PathVariable UUID id) {
        partecipanteService.deletePartecipante(id);
        return ResponseEntity.noContent().build();
    }
}
