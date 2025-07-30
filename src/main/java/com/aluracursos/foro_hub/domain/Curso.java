package com.aluracursos.foro_hub.domain;
import com.aluracursos.foro_hub.domain.enums.CategoriaCurso;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    //private String categoria;
    @Enumerated(EnumType.STRING)
    private CategoriaCurso categoria;

    private Boolean activo = true;

    @OneToMany(mappedBy = "curso")
    private List<Topico> topicos;
}
