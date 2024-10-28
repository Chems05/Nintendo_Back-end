package Chems.NintendoTournaments.payloads;

import java.util.UUID;

public record MessaggioRespDTO(
        UUID id,
        UUID mittenteId,
        String contenuto,
        String username, // Nome utente del mittente
        String avatar // URL dell'avatar del mittente
) {}
