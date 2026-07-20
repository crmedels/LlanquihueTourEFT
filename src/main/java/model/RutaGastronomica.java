package model;

import exception.DatoInvalidoException;
import util.FormateadorMoneda;
import util.Validador;

/**
 * Representa una ruta gastronomica ofrecida
 * por Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class RutaGastronomica extends ServicioTuristico {

    private String tipoCocina;
    private int cantidadParadas;
    private double costoDegustacionPorPersona;

    /**
     * Crea una ruta gastronomica con sus datos generales
     * y sus caracteristicas particulares.
     *
     * @param codigoServicio codigo unico del servicio
     * @param nombre nombre del servicio
     * @param descripcion descripcion de la ruta
     * @param precioBase precio base por persona
     * @param duracionHoras duracion aproximada en horas
     * @param capacidadMaxima capacidad maxima de participantes
     * @param disponible disponibilidad actual
     * @param tipoCocina tipo de cocina incluida en la ruta
     * @param cantidadParadas cantidad de establecimientos visitados
     * @param costoDegustacionPorPersona costo adicional de degustacion
     * @throws DatoInvalidoException si algun dato no es valido
     */
    public RutaGastronomica(
            String codigoServicio,
            String nombre,
            String descripcion,
            double precioBase,
            int duracionHoras,
            int capacidadMaxima,
            boolean disponible,
            String tipoCocina,
            int cantidadParadas,
            double costoDegustacionPorPersona
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

        setTipoCocina(tipoCocina);
        setCantidadParadas(cantidadParadas);
        setCostoDegustacionPorPersona(
                costoDegustacionPorPersona
        );
    }

    /**
     * Retorna el tipo de cocina de la ruta.
     *
     * @return tipo de cocina
     */
    public String getTipoCocina() {
        return tipoCocina;
    }

    /**
     * Asigna el tipo de cocina de la ruta.
     *
     * @param tipoCocina tipo de cocina que se desea asignar
     * @throws DatoInvalidoException si el dato esta vacio
     */
    public void setTipoCocina(String tipoCocina)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                tipoCocina,
                "El tipo de cocina"
        );

        this.tipoCocina = tipoCocina.trim();
    }

    /**
     * Retorna la cantidad de paradas de la ruta.
     *
     * @return cantidad de paradas
     */
    public int getCantidadParadas() {
        return cantidadParadas;
    }

    /**
     * Asigna la cantidad de paradas de la ruta.
     *
     * @param cantidadParadas cantidad de paradas
     * @throws DatoInvalidoException si la cantidad no es positiva
     */
    public void setCantidadParadas(int cantidadParadas)
            throws DatoInvalidoException {

        Validador.validarEnteroPositivo(
                cantidadParadas,
                "La cantidad de paradas"
        );

        this.cantidadParadas = cantidadParadas;
    }

    /**
     * Retorna el costo de degustacion por persona.
     *
     * @return costo de degustacion
     */
    public double getCostoDegustacionPorPersona() {
        return costoDegustacionPorPersona;
    }

    /**
     * Asigna el costo de degustacion por persona.
     *
     * @param costoDegustacionPorPersona costo de degustacion
     * @throws DatoInvalidoException si el costo no es positivo
     */
    public void setCostoDegustacionPorPersona(
            double costoDegustacionPorPersona
    ) throws DatoInvalidoException {

        Validador.validarDecimalPositivo(
                costoDegustacionPorPersona,
                "El costo de degustacion por persona"
        );

        this.costoDegustacionPorPersona =
                costoDegustacionPorPersona;
    }

    /**
     * Calcula el precio total de la ruta segun
     * la cantidad de participantes.
     *
     * @param cantidadPersonas cantidad de participantes
     * @return precio total de la ruta
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
                            + "la capacidad maxima de la ruta."
            );
        }

        double precioPorPersona =
                getPrecioBase() + costoDegustacionPorPersona;

        return precioPorPersona * cantidadPersonas;
    }

    /**
     * Retorna un resumen de la ruta gastronomica.
     *
     * @return resumen con los datos principales
     */
    @Override
    public String mostrarResumen() {
        return super.mostrarResumen()
                + " | Cocina: " + tipoCocina
                + " | Paradas: " + cantidadParadas;
    }

    /**
     * Retorna todos los datos de la ruta gastronomica.
     *
     * @return representacion completa de la ruta
     */
    @Override
    public String toString() {
        return "=== Ruta gastronomica ==="
                + "\n" + super.toString()
                + "\nTipo de cocina: " + tipoCocina
                + "\nCantidad de paradas: " + cantidadParadas
                + "\nCosto de degustacion por persona: "
                + FormateadorMoneda.formatear(
                costoDegustacionPorPersona
        );
    }
}