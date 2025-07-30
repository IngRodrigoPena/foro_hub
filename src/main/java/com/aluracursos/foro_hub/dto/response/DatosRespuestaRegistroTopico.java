package com.aluracursos.foro_hub.dto.response;
import java.time.LocalDateTime;

public record DatosRespuestaRegistroTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor,
        String curso
) {
}
