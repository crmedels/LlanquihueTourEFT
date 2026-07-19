package service;

import exception.DatoInvalidoException;
import exception.RegistroDuplicadoException;
import model.Reserva;

import java.util.ArrayList;

/**
 * Gestiona las reservas registradas en el sistema
 * Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class GestorReservas {

    private final ArrayList<Reserva> reservas;

    /**
     * Crea un gestor con una coleccion vacia.
     */
    public GestorReservas() {
        reservas = new ArrayList<>();
    }

    /**
     * Registra una reserva si su codigo no existe
     * y sus recursos estan disponibles en la fecha indicada.
     *
     * @param reserva reserva que se desea registrar
     * @throws DatoInvalidoException si la reserva es nula
     * o existe un conflicto de disponibilidad
     * @throws RegistroDuplicadoException si el codigo ya existe
     */
    public void registrarReserva(Reserva reserva)
            throws DatoInvalidoException,
            RegistroDuplicadoException {

        if (reserva == null) {
            throw new DatoInvalidoException(
                    "La reserva no puede ser nula."
            );
        }

        if (existeCodigo(reserva.getCodigoReserva())) {
            throw new RegistroDuplicadoException(
                    "Ya existe una reserva con el codigo "
                            + reserva.getCodigoReserva() + "."
            );
        }

        if (guiaOcupadoEnFecha(reserva)) {
            throw new DatoInvalidoException(
                    "El guia seleccionado ya tiene una reserva "
                            + "asignada en la fecha "
                            + reserva.getFecha() + "."
            );
        }

        if (vehiculoOcupadoEnFecha(reserva)) {
            throw new DatoInvalidoException(
                    "El vehiculo seleccionado ya tiene una reserva "
                            + "asignada en la fecha "
                            + reserva.getFecha() + "."
            );
        }

        reservas.add(reserva);
    }

    /**
     * Verifica si un codigo de reserva ya existe.
     *
     * @param codigo codigo que se desea comprobar
     * @return true si el codigo ya esta registrado
     */
    public boolean existeCodigo(String codigo) {

        if (codigo == null) {
            return false;
        }

        String codigoBuscado = codigo.trim();

        for (Reserva reserva : reservas) {
            if (reserva.getCodigoReserva()
                    .equalsIgnoreCase(codigoBuscado)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Busca una reserva mediante su codigo.
     *
     * @param codigo codigo de la reserva buscada
     * @return reserva encontrada o null si no existe
     */
    public Reserva buscarPorCodigo(String codigo) {

        if (codigo == null) {
            return null;
        }

        String codigoBuscado = codigo.trim();

        for (Reserva reserva : reservas) {
            if (reserva.getCodigoReserva()
                    .equalsIgnoreCase(codigoBuscado)) {

                return reserva;
            }
        }

        return null;
    }

    /**
     * Busca todas las reservas asociadas a un cliente.
     *
     * @param rutCliente RUT del cliente
     * @return coleccion con las reservas encontradas
     */
    public ArrayList<Reserva> buscarPorRutCliente(
            String rutCliente
    ) {

        ArrayList<Reserva> resultados = new ArrayList<>();

        if (rutCliente == null) {
            return resultados;
        }

        String rutBuscado = rutCliente
                .replace(".", "")
                .replace(" ", "")
                .toUpperCase()
                .trim();

        for (Reserva reserva : reservas) {
            if (reserva.getCliente()
                    .getRut()
                    .equalsIgnoreCase(rutBuscado)) {

                resultados.add(reserva);
            }
        }

        return resultados;
    }

    /**
     * Filtra las reservas realizadas para una fecha.
     *
     * @param fecha fecha que se desea consultar
     * @return coleccion con las reservas coincidentes
     */
    public ArrayList<Reserva> buscarPorFecha(String fecha) {

        ArrayList<Reserva> resultados = new ArrayList<>();

        if (fecha == null) {
            return resultados;
        }

        String fechaBuscada = fecha.trim();

        for (Reserva reserva : reservas) {
            if (reserva.getFecha()
                    .equalsIgnoreCase(fechaBuscada)) {

                resultados.add(reserva);
            }
        }

        return resultados;
    }

    /**
     * Verifica si el guia de una reserva ya esta
     * asignado en la misma fecha.
     *
     * @param nuevaReserva reserva que se desea comprobar
     * @return true si existe un conflicto con el guia
     */
    private boolean guiaOcupadoEnFecha(Reserva nuevaReserva) {

        for (Reserva reservaRegistrada : reservas) {

            boolean mismaFecha =
                    reservaRegistrada.getFecha()
                            .equalsIgnoreCase(
                                    nuevaReserva.getFecha()
                            );

            boolean mismoGuia =
                    reservaRegistrada.getGuia()
                            .getRut()
                            .equalsIgnoreCase(
                                    nuevaReserva.getGuia().getRut()
                            );

            if (mismaFecha && mismoGuia) {
                return true;
            }
        }

        return false;
    }

    /**
     * Verifica si el vehiculo de una reserva ya esta
     * asignado en la misma fecha.
     *
     * @param nuevaReserva reserva que se desea comprobar
     * @return true si existe un conflicto con el vehiculo
     */
    private boolean vehiculoOcupadoEnFecha(
            Reserva nuevaReserva
    ) {

        for (Reserva reservaRegistrada : reservas) {

            boolean mismaFecha =
                    reservaRegistrada.getFecha()
                            .equalsIgnoreCase(
                                    nuevaReserva.getFecha()
                            );

            boolean mismoVehiculo =
                    reservaRegistrada.getVehiculo()
                            .getPatente()
                            .equalsIgnoreCase(
                                    nuevaReserva
                                            .getVehiculo()
                                            .getPatente()
                            );

            if (mismaFecha && mismoVehiculo) {
                return true;
            }
        }

        return false;
    }

    /**
     * Calcula la suma de los valores de todas
     * las reservas registradas.
     *
     * @return ingresos totales de las reservas
     */
    public double calcularIngresosTotales() {

        double totalIngresos = 0;

        for (Reserva reserva : reservas) {
            totalIngresos += reserva.getTotal();
        }

        return totalIngresos;
    }

    /**
     * Genera un resumen de todas las reservas.
     *
     * @return resumen de las reservas registradas
     */
    public String generarResumenReservas() {

        if (reservas.isEmpty()) {
            return "No existen reservas registradas.";
        }

        StringBuilder resumen = new StringBuilder();

        for (Reserva reserva : reservas) {
            resumen.append(reserva.mostrarResumen())
                    .append("\n");
        }

        return resumen.toString();
    }

    /**
     * Retorna una copia de la coleccion de reservas.
     *
     * @return copia de las reservas registradas
     */
    public ArrayList<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }

    /**
     * Retorna la cantidad de reservas registradas.
     *
     * @return cantidad total de reservas
     */
    public int obtenerCantidadReservas() {
        return reservas.size();
    }
}