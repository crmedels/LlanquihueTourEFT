package ui;

import data.CargadorDatosIniciales;
import exception.CupoInsuficienteException;
import exception.DatoInvalidoException;
import exception.RegistroDuplicadoException;
import interfaces.Registrable;
import model.Cliente;
import model.ColaboradorExterno;
import model.Direccion;
import model.GuiaTuristico;
import model.PaseoLacustre;
import model.Persona;
import model.Reserva;
import model.RutaGastronomica;
import model.ServicioTuristico;
import model.Vehiculo;
import service.GestorEntidades;
import service.GestorReservas;
import service.GestorServicios;
import util.Validador;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

/**
 * Administra el menu principal y coordina el acceso
 * a los distintos modulos del sistema.
 *
 * @author Cristofer Medel
 */
public class MenuPrincipal {

    private final GestorEntidades gestorEntidades;
    private final GestorServicios gestorServicios;
    private final GestorReservas gestorReservas;

    /**
     * Crea el menu principal y los gestores.
     */
    public MenuPrincipal() {
        gestorEntidades = new GestorEntidades();
        gestorServicios = new GestorServicios();
        gestorReservas = new GestorReservas();
    }

    /**
     * Carga los datos iniciales y abre el menu principal.
     */
    public void iniciar() {

        try {
            CargadorDatosIniciales cargadorDatos =
                    new CargadorDatosIniciales();

            cargadorDatos.cargarDatosIniciales(
                    gestorEntidades,
                    gestorServicios
            );

            mostrarMenu();

        } catch (IOException
                 | DatoInvalidoException
                 | RegistroDuplicadoException e) {

            mostrarError(
                    "No fue posible iniciar el sistema.\n"
                            + e.getMessage()
            );
        }
    }

    /**
     * Muestra el menu principal.
     */
    private void mostrarMenu() {

        String[] opciones = {
                "Ver resumen general",
                "Gestionar entidades",
                "Gestionar servicios",
                "Gestionar reservas",
                "Salir"
        };

        boolean continuar = true;

        while (continuar) {

            int opcion =
                    mostrarMenuVertical(
                            "Llanquihue Tour",
                            "Seleccione una opcion:",
                            opciones
                    );

            switch (opcion) {

                case 0:
                    mostrarResumenGeneral();
                    break;

                case 1:
                    mostrarMenuEntidades();
                    break;

                case 2:
                    mostrarMenuServicios();
                    break;

                case 3:
                    mostrarMenuReservas();
                    break;

                case 4:
                case JOptionPane.CLOSED_OPTION:
                    continuar = false;
                    break;

                default:
                    continuar = false;
                    break;
            }
        }

        mostrarMensaje(
                "Llanquihue Tour",
                "El sistema se ha cerrado correctamente."
        );
    }

    /**
     * Muestra el menu de entidades.
     */
    private void mostrarMenuEntidades() {

        String[] opciones = {
                "Registrar cliente",
                "Registrar guia turistico",
                "Registrar vehiculo",
                "Registrar colaborador externo",
                "Listar todas",
                "Buscar por identificador",
                "Buscar persona por RUT",
                "Filtrar por tipo",
                "Volver"
        };

        boolean continuar = true;

        while (continuar) {

            int opcion =
                    mostrarMenuVertical(
                            "Gestion de entidades",
                            "Seleccione una opcion:",
                            opciones
                    );

            switch (opcion) {

                case 0:
                    registrarCliente();
                    break;

                case 1:
                    registrarGuiaTuristico();
                    break;

                case 2:
                    registrarVehiculo();
                    break;

                case 3:
                    registrarColaboradorExterno();
                    break;

                case 4:
                    mostrarTodasLasEntidades();
                    break;

                case 5:
                    buscarEntidadPorIdentificador();
                    break;

                case 6:
                    buscarPersonaPorRut();
                    break;

                case 7:
                    filtrarEntidadesPorTipo();
                    break;

                case 8:
                case JOptionPane.CLOSED_OPTION:
                    continuar = false;
                    break;

                default:
                    continuar = false;
                    break;
            }
        }
    }

