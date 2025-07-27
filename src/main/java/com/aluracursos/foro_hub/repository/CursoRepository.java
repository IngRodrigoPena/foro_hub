package com.aluracursos.foro_hub.repository;
import com.aluracursos.foro_hub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
