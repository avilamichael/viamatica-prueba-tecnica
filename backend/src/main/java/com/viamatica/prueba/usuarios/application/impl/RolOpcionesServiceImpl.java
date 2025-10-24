package com.viamatica.prueba.usuarios.application.impl;

import com.viamatica.prueba.usuarios.application.RolOpcionesService;
import com.viamatica.prueba.usuarios.domain.entity.RolOpciones;
import com.viamatica.prueba.usuarios.infrastructure.RolOpcionesRepository;
import com.viamatica.prueba.util.RespuestaWs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolOpcionesServiceImpl implements RolOpcionesService {

    private final RolOpcionesRepository rolOpcionesRepository;

    public RolOpcionesServiceImpl(RolOpcionesRepository rolOpcionesRepository) {
        this.rolOpcionesRepository = rolOpcionesRepository;
    }

    @Override
    public RolOpciones crearRolOpcion(RolOpciones rolOpciones) {
        return rolOpcionesRepository.save(rolOpciones);
    }

    @Override
    public List<RolOpciones> findAll() {
        return rolOpcionesRepository.findAll();
    }

    @Override
    public RolOpciones findById(Long id) {
        return rolOpcionesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rol Opción no encontrada con ID: " + id));
    }

    @Override
    public RolOpciones updateRolOpcion(Long id, RolOpciones rolOpcionesActualizado) {
        RolOpciones existente = findById(id);
        existente.setCodigo(rolOpcionesActualizado.getCodigo());
        existente.setDescripcion(rolOpcionesActualizado.getDescripcion());
        return rolOpcionesRepository.save(existente);
    }

    @Override
    public RespuestaWs eliminarRolOpcion(Long id) {
        RolOpciones rolOpcion = findById(id);
        rolOpcionesRepository.delete(rolOpcion);
        return new RespuestaWs("Rol opción eliminada exitosamente.", null, true);
    }
}
