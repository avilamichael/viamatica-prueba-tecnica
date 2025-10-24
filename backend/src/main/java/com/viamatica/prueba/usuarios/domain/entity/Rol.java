package com.viamatica.prueba.usuarios.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "rol", schema = "seguridad")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String codigo;
    private String descripcion;
    @ManyToMany
    @JoinTable(
            name = "rol_rol_opciones",
            schema = "seguridad",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_opcion_id")
    )
    private List<RolOpciones> opciones;

}
