// src/main/java/com/aluracursos/foro_hub/infra/errores/ValidacionExcepcion.java
package com.aluracursos.foro_hub.infra.errores;

public class ValidacionExcepcion extends RuntimeException {
    public ValidacionExcepcion(String mensaje) {
        super(mensaje);
    }
}