    /**
     * Muestra el menu de servicios turisticos.
     */
    private void mostrarMenuServicios() {

        String[] opciones = {
                "Registrar ruta gastronomica",
                "Registrar paseo lacustre",
                "Listar todos",
                "Buscar por codigo",
                "Filtrar por tipo",
                "Volver"
        };

        boolean continuar = true;

        while (continuar) {

            int opcion =
                    mostrarMenuVertical(
                            "Gestion de servicios",
                            "Seleccione una opcion:",
                            opciones
                    );

            switch (opcion) {

                case 0:
                    registrarRutaGastronomica();
                    break;

                case 1:
                    registrarPaseoLacustre();
                    break;

                case 2:
                    mostrarTodosLosServicios();
                    break;

                case 3:
                    buscarServicioPorCodigo();
                    break;

                case 4:
                    filtrarServiciosPorTipo();
                    break;

                case 5:
                case JOptionPane.CLOSED_OPTION:
                    continuar = false;
                    break;

                default:
                    continuar = false;
                    break;
            }
        }
    }

    /**
     * Muestra el menu de reservas.
     */
    private void mostrarMenuReservas() {

        String[] opciones = {
                "Registrar reserva",
                "Ver datos disponibles",
                "Listar todas",
                "Buscar por codigo",
                "Buscar por RUT del cliente",
                "Buscar por fecha",
                "Ver ingresos totales",
                "Volver"
        };

        boolean continuar = true;

        while (continuar) {

            int opcion =
                    mostrarMenuVertical(
                            "Gestion de reservas",
                            "Seleccione una opcion:",
                            opciones
                    );

            switch (opcion) {

                case 0:
                    registrarReserva();
                    break;

                case 1:
                    mostrarDatosDisponiblesReserva();
                    break;

                case 2:
                    mostrarTodasLasReservas();
                    break;

                case 3:
                    buscarReservaPorCodigo();
                    break;

                case 4:
                    buscarReservasPorRut();
                    break;

                case 5:
                    buscarReservasPorFecha();
                    break;

                case 6:
                    mostrarIngresosTotales();
                    break;

                case 7:
                case JOptionPane.CLOSED_OPTION:
                    continuar = false;
                    break;

                default:
                    continuar = false;
                    break;
            }
        }
    }

    /**
     * Muestra un resumen general del sistema.
     */
    private void mostrarResumenGeneral() {

        String resumen =
                "=== ENTIDADES REGISTRADAS ===\n"
                        + gestorEntidades.generarResumenEntidades()
                        + "\n\n=== SERVICIOS TURISTICOS ===\n"
                        + gestorServicios.generarResumenServicios()
                        + "\n\n=== RESERVAS ===\n"
                        + gestorReservas.generarResumenReservas()
                        + "\n\n=== INGRESOS ===\n"
                        + "Total: $"
                        + obtenerMontoSinDecimales(
                        gestorReservas.calcularIngresosTotales()
                );

        mostrarMensaje(
                "Resumen general",
                resumen
        );
    }

