package com.aluracursos.foro_hub.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull String status,
        @NotNull Long idCurso
) {
}
