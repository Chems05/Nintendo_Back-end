package Chems.NintendoTournaments.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "messaggi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Messaggio {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "mittente_id")
    private Utente mittente;

    private String contenuto;

    // Costruttore per creare messaggio senza timestamp
    public Messaggio(Utente mittente, String contenuto) {
        this.mittente = mittente;
        this.contenuto = contenuto;
    }
}
