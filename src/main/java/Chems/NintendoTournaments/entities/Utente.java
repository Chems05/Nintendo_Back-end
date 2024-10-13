package Chems.NintendoTournaments.entities;

import Chems.NintendoTournaments.enums.RuoloUtente;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "utenti")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Utente {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private RuoloUtente ruolo;

    @OneToMany(mappedBy = "organizzatore")
    private List<Torneo> torneiOrganizzati;

    @OneToMany(mappedBy = "utente")
    private List<Partecipante> partecipazioni;
}

