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
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_torneo")
    private Torneo torneo;

    private int posizione;  // Posizione finale nel torneo

    public Partecipante(Utente utente, Torneo torneo, int posizione) {
        this.utente = utente;
        this.torneo = torneo;
        this.posizione = posizione;
    }
}

