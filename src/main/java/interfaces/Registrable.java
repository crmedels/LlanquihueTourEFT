package interfaces;

/**
 * Define el contrato comun para las entidades que pueden
 * ser gestionadas dentro del sistema Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public interface Registrable {

    /**
     * Retorna el identificador unico de la entidad.
     *
     * @return identificador de la entidad registrable
     */
    String obtenerIdentificador();

    /**
     * Retorna un resumen con los datos principales
     * de la entidad.
     *
     * @return resumen de la entidad registrable
     */
    String mostrarResumen();
}