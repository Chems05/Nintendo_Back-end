package Chems.NintendoTournaments.payloads;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

public record SquadraDTO(
        UUID id,
        @NotEmpty(message = "Il nome della squadra è obbligatorio.")
        String nome,
        UUID torneoId, // Aggiungi l'ID del torneo
        List<UtenteDTO> giocatori // Elenco degli utenti nella squadra
) {}

