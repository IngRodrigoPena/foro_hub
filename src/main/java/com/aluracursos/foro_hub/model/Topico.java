package com.aluracursos.foro_hub.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    //private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaCreacion;
    //private String status;
    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas;

    //Si quieres que siempre se asigne automáticamente
    // la fecha al guardar el objeto, puedes usar la anotacion @PrePersist
    @PrePersist
    public void asignarFecha() {
        this.fechaCreacion = LocalDateTime.now();
    }

}



