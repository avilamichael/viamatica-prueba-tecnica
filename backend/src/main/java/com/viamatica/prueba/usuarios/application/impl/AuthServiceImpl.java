package com.viamatica.prueba.usuarios.application.impl;

import com.viamatica.prueba.usuarios.application.AuthService;
import com.viamatica.prueba.usuarios.domain.entity.Session;
import com.viamatica.prueba.usuarios.domain.entity.Usuario;
import com.viamatica.prueba.usuarios.infrastructure.SessionRepository;
import com.viamatica.prueba.usuarios.infrastructure.UsuarioRepository;
import com.viamatica.prueba.util.JwtTokenUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final SessionRepository sessionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    private static final int INTENTOS_SESSION_MAX = 3;

    public AuthServiceImpl(UsuarioRepository usuarioRepository, SessionRepository sessionRepository,
                           PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.usuarioRepository = usuarioRepository;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public String login(String usernameOrEmail, String password) {
        Usuario usuario = usuarioRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        if ("BLOQUEADO".equals(usuario.getEstado())) {
            throw new IllegalStateException("El usuario está bloqueado.");
        }

        if (sessionRepository.existsByUsuario_IdAndFechaLogoutIsNull(usuario.getId())) {
            throw new IllegalStateException("El usuario ya tiene una sesión activa.");
        }

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            registrarLoginFallido(usuario);
            throw new IllegalArgumentException("Credenciales incorrectas.");
        }

        if (usuario.getIntentosFallidos() > 0) {
            registrarLoginExitoso(usuario);
        }

        String token = jwtTokenUtil.generateToken(usuario.getUsername());
        Session session = Session.builder()
                .usuario(usuario)
                .fechaLogin(LocalDateTime.now())
                .build();
        sessionRepository.save(session);

        return token;
    }

    @Override
    public void logout(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        sessionRepository.findByUsuario_IdAndFechaLogoutIsNull(usuario.getId())
                .ifPresent(session -> {
                    session.setFechaLogout(LocalDateTime.now());
                    sessionRepository.save(session);
                });
    }

    private void registrarLoginFallido(Usuario usuario) {
        usuario.setIntentosFallidos(usuario.getIntentosFallidos() + 1);
        if (usuario.getIntentosFallidos() >= INTENTOS_SESSION_MAX) {
            usuario.setIntentosFallidos(0);
            usuario.setEstado("BLOQUEADO");
        }
        sessionRepository.registrarIntentoLogin(usuario.getId());
        usuarioRepository.save(usuario);
    }

    private void registrarLoginExitoso(Usuario usuario) {
        usuario.setIntentosFallidos(0);
        usuarioRepository.save(usuario);
    }
}
