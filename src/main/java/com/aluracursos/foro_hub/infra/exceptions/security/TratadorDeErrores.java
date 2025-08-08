package com.aluracursos.foro_hub.infra.exceptions.security;
import com.aluracursos.foro_hub.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;

//@Component
//public class TratadorDeErrores implements AuthenticationEntryPoint {
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void commence(HttpServletRequest request,
//                         HttpServletResponse response,
//                         AuthenticationException authException) throws IOException {
//
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setContentType("application/json");
//
//        ErrorResponse errorResponse = new ErrorResponse(
//                LocalDateTime.now(),
//                401,
//                "Unauthorized",
//                "No estás autorizado para acceder a este recurso.",
//                request.getRequestURI()
//        );
//
//        String json = objectMapper.writeValueAsString(errorResponse);
//        response.getWriter().write(json);
//    }
//}


import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Component
public class TratadorDeErrores implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public TratadorDeErrores() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        // Opcional: para que las fechas no se serialicen como timestamps numéricos
        // this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                401,
                "Unauthorized",
                "No estás autorizado para acceder a este recurso.",
                request.getRequestURI()
        );

        String json = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(json);
    }
}
