package Chems.NintendoTournaments.entities;

import Chems.NintendoTournaments.enums.RuoloUtente;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utente implements UserDetails {

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

    @OneToMany(mappedBy = "giocatore")
    private List<Partecipante> partecipazioni;

    // Metodo per ottenere le autorità (ruoli) dell'utente
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Restituisci l'autorità in base al ruolo dell'utente
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + ruolo.name()));
    }

    // Indica che le credenziali non sono scadute
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Indica che l'account non è bloccato
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Indica che le credenziali non sono scadute
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Indica se l'account è abilitato
    @Override
    public boolean isEnabled() {
        return true;
    }
}
