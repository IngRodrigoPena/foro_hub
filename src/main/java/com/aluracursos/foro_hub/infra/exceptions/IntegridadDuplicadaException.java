package com.aluracursos.foro_hub.infra.exceptions;


public class IntegridadDuplicadaException extends RuntimeException {
    public IntegridadDuplicadaException(String mensaje) {
        super(mensaje);
    }
}
