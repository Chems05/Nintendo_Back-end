package Chems.NintendoTournaments.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "squadre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Squadra {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_torneo", nullable = false)  // Assicurati che non sia nullable nel DB
    private Torneo torneo;

    @ManyToMany
    @JoinTable(
            name = "squadra_utente",
            joinColumns = @JoinColumn(name = "squadra_id"),
            inverseJoinColumns = @JoinColumn(name = "utente_id")
    )
    private List<Utente> giocatori;


    public Squadra(String nome, Torneo torneo) {
        if (torneo == null) {
            throw new IllegalArgumentException("Il campo 'torneo' non può essere nullo.");
        }
        this.nome = nome;
        this.torneo = torneo;
    }
}
