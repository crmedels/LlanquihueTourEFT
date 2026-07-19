package model;

import exception.DatoInvalidoException;
import interfaces.Registrable;
import util.Validador;

/**
 * Representa una reserva de un servicio turistico
 * realizada por un cliente de Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class Reserva implements Registrable {

    private String codigoReserva;
    private Cliente cliente;
    private ServicioTuristico servicio;
    private GuiaTuristico guia;
    private Vehiculo vehiculo;
    private String fecha;
    private int cantidadPersonas;
    private double total;

    /**
     * Crea una reserva con todos sus datos.
     *
     * @param codigoReserva codigo unico de la reserva
     * @param cliente cliente que realiza la reserva
     * @param servicio servicio turistico contratado
     * @param guia guia asignado a la actividad
     * @param vehiculo vehiculo asignado al traslado
     * @param fecha fecha de realizacion en formato DD-MM-AAAA
     * @param cantidadPersonas cantidad de participantes
     * @throws DatoInvalidoException si algun dato no es valido
     */
    public Reserva(
            String codigoReserva,
            Cliente cliente,
            ServicioTuristico servicio,
            GuiaTuristico guia,
            Vehiculo vehiculo,
            String fecha,
            int cantidadPersonas
    ) throws DatoInvalidoException {

        setCodigoReserva(codigoReserva);
        setCliente(cliente);
        setServicio(servicio);
        setGuia(guia);
        setVehiculo(vehiculo);
        setFecha(fecha);
        setCantidadPersonas(cantidadPersonas);
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    /**
     * Asigna y normaliza el codigo de la reserva.
     *
     * @param codigoReserva codigo que se desea asignar
     * @throws DatoInvalidoException si el codigo no es valido
     */
    public void setCodigoReserva(String codigoReserva)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                codigoReserva,
                "El codigo de la reserva"
        );

        String codigoNormalizado =
                codigoReserva.trim().toUpperCase();

        if (!codigoNormalizado.matches("RES-\\d{3,6}")) {
            throw new DatoInvalidoException(
                    "El codigo de la reserva debe tener el formato "
                            + "RES seguido de tres a seis numeros. "
                            + "Ejemplo: RES-001."
            );
        }

        this.codigoReserva = codigoNormalizado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Asigna el cliente asociado a la reserva.
     *
     * @param cliente cliente que se desea asignar
     * @throws DatoInvalidoException si el cliente es nulo
     */
    public void setCliente(Cliente cliente)
            throws DatoInvalidoException {

        if (cliente == null) {
            throw new DatoInvalidoException(
                    "El cliente de la reserva no puede ser nulo."
            );
        }

        this.cliente = cliente;
    }

    public ServicioTuristico getServicio() {
        return servicio;
    }

    /**
     * Asigna el servicio turistico de la reserva.
     *
     * @param servicio servicio que se desea asignar
     * @throws DatoInvalidoException si el servicio es nulo,
     * no esta disponible o no tiene capacidad suficiente
     */
    public void setServicio(ServicioTuristico servicio)
            throws DatoInvalidoException {

        if (servicio == null) {
            throw new DatoInvalidoException(
                    "El servicio turistico no puede ser nulo."
            );
        }

        if (!servicio.isDisponible()) {
            throw new DatoInvalidoException(
                    "El servicio turistico seleccionado no esta disponible."
            );
        }

        if (cantidadPersonas > 0
                && !servicio.tieneCapacidadPara(cantidadPersonas)) {

            throw new DatoInvalidoException(
                    "El servicio no tiene capacidad suficiente "
                            + "para la reserva."
            );
        }

        this.servicio = servicio;
        actualizarTotal();
    }

    public GuiaTuristico getGuia() {
        return guia;
    }

    /**
     * Asigna el guia turistico de la reserva.
     *
     * @param guia guia que se desea asignar
     * @throws DatoInvalidoException si el guia es nulo
     * o no esta disponible
     */
    public void setGuia(GuiaTuristico guia)
            throws DatoInvalidoException {

        if (guia == null) {
            throw new DatoInvalidoException(
                    "El guia turistico no puede ser nulo."
            );
        }

        if (!guia.isDisponible()) {
            throw new DatoInvalidoException(
                    "El guia turistico seleccionado no esta disponible."
            );
        }

        this.guia = guia;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * Asigna el vehiculo utilizado en la reserva.
     *
     * @param vehiculo vehiculo que se desea asignar
     * @throws DatoInvalidoException si el vehiculo es nulo,
     * no esta disponible o no tiene capacidad suficiente
     */
    public void setVehiculo(Vehiculo vehiculo)
            throws DatoInvalidoException {

        if (vehiculo == null) {
            throw new DatoInvalidoException(
                    "El vehiculo no puede ser nulo."
            );
        }

        if (!vehiculo.isDisponible()) {
            throw new DatoInvalidoException(
                    "El vehiculo seleccionado no esta disponible."
            );
        }

        if (cantidadPersonas > 0
                && !vehiculo.tieneCapacidadPara(cantidadPersonas)) {

            throw new DatoInvalidoException(
                    "El vehiculo no tiene capacidad suficiente "
                            + "para la reserva."
            );
        }

        this.vehiculo = vehiculo;
    }

    public String getFecha() {
        return fecha;
    }

    /**
     * Asigna la fecha de realizacion de la reserva.
     *
     * @param fecha fecha en formato DD-MM-AAAA
     * @throws DatoInvalidoException si el formato no es valido
     */
    public void setFecha(String fecha)
            throws DatoInvalidoException {

        Validador.validarTextoObligatorio(
                fecha,
                "La fecha"
        );

        String fechaNormalizada = fecha.trim();

        if (!fechaNormalizada.matches("\\d{2}-\\d{2}-\\d{4}")) {
            throw new DatoInvalidoException(
                    "La fecha debe tener el formato DD-MM-AAAA. "
                            + "Ejemplo: 25-07-2026."
            );
        }

        String[] partesFecha = fechaNormalizada.split("-");

        int dia = Integer.parseInt(partesFecha[0]);
        int mes = Integer.parseInt(partesFecha[1]);

        if (dia < 1 || dia > 31) {
            throw new DatoInvalidoException(
                    "El dia de la fecha debe estar entre 1 y 31."
            );
        }

        if (mes < 1 || mes > 12) {
            throw new DatoInvalidoException(
                    "El mes de la fecha debe estar entre 1 y 12."
            );
        }

        this.fecha = fechaNormalizada;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    /**
     * Asigna la cantidad de participantes y comprueba
     * la capacidad del servicio y del vehiculo.
     *
     * @param cantidadPersonas cantidad de participantes
     * @throws DatoInvalidoException si la cantidad no es valida
     * o supera alguna capacidad
     */
    public void setCantidadPersonas(int cantidadPersonas)
            throws DatoInvalidoException {

        Validador.validarEnteroPositivo(
                cantidadPersonas,
                "La cantidad de personas"
        );

        if (servicio == null) {
            throw new DatoInvalidoException(
                    "Debe existir un servicio antes de asignar "
                            + "la cantidad de personas."
            );
        }

        if (vehiculo == null) {
            throw new DatoInvalidoException(
                    "Debe existir un vehiculo antes de asignar "
                            + "la cantidad de personas."
            );
        }

        if (!servicio.tieneCapacidadPara(cantidadPersonas)) {
            throw new DatoInvalidoException(
                    "La cantidad de personas supera "
                            + "la capacidad maxima del servicio."
            );
        }

        if (!vehiculo.tieneCapacidadPara(cantidadPersonas)) {
            throw new DatoInvalidoException(
                    "La cantidad de personas supera "
                            + "la capacidad maxima del vehiculo."
            );
        }

        this.cantidadPersonas = cantidadPersonas;
        actualizarTotal();
    }

    public double getTotal() {
        return total;
    }

    /**
     * Actualiza el valor total de la reserva utilizando
     * el calculo polimorfico del servicio turistico.
     *
     * @throws DatoInvalidoException si no es posible calcular el total
     */
    private void actualizarTotal()
            throws DatoInvalidoException {

        if (servicio != null && cantidadPersonas > 0) {
            total = servicio.calcularPrecio(cantidadPersonas);
        }
    }

    /**
     * Retorna el identificador unico de la reserva.
     *
     * @return codigo de la reserva
     */
    @Override
    public String obtenerIdentificador() {
        return codigoReserva;
    }

    /**
     * Retorna un resumen breve de la reserva.
     *
     * @return resumen de la reserva
     */
    @Override
    public String mostrarResumen() {
        return codigoReserva
                + " | Cliente: " + cliente.getNombreCompleto()
                + " | Servicio: " + servicio.getNombre()
                + " | Fecha: " + fecha
                + " | Personas: " + cantidadPersonas
                + " | Total: $" + total;
    }

    /**
     * Retorna todos los datos de la reserva.
     *
     * @return representacion completa de la reserva
     */
    @Override
    public String toString() {
        return "=== Reserva ==="
                + "\nCodigo: " + codigoReserva
                + "\nCliente: " + cliente.getNombreCompleto()
                + "\nRUT del cliente: " + cliente.getRut()
                + "\nServicio: " + servicio.getNombre()
                + "\nGuia: " + guia.getNombreCompleto()
                + "\nVehiculo: " + vehiculo.getPatente()
                + " - " + vehiculo.getMarca()
                + " " + vehiculo.getModelo()
                + "\nFecha: " + fecha
                + "\nCantidad de personas: " + cantidadPersonas
                + "\nTotal: $" + total;
    }
}