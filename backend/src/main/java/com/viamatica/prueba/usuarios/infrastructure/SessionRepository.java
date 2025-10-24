package com.viamatica.prueba.usuarios.infrastructure;

import com.viamatica.prueba.usuarios.domain.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Boolean existsByUsuario_IdAndFechaLogoutIsNull(Long idUsuario);

    Optional<Session> findByUsuario_IdAndFechaLogoutIsNull(Long idUsuario);
}