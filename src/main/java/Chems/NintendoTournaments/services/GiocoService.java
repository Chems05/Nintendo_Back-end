package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Gioco;
import Chems.NintendoTournaments.exceptions.BadRequestException;
import Chems.NintendoTournaments.exceptions.NotFoundException;
import Chems.NintendoTournaments.payloads.GiocoDTO;
import Chems.NintendoTournaments.repositories.GiocoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<GiocoDTO> findAll() {
        List<Gioco> giochi = giochiRepository.findAll(); // Assicurati che il metodo findAll() nel repository restituisca una lista
        return giochi.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private GiocoDTO convertToDTO(Gioco gioco) {
        return new GiocoDTO(gioco.getId(), gioco.getNome(), gioco.getGenere(), gioco.getDescrizione(), gioco.getImmagine());
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
