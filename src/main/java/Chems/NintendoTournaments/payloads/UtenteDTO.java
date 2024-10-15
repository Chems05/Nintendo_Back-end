package Chems.NintendoTournaments.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UtenteDTO(
        Long id,
        @NotEmpty(message = "L'email è obbligatoria.")
        @Email(message = "L'email non valida!")
        String email,
        @NotEmpty(message = "La password è obbligatoria.")
        String password
) {}

