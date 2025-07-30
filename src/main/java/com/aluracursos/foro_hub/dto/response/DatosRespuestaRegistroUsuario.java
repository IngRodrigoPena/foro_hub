// src/main/java/com/aluracursos/foro_hub/dto/response/DatosRespuestaUsuario.java
package com.aluracursos.foro_hub.dto.response;

import com.aluracursos.foro_hub.domain.enums.Perfil;

public record DatosRespuestaRegistroUsuario(
        Long id,
        String nombre,
        String correo,
        Perfil perfil
) {}
