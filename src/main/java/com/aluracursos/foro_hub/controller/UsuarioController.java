package com.aluracursos.foro_hub.controller;
import com.aluracursos.foro_hub.dto.DatosRegistroUsuario;
import com.aluracursos.foro_hub.model.Usuario;
import com.aluracursos.foro_hub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public void registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos) {
        usuarioRepository.save(Usuario.builder()
                .nombre(datos.nombre())
                .correo(datos.correo())
                .contrasena(datos.contrasena())
                .perfil(datos.perfil())
                .activo(true)
                .build()
        );
    }
}

