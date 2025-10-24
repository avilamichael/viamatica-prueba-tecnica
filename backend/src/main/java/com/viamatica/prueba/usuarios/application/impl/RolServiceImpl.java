package com.viamatica.prueba.usuarios.application.impl;

import com.viamatica.prueba.usuarios.application.RolService;
import com.viamatica.prueba.usuarios.domain.entity.Rol;
import com.viamatica.prueba.usuarios.infrastructure.RolRepository;
import com.viamatica.prueba.util.RespuestaWs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public Rol crearRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public Rol findById(Long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con ID: " + id));
    }

    @Override
    public Rol findByCodigo(String codigo) {
        return rolRepository.findByCodigo(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con codigo: " + codigo));
    }

    @Override
    public Rol updateRol(Long id, Rol rolActualizado) {
        Rol rol = findById(id);
        rol.setCodigo(rolActualizado.getCodigo());
        rol.setDescripcion(rolActualizado.getDescripcion());
        rol.setOpciones(rolActualizado.getOpciones());
        return rolRepository.save(rol);
    }

    @Override
    public RespuestaWs eliminarRol(Long id) {
        Rol rol = findById(id);
        rolRepository.delete(rol);
        return new RespuestaWs("Rol eliminado exitosamente.", null, true);
    }
}
