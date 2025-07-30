package com.aluracursos.foro_hub.controllers;
import com.aluracursos.foro_hub.dto.request.DatosRegistroCurso;
import com.aluracursos.foro_hub.domain.Curso;
import com.aluracursos.foro_hub.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroCurso datos) {
        Curso curso = Curso.builder()
                .nombre(datos.nombre())
                .categoria(datos.categoria())
                .activo(true)
                .build();
        cursoRepository.save(curso);
    }
}

