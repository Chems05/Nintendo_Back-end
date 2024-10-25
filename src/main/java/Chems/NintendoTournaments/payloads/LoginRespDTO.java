package Chems.NintendoTournaments.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record LoginRespDTO(
        @NotEmpty(message = "L'accessToken è obbligatorio")
        @Size(min = 20, max = 300, message = "L'accessToken deve essere compreso tra 20 e 300 caratteri")
        String accessToken,

        @NotNull(message = "L'UUID è obbligatorio")
        UUID employeeId // Aggiungi il campo employeeId
) {}

