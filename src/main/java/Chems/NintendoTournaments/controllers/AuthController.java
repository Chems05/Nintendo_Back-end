package Chems.NintendoTournaments.controllers;

import Chems.NintendoTournaments.services.AuthService;
import Chems.NintendoTournaments.exceptions.BadRequestException;
import Chems.NintendoTournaments.payloads.UtenteDTO;
import Chems.NintendoTournaments.payloads.LoginDTO;
import Chems.NintendoTournaments.payloads.LoginRespDTO;
import Chems.NintendoTournaments.payloads.UtenteRespDTO;
import Chems.NintendoTournaments.services.UtenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;


import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UtenteService UsersService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody LoginDTO payload) {
        return new LoginRespDTO(this.authService.checkCredentialsAndGenerateToken(payload));
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UtenteRespDTO createUser(@RequestBody  @Validated UtenteDTO body, BindingResult validationResult) {
        if(validationResult.hasErrors())  {
            String messages = validationResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            return new UtenteRespDTO(this.UsersService.saveUtente(body).getId());
        }
    }
}

