package com.aluracursos.foro_hub.service;
import com.aluracursos.foro_hub.domain.Topico;
import com.aluracursos.foro_hub.domain.enums.StatusTopico;
import com.aluracursos.foro_hub.dto.request.DatosRegistroTopico;
import com.aluracursos.foro_hub.dto.response.DatosRespuestaRegistroTopico;
import com.aluracursos.foro_hub.repository.CursoRepository;
import com.aluracursos.foro_hub.repository.TopicoRepository;
import com.aluracursos.foro_hub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DatosRespuestaRegistroTopico registrar(DatosRegistroTopico datos) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un tópico con el mismo título y mensaje.");
        }

        var curso = cursoRepository.getReferenceById(datos.idCurso());
        var autor = usuarioRepository.getReferenceById(datos.idAutor());

        var topico = Topico.builder()
                .titulo(datos.titulo())
                .mensaje(datos.mensaje())
                .curso(curso)
                .autor(autor)
                .status(StatusTopico.NO_RESPONDIDO)
                .activo(true)
                .build();

        topicoRepository.save(topico);

        return new DatosRespuestaRegistroTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}

