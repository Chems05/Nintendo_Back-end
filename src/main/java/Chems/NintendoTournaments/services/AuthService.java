package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Utente;
import Chems.NintendoTournaments.exceptions.UnauthorizedException;
import Chems.NintendoTournaments.payloads.LoginDTO;
import Chems.NintendoTournaments.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(LoginDTO body) {

        Utente found = this.utenteService.trovaUtentePerEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            return jwtUtil.createToken(found);
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }


    }
}
