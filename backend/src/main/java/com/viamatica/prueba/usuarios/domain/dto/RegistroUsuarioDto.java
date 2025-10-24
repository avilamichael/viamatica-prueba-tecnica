package com.viamatica.prueba.usuarios.domain.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RegistroUsuarioDto {

    private String username;
    private String password;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private List<String> roles;

}
