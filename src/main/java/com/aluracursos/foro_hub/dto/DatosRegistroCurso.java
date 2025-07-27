package com.aluracursos.foro_hub.dto;
import com.aluracursos.foro_hub.model.CategoriaCurso;

public record DatosRegistroCurso(
        String nombre,
        CategoriaCurso categoria
) {}

