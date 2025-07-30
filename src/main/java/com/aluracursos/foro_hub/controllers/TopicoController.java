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
import com.aluracursos.foro_hub.dto.response.DatosRespuestaRegistroTopico;
import com.aluracursos.foro_hub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosRespuestaRegistroTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {
        var respuesta = topicoService.registrar(datos);
        return ResponseEntity.status(201).body(respuesta);
    }
}
