package com.viamatica.prueba.usuarios.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "session", schema = "seguridad")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Usuario.class)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    private LocalDateTime fechaLogin;
    private LocalDateTime fechaLogout;

}
