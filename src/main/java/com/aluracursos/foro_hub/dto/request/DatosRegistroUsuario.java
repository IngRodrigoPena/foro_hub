package com.aluracursos.foro_hub.dto.request;
import com.aluracursos.foro_hub.domain.enums.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @NotBlank String nombre,
        @NotBlank @Email String correo,
        @NotBlank String contrasena,
        @NotNull Perfil perfil
) {}
