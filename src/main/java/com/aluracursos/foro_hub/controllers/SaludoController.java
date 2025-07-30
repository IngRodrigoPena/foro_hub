package com.aluracursos.foro_hub.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/saludo")
public class SaludoController {
    @GetMapping
    public String saludar(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String mensaje = "Hola, iniciando un API REST...";
        String fechaHora = ahora.format(formato);

        return mensaje + " Fecha y hora de la solicitud: " + fechaHora;
    }
}
