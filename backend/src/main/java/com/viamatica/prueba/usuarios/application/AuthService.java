package com.viamatica.prueba.usuarios.application;

import com.viamatica.prueba.usuarios.domain.entity.Session;
import com.viamatica.prueba.usuarios.domain.entity.Usuario;

public interface AuthService {

    String login(String usernameOrEmail, String password);

    void logout(Long userId);

}
