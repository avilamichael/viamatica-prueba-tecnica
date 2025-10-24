package com.viamatica.prueba.usuarios.infrastructure;

import com.viamatica.prueba.usuarios.domain.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    public Optional<Persona> findByIdentificacion(String identificacion);
}