package com.viamatica.prueba.usuarios.application;

import com.viamatica.prueba.usuarios.domain.entity.Rol;
import com.viamatica.prueba.util.RespuestaWs;

import java.util.List;

public interface RolService {

    Rol crearRol(Rol rol);

    List<Rol> findAll();

    Rol findById(Long id);

    Rol findByCodigo(String codigo);

    Rol updateRol(Long id, Rol rolActualizado);

    RespuestaWs eliminarRol(Long id);
}
