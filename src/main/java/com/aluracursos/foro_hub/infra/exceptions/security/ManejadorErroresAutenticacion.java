package com.aluracursos.foro_hub.infra.exceptions.security;
import com.aluracursos.foro_hub.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ManejadorErroresAutenticacion {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(BadCredentialsException.class)
    public void manejarCredencialesInvalidas(BadCredentialsException ex,
                                             HttpServletRequest request,
                                             HttpServletResponse response) throws IOException {
        enviarErrorJson(response, request, 401, "Unauthorized", "Credenciales inválidas");
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public void manejarCredencialesExpiradas(CredentialsExpiredException ex,
                                             HttpServletRequest request,
                                             HttpServletResponse response) throws IOException {
        enviarErrorJson(response, request, 401, "Unauthorized", "Las credenciales han expirado");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public void manejarUsuarioNoEncontrado(UsernameNotFoundException ex,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws IOException {
        enviarErrorJson(response, request, 401, "Unauthorized", "Usuario no encontrado");
    }

    private void enviarErrorJson(HttpServletResponse response,
                                 HttpServletRequest request,
                                 int status,
                                 String error,
                                 String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status,
                error,
                message,
                request.getRequestURI()
        );

        String json = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(json);
    }
}

