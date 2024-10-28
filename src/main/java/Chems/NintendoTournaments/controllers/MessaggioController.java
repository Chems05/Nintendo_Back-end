package Chems.NintendoTournaments.controllers;

import Chems.NintendoTournaments.entities.Messaggio;
import Chems.NintendoTournaments.entities.Utente;
import Chems.NintendoTournaments.payloads.MessaggioDTO;
import Chems.NintendoTournaments.payloads.MessaggioRespDTO;
import Chems.NintendoTournaments.services.MessaggioService;
import Chems.NintendoTournaments.services.UtenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/messaggi")
@RequiredArgsConstructor
public class MessaggioController {

    private final MessaggioService messaggioService;
    private final UtenteService utenteService;

    @PostMapping("/invia")
    public MessaggioRespDTO inviaMessaggio(@Valid @RequestBody MessaggioDTO messaggioDTO) {
        Utente mittente = utenteService.findById(messaggioDTO.mittenteId());
        Messaggio messaggio = messaggioService.salvaMessaggio(mittente, messaggioDTO.contenuto());
        return new MessaggioRespDTO(
                messaggio.getId(),
                mittente.getId(),
                messaggio.getContenuto(),
                mittente.getUsername(),
                mittente.getAvatar() 
        );
    }

    @GetMapping
    public List<MessaggioRespDTO> getMessaggi() {
        return messaggioService.getAllMessages();
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable UUID id) {
        messaggioService.deleteMessage(id);
    }
}
