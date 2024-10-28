package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Gioco;
import Chems.NintendoTournaments.entities.Torneo;
import Chems.NintendoTournaments.entities.Utente;
import Chems.NintendoTournaments.exceptions.BadRequestException;
import Chems.NintendoTournaments.exceptions.NotFoundException;
import Chems.NintendoTournaments.exceptions.UnauthorizedException;
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
                organizzatore,
                torneoDTO.descrizione()
        );
        return torneoRepository.save(torneo);
    }

    public Torneo findById(UUID torneoId) {
        return torneoRepository.findById(torneoId)
                .orElseThrow(() -> new NotFoundException("Torneo non trovato con ID: " + torneoId));
    }

    public Page<Torneo> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return torneoRepository.findAll(pageable);
    }

    public Torneo updateTorneo(UUID torneoId, TorneoDTO torneoDTO, Utente utenteAutenticato) {
        Torneo found = findById(torneoId);

        // Verifica se l'utente è l'organizzatore
        if (!found.getOrganizzatore().getId().equals(utenteAutenticato.getId())) {
            throw new UnauthorizedException("Non sei autorizzato a modificare questo torneo");
        }

        found.setNomeTorneo(torneoDTO.nomeTorneo());
        found.setDataInizio(torneoDTO.dataInizio());
        found.setDataFine(torneoDTO.dataFine());
        found.setNumeroMassimoPartecipanti(torneoDTO.numeroMassimoPartecipanti());
        found.setStatoTorneo(torneoDTO.statoTorneo());
        found.setDescrizione(torneoDTO.descrizione());
        return torneoRepository.save(found);
    }

    public void deleteTorneo(UUID torneoId, Utente utenteAutenticato) {
        Torneo found = findById(torneoId);

        // Verifica se l'utente è l'organizzatore
        if (!found.getOrganizzatore().getId().equals(utenteAutenticato.getId())) {
            throw new UnauthorizedException("Non sei autorizzato a eliminare questo torneo");
        }

        torneoRepository.delete(found);
    }
}
