package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Squadra;
import Chems.NintendoTournaments.entities.Torneo;
import Chems.NintendoTournaments.exceptions.BadRequestException;
import Chems.NintendoTournaments.exceptions.NotFoundException;
import Chems.NintendoTournaments.payloads.SquadraDTO;
import Chems.NintendoTournaments.repositories.SquadraRepository;
import Chems.NintendoTournaments.repositories.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SquadraService {

    @Autowired
    private SquadraRepository squadraRepository;

    @Autowired
    private TorneoRepository torneoRepository;

    public Squadra saveSquadra(SquadraDTO squadraDTO) {
        if (squadraDTO == null) {
            throw new BadRequestException("La squadra deve avere un body!");
        }

        UUID torneoId = squadraDTO.torneoId();
        Torneo torneo = torneoRepository.findById(torneoId)
                .orElseThrow(() -> new NotFoundException("Torneo non trovato con ID: " + torneoId));

        Squadra squadra = new Squadra(squadraDTO.nome(), torneo);
        return squadraRepository.save(squadra);
    }

    public Squadra findById(UUID squadraId) {
        return squadraRepository.findById(squadraId)
                .orElseThrow(() -> new NotFoundException("Squadra non trovata con ID: " + squadraId));
    }

    public List<Squadra> findAll() {
        return squadraRepository.findAll();
    }

    public void deleteSquadra(UUID squadraId) {
        Squadra found = findById(squadraId);
        squadraRepository.delete(found);
    }

    public List<Squadra> findAllByTorneo(UUID torneoId) {
        Torneo torneo = torneoRepository.findById(torneoId)
                .orElseThrow(() -> new NotFoundException("Torneo non trovato con ID: " + torneoId));
        return squadraRepository.findByTorneo(torneo);
    }
}
