package Chems.NintendoTournaments.controllers;

import Chems.NintendoTournaments.entities.Gioco;
import Chems.NintendoTournaments.payloads.GiocoDTO;
import Chems.NintendoTournaments.services.GiocoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("giochi")
public class GiocoController {

    @Autowired
    private GiocoService giocoService;

    @PostMapping
    public ResponseEntity<Gioco> createGioco(@RequestBody GiocoDTO giocoDTO) {
        Gioco createdGioco = giocoService.saveGioco(giocoDTO);
        return ResponseEntity.status(201).body(createdGioco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gioco> getGiocoById(@PathVariable UUID id) {
        Gioco gioco = giocoService.findById(id);
        return ResponseEntity.ok(gioco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gioco> updateGioco(@PathVariable UUID id, @RequestBody GiocoDTO giocoDTO) {
        Gioco updatedGioco = giocoService.updateGioco(id, giocoDTO);
        return ResponseEntity.ok(updatedGioco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGioco(@PathVariable UUID id) {
        giocoService.deleteGioco(id);
        return ResponseEntity.noContent().build();
    }
}



