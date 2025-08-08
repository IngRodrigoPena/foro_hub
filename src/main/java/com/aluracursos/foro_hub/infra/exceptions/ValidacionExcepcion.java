// src/main/java/com/aluracursos/foro_hub/infra/exceptions/ValidacionExcepcion.java
package com.aluracursos.foro_hub.infra.exceptions;

public class ValidacionExcepcion extends RuntimeException {
    public ValidacionExcepcion(String mensaje) {
        super(mensaje);
    }
}

