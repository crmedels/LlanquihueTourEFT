package model;

import exception.DatoInvalidoException;
import util.Validador;

/**
 * Representa los datos comunes de una persona
 * relacionada con Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class Persona {

    private String rut;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private Direccion direccion;

    /**
     * Crea una persona con todos sus datos.
     *
     * @param rut RUT de la persona
     * @param nombre nombre de la persona
     * @param apellido apellido de la persona
     * @param telefono telefono de contacto
     * @param correo correo electronico
     * @param direccion direccion de la persona
     * @throws DatoInvalidoException si algun dato no es valido
     */
    public Persona(
            String rut,
            String nombre,
            String apellido,
            String telefono,
            String correo,
            Direccion direccion
    ) throws DatoInvalidoException {

        setRut(rut);
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
        setCorreo(correo);
        setDireccion(direccion);
    }

    /**
     * Retorna el RUT de la persona.
     *
     * @return RUT normalizado
     */
    public String getRut() {
        return rut;
    }

    /**
     * Asigna y normaliza el RUT de la persona.
     *
     * @param rut RUT que se desea asignar
     * @throws DatoInvalidoException si el RUT no es valido
     */
    public void setRut(String rut) throws DatoInvalidoException {
        this.rut = Validador.normalizarRut(rut);
    }

    /**
     * Retorna el nombre de la persona.
     *
     * @return nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre de la persona.
     *
     * @param nombre nombre que se desea asignar
     * @throws DatoInvalidoException si el nombre no es valido
     */
    public void setNombre(String nombre) throws DatoInvalidoException {
        Validador.validarTextoSinNumeros(nombre, "El nombre");
        this.nombre = nombre.trim();
    }

    /**
     * Retorna el apellido de la persona.
     *
     * @return apellido de la persona
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Asigna el apellido de la persona.
     *
     * @param apellido apellido que se desea asignar
     * @throws DatoInvalidoException si el apellido no es valido
     */
    public void setApellido(String apellido)
            throws DatoInvalidoException {

        Validador.validarTextoSinNumeros(apellido, "El apellido");
        this.apellido = apellido.trim();
    }

    /**
     * Retorna el telefono de la persona.
     *
     * @return telefono normalizado
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna y normaliza el telefono de la persona.
     *
     * @param telefono telefono que se desea asignar
     * @throws DatoInvalidoException si el telefono no es valido
     */
    public void setTelefono(String telefono)
            throws DatoInvalidoException {

        this.telefono = Validador.normalizarTelefono(telefono);
    }

    /**
     * Retorna el correo electronico.
     *
     * @return correo de la persona
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Asigna el correo electronico.
     *
     * @param correo correo que se desea asignar
     * @throws DatoInvalidoException si el correo no es valido
     */
    public void setCorreo(String correo)
            throws DatoInvalidoException {

        Validador.validarCorreo(correo);
        this.correo = correo.trim();
    }

    /**
     * Retorna la direccion asociada a la persona.
     *
     * @return direccion de la persona
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Asigna la direccion de la persona.
     *
     * @param direccion direccion que se desea asignar
     * @throws DatoInvalidoException si la direccion es nula
     */
    public void setDireccion(Direccion direccion)
            throws DatoInvalidoException {

        if (direccion == null) {
            throw new DatoInvalidoException(
                    "La dirección no puede ser nula."
            );
        }

        this.direccion = direccion;
    }

    /**
     * Retorna el nombre completo de la persona.
     *
     * @return nombre y apellido
     */
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    /**
     * Retorna una representacion completa de la persona.
     *
     * @return datos principales de la persona
     */
    @Override
    public String toString() {
        return "RUT: " + rut
                + "\nNombre: " + getNombreCompleto()
                + "\nTeléfono: " + telefono
                + "\nCorreo: " + correo
                + "\nDirección: " + direccion;
    }
}