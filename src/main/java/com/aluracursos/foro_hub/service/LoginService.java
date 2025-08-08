package com.aluracursos.foro_hub.service;

import com.aluracursos.foro_hub.domain.Usuario;
import com.aluracursos.foro_hub.dto.request.DatosLogin;
import com.aluracursos.foro_hub.dto.response.DatosJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginService(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public DatosJWT autenticar(DatosLogin datosLogin) {
        var authToken = new UsernamePasswordAuthenticationToken(datosLogin.correo(), datosLogin.contrasena());
        var authentication = authenticationManager.authenticate(authToken);

        var usuario = (Usuario) authentication.getPrincipal();
        var jwt = tokenService.generarToken(usuario);

        return new DatosJWT(jwt);
    }
}
