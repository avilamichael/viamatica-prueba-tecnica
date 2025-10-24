package com.viamatica.prueba.usuarios.application;

import com.viamatica.prueba.usuarios.domain.entity.Persona;
import com.viamatica.prueba.util.RespuestaWs;

import java.util.List;

public interface PersonaService {

    Persona crearPersona(Persona persona);

    List<Persona> findAll();

    Persona findById(Long id);

    Persona findByIdentificacion(String identificacion);

    Persona updatePersona(Long id, Persona personaActualizada);

    RespuestaWs eliminarPersona(Long id);
}
