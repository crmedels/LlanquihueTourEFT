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
     * Verifica que un texto contenga solamente letras y espacios.
     *
     * @param texto dato que se desea validar
     * @param nombreCampo nombre del campo validado
     * @throws DatoInvalidoException si el texto contiene caracteres invalidos
     */
    public static void validarTextoSinNumeros(
            String texto,
            String nombreCampo
    ) throws DatoInvalidoException {

        validarTextoObligatorio(texto, nombreCampo);

        if (!texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+")) {
            throw new DatoInvalidoException(
                    nombreCampo + " solo puede contener letras y espacios."
            );
        }
    }

    /**
     * Verifica que un RUT tenga un formato y digito verificador validos.
     *
     * @param rut RUT que se desea validar
     * @throws DatoInvalidoException si el RUT no es valido
     */
    public static void validarRut(String rut)
            throws DatoInvalidoException {

        validarTextoObligatorio(rut, "El RUT");

        String rutLimpio = rut
                .replace(".", "")
                .replace(" ", "")
                .toUpperCase();

        if (!rutLimpio.matches("\\d{7,8}-[0-9K]")) {
            throw new DatoInvalidoException(
                    "El RUT debe tener un formato valido. Ejemplo: 12345678-9."
            );
        }

        String[] partes = rutLimpio.split("-");
        String cuerpo = partes[0];
        char digitoIngresado = partes[1].charAt(0);

        int suma = 0;
        int factor = 2;

        for (int i = cuerpo.length() - 1; i >= 0; i--) {
            int numero = Character.getNumericValue(cuerpo.charAt(i));
            suma += numero * factor;

            factor++;

            if (factor > 7) {
                factor = 2;
            }
        }

        int resultado = 11 - (suma % 11);
        char digitoCalculado;

        if (resultado == 11) {
            digitoCalculado = '0';
        } else if (resultado == 10) {
            digitoCalculado = 'K';
        } else {
            digitoCalculado = Character.forDigit(resultado, 10);
        }

        if (digitoIngresado != digitoCalculado) {
            throw new DatoInvalidoException(
                    "El digito verificador del RUT no es valido."
            );
        }
    }

    /**
     * Retorna un RUT sin puntos, sin espacios y con la letra K en mayuscula.
     *
     * @param rut RUT que se desea normalizar
     * @return RUT normalizado
     * @throws DatoInvalidoException si el RUT no es valido
     */
    public static String normalizarRut(String rut)
            throws DatoInvalidoException {

        validarRut(rut);

        return rut
                .replace(".", "")
                .replace(" ", "")
                .toUpperCase();
    }
}