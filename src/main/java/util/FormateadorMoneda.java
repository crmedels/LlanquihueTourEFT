package util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Contiene funciones para presentar valores monetarios
 * con el formato utilizado en Chile.
 *
 * @author Cristofer Medel
 */
public final class FormateadorMoneda {

    private static final NumberFormat FORMATO_ENTERO =
            NumberFormat.getIntegerInstance(
                    Locale.forLanguageTag("es-CL")
            );

    /**
     * Evita la creacion de objetos de esta clase utilitaria.
     */
    private FormateadorMoneda() {
    }

    /**
     * Convierte un valor numerico en un monto sin decimales
     * y con separadores de miles.
     *
     * @param monto valor que se desea presentar
     * @return monto con formato, por ejemplo $132.000
     */
    public static String formatear(double monto) {
        return "$" + FORMATO_ENTERO.format(
                Math.round(monto)
        );
    }
}
