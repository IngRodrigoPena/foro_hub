//package com.aluracursos.foro_hub.service;
//import com.aluracursos.foro_hub.domain.Curso;
//import com.aluracursos.foro_hub.dto.request.DatosRegistroCurso;
//import com.aluracursos.foro_hub.dto.response.DatosRespuestaRegistroCurso;
//import com.aluracursos.foro_hub.repository.CursoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CursoService {
//
//    @Autowired
//    private CursoRepository cursoRepository;
//
//    public DatosRespuestaRegistroCurso registrarCurso(DatosRegistroCurso datos) {
//
//        Curso curso = Curso.builder()
//                .nombre(datos.nombre())
//                .categoria(datos.categoria())
//                .activo(true)
//                .build();
//
//        cursoRepository.save(curso);
//        return new DatosRespuestaRegistroCurso(
//                curso.getId(),
//                curso.getNombre(),
//                curso.getCategoria()
//        );
//    }
//}

package com.aluracursos.foro_hub.service;

import com.aluracursos.foro_hub.domain.Curso;
import com.aluracursos.foro_hub.dto.request.DatosRegistroCurso;
import com.aluracursos.foro_hub.dto.response.DatosRespuestaRegistroCurso;
import com.aluracursos.foro_hub.repository.CursoRepository;
import com.aluracursos.foro_hub.infra.exceptions.IntegridadDuplicadaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public DatosRespuestaRegistroCurso registrarCurso(DatosRegistroCurso datos) {
        if (cursoRepository.existsByNombreIgnoreCase(datos.nombre())) {
            throw new IntegridadDuplicadaException("Ya existe un curso con el nombre: " + datos.nombre());
        }

        Curso curso = Curso.builder()
                .nombre(datos.nombre())
                .categoria(datos.categoria())
                .activo(true)
                .build();

        cursoRepository.save(curso);

        return new DatosRespuestaRegistroCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
    }
}
