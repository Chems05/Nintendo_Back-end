package Chems.NintendoTournaments.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UtenteRespDTO(
        @NotNull(message = "L'UUID è obbligatorio")
        UUID employeeId
) {
}
