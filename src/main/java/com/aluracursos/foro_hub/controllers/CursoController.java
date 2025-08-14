//package com.aluracursos.foro_hub.controllers;
//import com.aluracursos.foro_hub.dto.request.DatosRegistroCurso;
//import com.aluracursos.foro_hub.domain.Curso;
//import com.aluracursos.foro_hub.repository.CursoRepository;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/cursos")
//public class CursoController {
//
//    @Autowired
//    private CursoRepository cursoRepository;
//
//    @PostMapping
//    public void registrar(@RequestBody @Valid DatosRegistroCurso datos) {
//        Curso curso = Curso.builder()
//                .nombre(datos.nombre())
//                .categoria(datos.categoria())
//                .activo(true)
//                .build();
//        cursoRepository.save(curso);
//    }
//}
//
//package com.aluracursos.foro_hub.controllers;
//import com.aluracursos.foro_hub.dto.request.DatosRegistroCurso;
//import com.aluracursos.foro_hub.service.CursoService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/cursos")
//public class CursoController {
//
//    @Autowired
//    private CursoService cursoService;
//
//    @PostMapping
//    public void registrar(@RequestBody @Valid DatosRegistroCurso datos) {
//        cursoService.registrarCurso(datos);
//    }
//}
//
package com.aluracursos.foro_hub.controllers;

import com.aluracursos.foro_hub.dto.request.DatosRegistroCurso;
import com.aluracursos.foro_hub.dto.response.DatosRespuestaRegistroCurso;

import com.aluracursos.foro_hub.service.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public DatosRespuestaRegistroCurso registrar(@RequestBody @Valid DatosRegistroCurso datos) {
        return cursoService.registrarCurso(datos);
    }
}
