package Chems.NintendoTournaments.entities;

import jakarta.persistence.*;
import lombok.*;
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

    @Column(length = 1000)
    private String descrizione;

    private String immagine;

    @OneToMany(mappedBy = "gioco", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Torneo> tornei;

    public Gioco(String nome, String genere, String descrizione, String immagine) {
        this.nome = nome;
        this.genere = genere;
        this.descrizione = descrizione;
        this.immagine = immagine;
    }
}

