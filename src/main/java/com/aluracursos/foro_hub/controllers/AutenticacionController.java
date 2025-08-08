package com.aluracursos.foro_hub.controllers;

import com.aluracursos.foro_hub.domain.Usuario;
import com.aluracursos.foro_hub.dto.request.DatosLogin;
import com.aluracursos.foro_hub.dto.response.DatosJWT;
import com.aluracursos.foro_hub.exception.ErrorResponse;
import com.aluracursos.foro_hub.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacionController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid DatosLogin datosLogin, HttpServletRequest request) {

        try {
            var authToken = new UsernamePasswordAuthenticationToken(datosLogin.correo(), datosLogin.contrasena());
            var authentication = authenticationManager.authenticate(authToken);

            var usuario = (Usuario) authentication.getPrincipal();
            var jwt = tokenService.generarToken(usuario);

            return ResponseEntity.ok(new DatosJWT(jwt));

        } catch (BadCredentialsException | UsernameNotFoundException ex) {
            var error = new ErrorResponse(
                    LocalDateTime.now(),
                    401,
                    "Unauthorized",
                    "Credenciales inválidas",
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

        } catch (CredentialsExpiredException ex) {
            var error = new ErrorResponse(
                    LocalDateTime.now(),
                    401,
                    "Unauthorized",
                    "Las credenciales han expirado",
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}

