package Chems.NintendoTournaments.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "partecipanti")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Partecipante {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "torneo_id")
    private Torneo torneo;

    @ManyToOne
    @JoinColumn(name = "giocatore_id")
    private Utente giocatore;

    public Partecipante(Torneo torneo, Utente giocatore) {
        this.torneo = torneo;
        this.giocatore = giocatore;
    }
}
