package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Gioco;
import Chems.NintendoTournaments.exceptions.BadRequestException;
import Chems.NintendoTournaments.exceptions.NotFoundException;
import Chems.NintendoTournaments.payloads.GiocoDTO;
import Chems.NintendoTournaments.repositories.GiocoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GiocoService {

    @Autowired
    private GiocoRepository giochiRepository;

    public Gioco saveGioco(GiocoDTO giocoDTO) {
        if (giocoDTO == null) {
            throw new BadRequestException("Il gioco deve avere un body!");
        }
        Gioco gioco = new Gioco(giocoDTO.nome(), giocoDTO.genere(), giocoDTO.descrizione(), giocoDTO.immagine());
        return this.giochiRepository.save(gioco);
    }

    public Gioco findById(UUID giocoId) {
        return this.giochiRepository.findById(giocoId)
                .orElseThrow(() -> new NotFoundException("Gioco non trovato con ID: " + giocoId));
    }

    public Page<Gioco> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.giochiRepository.findAll(pageable);
    }

    public void deleteGioco(UUID giocoId) {
        Gioco found = findById(giocoId);
        this.giochiRepository.delete(found);
    }


    public Gioco updateGioco(UUID giocoId, GiocoDTO updateBody) {
        Gioco found = findById(giocoId);
        found.setNome(updateBody.nome());
        found.setGenere(updateBody.genere());
        found.setDescrizione(updateBody.descrizione());
        found.setImmagine(updateBody.immagine());
        return giochiRepository.save(found);


    }
}
