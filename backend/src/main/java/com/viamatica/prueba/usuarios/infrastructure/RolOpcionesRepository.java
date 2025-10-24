package com.viamatica.prueba.usuarios.infrastructure;

import com.viamatica.prueba.usuarios.domain.entity.RolOpciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolOpcionesRepository extends JpaRepository<RolOpciones, Long> {
}
