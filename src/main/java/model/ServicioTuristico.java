package model;

import exception.DatoInvalidoException;
import interfaces.Registrable;
import util.FormateadorMoneda;
import util.Validador;

/**
 * Representa los datos y comportamientos comunes
 * de los servicios ofrecidos por Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public abstract class ServicioTuristico implements Registrable {

    private String codigoServicio;
    private String nombre;
    private String descripcion;
    private double precioBase;
    private int duracionHoras;
    private int capacidadMaxima;
    private boolean disponible;

    /**
     * Crea un servicio turistico con sus datos principales.
     *
     * @param codigoServicio codigo unico del servicio
     * @param nombre nombre del servicio
     * @param descripcion descripcion del servicio
     * @param precioBase precio base por persona
     * @param duracionHoras duracion aproximada en horas
     * @param capacidadMaxima capacidad maxima de participantes
     * @param disponible disponibilidad actual del servicio
     * @throws DatoInvalidoException si algun dato no es valido
     */
    public ServicioTuristico(
            String codigoServicio,
            String nombre,
            String descripcion,
            double precioBase,
            int duracionHoras,
            int capacidadMaxima,
            boolean disponible
    ) throws DatoInvalidoException {

        setCodigoServicio(codigoServicio);
        setNombre(nombre);
        setDescripcion(descripcion);
        setPrecioBase(precioBase);
        setDuracionHoras(duracionHoras);
        setCapacidadMaxima(capacidadMaxima);
        setDisponible(disponible);
    }

    public String getCodigoServicio() {
        return codigoServicio;
    }

    /**
     * Asigna y normaliza el codigo del servicio.
     *
     * @param codigoServicio codigo que se desea asignar
     * @throws DatoInvalidoException si el codigo no es valido
     */
    public void setCodigoServicio(String codigoServicio)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                codigoServicio,
                "El código del servicio"
        );

        String codigoNormalizado =
                codigoServicio.trim().toUpperCase();

        if (!codigoNormalizado.matches("SER-\\d{3,6}")) {
            throw new DatoInvalidoException(
                    "El código del servicio debe tener el formato "
                            + "SER seguido de tres a seis números. "
                            + "Ejemplo: SER-001."
            );
        }

        this.codigoServicio = codigoNormalizado;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del servicio.
     *
     * @param nombre nombre que se desea asignar
     * @throws DatoInvalidoException si el nombre esta vacio
     */
    public void setNombre(String nombre)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                nombre,
                "El nombre del servicio"
        );

        this.nombre = nombre.trim();
    }

    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna la descripcion del servicio.
     *
     * @param descripcion descripcion que se desea asignar
     * @throws DatoInvalidoException si la descripcion esta vacia
     */
    public void setDescripcion(String descripcion)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                descripcion,
                "La descripción del servicio"
        );

        this.descripcion = descripcion.trim();
    }

    public double getPrecioBase() {
        return precioBase;
    }

    /**
     * Asigna el precio base por persona.
     *
     * @param precioBase precio que se desea asignar
     * @throws DatoInvalidoException si el precio no es positivo
     */
    public void setPrecioBase(double precioBase)
            throws DatoInvalidoException {

        Validador.validarDecimalPositivo(
                precioBase,
                "El precio base"
        );

        this.precioBase = precioBase;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    /**
     * Asigna la duracion aproximada del servicio.
     *
     * @param duracionHoras duracion en horas
     * @throws DatoInvalidoException si la duracion no es positiva
     */
    public void setDuracionHoras(int duracionHoras)
            throws DatoInvalidoException {

        Validador.validarEnteroPositivo(
                duracionHoras,
                "La duración del servicio"
        );

        this.duracionHoras = duracionHoras;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    /**
     * Asigna la capacidad maxima del servicio.
     *
     * @param capacidadMaxima capacidad que se desea asignar
     * @throws DatoInvalidoException si la capacidad no es positiva
     */
    public void setCapacidadMaxima(int capacidadMaxima)
            throws DatoInvalidoException {

        Validador.validarEnteroPositivo(
                capacidadMaxima,
                "La capacidad maxima"
        );

        this.capacidadMaxima = capacidadMaxima;
    }

    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Modifica la disponibilidad del servicio.
     *
     * @param disponible nueva disponibilidad
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Verifica si el servicio admite una cantidad de participantes.
     *
     * @param cantidadPersonas cantidad solicitada
     * @return true si la cantidad no supera la capacidad maxima
     * @throws DatoInvalidoException si la cantidad no es positiva
     */
    public boolean tieneCapacidadPara(int cantidadPersonas)
            throws DatoInvalidoException {

        Validador.validarEnteroPositivo(
                cantidadPersonas,
                "La cantidad de personas"
        );

        return cantidadPersonas <= capacidadMaxima;
    }

    /**
     * Retorna solamente el precio base del servicio.
     *
     * Este metodo representa una sobrecarga de calcularPrecio.
     *
     * @return precio base por persona
     */
    public double calcularPrecio() {
        return precioBase;
    }

    /**
     * Calcula el precio total segun la cantidad de participantes.
     *
     * Cada subclase debe implementar su propia forma de calculo.
     *
     * @param cantidadPersonas cantidad de participantes
     * @return precio total del servicio
     * @throws DatoInvalidoException si la cantidad no es valida
     */
    public abstract double calcularPrecio(int cantidadPersonas)
            throws DatoInvalidoException;

    /**
     * Retorna el identificador unico del servicio.
     *
     * @return codigo del servicio
     */
    @Override
    public String obtenerIdentificador() {
        return codigoServicio;
    }

    /**
     * Retorna un resumen del servicio turistico.
     *
     * @return resumen con los datos principales
     */
    @Override
    public String mostrarResumen() {
        String estado = disponible
                ? "Disponible"
                : "No disponible";

        return codigoServicio
                + " | " + nombre
                + " | Precio base: "
                + FormateadorMoneda.formatear(precioBase)
                + " | Capacidad: " + capacidadMaxima
                + " | " + estado;
    }

    /**
     * Retorna todos los datos del servicio turistico.
     *
     * @return representacion completa del servicio
     */
    @Override
    public String toString() {
        String estado = disponible
                ? "Disponible"
                : "No disponible";

        return "Codigo: " + codigoServicio
                + "\nNombre: " + nombre
                + "\nDescripción: " + descripcion
                + "\nPrecio base: "
                + FormateadorMoneda.formatear(precioBase)
                + "\nDuración: " + duracionHoras + " horas"
                + "\nCapacidad maxima: " + capacidadMaxima
                + "\nEstado: " + estado;
    }
}