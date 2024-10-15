package Chems.NintendoTournaments.payloads;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record SquadraDTO(
        Long id,
        @NotEmpty(message = "Il nome della squadra è obbligatorio.")
        String nome,
        List<GiocatoreDTO> giocatori // Lista di giocatori che compongono la squadra
) {}
