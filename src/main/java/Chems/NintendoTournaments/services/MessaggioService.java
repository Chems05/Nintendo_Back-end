package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Messaggio;
import Chems.NintendoTournaments.entities.Utente;
import Chems.NintendoTournaments.payloads.MessaggioRespDTO;
import Chems.NintendoTournaments.repositories.MessaggioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessaggioService {

    private final MessaggioRepository messaggioRepository;

    public MessaggioService(MessaggioRepository messaggioRepository) {
        this.messaggioRepository = messaggioRepository;
    }

    public Messaggio salvaMessaggio(Utente mittente, String contenuto) {
        Messaggio messaggio = new Messaggio();
        messaggio.setMittente(mittente);
        messaggio.setContenuto(contenuto);
        return messaggioRepository.save(messaggio);
    }

    public List<MessaggioRespDTO> getAllMessages() {
        return messaggioRepository.findAll().stream()
                .map(messaggio -> new MessaggioRespDTO(
                        messaggio.getId(),
                        messaggio.getMittente().getId(),
                        messaggio.getContenuto(),
                        messaggio.getMittente().getUsername(), // Nome utente
                        messaggio.getMittente().getAvatar()   // URL avatar
                ))
                .collect(Collectors.toList());
    }

    public void deleteMessage(UUID id) {
        messaggioRepository.deleteById(id);
    }

    public Messaggio findById(UUID id) {
        return messaggioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Messaggio non trovato"));
    }
}
