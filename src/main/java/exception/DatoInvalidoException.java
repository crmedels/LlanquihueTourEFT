package exception;

/**
 * Excepcion lanzada cuando un dato no cumple
 * con las reglas de validacion del sistema.
 *
 * @author Cristofer Medel
 */
public class DatoInvalidoException extends Exception {

    /**
     * Crea una excepcion con un mensaje descriptivo.
     *
     * @param mensaje descripcion del error detectado
     */
    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
