package com.viamatica.prueba.usuarios.infrastructure;

import com.viamatica.prueba.usuarios.domain.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByCodigo(String codigo);
}