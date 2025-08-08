package com.aluracursos.foro_hub.repository;
import com.aluracursos.foro_hub.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCorreo(String correo);
    Optional<Usuario> findByCorreo(String correo);

    //Usuario findByLogin(String username);
    Usuario  findByNombre(String nombre);
}
