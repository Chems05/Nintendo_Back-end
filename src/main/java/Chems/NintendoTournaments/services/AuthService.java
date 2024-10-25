package Chems.NintendoTournaments.services;

import Chems.NintendoTournaments.entities.Utente;
import Chems.NintendoTournaments.exceptions.UnauthorizedException;
import Chems.NintendoTournaments.payloads.LoginDTO;
import Chems.NintendoTournaments.payloads.LoginRespDTO;
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

    public LoginRespDTO checkCredentialsAndGenerateToken(LoginDTO body) {
        Utente found = this.utenteService.trovaUtentePerEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            String token = jwtUtil.createToken(found);
            return new LoginRespDTO(token, found.getId()); // Usa found.getId() per l'employeeId
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }

}
