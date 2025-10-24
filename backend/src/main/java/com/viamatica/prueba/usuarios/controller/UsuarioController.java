package com.viamatica.prueba.usuarios.controller;

import com.viamatica.prueba.usuarios.application.UsuarioService;
import com.viamatica.prueba.usuarios.domain.dto.RegistroUsuarioDto;
import com.viamatica.prueba.usuarios.domain.entity.Usuario;
import com.viamatica.prueba.util.RespuestaWs;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody RegistroUsuarioDto usuario) {
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuarioActualizado) {
        return ResponseEntity.ok(usuarioService.updateUsuario(id, usuarioActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaWs> eliminarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.eliminarUsuario(id));
    }
}
