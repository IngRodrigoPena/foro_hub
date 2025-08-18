package com.aluracursos.foro_hub.infra.springdoc;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                   .addSecuritySchemes("bearer-key",
                       new SecurityScheme()
                           .type(SecurityScheme.Type.HTTP)
                           .scheme("bearer")
                           .bearerFormat("JWT")
                   )
                )
                .info(new Info()
                        .title("API ForoHub")
                        .description("API Rest de la aplicación Foro Hub, que contiene:\n" +
                                "- Funcionalidades CRUD de Tópicos\n" +
                                "- Documentación con SpringDoc y JWT\n" +
                                "\n⚠\uFE0F ESTADO: EN DESARROLLO")
                        .version("1.0.0")
                        .contact(new Contact()
                             .name("Equipo Backend")
                             .email("backend@foro_hub.mx"))
                        .license(new License()
                             .name("Apache 2.0")
                             .url("http://foro_hub.mx/api/licencia")));
    }
}
