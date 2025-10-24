package com.viamatica.prueba.usuarios.application.impl;

import com.viamatica.prueba.usuarios.application.PersonaService;
import com.viamatica.prueba.usuarios.domain.entity.Persona;
import com.viamatica.prueba.usuarios.infrastructure.PersonaRepository;
import com.viamatica.prueba.util.RespuestaWs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    @Override
    public Persona findById(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + id));
    }

    @Override
    public Persona findByIdentificacion(String identificacion) {
        return personaRepository.findByIdentificacion(identificacion)
                .orElse(null);
    }

    @Override
    public Persona updatePersona(Long id, Persona personaActualizada) {
        Persona persona = findById(id);
        persona.setIdentificacion(personaActualizada.getIdentificacion());
        persona.setNombres(personaActualizada.getNombres());
        persona.setApellidos(personaActualizada.getApellidos());
        persona.setFechaNacimiento(personaActualizada.getFechaNacimiento());
        return personaRepository.save(persona);
    }

    @Override
    public RespuestaWs eliminarPersona(Long id) {
        Persona persona = findById(id);
        personaRepository.delete(persona);
        return new RespuestaWs("Persona eliminada exitosamente.", null, true);
    }
}
