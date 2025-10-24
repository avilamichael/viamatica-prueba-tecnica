package com.viamatica.prueba.usuarios.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "El nombre de usuario o correo electrónico es obligatorio.")
    private String usernameOrEmail;

    @NotBlank(message = "La contraseña es obligatoria.")
    private String password;
}
