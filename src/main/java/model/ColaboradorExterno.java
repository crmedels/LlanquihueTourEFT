package model;

import exception.DatoInvalidoException;
import util.Validador;

/**
 * Representa a una persona externa que presta servicios
 * ocasionales para Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class ColaboradorExterno extends Persona {

    private String codigoColaborador;
    private String tipoServicio;
    private double tarifaPorServicio;
    private boolean disponible;

    /**
     * Crea un colaborador externo con sus datos personales
     * y la informacion del servicio que presta.
     *
     * @param rut RUT del colaborador
     * @param nombre nombre del colaborador
     * @param apellido apellido del colaborador
     * @param telefono telefono de contacto
     * @param correo correo electronico
     * @param direccion direccion del colaborador
     * @param codigoColaborador codigo unico del colaborador
     * @param tipoServicio servicio que presta
     * @param tarifaPorServicio tarifa cobrada por servicio
     * @param disponible disponibilidad actual
     * @throws DatoInvalidoException si algun dato no es valido
     */
    public ColaboradorExterno(
            String rut,
            String nombre,
            String apellido,
            String telefono,
            String correo,
            Direccion direccion,
            String codigoColaborador,
            String tipoServicio,
            double tarifaPorServicio,
            boolean disponible
    ) throws DatoInvalidoException {

        super(
                rut,
                nombre,
                apellido,
                telefono,
                correo,
                direccion
        );

        setCodigoColaborador(codigoColaborador);
        setTipoServicio(tipoServicio);
        setTarifaPorServicio(tarifaPorServicio);
        setDisponible(disponible);
    }

    /**
     * Retorna el codigo del colaborador.
     *
     * @return codigo del colaborador
     */
    public String getCodigoColaborador() {
        return codigoColaborador;
    }

    /**
     * Asigna y normaliza el codigo del colaborador.
     *
     * @param codigoColaborador codigo que se desea asignar
     * @throws DatoInvalidoException si el codigo no es valido
     */
    public void setCodigoColaborador(String codigoColaborador)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                codigoColaborador,
                "El codigo del colaborador"
        );

        String codigoNormalizado =
                codigoColaborador.trim().toUpperCase();

        if (!codigoNormalizado.matches("COL-\\d{3,6}")) {
            throw new DatoInvalidoException(
                    "El codigo del colaborador debe tener el formato "
                            + "COL seguido de tres a seis numeros. "
                            + "Ejemplo: COL-001."
            );
        }

        this.codigoColaborador = codigoNormalizado;
    }

    /**
     * Retorna el tipo de servicio prestado.
     *
     * @return tipo de servicio
     */
    public String getTipoServicio() {
        return tipoServicio;
    }

    /**
     * Asigna el tipo de servicio prestado.
     *
     * @param tipoServicio servicio que se desea asignar
     * @throws DatoInvalidoException si el servicio esta vacio
     */
    public void setTipoServicio(String tipoServicio)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                tipoServicio,
                "El tipo de servicio"
        );

        this.tipoServicio = tipoServicio.trim();
    }

    /**
     * Retorna la tarifa por servicio.
     *
     * @return tarifa por servicio
     */
    public double getTarifaPorServicio() {
        return tarifaPorServicio;
    }

    /**
     * Asigna la tarifa por servicio.
     *
     * @param tarifaPorServicio tarifa que se desea asignar
     * @throws DatoInvalidoException si la tarifa no es positiva
     */
    public void setTarifaPorServicio(double tarifaPorServicio)
            throws DatoInvalidoException {

        Validador.validarDecimalPositivo(
                tarifaPorServicio,
                "La tarifa por servicio"
        );

        this.tarifaPorServicio = tarifaPorServicio;
    }

    /**
     * Indica si el colaborador se encuentra disponible.
     *
     * @return true si esta disponible
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Modifica la disponibilidad del colaborador.
     *
     * @param disponible nueva disponibilidad
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Calcula el pago total segun la cantidad de servicios realizados.
     *
     * @param cantidadServicios cantidad de servicios realizados
     * @return pago total correspondiente
     * @throws DatoInvalidoException si la cantidad no es positiva
     */
    public double calcularPago(int cantidadServicios)
            throws DatoInvalidoException {

        Validador.validarEnteroPositivo(
                cantidadServicios,
                "La cantidad de servicios"
        );

        return tarifaPorServicio * cantidadServicios;
    }

    /**
     * Retorna un resumen breve del colaborador.
     *
     * @return resumen del colaborador
     */
    public String mostrarResumen() {
        String estado = disponible
                ? "Disponible"
                : "No disponible";

        return codigoColaborador
                + " | " + getNombreCompleto()
                + " | " + tipoServicio
                + " | " + estado;
    }

    /**
     * Retorna todos los datos del colaborador externo.
     *
     * @return representacion completa del colaborador
     */
    @Override
    public String toString() {
        String estado = disponible
                ? "Disponible"
                : "No disponible";

        return "=== Colaborador externo ==="
                + "\n" + super.toString()
                + "\nCodigo: " + codigoColaborador
                + "\nTipo de servicio: " + tipoServicio
                + "\nTarifa por servicio: $" + tarifaPorServicio
                + "\nEstado: " + estado;
    }
}