package Chems.NintendoTournaments.entities;

import Chems.NintendoTournaments.enums.StatoTorneo;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tornei")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Torneo {

    @Id
    @GeneratedValue
    private UUID id;

    private String nomeTorneo;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private int numeroMassimoPartecipanti;

    @Enumerated(EnumType.STRING)
    private StatoTorneo statoTorneo;

    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "id_organizzatore")
    @JsonBackReference // Aggiungi questa annotazione per evitare ricorsione
    private Utente organizzatore;

    @ManyToOne
    @JoinColumn(name = "id_gioco", nullable = false)
    private Gioco gioco;

    @OneToMany(mappedBy = "torneo")
    private List<Squadra> partecipanti;

    public Torneo(String nomeTorneo, LocalDate dataInizio, LocalDate dataFine, int numeroMassimoPartecipanti,
                  StatoTorneo statoTorneo, Gioco gioco, Utente organizzatore, String descrizione) {
        if (gioco == null) {
            throw new IllegalArgumentException("Il campo 'gioco' non può essere nullo.");
        }
        this.nomeTorneo = nomeTorneo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
        this.statoTorneo = statoTorneo;
        this.gioco = gioco;
        this.organizzatore = organizzatore;
        this.descrizione = descrizione;
    }
}
