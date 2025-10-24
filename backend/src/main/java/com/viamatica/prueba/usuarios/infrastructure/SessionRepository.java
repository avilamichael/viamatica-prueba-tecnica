package com.viamatica.prueba.usuarios.infrastructure;

import com.viamatica.prueba.usuarios.domain.entity.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {


    Boolean existsByUsuario_IdAndFechaLogoutIsNull(Long idUsuario);

    Optional<Session> findByUsuario_IdAndFechaLogoutIsNull(Long idUsuario);

    @Procedure(procedureName = "seguridad.registrar_intento_login")
    void registrarIntentoLogin(@Param("usuario_id") Long idUsuario);

}