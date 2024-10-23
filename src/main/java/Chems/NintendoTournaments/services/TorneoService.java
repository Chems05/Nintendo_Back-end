package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Gioco;
import Chems.NintendoTournaments.entities.Torneo;
import Chems.NintendoTournaments.entities.Utente;
import Chems.NintendoTournaments.exceptions.BadRequestException;
import Chems.NintendoTournaments.exceptions.NotFoundException;
import Chems.NintendoTournaments.payloads.TorneoDTO;
import Chems.NintendoTournaments.repositories.TorneoRepository;
import Chems.NintendoTournaments.repositories.GiocoRepository;
import Chems.NintendoTournaments.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TorneoService {

    @Autowired
    private TorneoRepository torneoRepository;

    @Autowired
    private GiocoRepository giocoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public Torneo saveTorneo(TorneoDTO torneoDTO) {
        if (torneoDTO == null) {
            throw new BadRequestException("Il torneo deve avere un body!");
        }


        UUID giocoId = torneoDTO.giocoId();
        Gioco gioco = giocoRepository.findById(giocoId)
                .orElseThrow(() -> new NotFoundException("Gioco non trovato con ID: " + giocoId));


        UUID organizzatoreId = torneoDTO.organizzatoreId();
        Utente organizzatore = utenteRepository.findById(organizzatoreId)
                .orElseThrow(() -> new NotFoundException("Organizzatore non trovato con ID: " + organizzatoreId));

        Torneo torneo = new Torneo(
                torneoDTO.nomeTorneo(),
                torneoDTO.dataInizio(),
                torneoDTO.dataFine(),
                torneoDTO.numeroMassimoPartecipanti(),
                torneoDTO.statoTorneo(),
                gioco,
                organizzatore
        );
        return torneoRepository.save(torneo);
    }

    public Torneo findById(UUID torneoId) {
        return torneoRepository.findById(torneoId)
                .orElseThrow(() -> new NotFoundException("Torneo non trovato con ID: " + torneoId));
    }

    public List<Torneo> findAll() {
        return torneoRepository.findAll();
    }

    public Page<Torneo> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return torneoRepository.findAll(pageable);
    }

    public Torneo updateTorneo(UUID torneoId, TorneoDTO torneoDTO) {
        Torneo found = findById(torneoId);
        found.setNomeTorneo(torneoDTO.nomeTorneo());
        found.setDataInizio(torneoDTO.dataInizio());
        found.setDataFine(torneoDTO.dataFine());
        found.setNumeroMassimoPartecipanti(torneoDTO.numeroMassimoPartecipanti());
        found.setStatoTorneo(torneoDTO.statoTorneo());
        return torneoRepository.save(found);
    }

    public void deleteTorneo(UUID torneoId) {
        Torneo found = findById(torneoId);
        torneoRepository.delete(found);
    }
}