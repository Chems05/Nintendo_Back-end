package Chems.NintendoTournaments.payloads;

import Chems.NintendoTournaments.enums.StatoTorneo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record TorneoDTO(
        @NotEmpty(message = "Il nome del torneo è obbligatorio.")
        String nomeTorneo,
        @NotNull(message = "La data di inizio è obbligatoria.")
        LocalDate dataInizio,
        @NotNull(message = "La data di fine è obbligatoria.")
        LocalDate dataFine,
        @NotNull(message = "Il numero massimo di partecipanti è obbligatorio.")
        int numeroMassimoPartecipanti,
        @NotNull(message = "Lo stato del torneo è obbligatorio.")
        StatoTorneo statoTorneo
) {}



