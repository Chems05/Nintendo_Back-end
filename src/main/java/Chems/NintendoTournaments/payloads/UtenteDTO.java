package Chems.NintendoTournaments.payloads;

import Chems.NintendoTournaments.enums.RuoloUtente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.util.UUID;

public record UtenteDTO(
        UUID id, // Cambia da Long a UUID
        @NotEmpty(message = "L'email è obbligatoria.")
        @Email(message = "L'email non valida!")
        String email,
        @NotEmpty(message = "La password è obbligatoria.")
        String password,
        @NotEmpty(message = "Il nome è obbligatorio.")
        String nome,
        @NotEmpty(message = "Il cognome è obbligatorio.")
        String cognome,
        @NotEmpty(message = "Il username è obbligatorio.")
        String username,
        RuoloUtente ruolo
) {}


