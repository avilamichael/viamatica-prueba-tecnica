package com.viamatica.prueba.usuarios.controller;

import com.viamatica.prueba.usuarios.application.AuthService;
import com.viamatica.prueba.usuarios.domain.dto.LoginRequestDto;
import com.viamatica.prueba.usuarios.domain.dto.LoginRespuestaDto;
import com.viamatica.prueba.util.RespuestaWs;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRespuestaDto> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        try {
            String token = authService.login(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(new LoginRespuestaDto("Inicio de sesión exitoso.", true, token));
        } catch (IllegalArgumentException | IllegalStateException ex) {
            return ResponseEntity.badRequest().body(new LoginRespuestaDto(ex.getMessage(), false, null));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<RespuestaWs> logout(@RequestParam Long userId) {
        authService.logout(userId);
        return ResponseEntity.ok(new RespuestaWs("Sesión cerrada exitosamente.", null, true));
    }
}