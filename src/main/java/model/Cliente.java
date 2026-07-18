package model;

import exception.DatoInvalidoException;
import util.Validador;
import interfaces.Registrable;

/**
 * Representa a un cliente que contrata servicios
 * turisticos de Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class Cliente extends Persona implements Registrable {

    private String codigoCliente;
    private String preferenciaTuristica;

    /**
     * Crea un cliente con sus datos personales
     * y la informacion propia de su rol.
     *
     * @param rut RUT del cliente
     * @param nombre nombre del cliente
     * @param apellido apellido del cliente
     * @param telefono telefono de contacto
     * @param correo correo electronico
     * @param direccion direccion del cliente
     * @param codigoCliente codigo unico del cliente
     * @param preferenciaTuristica preferencia turistica del cliente
     * @throws DatoInvalidoException si algun dato no es valido
     */
    public Cliente(
            String rut,
            String nombre,
            String apellido,
            String telefono,
            String correo,
            Direccion direccion,
            String codigoCliente,
            String preferenciaTuristica
    ) throws DatoInvalidoException {

        super(
                rut,
                nombre,
                apellido,
                telefono,
                correo,
                direccion
        );

        setCodigoCliente(codigoCliente);
        setPreferenciaTuristica(preferenciaTuristica);
    }

    /**
     * Retorna el codigo del cliente.
     *
     * @return codigo del cliente
     */
    public String getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * Asigna y normaliza el codigo del cliente.
     *
     * @param codigoCliente codigo que se desea asignar
     * @throws DatoInvalidoException si el codigo no es valido
     */
    public void setCodigoCliente(String codigoCliente)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                codigoCliente,
                "El codigo del cliente"
        );

        String codigoNormalizado =
                codigoCliente.trim().toUpperCase();

        if (!codigoNormalizado.matches("CLI-\\d{3,6}")) {
            throw new DatoInvalidoException(
                    "El codigo del cliente debe tener el formato "
                            + "CLI seguido de tres a seis numeros. "
                            + "Ejemplo: CLI-001."
            );
        }

        this.codigoCliente = codigoNormalizado;
    }

    /**
     * Retorna la preferencia turistica del cliente.
     *
     * @return preferencia turistica
     */
    public String getPreferenciaTuristica() {
        return preferenciaTuristica;
    }

    /**
     * Asigna la preferencia turistica del cliente.
     *
     * @param preferenciaTuristica preferencia que se desea asignar
     * @throws DatoInvalidoException si la preferencia esta vacia
     */
    public void setPreferenciaTuristica(
            String preferenciaTuristica
    ) throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                preferenciaTuristica,
                "La preferencia turistica"
        );

        this.preferenciaTuristica =
                preferenciaTuristica.trim();
    }

    /**
     * Retorna el identificador unico del cliente.
     *
     * @return codigo del cliente
     */
    @Override
    public String obtenerIdentificador() {
        return codigoCliente;
    }

    /**
     * Retorna un resumen breve del cliente.
     *
     * @return resumen del cliente
     */
    @Override
    public String mostrarResumen() {
        return codigoCliente
                + " | " + getNombreCompleto()
                + " | Preferencia: " + preferenciaTuristica;
    }

    /**
     * Retorna todos los datos del cliente.
     *
     * @return representacion completa del cliente
     */
    @Override
    public String toString() {
        return "=== Cliente ==="
                + "\n" + super.toString()
                + "\nCodigo: " + codigoCliente
                + "\nPreferencia turistica: "
                + preferenciaTuristica;
    }
}