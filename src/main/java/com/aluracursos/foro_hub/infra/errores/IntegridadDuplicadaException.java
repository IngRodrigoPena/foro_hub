package com.aluracursos.foro_hub.infra.errores;


public class IntegridadDuplicadaException extends RuntimeException {
    public IntegridadDuplicadaException(String mensaje) {
        super(mensaje);
    }
}
