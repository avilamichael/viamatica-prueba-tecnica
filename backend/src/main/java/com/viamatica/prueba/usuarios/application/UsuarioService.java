package com.viamatica.prueba.usuarios.application;

import com.viamatica.prueba.usuarios.domain.dto.RegistroUsuarioDto;
import com.viamatica.prueba.usuarios.domain.entity.Usuario;
import com.viamatica.prueba.util.RespuestaWs;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario crearUsuario(RegistroUsuarioDto usuario);

    List<Usuario> findAll();

    Usuario findById(Long id);

    Usuario updateUsuario(Long id, Usuario usuarioActualizado);

    RespuestaWs eliminarUsuario(Long id);

}