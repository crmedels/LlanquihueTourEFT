package model;

import exception.DatoInvalidoException;
import interfaces.Registrable;
import util.Validador;

/**
 * Representa un vehiculo utilizado para transportar
 * pasajeros en los servicios de Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class Vehiculo implements Registrable {

    private String patente;
    private String marca;
    private String modelo;
    private String tipoVehiculo;
    private int capacidadPasajeros;
    private boolean disponible;

    /**
     * Crea un vehiculo con todos sus datos.
     *
     * @param patente patente unica del vehiculo
     * @param marca marca del vehiculo
     * @param modelo modelo del vehiculo
     * @param tipoVehiculo tipo o categoria del vehiculo
     * @param capacidadPasajeros capacidad maxima de pasajeros
     * @param disponible disponibilidad actual
     * @throws DatoInvalidoException si algun dato no es valido
     */
    public Vehiculo(
            String patente,
            String marca,
            String modelo,
            String tipoVehiculo,
            int capacidadPasajeros,
            boolean disponible
    ) throws DatoInvalidoException {

        setPatente(patente);
        setMarca(marca);
        setModelo(modelo);
        setTipoVehiculo(tipoVehiculo);
        setCapacidadPasajeros(capacidadPasajeros);
        setDisponible(disponible);
    }

    /**
     * Retorna la patente del vehiculo.
     *
     * @return patente normalizada
     */
    public String getPatente() {
        return patente;
    }

    /**
     * Asigna y normaliza la patente del vehiculo.
     *
     * @param patente patente que se desea asignar
     * @throws DatoInvalidoException si la patente no es valida
     */
    public void setPatente(String patente)
            throws DatoInvalidoException {

        this.patente = Validador.normalizarPatente(patente);
    }

    /**
     * Retorna la marca del vehiculo.
     *
     * @return marca del vehiculo
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Asigna la marca del vehiculo.
     *
     * @param marca marca que se desea asignar
     * @throws DatoInvalidoException si la marca esta vacia
     */
    public void setMarca(String marca)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                marca,
                "La marca"
        );

        this.marca = marca.trim();
    }

    /**
     * Retorna el modelo del vehiculo.
     *
     * @return modelo del vehiculo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Asigna el modelo del vehiculo.
     *
     * @param modelo modelo que se desea asignar
     * @throws DatoInvalidoException si el modelo esta vacio
     */
    public void setModelo(String modelo)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                modelo,
                "El modelo"
        );

        this.modelo = modelo.trim();
    }

    /**
     * Retorna el tipo del vehiculo.
     *
     * @return tipo del vehiculo
     */
    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    /**
     * Asigna el tipo del vehiculo.
     *
     * @param tipoVehiculo tipo que se desea asignar
     * @throws DatoInvalidoException si el tipo esta vacio
     */
    public void setTipoVehiculo(String tipoVehiculo)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                tipoVehiculo,
                "El tipo de vehiculo"
        );

        this.tipoVehiculo = tipoVehiculo.trim();
    }

    /**
     * Retorna la capacidad maxima de pasajeros.
     *
     * @return capacidad del vehiculo
     */
    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    /**
     * Asigna la capacidad maxima de pasajeros.
     *
     * @param capacidadPasajeros capacidad que se desea asignar
     * @throws DatoInvalidoException si la capacidad no es positiva
     */
    public void setCapacidadPasajeros(int capacidadPasajeros)
            throws DatoInvalidoException {

        Validador.validarEnteroPositivo(
                capacidadPasajeros,
                "La capacidad de pasajeros"
        );

        this.capacidadPasajeros = capacidadPasajeros;
    }

    /**
     * Indica si el vehiculo se encuentra disponible.
     *
     * @return true si esta disponible
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Modifica la disponibilidad del vehiculo.
     *
     * @param disponible nueva disponibilidad
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Verifica si el vehiculo tiene capacidad suficiente.
     *
     * @param cantidadPasajeros cantidad de pasajeros solicitada
     * @return true si la cantidad no supera la capacidad
     * @throws DatoInvalidoException si la cantidad no es positiva
     */
    public boolean tieneCapacidadPara(int cantidadPasajeros)
            throws DatoInvalidoException {

        Validador.validarEnteroPositivo(
                cantidadPasajeros,
                "La cantidad de pasajeros"
        );

        return cantidadPasajeros <= capacidadPasajeros;
    }

    /**
     * Retorna el identificador unico del vehiculo.
     *
     * @return patente del vehiculo
     */
    @Override
    public String obtenerIdentificador() {
        return patente;
    }

    /**
     * Retorna un resumen breve del vehiculo.
     *
     * @return resumen del vehiculo
     */
    @Override
    public String mostrarResumen() {
        String estado = disponible
                ? "Disponible"
                : "No disponible";

        return patente
                + " | " + marca + " " + modelo
                + " | " + tipoVehiculo
                + " | Capacidad: " + capacidadPasajeros
                + " | " + estado;
    }

    /**
     * Retorna todos los datos del vehiculo.
     *
     * @return representacion completa del vehiculo
     */
    @Override
    public String toString() {
        String estado = disponible
                ? "Disponible"
                : "No disponible";

        return "=== Vehiculo ==="
                + "\nPatente: " + patente
                + "\nMarca: " + marca
                + "\nModelo: " + modelo
                + "\nTipo: " + tipoVehiculo
                + "\nCapacidad de pasajeros: "
                + capacidadPasajeros
                + "\nEstado: " + estado;
    }
}