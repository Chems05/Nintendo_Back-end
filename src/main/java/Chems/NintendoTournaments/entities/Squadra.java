package Chems.NintendoTournaments.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "squadre")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Squadra {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "torneo_id", nullable = false)
    @JsonIgnore
    private Torneo torneo;

    public Squadra(String nome, Torneo torneo) {
        this.nome = nome;
        this.torneo = torneo;
    }
}
