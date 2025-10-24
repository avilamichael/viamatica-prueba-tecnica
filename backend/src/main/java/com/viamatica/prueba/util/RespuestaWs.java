package com.viamatica.prueba.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaWs {

    private String mensaje;
    private String sugerencia;
    private Boolean estado;

}
