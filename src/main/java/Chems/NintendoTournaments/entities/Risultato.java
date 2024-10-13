package Chems.NintendoTournaments.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "risultati")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Risultato {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_partecipante_vincitore")
    private Partecipante vincitore;

    @ManyToOne
    @JoinColumn(name = "id_partecipante_perdente")
    private Partecipante perdente;

    private int punteggioVincitore;
    private int punteggioPerdente;

    public Risultato(Partecipante vincitore, Partecipante perdente, int punteggioVincitore, int punteggioPerdente) {
        this.vincitore = vincitore;
        this.perdente = perdente;
        this.punteggioVincitore = punteggioVincitore;
        this.punteggioPerdente = punteggioPerdente;
    }
}

