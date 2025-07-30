package com.aluracursos.foro_hub.dto.request;
import com.aluracursos.foro_hub.domain.enums.CategoriaCurso;

public record DatosRegistroCurso(
        String nombre,
        CategoriaCurso categoria
) {}

