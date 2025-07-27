package com.aluracursos.foro_hub.dto;
import com.aluracursos.foro_hub.model.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @NotBlank String nombre,
        @NotBlank @Email String correo,
        @NotBlank String contrasena,
        @NotNull Perfil perfil
) {}
