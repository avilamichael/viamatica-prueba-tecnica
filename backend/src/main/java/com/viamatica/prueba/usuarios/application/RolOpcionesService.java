package com.viamatica.prueba.usuarios.application;

import com.viamatica.prueba.usuarios.domain.entity.RolOpciones;
import com.viamatica.prueba.util.RespuestaWs;

import java.util.List;

public interface RolOpcionesService {

    RolOpciones crearRolOpcion(RolOpciones rolOpciones);

    List<RolOpciones> findAll();

    RolOpciones findById(Long id);

    RolOpciones updateRolOpcion(Long id, RolOpciones rolOpcionesActualizado);

    RespuestaWs eliminarRolOpcion(Long id);
}
