package com.aluracursos.foro_hub.service;
import com.aluracursos.foro_hub.domain.Curso;
import com.aluracursos.foro_hub.domain.Topico;
import com.aluracursos.foro_hub.domain.Usuario;
import com.aluracursos.foro_hub.domain.enums.StatusTopico;
import com.aluracursos.foro_hub.dto.request.DatosActualizarTopico;
import com.aluracursos.foro_hub.dto.request.DatosRegistroTopico;
import com.aluracursos.foro_hub.dto.response.DatosDetalleTopico;
import com.aluracursos.foro_hub.dto.response.DatosListadoTopico;
import com.aluracursos.foro_hub.dto.response.DatosRespuestaRegistroTopico;
import com.aluracursos.foro_hub.infra.exceptions.IntegridadDuplicadaException;
import com.aluracursos.foro_hub.infra.exceptions.RecursoNoEncontradoException;
import com.aluracursos.foro_hub.infra.exceptions.ValidacionExcepcion;
import com.aluracursos.foro_hub.repository.CursoRepository;
import com.aluracursos.foro_hub.repository.TopicoRepository;
import com.aluracursos.foro_hub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//@Service
//public class TopicoService {
//
//    @Autowired
//    private TopicoRepository topicoRepository;
//
//    @Autowired
//    private CursoRepository cursoRepository;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    // ====== METODOS DEL CRUD =====
//
//    public DatosRespuestaRegistroTopico registrar(DatosRegistroTopico datos) {
//        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un tópico con el mismo título y mensaje.");
//        }
//
//        var curso = cursoRepository.getReferenceById(datos.idCurso());
//        var autor = usuarioRepository.getReferenceById(datos.idAutor());
//
//        var topico = Topico.builder()
//                .titulo(datos.titulo())
//                .mensaje(datos.mensaje())
//                .curso(curso)
//                .autor(autor)
//                .status(StatusTopico.NO_RESPONDIDO)
//                .activo(true)
//                .build();
//
//        topicoRepository.save(topico);
//
//        return new DatosRespuestaRegistroTopico(
//                topico.getId(),
//                topico.getTitulo(),
//                topico.getMensaje(),
//                topico.getFechaCreacion(),
//                topico.getStatus().toString(),
//                topico.getAutor().getNombre(),
//                topico.getCurso().getNombre()
//        );
//    }
//
//
//    public Page<DatosListadoTopico> listar(Pageable paginacion) {
//        return topicoRepository.findByActivoTrue(paginacion)
//                .map(DatosListadoTopico::new); // usando el constructor que recibe Topico
//    }
//
//    public DatosDetalleTopico obtenerDetalle(Long id) {
//        var topico = topicoRepository.findById(id)
//                .orElseThrow(() -> new RecursoNoEncontradoException(
//                        "Tópico no encontrado con ID: " + id
//                ));
//
//        return new DatosDetalleTopico(
//                topico.getId(),
//                topico.getTitulo(),
//                topico.getMensaje(),
//                topico.getFechaCreacion(),
//                topico.getStatus().toString(),
//                topico.getAutor().getNombre(),
//                topico.getCurso().getNombre()
//        );
//    }
//
//    public DatosRespuestaRegistroTopico actualizar(Long id, DatosActualizarTopico datos) {
//        var topico = obtenerTopicoPorId(id);
//        validarDuplicado(datos.titulo(), datos.mensaje());
//        var nuevoStatus = validarStatus(datos.status());
//        var curso = obtenerCursoPorId(datos.idCurso());
//
//        topico.setTitulo(datos.titulo());
//        topico.setMensaje(datos.mensaje());
//        topico.setStatus(nuevoStatus);
//        topico.setCurso(curso);
//
//        topicoRepository.save(topico);
//
//        return new DatosRespuestaRegistroTopico(
//                topico.getId(),
//                topico.getTitulo(),
//                topico.getMensaje(),
//                topico.getFechaCreacion(),
//                topico.getStatus().toString(),
//                topico.getAutor().getNombre(),
//                topico.getCurso().getNombre()
//        );
//    }
//
//
//    public ResponseEntity<Void> eliminar(Long id) {
//        var topicoOptional = topicoRepository.findById(id);
//
//        if (topicoOptional.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        var topico = topicoOptional.get();
//        topico.setActivo(false);
//        topicoRepository.save(topico);
//
//        return ResponseEntity.noContent().build(); // 204 No Content
//    }
//
//    //Metodos de validacion privados
//    private Topico obtenerTopicoPorId(Long id) {
//        return topicoRepository.findById(id)
//                .orElseThrow(() -> new ValidacionExcepcion("Tópico no encontrado con ID: " + id));
//    }
//
//    private void validarDuplicado(String titulo, String mensaje) {
//        if (topicoRepository.existsByTituloAndMensaje(titulo, mensaje)) {
//            throw new IntegridadDuplicadaException("Ya existe un tópico con el mismo título y mensaje.");
//        }
//    }
//
//    private StatusTopico validarStatus(String status) {
//        try {
//            return StatusTopico.valueOf(status);
//        } catch (IllegalArgumentException e) {
//            throw new ValidacionExcepcion("Status inválido: " + status);
//        }
//    }
//
//    private Curso obtenerCursoPorId(Long idCurso) {
//        return cursoRepository.findById(idCurso)
//                .orElseThrow(() -> new ValidacionExcepcion("Curso no encontrado con ID: " + idCurso));
//    }
//
//
//
//}
@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ===== MÉTODOS DEL CRUD =====

    public DatosRespuestaRegistroTopico registrar(DatosRegistroTopico datos) {
        validarDuplicado(datos.titulo(), datos.mensaje());

        var curso = obtenerCursoPorId(datos.idCurso());
        //var autor = usuarioRepository.getReferenceById(datos.idAutor());
        var autor = obtenerAutorPorId(datos.idAutor());


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

    public Page<DatosListadoTopico> listar(Pageable paginacion) {
        return topicoRepository.findByActivoTrue(paginacion)
                .map(DatosListadoTopico::new);
    }

    public DatosDetalleTopico obtenerDetalle(Long id) {
        var topico = obtenerTopicoPorId(id);

        return new DatosDetalleTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }

    public DatosRespuestaRegistroTopico actualizar(Long id, DatosActualizarTopico datos) {
        var topico = obtenerTopicoPorId(id);
        validarDuplicado(datos.titulo(), datos.mensaje());
        var nuevoStatus = validarStatus(datos.status());
        var curso = obtenerCursoPorId(datos.idCurso());

        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setStatus(nuevoStatus);
        topico.setCurso(curso);

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

    public ResponseEntity<Void> eliminar(Long id) {
        var topico = obtenerTopicoPorId(id);

        topico.setActivo(false);
        topicoRepository.save(topico);

        return ResponseEntity.noContent().build();
    }

    // ===== MÉTODOS AUXILIARES =====

    private Topico obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Tópico no encontrado con ID: " + id
                ));
    }

    private void validarDuplicado(String titulo, String mensaje) {
        if (topicoRepository.existsByTituloAndMensaje(titulo, mensaje)) {
            throw new IntegridadDuplicadaException(
                    "Ya existe un tópico con el mismo título y mensaje."
            );
        }
    }

    private StatusTopico validarStatus(String status) {
        try {
            return StatusTopico.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new ValidacionExcepcion("Status inválido: " + status);
        }
    }

    private Curso obtenerCursoPorId(Long idCurso) {
        return cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Curso no encontrado con ID: " + idCurso
                ));
    }

    private Usuario obtenerAutorPorId(Long idAutor) {
        return usuarioRepository.findById(idAutor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Autor no encontrado con ID: " + idAutor));
    }

}

