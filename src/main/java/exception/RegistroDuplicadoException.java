package exception;

/**
 * Excepcion lanzada cuando se intenta agregar una entidad
 * cuyo identificador ya se encuentra registrado.
 *
 * @author Cristofer Medel
 */
public class RegistroDuplicadoException extends Exception {

    /**
     * Crea una excepcion con un mensaje descriptivo.
     *
     * @param mensaje descripcion del registro duplicado
     */
    public RegistroDuplicadoException(String mensaje) {
        super(mensaje);
    }
}