package com.viamatica.prueba.usuarios.infrastructure;

import com.viamatica.prueba.usuarios.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsernameOrEmail(String username, String email);

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmail(String email);
}
