package com.aluracursos.foro_hub.repository;
import com.aluracursos.foro_hub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
