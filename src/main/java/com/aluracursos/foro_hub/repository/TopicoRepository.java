package com.aluracursos.foro_hub.repository;
import com.aluracursos.foro_hub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    //verificar duplicados antes de guardar
    boolean existsByTituloAndMensaje(String titulo, String mensaje);


}
