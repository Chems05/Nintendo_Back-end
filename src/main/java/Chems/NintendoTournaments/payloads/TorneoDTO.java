package Chems.NintendoTournaments.payloads;

import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public record TorneoDTO(
        Long id,
        @NotEmpty(message = "Il nome del torneo è obbligatorio.")
        String nome,
        LocalDateTime dataInizio,
        LocalDateTime dataFine,
        String descrizione,
        Long giocoId // Riferimento al GiocoDTO
) {}


