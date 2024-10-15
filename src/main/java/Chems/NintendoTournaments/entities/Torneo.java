package Chems.NintendoTournaments.entities;

import Chems.NintendoTournaments.enums.StatoTorneo;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "id_organizzatore")
    private Utente organizzatore;

    @ManyToOne
    @JoinColumn(name = "id_gioco") // Aggiunge la chiave esterna per il gioco
    private Gioco gioco; // Definisce la relazione ManyToOne con Gioco

    @OneToMany(mappedBy = "torneo")
    private List<Partecipante> partecipanti;

    public Torneo(String nomeTorneo, LocalDate dataInizio, LocalDate dataFine, int numeroMassimoPartecipanti, StatoTorneo statoTorneo, Utente organizzatore) {
        this.nomeTorneo = nomeTorneo;
        this.gioco = gioco;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
        this.statoTorneo = statoTorneo;
        this.organizzatore = organizzatore; // Utilizza il valore passato
    }
}

