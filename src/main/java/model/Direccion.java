package model;

import exception.DatoInvalidoException;
import util.Validador;

/**
 * Representa una direccion asociada a una persona
 * registrada en el sistema Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class Direccion {

    private String calle;
    private int numero;
    private String comuna;
    private String ciudad;

    /**
     * Crea una direccion con todos sus datos.
     *
     * @param calle nombre de la calle
     * @param numero numero de la propiedad
     * @param comuna comuna de la direccion
     * @param ciudad ciudad de la direccion
     * @throws DatoInvalidoException si alguno de los datos no es valido
     */
    public Direccion(
            String calle,
            int numero,
            String comuna,
            String ciudad
    ) throws DatoInvalidoException {

        setCalle(calle);
        setNumero(numero);
        setComuna(comuna);
        setCiudad(ciudad);
    }

    public String getCalle() {
        return calle;
    }

    /**
     * Asigna el nombre de la calle.
     *
     * @param calle nombre de la calle
     * @throws DatoInvalidoException si la calle esta vacia
     */
    public void setCalle(String calle) throws DatoInvalidoException {
        Validador.validarTextoObligatorio(calle, "La calle");
        this.calle = calle.trim();
    }

    public int getNumero() {
        return numero;
    }

    /**
     * Asigna el numero de la propiedad.
     *
     * @param numero numero de la propiedad
     * @throws DatoInvalidoException si el numero no es positivo
     */
    public void setNumero(int numero) throws DatoInvalidoException {
        Validador.validarEnteroPositivo(
                numero,
                "El numero de la dirección"
        );

        this.numero = numero;
    }

    public String getComuna() {
        return comuna;
    }

    /**
     * Asigna la comuna de la direccion.
     *
     * @param comuna comuna de la direccion
     * @throws DatoInvalidoException si la comuna contiene datos invalidos
     */
    public void setComuna(String comuna) throws DatoInvalidoException {
        Validador.validarTextoSinNumeros(comuna, "La comuna");
        this.comuna = comuna.trim();
    }

    public String getCiudad() {
        return ciudad;
    }

    /**
     * Asigna la ciudad de la direccion.
     *
     * @param ciudad ciudad de la direccion
     * @throws DatoInvalidoException si la ciudad contiene datos invalidos
     */
    public void setCiudad(String ciudad) throws DatoInvalidoException {
        Validador.validarTextoSinNumeros(ciudad, "La ciudad");
        this.ciudad = ciudad.trim();
    }

    /**
     * Retorna una representacion completa de la direccion.
     *
     * @return datos de la direccion
     */
    @Override
    public String toString() {
        return calle + " " + numero
                + ", " + comuna
                + ", " + ciudad;
    }
}