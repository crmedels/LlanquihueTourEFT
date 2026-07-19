package data;

import exception.DatoInvalidoException;
import exception.RegistroDuplicadoException;
import model.Cliente;
import model.GuiaTuristico;
import model.ServicioTuristico;
import model.Vehiculo;
import service.GestorEntidades;
import service.GestorServicios;

import java.io.IOException;

/**
 * Centraliza la carga inicial de los archivos de datos
 * utilizados por el sistema Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class CargadorDatosIniciales {

    private static final String RUTA_CLIENTES =
            "data/clientes.txt";

    private static final String RUTA_GUIAS =
            "data/guias.txt";

    private static final String RUTA_VEHICULOS =
            "data/vehiculos.txt";

    private static final String RUTA_SERVICIOS =
            "data/servicios.txt";

    private final LectorClientes lectorClientes;
    private final LectorGuias lectorGuias;
    private final LectorVehiculos lectorVehiculos;
    private final LectorServicios lectorServicios;

    /**
     * Crea el cargador y prepara los lectores
     * de los distintos archivos del sistema.
     */
    public CargadorDatosIniciales() {
        lectorClientes = new LectorClientes();
        lectorGuias = new LectorGuias();
        lectorVehiculos = new LectorVehiculos();
        lectorServicios = new LectorServicios();
    }

    /**
     * Carga todas las entidades y servicios desde
     * los archivos almacenados en resources.
     *
     * @param gestorEntidades gestor que recibira personas y vehiculos
     * @param gestorServicios gestor que recibira servicios turisticos
     * @throws IOException si algun archivo no puede ser leido
     * @throws DatoInvalidoException si algun dato no es valido
     * @throws RegistroDuplicadoException si existe un identificador repetido
     */
    public void cargarDatosIniciales(
            GestorEntidades gestorEntidades,
            GestorServicios gestorServicios
    ) throws IOException,
            DatoInvalidoException,
            RegistroDuplicadoException {

        if (gestorEntidades == null) {
            throw new DatoInvalidoException(
                    "El gestor de entidades no puede ser nulo."
            );
        }

        if (gestorServicios == null) {
            throw new DatoInvalidoException(
                    "El gestor de servicios no puede ser nulo."
            );
        }

        cargarClientes(gestorEntidades);
        cargarGuias(gestorEntidades);
        cargarVehiculos(gestorEntidades);
        cargarServicios(gestorServicios);
    }

    /**
     * Carga los clientes en el gestor de entidades.
     *
     * @param gestorEntidades gestor que recibira los clientes
     * @throws IOException si el archivo no puede ser leido
     * @throws DatoInvalidoException si algun dato no es valido
     * @throws RegistroDuplicadoException si existe un codigo repetido
     */
    private void cargarClientes(
            GestorEntidades gestorEntidades
    ) throws IOException,
            DatoInvalidoException,
            RegistroDuplicadoException {

        for (Cliente cliente
                : lectorClientes.cargarClientes(RUTA_CLIENTES)) {

            gestorEntidades.registrarEntidad(cliente);
        }
    }

    /**
     * Carga los guias en el gestor de entidades.
     *
     * @param gestorEntidades gestor que recibira los guias
     * @throws IOException si el archivo no puede ser leido
     * @throws DatoInvalidoException si algun dato no es valido
     * @throws RegistroDuplicadoException si existe un codigo repetido
     */
    private void cargarGuias(
            GestorEntidades gestorEntidades
    ) throws IOException,
            DatoInvalidoException,
            RegistroDuplicadoException {

        for (GuiaTuristico guia
                : lectorGuias.cargarGuias(RUTA_GUIAS)) {

            gestorEntidades.registrarEntidad(guia);
        }
    }

    /**
     * Carga los vehiculos en el gestor de entidades.
     *
     * @param gestorEntidades gestor que recibira los vehiculos
     * @throws IOException si el archivo no puede ser leido
     * @throws DatoInvalidoException si algun dato no es valido
     * @throws RegistroDuplicadoException si existe una patente repetida
     */
    private void cargarVehiculos(
            GestorEntidades gestorEntidades
    ) throws IOException,
            DatoInvalidoException,
            RegistroDuplicadoException {

        for (Vehiculo vehiculo
                : lectorVehiculos.cargarVehiculos(RUTA_VEHICULOS)) {

            gestorEntidades.registrarEntidad(vehiculo);
        }
    }

    /**
     * Carga los servicios en el gestor correspondiente.
     *
     * @param gestorServicios gestor que recibira los servicios
     * @throws IOException si el archivo no puede ser leido
     * @throws DatoInvalidoException si algun dato no es valido
     * @throws RegistroDuplicadoException si existe un codigo repetido
     */
    private void cargarServicios(
            GestorServicios gestorServicios
    ) throws IOException,
            DatoInvalidoException,
            RegistroDuplicadoException {

        for (ServicioTuristico servicio
                : lectorServicios.cargarServicios(RUTA_SERVICIOS)) {

            gestorServicios.registrarServicio(servicio);
        }
    }
}
