package com.viamatica.prueba.usuarios.controller;

import com.viamatica.prueba.usuarios.application.RolOpcionesService;
import com.viamatica.prueba.usuarios.domain.entity.RolOpciones;
import com.viamatica.prueba.util.RespuestaWs;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol-opciones")
public class RolOpcionesController {

    private final RolOpcionesService rolOpcionesService;

    public RolOpcionesController(RolOpcionesService rolOpcionesService) {
        this.rolOpcionesService = rolOpcionesService;
    }

    @PostMapping
    public ResponseEntity<RolOpciones> crearRolOpcion(@Valid @RequestBody RolOpciones rolOpciones) {
        return ResponseEntity.ok(rolOpcionesService.crearRolOpcion(rolOpciones));
    }

    @GetMapping
    public ResponseEntity<List<RolOpciones>> findAll() {
        return ResponseEntity.ok(rolOpcionesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolOpciones> findById(@PathVariable Long id) {
        return ResponseEntity.ok(rolOpcionesService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolOpciones> updateRolOpcion(@PathVariable Long id, @Valid @RequestBody RolOpciones rolOpcionesActualizado) {
        return ResponseEntity.ok(rolOpcionesService.updateRolOpcion(id, rolOpcionesActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaWs> eliminarRolOpcion(@PathVariable Long id) {
        return ResponseEntity.ok(rolOpcionesService.eliminarRolOpcion(id));
    }
}
