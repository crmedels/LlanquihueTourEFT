package model;

import exception.DatoInvalidoException;
import util.FormateadorMoneda;
import util.Validador;
import interfaces.Registrable;

/**
 * Representa a un guia turistico que presta servicios
 * para Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class GuiaTuristico extends Persona implements Registrable {

    private String codigoGuia;
    private String especialidad;
    private int aniosExperiencia;
    private double tarifaDiaria;
    private boolean disponible;

    /**
     * Crea un guia turistico con sus datos personales
     * y profesionales.
     *
     * @param rut RUT del guia
     * @param nombre nombre del guia
     * @param apellido apellido del guia
     * @param telefono telefono de contacto
     * @param correo correo electronico
     * @param direccion direccion del guia
     * @param codigoGuia codigo unico del guia
     * @param especialidad especialidad profesional
     * @param aniosExperiencia cantidad de anios de experiencia
     * @param tarifaDiaria tarifa diaria del guia
     * @param disponible disponibilidad actual
     * @throws DatoInvalidoException si algun dato no es valido
     */
    public GuiaTuristico(
            String rut,
            String nombre,
            String apellido,
            String telefono,
            String correo,
            Direccion direccion,
            String codigoGuia,
            String especialidad,
            int aniosExperiencia,
            double tarifaDiaria,
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

        setCodigoGuia(codigoGuia);
        setEspecialidad(especialidad);
        setAniosExperiencia(aniosExperiencia);
        setTarifaDiaria(tarifaDiaria);
        setDisponible(disponible);
    }

    /**
     * Retorna el codigo del guia.
     *
     * @return codigo del guia
     */
    public String getCodigoGuia() {
        return codigoGuia;
    }

    /**
     * Asigna y normaliza el codigo del guia.
     *
     * @param codigoGuia codigo que se desea asignar
     * @throws DatoInvalidoException si el codigo no tiene un formato valido
     */
    public void setCodigoGuia(String codigoGuia)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                codigoGuia,
                "El código del guia"
        );

        String codigoNormalizado =
                codigoGuia.trim().toUpperCase();

        if (!codigoNormalizado.matches("GUI-\\d{3,6}")) {
            throw new DatoInvalidoException(
                    "El código del guia debe tener el formato "
                            + "GUI seguido de tres a seis números. "
                            + "Ejemplo: GUI-001."
            );
        }

        this.codigoGuia = codigoNormalizado;
    }

    /**
     * Retorna la especialidad del guia.
     *
     * @return especialidad profesional
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Asigna la especialidad del guia.
     *
     * @param especialidad especialidad que se desea asignar
     * @throws DatoInvalidoException si la especialidad esta vacia
     */
    public void setEspecialidad(String especialidad)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                especialidad,
                "La especialidad"
        );

        this.especialidad = especialidad.trim();
    }

    /**
     * Retorna los anios de experiencia.
     *
     * @return cantidad de anios de experiencia
     */
    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    /**
     * Asigna los anios de experiencia.
     *
     * @param aniosExperiencia cantidad de anios
     * @throws DatoInvalidoException si el valor es negativo
     */
    public void setAniosExperiencia(int aniosExperiencia)
            throws DatoInvalidoException {

        Validador.validarEnteroNoNegativo(
                aniosExperiencia,
                "Los años de experiencia"
        );

        this.aniosExperiencia = aniosExperiencia;
    }

    /**
     * Retorna la tarifa diaria del guia.
     *
     * @return tarifa diaria
     */
    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    /**
     * Asigna la tarifa diaria del guia.
     *
     * @param tarifaDiaria tarifa que se desea asignar
     * @throws DatoInvalidoException si la tarifa no es positiva
     */
    public void setTarifaDiaria(double tarifaDiaria)
            throws DatoInvalidoException {

        Validador.validarDecimalPositivo(
                tarifaDiaria,
                "La tarifa diaria"
        );

        this.tarifaDiaria = tarifaDiaria;
    }

    /**
     * Indica si el guia se encuentra disponible.
     *
     * @return true si esta disponible
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Modifica la disponibilidad del guia.
     *
     * @param disponible nueva disponibilidad
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Calcula el pago del guia segun la cantidad de dias trabajados.
     *
     * @param cantidadDias cantidad de dias trabajados
     * @return pago total correspondiente
     * @throws DatoInvalidoException si la cantidad de dias no es positiva
     */
    public double calcularPago(int cantidadDias)
            throws DatoInvalidoException {

        Validador.validarEnteroPositivo(
                cantidadDias,
                "La cantidad de días"
        );

        return tarifaDiaria * cantidadDias;
    }

    /**
     * Retorna el identificador unico del guia.
     *
     * @return codigo del guia
     */
    @Override
    public String obtenerIdentificador() {
        return codigoGuia;
    }

    /**
     * Retorna un resumen breve del guia.
     *
     * @return resumen del guia
     */
    @Override
    public String mostrarResumen() {
        String estado = disponible
                ? "Disponible"
                : "No disponible";

        return codigoGuia
                + " | " + getNombreCompleto()
                + " | " + especialidad
                + " | " + estado;
    }

    /**
     * Retorna todos los datos del guia turistico.
     *
     * @return representacion completa del guia
     */
    @Override
    public String toString() {
        String estado = disponible
                ? "Disponible"
                : "No disponible";

        return "=== Guia turístico ==="
                + "\n" + super.toString()
                + "\nCódigo: " + codigoGuia
                + "\nEspecialidad: " + especialidad
                + "\nAños de experiencia: " + aniosExperiencia
                + "\nTarifa diaria: "
                + FormateadorMoneda.formatear(tarifaDiaria)
                + "\nEstado: " + estado;
    }
}