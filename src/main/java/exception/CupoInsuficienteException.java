package exception;

/**
 * Excepcion lanzada cuando una reserva supera
 * los cupos disponibles de un servicio turistico.
 *
 * @author Cristofer Medel
 */
public class CupoInsuficienteException extends Exception {

    /**
     * Crea una excepcion con un mensaje descriptivo.
     *
     * @param mensaje descripcion del problema de capacidad
     */
    public CupoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}