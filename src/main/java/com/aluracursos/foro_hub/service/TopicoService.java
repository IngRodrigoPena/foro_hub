package com.aluracursos.foro_hub.service;
import com.aluracursos.foro_hub.domain.Topico;
import com.aluracursos.foro_hub.domain.enums.StatusTopico;
import com.aluracursos.foro_hub.dto.request.DatosActualizarTopico;
import com.aluracursos.foro_hub.dto.request.DatosRegistroTopico;
import com.aluracursos.foro_hub.dto.response.DatosDetalleTopico;
import com.aluracursos.foro_hub.dto.response.DatosListadoTopico;
import com.aluracursos.foro_hub.dto.response.DatosRespuestaRegistroTopico;
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

    public Page<DatosListadoTopico> listar(Pageable paginacion) {
        return topicoRepository.findByActivoTrue(paginacion)
                .map(DatosListadoTopico::new); // usando el constructor que recibe Topico
    }

    public DatosDetalleTopico obtenerDetalle(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado con ID: " + id));

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

    public ResponseEntity<?> actualizar(Long id, DatosActualizarTopico datos) {

        var topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var duplicado = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (duplicado) {
            return ResponseEntity.status(409).build();
        }

        // ✅ Validación segura del enum
        StatusTopico nuevoStatus;
        try {
            nuevoStatus = StatusTopico.valueOf(datos.status());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Status invalido: " + datos.status());
        }

        var cursoOptional = cursoRepository.findById(datos.idCurso());
        if (cursoOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var topico = topicoOptional.get();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setStatus(nuevoStatus);
        topico.setCurso(cursoOptional.get());

        topicoRepository.save(topico);

        var dtoRespuesta = new DatosRespuestaRegistroTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );

        return ResponseEntity.ok(dtoRespuesta);
    }

    public ResponseEntity<Void> eliminar(Long id) {
        var topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = topicoOptional.get();
        topico.setActivo(false);
        topicoRepository.save(topico);

        return ResponseEntity.noContent().build(); // 204 No Content
    }


}

