package Chems.NintendoTournaments.payloads;

import jakarta.validation.constraints.NotEmpty;
import java.util.UUID;

public record GiocoDTO(
        UUID id, // Cambiato da Long a UUID
        @NotEmpty(message = "Il nome del gioco è obbligatorio.")
        String nome,
        @NotEmpty(message = "Il genere del gioco è obbligatorio.")
        String genere,
        String descrizione,
        String immagine // Rimosso sviluppatore e dataUscita
) {}
