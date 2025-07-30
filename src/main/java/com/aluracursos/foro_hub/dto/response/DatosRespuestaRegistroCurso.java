package com.aluracursos.foro_hub.dto.response;

import com.aluracursos.foro_hub.domain.enums.CategoriaCurso;

public record DatosRespuestaRegistroCurso(
        Long id,
        String nombre,
        CategoriaCurso categoria
        ) {
}
