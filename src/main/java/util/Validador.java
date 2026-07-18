package util;

import exception.DatoInvalidoException;

/**
 * Contiene metodos reutilizables para validar
 * los datos ingresados al sistema.
 *
 * @author Cristofer Medel
 */
public class Validador {

    /**
     * Verifica que un texto no sea nulo ni este vacio.
     *
     * @param texto dato que se desea validar
     * @param nombreCampo nombre del campo validado
     * @throws DatoInvalidoException si el texto es nulo o esta vacio
     */
    public static void validarTextoObligatorio(
            String texto,
            String nombreCampo
    ) throws DatoInvalidoException {

        if (texto == null || texto.trim().isEmpty()) {
            throw new DatoInvalidoException(
                    nombreCampo + " no puede estar vacio."
            );
        }
    }

    /**
     * Verifica que un texto no contenga numeros.
     *
     * @param texto dato que se desea validar
     * @param nombreCampo nombre del campo validado
     * @throws DatoInvalidoException si el texto contiene numeros
     */
    public static void validarTextoSinNumeros(
            String texto,
            String nombreCampo
    ) throws DatoInvalidoException {

        validarTextoObligatorio(texto, nombreCampo);

        if (!texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+")) {
            throw new DatoInvalidoException(
                    nombreCampo + " solo puede contener letras."
            );
        }
    }
}