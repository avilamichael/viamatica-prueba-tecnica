package com.viamatica.prueba.usuarios.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRespuestaDto {

    private String mensaje;
    private Boolean estado;
    private String token;

}
