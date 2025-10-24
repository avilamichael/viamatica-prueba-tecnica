package com.viamatica.prueba.usuarios.controller;

import com.viamatica.prueba.usuarios.application.RolService;
import com.viamatica.prueba.usuarios.domain.entity.Rol;
import com.viamatica.prueba.util.RespuestaWs;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @PostMapping
    public ResponseEntity<Rol> crearRol(@Valid @RequestBody Rol rol) {
        return ResponseEntity.ok(rolService.crearRol(rol));
    }

    @GetMapping
    public ResponseEntity<List<Rol>> findAll() {
        return ResponseEntity.ok(rolService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> findById(@PathVariable Long id) {
        return ResponseEntity.ok(rolService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable Long id, @Valid @RequestBody Rol rolActualizado) {
        return ResponseEntity.ok(rolService.updateRol(id, rolActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaWs> eliminarRol(@PathVariable Long id) {
        return ResponseEntity.ok(rolService.eliminarRol(id));
    }
}
