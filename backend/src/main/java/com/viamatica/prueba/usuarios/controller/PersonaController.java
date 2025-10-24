package com.viamatica.prueba.usuarios.controller;

import com.viamatica.prueba.usuarios.application.PersonaService;
import com.viamatica.prueba.usuarios.domain.entity.Persona;
import com.viamatica.prueba.util.RespuestaWs;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<Persona> crearPersona(@Valid @RequestBody Persona persona) {
        return ResponseEntity.ok(personaService.crearPersona(persona));
    }

    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        return ResponseEntity.ok(personaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @Valid @RequestBody Persona personaActualizada) {
        return ResponseEntity.ok(personaService.updatePersona(id, personaActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaWs> eliminarPersona(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.eliminarPersona(id));
    }
}