    /**
     * Registra un cliente desde la interfaz.
     */
    private void registrarCliente() {

        String titulo = "Registrar cliente";

        String[] datos =
                solicitarDatos(
                        titulo,
                        "Ingrese el codigo del cliente:",
                        "Ingrese el RUT:",
                        "Ingrese el nombre:",
                        "Ingrese el apellido:",
                        "Ingrese el telefono:",
                        "Ingrese el correo electronico:",
                        "Ingrese la calle:",
                        "Ingrese el numero de la direccion:",
                        "Ingrese la comuna:",
                        "Ingrese la ciudad:",
                        "Ingrese la preferencia turistica:"
                );

        if (datos == null) {
            return;
        }

        try {
            int numeroDireccion =
                    Validador.convertirEntero(
                            datos[7],
                            "El numero de la direccion"
                    );

            Direccion direccion =
                    new Direccion(
                            datos[6],
                            numeroDireccion,
                            datos[8],
                            datos[9]
                    );

            Cliente cliente =
                    new Cliente(
                            datos[1],
                            datos[2],
                            datos[3],
                            datos[4],
                            datos[5],
                            direccion,
                            datos[0],
                            datos[10]
                    );

            gestorEntidades.registrarEntidad(
                    cliente
            );

            mostrarMensaje(
                    "Registro exitoso",
                    "Cliente registrado correctamente.\n\n"
                            + cliente.mostrarResumen()
            );

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    /**
     * Registra un guia turistico desde la interfaz.
     */
    private void registrarGuiaTuristico() {

        String titulo = "Registrar guia turistico";

        String[] datos =
                solicitarDatos(
                        titulo,
                        "Ingrese el codigo del guia:",
                        "Ingrese el RUT:",
                        "Ingrese el nombre:",
                        "Ingrese el apellido:",
                        "Ingrese el telefono:",
                        "Ingrese el correo electronico:",
                        "Ingrese la calle:",
                        "Ingrese el numero de la direccion:",
                        "Ingrese la comuna:",
                        "Ingrese la ciudad:",
                        "Ingrese la especialidad:",
                        "Ingrese los anios de experiencia:",
                        "Ingrese la tarifa diaria:"
                );

        if (datos == null) {
            return;
        }

        Boolean disponible =
                solicitarDisponibilidad(
                        titulo
                );

        if (disponible == null) {
            return;
        }

        try {
            int numeroDireccion =
                    Validador.convertirEntero(
                            datos[7],
                            "El numero de la direccion"
                    );

            int aniosExperiencia =
                    Validador.convertirEntero(
                            datos[11],
                            "Los anios de experiencia"
                    );

            double tarifaDiaria =
                    Validador.convertirDecimal(
                            datos[12],
                            "La tarifa diaria"
                    );

            Direccion direccion =
                    new Direccion(
                            datos[6],
                            numeroDireccion,
                            datos[8],
                            datos[9]
                    );

            GuiaTuristico guia =
                    new GuiaTuristico(
                            datos[1],
                            datos[2],
                            datos[3],
                            datos[4],
                            datos[5],
                            direccion,
                            datos[0],
                            datos[10],
                            aniosExperiencia,
                            tarifaDiaria,
                            disponible
                    );

            gestorEntidades.registrarEntidad(
                    guia
            );

            mostrarMensaje(
                    "Registro exitoso",
                    "Guia turistico registrado correctamente.\n\n"
                            + guia.mostrarResumen()
            );

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    /**
     * Registra un vehiculo desde la interfaz.
     */
    private void registrarVehiculo() {

        String titulo = "Registrar vehiculo";

        String[] datos =
                solicitarDatos(
                        titulo,
                        "Ingrese la patente:",
                        "Ingrese la marca:",
                        "Ingrese el modelo:",
                        "Ingrese el tipo de vehiculo:",
                        "Ingrese la capacidad de pasajeros:"
                );

        if (datos == null) {
            return;
        }

        Boolean disponible =
                solicitarDisponibilidad(
                        titulo
                );

        if (disponible == null) {
            return;
        }

        try {
            int capacidad =
                    Validador.convertirEntero(
                            datos[4],
                            "La capacidad de pasajeros"
                    );

            Vehiculo vehiculo =
                    new Vehiculo(
                            datos[0],
                            datos[1],
                            datos[2],
                            datos[3],
                            capacidad,
                            disponible
                    );

            gestorEntidades.registrarEntidad(
                    vehiculo
            );

            mostrarMensaje(
                    "Registro exitoso",
                    "Vehiculo registrado correctamente.\n\n"
                            + vehiculo.mostrarResumen()
            );

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    /**
     * Registra un colaborador externo desde la interfaz.
     */
    private void registrarColaboradorExterno() {

        String titulo = "Registrar colaborador externo";

        String[] datos =
                solicitarDatos(
                        titulo,
                        "Ingrese el codigo del colaborador:",
                        "Ingrese el RUT:",
                        "Ingrese el nombre:",
                        "Ingrese el apellido:",
                        "Ingrese el telefono:",
                        "Ingrese el correo electronico:",
                        "Ingrese la calle:",
                        "Ingrese el numero de la direccion:",
                        "Ingrese la comuna:",
                        "Ingrese la ciudad:",
                        "Ingrese el tipo de servicio:",
                        "Ingrese la tarifa por servicio:"
                );

        if (datos == null) {
            return;
        }

        Boolean disponible =
                solicitarDisponibilidad(
                        titulo
                );

        if (disponible == null) {
            return;
        }

        try {
            int numeroDireccion =
                    Validador.convertirEntero(
                            datos[7],
                            "El numero de la direccion"
                    );

            double tarifa =
                    Validador.convertirDecimal(
                            datos[11],
                            "La tarifa por servicio"
                    );

            Direccion direccion =
                    new Direccion(
                            datos[6],
                            numeroDireccion,
                            datos[8],
                            datos[9]
                    );

            ColaboradorExterno colaborador =
                    new ColaboradorExterno(
                            datos[1],
                            datos[2],
                            datos[3],
                            datos[4],
                            datos[5],
                            direccion,
                            datos[0],
                            datos[10],
                            tarifa,
                            disponible
                    );

            gestorEntidades.registrarEntidad(
                    colaborador
            );

            mostrarMensaje(
                    "Registro exitoso",
                    "Colaborador externo registrado correctamente.\n\n"
                            + colaborador.mostrarResumen()
            );

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    /**
     * Registra una ruta gastronomica.
     */
    private void registrarRutaGastronomica() {

        String titulo = "Registrar ruta gastronomica";

        String[] datos =
                solicitarDatos(
                        titulo,
                        "Ingrese el codigo del servicio:",
                        "Ingrese el nombre:",
                        "Ingrese la descripcion:",
                        "Ingrese el precio base:",
                        "Ingrese la duracion en horas:",
                        "Ingrese la capacidad maxima:",
                        "Ingrese el tipo de cocina:",
                        "Ingrese la cantidad de paradas:",
                        "Ingrese el costo de degustacion por persona:"
                );

        if (datos == null) {
            return;
        }

        Boolean disponible =
                solicitarDisponibilidad(
                        titulo
                );

        if (disponible == null) {
            return;
        }

        try {
            double precioBase =
                    Validador.convertirDecimal(
                            datos[3],
                            "El precio base"
                    );

            int duracionHoras =
                    Validador.convertirEntero(
                            datos[4],
                            "La duracion en horas"
                    );

            int capacidadMaxima =
                    Validador.convertirEntero(
                            datos[5],
                            "La capacidad maxima"
                    );

            int cantidadParadas =
                    Validador.convertirEntero(
                            datos[7],
                            "La cantidad de paradas"
                    );

            double costoDegustacion =
                    Validador.convertirDecimal(
                            datos[8],
                            "El costo de degustacion por persona"
                    );

            RutaGastronomica ruta =
                    new RutaGastronomica(
                            datos[0],
                            datos[1],
                            datos[2],
                            precioBase,
                            duracionHoras,
                            capacidadMaxima,
                            disponible,
                            datos[6],
                            cantidadParadas,
                            costoDegustacion
                    );

            gestorServicios.registrarServicio(
                    ruta
            );

            mostrarMensaje(
                    "Registro exitoso",
                    "Ruta gastronomica registrada correctamente.\n\n"
                            + ruta.mostrarResumen()
            );

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    /**
     * Registra un paseo lacustre.
     */
    private void registrarPaseoLacustre() {

        String titulo = "Registrar paseo lacustre";

        String[] datos =
                solicitarDatos(
                        titulo,
                        "Ingrese el codigo del servicio:",
                        "Ingrese el nombre:",
                        "Ingrese la descripcion:",
                        "Ingrese el precio base:",
                        "Ingrese la duracion en horas:",
                        "Ingrese la capacidad maxima:",
                        "Ingrese el nombre de la embarcacion:",
                        "Ingrese el sector de navegacion:",
                        "Ingrese el costo de embarcacion por persona:"
                );

        if (datos == null) {
            return;
        }

        Boolean disponible =
                solicitarDisponibilidad(
                        titulo
                );

        if (disponible == null) {
            return;
        }

        Boolean incluyeChaleco =
                solicitarConfirmacion(
                        titulo,
                        "¿El paseo incluye chaleco salvavidas?"
                );

        if (incluyeChaleco == null) {
            return;
        }

        try {
            double precioBase =
                    Validador.convertirDecimal(
                            datos[3],
                            "El precio base"
                    );

            int duracionHoras =
                    Validador.convertirEntero(
                            datos[4],
                            "La duracion en horas"
                    );

            int capacidadMaxima =
                    Validador.convertirEntero(
                            datos[5],
                            "La capacidad maxima"
                    );

            double costoEmbarcacion =
                    Validador.convertirDecimal(
                            datos[8],
                            "El costo de embarcacion por persona"
                    );

            PaseoLacustre paseo =
                    new PaseoLacustre(
                            datos[0],
                            datos[1],
                            datos[2],
                            precioBase,
                            duracionHoras,
                            capacidadMaxima,
                            disponible,
                            datos[6],
                            datos[7],
                            costoEmbarcacion,
                            incluyeChaleco
                    );

            gestorServicios.registrarServicio(
                    paseo
            );

            mostrarMensaje(
                    "Registro exitoso",
                    "Paseo lacustre registrado correctamente.\n\n"
                            + paseo.mostrarResumen()
            );

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    /**
     * Registra una reserva.
     */
    private void registrarReserva() {

        String titulo = "Registrar reserva";

        String[] datos =
                solicitarDatos(
                        titulo,
                        "Ingrese el codigo de la reserva:",
                        "Ingrese el codigo del cliente:",
                        "Ingrese el codigo del servicio:",
                        "Ingrese el codigo del guia:",
                        "Ingrese la patente del vehiculo:",
                        "Ingrese la fecha en formato DD-MM-AAAA:",
                        "Ingrese la cantidad de personas:"
                );

        if (datos == null) {
            return;
        }

        try {
            Registrable entidadCliente =
                    gestorEntidades.buscarPorIdentificador(
                            datos[1]
                    );

            if (!(entidadCliente instanceof Cliente)) {
                throw new DatoInvalidoException(
                        "No existe un cliente con el codigo "
                                + datos[1] + "."
                );
            }

            ServicioTuristico servicio =
                    gestorServicios.buscarPorCodigo(
                            datos[2]
                    );

            if (servicio == null) {
                throw new DatoInvalidoException(
                        "No existe un servicio con el codigo "
                                + datos[2] + "."
                );
            }

            Registrable entidadGuia =
                    gestorEntidades.buscarPorIdentificador(
                            datos[3]
                    );

            if (!(entidadGuia instanceof GuiaTuristico)) {
                throw new DatoInvalidoException(
                        "No existe un guia con el codigo "
                                + datos[3] + "."
                );
            }

            Registrable entidadVehiculo =
                    gestorEntidades.buscarPorIdentificador(
                            datos[4]
                    );

            if (!(entidadVehiculo instanceof Vehiculo)) {
                throw new DatoInvalidoException(
                        "No existe un vehiculo con la patente "
                                + datos[4] + "."
                );
            }

            int cantidadPersonas =
                    Validador.convertirEntero(
                            datos[6],
                            "La cantidad de personas"
                    );

            Cliente cliente =
                    (Cliente) entidadCliente;

            GuiaTuristico guia =
                    (GuiaTuristico) entidadGuia;

            Vehiculo vehiculo =
                    (Vehiculo) entidadVehiculo;

            Reserva reserva =
                    new Reserva(
                            datos[0],
                            cliente,
                            servicio,
                            guia,
                            vehiculo,
                            datos[5],
                            cantidadPersonas
                    );

            gestorReservas.registrarReserva(
                    reserva
            );

            mostrarMensaje(
                    "Registro exitoso",
                    "Reserva registrada correctamente.\n\n"
                            + reserva.toString()
            );

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException
                 | CupoInsuficienteException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    /**
     * Muestra los datos necesarios para registrar una reserva.
     */
    private void mostrarDatosDisponiblesReserva() {

        String resumen =
                "=== ENTIDADES ===\n"
                        + gestorEntidades.generarResumenEntidades()
                        + "\n\n=== SERVICIOS ===\n"
                        + gestorServicios.generarResumenServicios();

        mostrarMensaje(
                "Datos disponibles para reservas",
                resumen
        );
    }

    /**
     * Muestra todas las entidades.
     */
    private void mostrarTodasLasEntidades() {

        String resumen =
                gestorEntidades.generarResumenEntidades();

        if (resumen == null || resumen.isBlank()) {
            resumen = "No existen entidades registradas.";
        }

        mostrarMensaje(
                "Entidades registradas",
                resumen
        );
    }

    /**
     * Busca una entidad por identificador.
     */
    private void buscarEntidadPorIdentificador() {

        String identificador =
                solicitarDato(
                        "Ingrese el codigo o patente:",
                        "Buscar entidad"
                );

        if (identificador == null) {
            return;
        }

        if (identificador.isEmpty()) {

            mostrarError(
                    "Debe ingresar un codigo o patente."
            );

            return;
        }

        Registrable entidad =
                gestorEntidades.buscarPorIdentificador(
                        identificador
                );

        if (entidad == null) {

            mostrarMensaje(
                    "Resultado de busqueda",
                    "No se encontro una entidad con el identificador "
                            + identificador + "."
            );

            return;
        }

        mostrarMensaje(
                "Entidad encontrada",
                entidad.mostrarResumen()
        );
    }

    /**
     * Busca una persona por RUT.
     */
    private void buscarPersonaPorRut() {

        String rut =
                solicitarDato(
                        "Ingrese el RUT de la persona:",
                        "Buscar persona"
                );

        if (rut == null) {
            return;
        }

        try {
            Persona persona =
                    gestorEntidades.buscarPersonaPorRut(
                            rut
                    );

            if (persona == null) {

                mostrarMensaje(
                        "Resultado de busqueda",
                        "No se encontro una persona con el RUT indicado."
                );

                return;
            }

            mostrarMensaje(
                    "Persona encontrada",
                    persona.toString()
            );

        } catch (DatoInvalidoException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    /**
     * Filtra entidades por tipo.
     */
    private void filtrarEntidadesPorTipo() {

        String[] opciones = {
                "Clientes",
                "Guias turisticos",
                "Colaboradores externos",
                "Vehiculos",
                "Cancelar"
        };

        int opcion =
                mostrarMenuVertical(
                        "Filtrar entidades",
                        "Seleccione el tipo de entidad:",
                        opciones
                );

        String tipo;
        String titulo;

        switch (opcion) {

            case 0:
                tipo = "cliente";
                titulo = "Clientes registrados";
                break;

            case 1:
                tipo = "guia";
                titulo = "Guias turisticos registrados";
                break;

            case 2:
                tipo = "colaborador";
                titulo = "Colaboradores externos registrados";
                break;

            case 3:
                tipo = "vehiculo";
                titulo = "Vehiculos registrados";
                break;

            case 4:
            case JOptionPane.CLOSED_OPTION:
                return;

            default:
                return;
        }

        try {
            List<Registrable> entidades =
                    gestorEntidades.filtrarPorTipo(
                            tipo
                    );

            mostrarEntidadesFiltradas(
                    entidades,
                    titulo
            );

        } catch (DatoInvalidoException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    /**
     * Muestra entidades filtradas.
     */
    private void mostrarEntidadesFiltradas(
            List<Registrable> entidades,
            String titulo
    ) {

        if (entidades.isEmpty()) {

            mostrarMensaje(
                    titulo,
                    "No se encontraron registros."
            );

            return;
        }

        StringBuilder resumen =
                new StringBuilder();

        for (Registrable entidad : entidades) {
            resumen.append(
                    entidad.mostrarResumen()
            ).append("\n");
        }

        mostrarMensaje(
                titulo,
                resumen.toString()
        );
    }

    /**
     * Muestra todos los servicios.
     */
    private void mostrarTodosLosServicios() {

        String resumen =
                gestorServicios.generarResumenServicios();

        if (resumen == null || resumen.isBlank()) {
            resumen = "No existen servicios turisticos registrados.";
        }

        mostrarMensaje(
                "Servicios turisticos",
                resumen
        );
    }

    /**
     * Busca un servicio por codigo.
     */
    private void buscarServicioPorCodigo() {

        String codigo =
                solicitarDato(
                        "Ingrese el codigo del servicio:",
                        "Buscar servicio"
                );

        if (codigo == null) {
            return;
        }

        if (codigo.isEmpty()) {

            mostrarError(
                    "Debe ingresar el codigo del servicio."
            );

            return;
        }

        ServicioTuristico servicio =
                gestorServicios.buscarPorCodigo(
                        codigo
                );

        if (servicio == null) {

            mostrarMensaje(
                    "Resultado de busqueda",
                    "No se encontro un servicio con el codigo "
                            + codigo + "."
            );

            return;
        }

        mostrarMensaje(
                "Servicio encontrado",
                servicio.mostrarResumen()
        );
    }

    /**
     * Filtra servicios por tipo.
     */
    private void filtrarServiciosPorTipo() {

        String[] opciones = {
                "Rutas gastronomicas",
                "Paseos lacustres",
                "Cancelar"
        };

        int opcion =
                mostrarMenuVertical(
                        "Filtrar servicios",
                        "Seleccione el tipo de servicio:",
                        opciones
                );

        String tipo;
        String titulo;

        switch (opcion) {

            case 0:
                tipo = "ruta";
                titulo = "Rutas gastronomicas";
                break;

            case 1:
                tipo = "paseo";
                titulo = "Paseos lacustres";
                break;

            case 2:
            case JOptionPane.CLOSED_OPTION:
                return;

            default:
                return;
        }

        try {
            List<ServicioTuristico> servicios =
                    gestorServicios.filtrarPorTipo(
                            tipo
                    );

            mostrarServiciosFiltrados(
                    servicios,
                    titulo
            );

        } catch (DatoInvalidoException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    /**
     * Muestra servicios filtrados.
     */
    private void mostrarServiciosFiltrados(
            List<ServicioTuristico> servicios,
            String titulo
    ) {

        if (servicios.isEmpty()) {

            mostrarMensaje(
                    titulo,
                    "No se encontraron servicios."
            );

            return;
        }

        StringBuilder resumen =
                new StringBuilder();

        for (ServicioTuristico servicio : servicios) {
            resumen.append(
                    servicio.mostrarResumen()
            ).append("\n");
        }

        mostrarMensaje(
                titulo,
                resumen.toString()
        );
    }

    /**
     * Muestra todas las reservas.
     */
    private void mostrarTodasLasReservas() {

        mostrarMensaje(
                "Reservas registradas",
                gestorReservas.generarResumenReservas()
        );
    }

    /**
     * Busca una reserva por codigo.
     */
    private void buscarReservaPorCodigo() {

        String codigo =
                solicitarDato(
                        "Ingrese el codigo de la reserva:",
                        "Buscar reserva"
                );

        if (codigo == null) {
            return;
        }

        if (codigo.isEmpty()) {

            mostrarError(
                    "Debe ingresar el codigo de la reserva."
            );

            return;
        }

        Reserva reserva =
                gestorReservas.buscarPorCodigo(
                        codigo
                );

        if (reserva == null) {

            mostrarMensaje(
                    "Resultado de busqueda",
                    "No se encontro una reserva con el codigo "
                            + codigo + "."
            );

            return;
        }

        mostrarMensaje(
                "Reserva encontrada",
                reserva.toString()
        );
    }

    /**
     * Busca reservas por RUT del cliente.
     */
    private void buscarReservasPorRut() {

        String rut =
                solicitarDato(
                        "Ingrese el RUT del cliente:",
                        "Buscar reservas"
                );

        if (rut == null) {
            return;
        }

        if (rut.isEmpty()) {

            mostrarError(
                    "Debe ingresar el RUT del cliente."
            );

            return;
        }

        List<Reserva> reservas =
                gestorReservas.buscarPorRutCliente(
                        rut
                );

        mostrarListaReservas(
                reservas,
                "Reservas del cliente"
        );
    }

    /**
     * Busca reservas por fecha.
     */
    private void buscarReservasPorFecha() {

        String fecha =
                solicitarDato(
                        "Ingrese la fecha en formato DD-MM-AAAA:",
                        "Buscar reservas"
                );

        if (fecha == null) {
            return;
        }

        if (!fecha.matches("\\d{2}-\\d{2}-\\d{4}")) {

            mostrarError(
                    "La fecha debe tener el formato DD-MM-AAAA."
            );

            return;
        }

        List<Reserva> reservas =
                gestorReservas.buscarPorFecha(
                        fecha
                );

        mostrarListaReservas(
                reservas,
                "Reservas de la fecha " + fecha
        );
    }

    /**
     * Muestra una lista de reservas.
     */
    private void mostrarListaReservas(
            List<Reserva> reservas,
            String titulo
    ) {

        if (reservas.isEmpty()) {

            mostrarMensaje(
                    titulo,
                    "No se encontraron reservas."
            );

            return;
        }

        StringBuilder resumen =
                new StringBuilder();

        for (Reserva reserva : reservas) {
            resumen.append(
                    reserva.mostrarResumen()
            ).append("\n");
        }

        mostrarMensaje(
                titulo,
                resumen.toString()
        );
    }

    /**
     * Muestra los ingresos totales.
     */
    private void mostrarIngresosTotales() {

        double ingresos =
                gestorReservas.calcularIngresosTotales();

        mostrarMensaje(
                "Ingresos totales",
                "Cantidad de reservas: "
                        + gestorReservas.obtenerCantidadReservas()
                        + "\nIngresos acumulados: $"
                        + obtenerMontoSinDecimales(
                        ingresos
                )
        );
    }

    /**
     * Convierte un monto a texto sin decimales.
     */
    private String obtenerMontoSinDecimales(
            double monto
    ) {
        return String.valueOf(
                Math.round(monto)
        );
    }

    /**
     * Solicita varios datos.
     */
    private String[] solicitarDatos(
            String titulo,
            String... mensajes
    ) {

        String[] datos =
                new String[mensajes.length];

        for (int i = 0; i < mensajes.length; i++) {

            datos[i] =
                    solicitarDato(
                            mensajes[i],
                            titulo
                    );

            if (datos[i] == null) {
                return null;
            }
        }

        return datos;
    }

    /**
     * Solicita un dato.
     */
    private String solicitarDato(
            String mensaje,
            String titulo
    ) {

        String valor =
                JOptionPane.showInputDialog(
                        null,
                        mensaje,
                        titulo,
                        JOptionPane.QUESTION_MESSAGE
                );

        if (valor == null) {
            return null;
        }

        return valor.trim();
    }

    /**
     * Solicita disponibilidad.
     */
    private Boolean solicitarDisponibilidad(
            String titulo
    ) {

        String[] opciones = {
                "Disponible",
                "No disponible",
                "Cancelar"
        };

        int opcion =
                mostrarMenuVertical(
                        titulo,
                        "Seleccione la disponibilidad:",
                        opciones
                );

        switch (opcion) {

            case 0:
                return true;

            case 1:
                return false;

            case 2:
            case JOptionPane.CLOSED_OPTION:
                return null;

            default:
                return null;
        }
    }

    /**
     * Solicita una confirmacion.
     */
    private Boolean solicitarConfirmacion(
            String titulo,
            String mensaje
    ) {

        String[] opciones = {
                "Si",
                "No",
                "Cancelar"
        };

        int opcion =
                mostrarMenuVertical(
                        titulo,
                        mensaje,
                        opciones
                );

        switch (opcion) {

            case 0:
                return true;

            case 1:
                return false;

            case 2:
            case JOptionPane.CLOSED_OPTION:
                return null;

            default:
                return null;
        }
    }

    /**
     * Muestra un menu con botones verticales.
     */
    private int mostrarMenuVertical(
            String titulo,
            String mensaje,
            String[] opciones
    ) {

        JPanel panelPrincipal =
                new JPanel(
                        new BorderLayout(
                                0,
                                10
                        )
                );

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        JLabel etiqueta =
                new JLabel(
                        mensaje
                );

        JPanel panelBotones =
                new JPanel(
                        new GridLayout(
                                0,
                                1,
                                0,
                                8
                        )
                );

        panelPrincipal.add(
                etiqueta,
                BorderLayout.NORTH
        );

        panelPrincipal.add(
                panelBotones,
                BorderLayout.CENTER
        );

        JDialog dialogo =
                new JDialog();

        dialogo.setTitle(
                titulo
        );

        dialogo.setModal(
                true
        );

        dialogo.setDefaultCloseOperation(
                JDialog.DISPOSE_ON_CLOSE
        );

        dialogo.setContentPane(
                panelPrincipal
        );

        int[] opcionSeleccionada = {
                JOptionPane.CLOSED_OPTION
        };

        for (int i = 0; i < opciones.length; i++) {

            final int indice = i;

            JButton boton =
                    new JButton(
                            opciones[i]
                    );

            boton.addActionListener(
                    evento -> {

                        opcionSeleccionada[0] =
                                indice;

                        dialogo.dispose();
                    }
            );

            panelBotones.add(
                    boton
            );
        }

        dialogo.setResizable(
                false
        );

        dialogo.pack();

        dialogo.setLocationRelativeTo(
                null
        );

        dialogo.setVisible(
                true
        );

        return opcionSeleccionada[0];
    }

    /**
     * Muestra un mensaje informativo.
     */
    private void mostrarMensaje(
            String titulo,
            String mensaje
    ) {

        JOptionPane.showMessageDialog(
                null,
                mensaje,
                titulo,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Muestra un mensaje de error.
     */
    private void mostrarError(
            String mensaje
    ) {

        JOptionPane.showMessageDialog(
                null,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}