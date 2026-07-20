package model;

import exception.DatoInvalidoException;
import util.FormateadorMoneda;
import util.Validador;

/**
 * Representa un paseo lacustre ofrecido
 * por Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class PaseoLacustre extends ServicioTuristico {

    private String nombreEmbarcacion;
    private String sectorNavegacion;
    private double costoEmbarcacionPorPersona;
    private boolean incluyeChalecoSalvavidas;

    /**
     * Crea un paseo lacustre con sus datos generales
     * y sus caracteristicas particulares.
     *
     * @param codigoServicio codigo unico del servicio
     * @param nombre nombre del servicio
     * @param descripcion descripcion del paseo
     * @param precioBase precio base por persona
     * @param duracionHoras duracion aproximada en horas
     * @param capacidadMaxima capacidad maxima de participantes
     * @param disponible disponibilidad actual
     * @param nombreEmbarcacion nombre de la embarcacion
     * @param sectorNavegacion sector donde se realiza el paseo
     * @param costoEmbarcacionPorPersona costo adicional por persona
     * @param incluyeChalecoSalvavidas indica si incluye chaleco salvavidas
     * @throws DatoInvalidoException si algun dato no es valido
     */
    public PaseoLacustre(
            String codigoServicio,
            String nombre,
            String descripcion,
            double precioBase,
            int duracionHoras,
            int capacidadMaxima,
            boolean disponible,
            String nombreEmbarcacion,
            String sectorNavegacion,
            double costoEmbarcacionPorPersona,
            boolean incluyeChalecoSalvavidas
    ) throws DatoInvalidoException {

        super(
                codigoServicio,
                nombre,
                descripcion,
                precioBase,
                duracionHoras,
                capacidadMaxima,
                disponible
        );

        setNombreEmbarcacion(nombreEmbarcacion);
        setSectorNavegacion(sectorNavegacion);
        setCostoEmbarcacionPorPersona(
                costoEmbarcacionPorPersona
        );
        setIncluyeChalecoSalvavidas(
                incluyeChalecoSalvavidas
        );
    }

    /**
     * Retorna el nombre de la embarcacion.
     *
     * @return nombre de la embarcacion
     */
    public String getNombreEmbarcacion() {
        return nombreEmbarcacion;
    }

    /**
     * Asigna el nombre de la embarcacion.
     *
     * @param nombreEmbarcacion nombre que se desea asignar
     * @throws DatoInvalidoException si el nombre esta vacio
     */
    public void setNombreEmbarcacion(String nombreEmbarcacion)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                nombreEmbarcacion,
                "El nombre de la embarcacion"
        );

        this.nombreEmbarcacion =
                nombreEmbarcacion.trim();
    }

    /**
     * Retorna el sector de navegacion.
     *
     * @return sector de navegacion
     */
    public String getSectorNavegacion() {
        return sectorNavegacion;
    }

    /**
     * Asigna el sector de navegacion.
     *
     * @param sectorNavegacion sector que se desea asignar
     * @throws DatoInvalidoException si el sector esta vacio
     */
    public void setSectorNavegacion(String sectorNavegacion)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                sectorNavegacion,
                "El sector de navegacion"
        );

        this.sectorNavegacion =
                sectorNavegacion.trim();
    }

    /**
     * Retorna el costo de embarcacion por persona.
     *
     * @return costo de embarcacion
     */
    public double getCostoEmbarcacionPorPersona() {
        return costoEmbarcacionPorPersona;
    }

    /**
     * Asigna el costo de embarcacion por persona.
     *
     * @param costoEmbarcacionPorPersona costo que se desea asignar
     * @throws DatoInvalidoException si el costo no es positivo
     */
    public void setCostoEmbarcacionPorPersona(
            double costoEmbarcacionPorPersona
    ) throws DatoInvalidoException {

        Validador.validarDecimalPositivo(
                costoEmbarcacionPorPersona,
                "El costo de embarcacion por persona"
        );

        this.costoEmbarcacionPorPersona =
                costoEmbarcacionPorPersona;
    }

    /**
     * Indica si el paseo incluye chaleco salvavidas.
     *
     * @return true si incluye chaleco salvavidas
     */
    public boolean isIncluyeChalecoSalvavidas() {
        return incluyeChalecoSalvavidas;
    }

    /**
     * Modifica la inclusion de chaleco salvavidas.
     *
     * @param incluyeChalecoSalvavidas nueva configuracion
     */
    public void setIncluyeChalecoSalvavidas(
            boolean incluyeChalecoSalvavidas
    ) {
        this.incluyeChalecoSalvavidas =
                incluyeChalecoSalvavidas;
    }

    /**
     * Calcula el precio total del paseo segun
     * la cantidad de participantes.
     *
     * @param cantidadPersonas cantidad de participantes
     * @return precio total del paseo
     * @throws DatoInvalidoException si la cantidad no es valida
     */
    @Override
    public double calcularPrecio(int cantidadPersonas)
            throws DatoInvalidoException {

        Validador.validarEnteroPositivo(
                cantidadPersonas,
                "La cantidad de personas"
        );

        if (!tieneCapacidadPara(cantidadPersonas)) {
            throw new DatoInvalidoException(
                    "La cantidad de personas supera "
                            + "la capacidad maxima del paseo."
            );
        }

        double precioPorPersona =
                getPrecioBase() + costoEmbarcacionPorPersona;

        return precioPorPersona * cantidadPersonas;
    }

    /**
     * Retorna un resumen del paseo lacustre.
     *
     * @return resumen con los datos principales
     */
    @Override
    public String mostrarResumen() {
        String chaleco = incluyeChalecoSalvavidas
                ? "Incluye chaleco"
                : "No incluye chaleco";

        return super.mostrarResumen()
                + " | Embarcacion: " + nombreEmbarcacion
                + " | Sector: " + sectorNavegacion
                + " | " + chaleco;
    }

    /**
     * Retorna todos los datos del paseo lacustre.
     *
     * @return representacion completa del paseo
     */
    @Override
    public String toString() {
        String chaleco = incluyeChalecoSalvavidas
                ? "Si"
                : "No";

        return "=== Paseo lacustre ==="
                + "\n" + super.toString()
                + "\nEmbarcacion: " + nombreEmbarcacion
                + "\nSector de navegacion: "
                + sectorNavegacion
                + "\nCosto de embarcacion por persona: "
                + FormateadorMoneda.formatear(
                costoEmbarcacionPorPersona
        )
                + "\nIncluye chaleco salvavidas: "
                + chaleco;
    }
}