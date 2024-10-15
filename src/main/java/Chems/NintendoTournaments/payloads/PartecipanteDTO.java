package Chems.NintendoTournaments.payloads;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record PartecipanteDTO(
        @NotNull(message = "L'ID del torneo è obbligatorio.")
        UUID torneoId,

        @NotNull(message = "L'ID del giocatore è obbligatorio.")
        UUID giocatoreId
) {}

