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

    /**
     * Verifica que un correo electronico tenga un formato valido.
     *
     * @param correo correo que se desea validar
     * @throws DatoInvalidoException si el correo no tiene un formato valido
     */
    public static void validarCorreo(String correo)
            throws DatoInvalidoException {

        validarTextoObligatorio(correo, "El correo");

        String formatoCorreo =
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!correo.trim().matches(formatoCorreo)) {
            throw new DatoInvalidoException(
                    "El correo electronico no tiene un formato valido."
            );
        }
    }

    /**
     * Verifica que un telefono contenga entre ocho y nueve digitos.
     *
     * @param telefono telefono que se desea validar
     * @throws DatoInvalidoException si el telefono no es valido
     */
    public static void validarTelefono(String telefono)
            throws DatoInvalidoException {

        validarTextoObligatorio(telefono, "El telefono");

        String telefonoLimpio = telefono
                .replace(" ", "")
                .replace("-", "")
                .replace("(", "")
                .replace(")", "");

        if (!telefonoLimpio.matches("\\d{8,9}")) {
            throw new DatoInvalidoException(
                    "El telefono debe contener entre ocho y nueve digitos."
            );
        }
    }

    /**
     * Retorna un telefono sin espacios, guiones ni parentesis.
     *
     * @param telefono telefono que se desea normalizar
     * @return telefono normalizado
     * @throws DatoInvalidoException si el telefono no es valido
     */
    public static String normalizarTelefono(String telefono)
            throws DatoInvalidoException {

        validarTelefono(telefono);

        return telefono
                .replace(" ", "")
                .replace("-", "")
                .replace("(", "")
                .replace(")", "");
    }

    /**
     * Verifica que un numero entero sea mayor que cero.
     *
     * @param valor numero que se desea validar
     * @param nombreCampo nombre del campo validado
     * @throws DatoInvalidoException si el valor no es positivo
     */
    public static void validarEnteroPositivo(
            int valor,
            String nombreCampo
    ) throws DatoInvalidoException {

        if (valor <= 0) {
            throw new DatoInvalidoException(
                    nombreCampo + " debe ser mayor que cero."
            );
        }
    }

    /**
     * Verifica que un numero entero no sea negativo.
     *
     * @param valor numero que se desea validar
     * @param nombreCampo nombre del campo validado
     * @throws DatoInvalidoException si el valor es negativo
     */
    public static void validarEnteroNoNegativo(
            int valor,
            String nombreCampo
    ) throws DatoInvalidoException {

        if (valor < 0) {
            throw new DatoInvalidoException(
                    nombreCampo + " no puede ser negativo."
            );
        }
    }

    /**
     * Verifica que un numero decimal sea mayor que cero.
     *
     * @param valor numero que se desea validar
     * @param nombreCampo nombre del campo validado
     * @throws DatoInvalidoException si el valor no es positivo
     */
    public static void validarDecimalPositivo(
            double valor,
            String nombreCampo
    ) throws DatoInvalidoException {

        if (valor <= 0) {
            throw new DatoInvalidoException(
                    nombreCampo + " debe ser mayor que cero."
            );
        }
    }

    /**
     * Verifica que una patente tenga uno de los formatos
     * aceptados por el sistema.
     *
     * @param patente patente que se desea validar
     * @throws DatoInvalidoException si la patente no es valida
     */
    public static void validarPatente(String patente)
            throws DatoInvalidoException {

        validarTextoObligatorio(patente, "La patente");

        String patenteLimpia = patente
                .replace(" ", "")
                .replace("-", "")
                .toUpperCase();

        boolean formatoAntiguo =
                patenteLimpia.matches("[A-Z]{2}\\d{4}");

        boolean formatoActual =
                patenteLimpia.matches("[A-Z]{4}\\d{2}");

        if (!formatoAntiguo && !formatoActual) {
            throw new DatoInvalidoException(
                    "La patente debe tener un formato valido. "
                            + "Ejemplos: AB1234 o ABCD12."
            );
        }
    }

    /**
     * Retorna una patente sin espacios ni guiones
     * y con sus letras en mayuscula.
     *
     * @param patente patente que se desea normalizar
     * @return patente normalizada
     * @throws DatoInvalidoException si la patente no es valida
     */
    public static String normalizarPatente(String patente)
            throws DatoInvalidoException {

        validarPatente(patente);

        return patente
                .replace(" ", "")
                .replace("-", "")
                .toUpperCase();
    }

}