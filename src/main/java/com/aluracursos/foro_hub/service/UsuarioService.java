// src/main/java/com/aluracursos/foro_hub/service/UsuarioService.java
package com.aluracursos.foro_hub.service;

import com.aluracursos.foro_hub.domain.Usuario;
import com.aluracursos.foro_hub.dto.request.DatosRegistroUsuario;
import com.aluracursos.foro_hub.dto.response.DatosRespuestaRegistroUsuario;
import com.aluracursos.foro_hub.repository.UsuarioRepository;
import com.aluracursos.foro_hub.infra.exceptions.ValidacionExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DatosRespuestaRegistroUsuario registrarUsuario(DatosRegistroUsuario datos) {
        if (usuarioRepository.existsByCorreo(datos.correo())) {
            throw new ValidacionExcepcion("Ya existe un usuario con el correo: " + datos.correo());
        }

        Usuario usuario = Usuario.builder()
                .nombre(datos.nombre())
                .correo(datos.correo())
                .contrasena(datos.contrasena())
                .perfil(datos.perfil())
                .activo(true)
                .build();

        usuarioRepository.save(usuario);

        return new DatosRespuestaRegistroUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getPerfil()
        );
    }
}
