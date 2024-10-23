package Chems.NintendoTournaments.payloads;

import jakarta.validation.constraints.NotEmpty;

public record GiocoDTO(
        Long id,
        @NotEmpty(message = "Il nome del gioco è obbligatorio.")
        String nome,
        @NotEmpty(message = "Il genere del gioco è obbligatorio.")
        String genere,
        String descrizione,
        String immagine // Rimosso sviluppatore e dataUscita
) {}

