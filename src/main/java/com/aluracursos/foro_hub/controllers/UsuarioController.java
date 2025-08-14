//package com.aluracursos.foro_hub.controllers;
//import com.aluracursos.foro_hub.dto.request.DatosRegistroUsuario;
//import com.aluracursos.foro_hub.domain.Usuario;
//import com.aluracursos.foro_hub.repository.UsuarioRepository;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/usuarios")
//public class UsuarioController {
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @PostMapping
//    public void registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos) {
//        usuarioRepository.save(Usuario.builder()
//                .nombre(datos.nombre())
//                .correo(datos.correo())
//                .contrasena(datos.contrasena())
//                .perfil(datos.perfil())
//                .activo(true)
//                .build()
//        );
//    }
//}
package com.aluracursos.foro_hub.controllers;

import com.aluracursos.foro_hub.dto.request.DatosRegistroUsuario;
import com.aluracursos.foro_hub.dto.response.DatosRespuestaRegistroUsuario;
import com.aluracursos.foro_hub.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public DatosRespuestaRegistroUsuario registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos) {
        return usuarioService.registrarUsuario(datos);
    }
}


