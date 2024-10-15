package Chems.NintendoTournaments.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "giochi")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Gioco {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String genere;

    @Column(name = "data_uscita")
    private LocalDate dataUscita;

    @Column(length = 1000)
    private String descrizione;

    @Column(nullable = false)
    private String sviluppatore;

    private String immagine;

    @OneToMany(mappedBy = "gioco", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Torneo> tornei;

    public Gioco(String nome, String genere, LocalDate dataUscita, String descrizione, String sviluppatore, String immagine) {
        this.nome = nome;
        this.genere = genere;
        this.dataUscita = dataUscita;
        this.descrizione = descrizione;
        this.sviluppatore = sviluppatore;
        this.immagine = immagine;
    }
}
