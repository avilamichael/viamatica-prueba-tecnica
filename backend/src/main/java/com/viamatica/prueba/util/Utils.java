package com.viamatica.prueba.util;

public class Utils {

    public static Boolean validarPassword(String password) {
        if (password == null || password.length() < 8 || password.length() > 20) {
            return false;
        }

        Boolean tieneNumero = false;
        Boolean tieneMayuscula = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                tieneNumero = true;
            } else if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            } else if (!Character.isLetter(c)) {
                return false; // Contiene caracteres especiales
            }
        }

        return tieneNumero && tieneMayuscula;
    }


    public static Boolean validarIdentificacionTieneDigitosRepetidosCuatroVeces(String identificacion) {
        for (int i = 0; i <= identificacion.length() - 4; i++) {
            char digito = identificacion.charAt(i);
            if (digito == identificacion.charAt(i + 1) &&
                    digito == identificacion.charAt(i + 2) &&
                    digito == identificacion.charAt(i + 3)) {
                return true;
            }
        }
        return false;
    }

}
