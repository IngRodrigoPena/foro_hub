//package com.aluracursos.foro_hub.controllers;
//import com.aluracursos.foro_hub.dto.request.DatosRegistroTopico;
//import com.aluracursos.foro_hub.domain.enums.StatusTopico;
//import com.aluracursos.foro_hub.domain.Topico;
//import com.aluracursos.foro_hub.repository.CursoRepository;
//import com.aluracursos.foro_hub.repository.TopicoRepository;
//import com.aluracursos.foro_hub.repository.UsuarioRepository;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//
//
//@RestController
//@RequestMapping("/topicos")
//public class TopicoController {
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
//    @PostMapping
//    public ResponseEntity<?> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {
//        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body("Ya existe un tópico con el mismo título y mensaje.");
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
//                .fechaCreacion(LocalDateTime.now())
//                .activo(true)
//                .build();
//
//        topicoRepository.save(topico);
//
//        return ResponseEntity.ok().build();
//    }
//
//}
package com.aluracursos.foro_hub.controllers;
import com.aluracursos.foro_hub.dto.request.DatosRegistroTopico;
import com.aluracursos.foro_hub.dto.response.DatosDetalleTopico;
import com.aluracursos.foro_hub.dto.response.DatosListadoTopico;
import com.aluracursos.foro_hub.dto.response.DatosRespuestaRegistroTopico;
import com.aluracursos.foro_hub.repository.TopicoRepository;
import com.aluracursos.foro_hub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;
    @Autowired
    private TopicoRepository topicoRepository;


    @PostMapping
    public ResponseEntity<DatosRespuestaRegistroTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {
        var respuesta = topicoService.registrar(datos);
        return ResponseEntity.status(201).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {

        Page<DatosListadoTopico> pagina = topicoRepository.findByActivoTrue(paginacion)
                .map(topico -> new DatosListadoTopico(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus(),
                        topico.getAutor().getNombre(),
                        topico.getCurso().getNombre()
                ));

        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerDetalleTopico(@PathVariable Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado con ID: " + id));

        var detalle = new DatosDetalleTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );

        return ResponseEntity.ok(detalle);
    }


}
