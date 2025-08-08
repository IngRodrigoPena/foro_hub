package com.aluracursos.foro_hub.domain.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Perfil implements GrantedAuthority {
    ALUMNO,
    INSTRUCTOR,
    ADMIN;

    @Override
    public String getAuthority() {
        //return "";
        return this.name();
    }
}
