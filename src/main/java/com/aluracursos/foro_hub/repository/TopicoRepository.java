package com.aluracursos.foro_hub.repository;
import com.aluracursos.foro_hub.domain.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    //verificar duplicados antes de guardar
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    Page<Topico> findByActivoTrue(Pageable paginacion);
}
