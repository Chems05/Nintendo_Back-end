package Chems.NintendoTournaments.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record RegistrazioneDTO(
        @NotEmpty(message = "Il nome è obbligatorio.")
        String nome,

        @NotEmpty(message = "Il cognome è obbligatorio.")
        String cognome,

        @NotEmpty(message = "L'email è obbligatoria.")
        @Email(message = "L'email non valida!")
        String email,

        @NotEmpty(message = "Lo username è obbligatorio.")
        String username,

        @NotEmpty(message = "La password è obbligatoria.")
        @Size(min = 3, max = 40, message = "La password deve essere compresa tra 3 e 40 caratteri.")
        String password
) {}

