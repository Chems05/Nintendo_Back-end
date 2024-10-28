package Chems.NintendoTournaments.payloads;

import jakarta.validation.constraints.NotEmpty;
import java.util.UUID;

public record MessaggioDTO(
        UUID mittenteId, // ID del mittente
        @NotEmpty(message = "Il contenuto del messaggio è obbligatorio.") String contenuto
) {}

