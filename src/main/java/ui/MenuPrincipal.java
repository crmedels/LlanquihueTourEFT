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
import util.FormateadorMoneda;
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
                            "Seleccione una opción:",
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
                "Registrar guia turístico",
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
                            "Seleccione una opción:",
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
                "Registrar ruta gastronómica",
                "Registrar paseo lacustre",
                "Listar todos",
                "Buscar por código",
                "Filtrar por tipo",
                "Volver"
        };

        boolean continuar = true;

        while (continuar) {

            int opcion =
                    mostrarMenuVertical(
                            "Gestion de servicios",
                            "Seleccione una opción:",
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
                "Buscar por código",
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
                            "Seleccione una opción:",
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
                        + "Total: "
                        + FormateadorMoneda.formatear(
                        gestorReservas.calcularIngresosTotales()
                );

        mostrarMensaje(
                "Resumen general",
                resumen
        );
    }

    /**
     * Registra un cliente validando cada dato
     * inmediatamente despues de ser ingresado.
     */
    private void registrarCliente() {

        String titulo = "Registrar cliente";

        String codigo = solicitarCodigoEntidadNuevo(
                "Ingrese el código del cliente:",
                titulo,
                "CLI-\\d{3,6}",
                "El código del cliente debe tener el formato "
                        + "CLI seguido de tres a seis números.\n"
                        + "Ejemplo: CLI-001."
        );

        if (codigo == null) {
            return;
        }

        String rut = solicitarRutNuevo(titulo);

        if (rut == null) {
            return;
        }

        String nombre = solicitarTextoSinNumeros(
                "Ingrese el nombre:",
                titulo,
                "El nombre"
        );

        if (nombre == null) {
            return;
        }

        String apellido = solicitarTextoSinNumeros(
                "Ingrese el apellido:",
                titulo,
                "El apellido"
        );

        if (apellido == null) {
            return;
        }

        String telefono = solicitarTelefonoValido(titulo);

        if (telefono == null) {
            return;
        }

        String correo = solicitarCorreoValido(titulo);

        if (correo == null) {
            return;
        }

        Direccion direccion = solicitarDireccion(titulo);

        if (direccion == null) {
            return;
        }

        String preferencia = solicitarTextoObligatorio(
                "Ingrese la preferencia turística:",
                titulo,
                "La preferencia turística"
        );

        if (preferencia == null) {
            return;
        }

        try {
            Cliente cliente = new Cliente(
                    rut,
                    nombre,
                    apellido,
                    telefono,
                    correo,
                    direccion,
                    codigo,
                    preferencia
            );

            gestorEntidades.registrarEntidad(cliente);

            mostrarMensaje(
                    "Registro exitoso",
                    "Cliente registrado correctamente.\n\n"
                            + cliente.mostrarResumen()
            );

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException e) {

            mostrarError(e.getMessage());
        }
    }

    /**
     * Registra un guia turistico validando cada dato
     * inmediatamente despues de ser ingresado.
     */
    private void registrarGuiaTuristico() {

        String titulo = "Registrar guia turístico";

        String codigo = solicitarCodigoEntidadNuevo(
                "Ingrese el codigo del guia:",
                titulo,
                "GUI-\\d{3,6}",
                "El código del guia debe tener el formato "
                        + "GUI seguido de tres a seis números.\n"
                        + "Ejemplo: GUI-001."
        );

        if (codigo == null) {
            return;
        }

        String rut = solicitarRutNuevo(titulo);

        if (rut == null) {
            return;
        }

        String nombre = solicitarTextoSinNumeros(
                "Ingrese el nombre:",
                titulo,
                "El nombre"
        );

        if (nombre == null) {
            return;
        }

        String apellido = solicitarTextoSinNumeros(
                "Ingrese el apellido:",
                titulo,
                "El apellido"
        );

        if (apellido == null) {
            return;
        }

        String telefono = solicitarTelefonoValido(titulo);

        if (telefono == null) {
            return;
        }

        String correo = solicitarCorreoValido(titulo);

        if (correo == null) {
            return;
        }

        Direccion direccion = solicitarDireccion(titulo);

        if (direccion == null) {
            return;
        }

        String especialidad = solicitarTextoObligatorio(
                "Ingrese la especialidad:",
                titulo,
                "La especialidad"
        );

        if (especialidad == null) {
            return;
        }

        Integer aniosExperiencia = solicitarEnteroNoNegativo(
                "Ingrese los años de experiencia:",
                titulo,
                "Los años de experiencia"
        );

        if (aniosExperiencia == null) {
            return;
        }

        Double tarifaDiaria = solicitarDecimalPositivo(
                "Ingrese la tarifa diaria:",
                titulo,
                "La tarifa diaria"
        );

        if (tarifaDiaria == null) {
            return;
        }

        Boolean disponible = solicitarDisponibilidad(titulo);

        if (disponible == null) {
            return;
        }

        try {
            GuiaTuristico guia = new GuiaTuristico(
                    rut,
                    nombre,
                    apellido,
                    telefono,
                    correo,
                    direccion,
                    codigo,
                    especialidad,
                    aniosExperiencia,
                    tarifaDiaria,
                    disponible
            );

            gestorEntidades.registrarEntidad(guia);

            mostrarMensaje(
                    "Registro exitoso",
                    "Guia turístico registrado correctamente.\n\n"
                            + guia.mostrarResumen()
            );

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException e) {

            mostrarError(e.getMessage());
        }
    }

    /**
     * Registra un vehiculo validando cada dato
     * inmediatamente despues de ser ingresado.
     */
    private void registrarVehiculo() {

        String titulo = "Registrar vehiculo";

        String patente = solicitarPatenteNueva(titulo);

        if (patente == null) {
            return;
        }

        String marca = solicitarTextoObligatorio(
                "Ingrese la marca:",
                titulo,
                "La marca"
        );

        if (marca == null) {
            return;
        }

        String modelo = solicitarTextoObligatorio(
                "Ingrese el modelo:",
                titulo,
                "El modelo"
        );

        if (modelo == null) {
            return;
        }

        String tipoVehiculo = solicitarTextoObligatorio(
                "Ingrese el tipo de vehiculo:",
                titulo,
                "El tipo de vehiculo"
        );

        if (tipoVehiculo == null) {
            return;
        }

        Integer capacidad = solicitarEnteroPositivo(
                "Ingrese la capacidad de pasajeros:",
                titulo,
                "La capacidad de pasajeros"
        );

        if (capacidad == null) {
            return;
        }

        Boolean disponible = solicitarDisponibilidad(titulo);

        if (disponible == null) {
            return;
        }

        try {
            Vehiculo vehiculo = new Vehiculo(
                    patente,
                    marca,
                    modelo,
                    tipoVehiculo,
                    capacidad,
                    disponible
            );

            gestorEntidades.registrarEntidad(vehiculo);

            mostrarMensaje(
                    "Registro exitoso",
                    "Vehiculo registrado correctamente.\n\n"
                            + vehiculo.mostrarResumen()
            );

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException e) {

            mostrarError(e.getMessage());
        }
    }

    /**
     * Registra un colaborador externo validando cada dato
     * inmediatamente despues de ser ingresado.
     */
    private void registrarColaboradorExterno() {

        String titulo = "Registrar colaborador externo";

        String codigo = solicitarCodigoEntidadNuevo(
                "Ingrese el código del colaborador:",
                titulo,
                "COL-\\d{3,6}",
                "El código del colaborador debe tener el formato "
                        + "COL seguido de tres a seis números.\n"
                        + "Ejemplo: COL-001."
        );

        if (codigo == null) {
            return;
        }

        String rut = solicitarRutNuevo(titulo);

        if (rut == null) {
            return;
        }

        String nombre = solicitarTextoSinNumeros(
                "Ingrese el nombre:",
                titulo,
                "El nombre"
        );

        if (nombre == null) {
            return;
        }

        String apellido = solicitarTextoSinNumeros(
                "Ingrese el apellido:",
                titulo,
                "El apellido"
        );

        if (apellido == null) {
            return;
        }

        String telefono = solicitarTelefonoValido(titulo);

        if (telefono == null) {
            return;
        }

        String correo = solicitarCorreoValido(titulo);

        if (correo == null) {
            return;
        }

        Direccion direccion = solicitarDireccion(titulo);

        if (direccion == null) {
            return;
        }

        String tipoServicio = solicitarTextoObligatorio(
                "Ingrese el tipo de servicio:",
                titulo,
                "El tipo de servicio"
        );

        if (tipoServicio == null) {
            return;
        }

        Double tarifa = solicitarDecimalPositivo(
                "Ingrese la tarifa por servicio:",
                titulo,
                "La tarifa por servicio"
        );

        if (tarifa == null) {
            return;
        }

        Boolean disponible = solicitarDisponibilidad(titulo);

        if (disponible == null) {
            return;
        }

        try {
            ColaboradorExterno colaborador = new ColaboradorExterno(
                    rut,
                    nombre,
                    apellido,
                    telefono,
                    correo,
                    direccion,
                    codigo,
                    tipoServicio,
                    tarifa,
                    disponible
            );

            gestorEntidades.registrarEntidad(colaborador);

            mostrarMensaje(
                    "Registro exitoso",
                    "Colaborador externo registrado correctamente.\n\n"
                            + colaborador.mostrarResumen()
            );

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException e) {

            mostrarError(e.getMessage());
        }
    }

    /**
     * Registra una ruta gastronomica validando cada dato
     * inmediatamente despues de ser ingresado.
     */
    private void registrarRutaGastronomica() {

        String titulo = "Registrar ruta gastronómica";

        String codigo = solicitarCodigoServicioNuevo(titulo);

        if (codigo == null) {
            return;
        }

        String nombre = solicitarTextoObligatorio(
                "Ingrese el nombre:",
                titulo,
                "El nombre del servicio"
        );

        if (nombre == null) {
            return;
        }

        String descripcion = solicitarTextoObligatorio(
                "Ingrese la descripción:",
                titulo,
                "La descripción del servicio"
        );

        if (descripcion == null) {
            return;
        }

        Double precioBase = solicitarDecimalPositivo(
                "Ingrese el precio base:",
                titulo,
                "El precio base"
        );

        if (precioBase == null) {
            return;
        }

        Integer duracionHoras = solicitarEnteroPositivo(
                "Ingrese la duración en horas:",
                titulo,
                "La duración del servicio"
        );

        if (duracionHoras == null) {
            return;
        }

        Integer capacidadMaxima = solicitarEnteroPositivo(
                "Ingrese la capacidad maxima:",
                titulo,
                "La capacidad maxima"
        );

        if (capacidadMaxima == null) {
            return;
        }

        String tipoCocina = solicitarTextoObligatorio(
                "Ingrese el tipo de cocina:",
                titulo,
                "El tipo de cocina"
        );

        if (tipoCocina == null) {
            return;
        }

        Integer cantidadParadas = solicitarEnteroPositivo(
                "Ingrese la cantidad de paradas:",
                titulo,
                "La cantidad de paradas"
        );

        if (cantidadParadas == null) {
            return;
        }

        Double costoDegustacion = solicitarDecimalPositivo(
                "Ingrese el costo de degustación por persona:",
                titulo,
                "El costo de degustación por persona"
        );

        if (costoDegustacion == null) {
            return;
        }

        Boolean disponible = solicitarDisponibilidad(titulo);

        if (disponible == null) {
            return;
        }

        try {
            RutaGastronomica ruta = new RutaGastronomica(
                    codigo,
                    nombre,
                    descripcion,
                    precioBase,
                    duracionHoras,
                    capacidadMaxima,
                    disponible,
                    tipoCocina,
                    cantidadParadas,
                    costoDegustacion
            );

            gestorServicios.registrarServicio(ruta);

            mostrarMensaje(
                    "Registro exitoso",
                    "Ruta gastronómica registrada correctamente.\n\n"
                            + ruta.mostrarResumen()
            );

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException e) {

            mostrarError(e.getMessage());
        }
    }

    /**
     * Registra un paseo lacustre validando cada dato
     * inmediatamente despues de ser ingresado.
     */
    private void registrarPaseoLacustre() {

        String titulo = "Registrar paseo lacustre";

        String codigo = solicitarCodigoServicioNuevo(titulo);

        if (codigo == null) {
            return;
        }

        String nombre = solicitarTextoObligatorio(
                "Ingrese el nombre:",
                titulo,
                "El nombre del servicio"
        );

        if (nombre == null) {
            return;
        }

        String descripcion = solicitarTextoObligatorio(
                "Ingrese la descripción:",
                titulo,
                "La descripción del servicio"
        );

        if (descripcion == null) {
            return;
        }

        Double precioBase = solicitarDecimalPositivo(
                "Ingrese el precio base:",
                titulo,
                "El precio base"
        );

        if (precioBase == null) {
            return;
        }

        Integer duracionHoras = solicitarEnteroPositivo(
                "Ingrese la duración en horas:",
                titulo,
                "La duración del servicio"
        );

        if (duracionHoras == null) {
            return;
        }

        Integer capacidadMaxima = solicitarEnteroPositivo(
                "Ingrese la capacidad maxima:",
                titulo,
                "La capacidad maxima"
        );

        if (capacidadMaxima == null) {
            return;
        }

        String nombreEmbarcacion = solicitarTextoObligatorio(
                "Ingrese el nombre de la embarcación:",
                titulo,
                "El nombre de la embarcación"
        );

        if (nombreEmbarcacion == null) {
            return;
        }

        String sectorNavegacion = solicitarTextoObligatorio(
                "Ingrese el sector de navegación:",
                titulo,
                "El sector de navegación"
        );

        if (sectorNavegacion == null) {
            return;
        }

        Double costoEmbarcacion = solicitarDecimalPositivo(
                "Ingrese el costo de embarcación por persona:",
                titulo,
                "El costo de embarcación por persona"
        );

        if (costoEmbarcacion == null) {
            return;
        }

        Boolean disponible = solicitarDisponibilidad(titulo);

        if (disponible == null) {
            return;
        }

        Boolean incluyeChaleco = solicitarConfirmacion(
                titulo,
                "¿El paseo incluye chaleco salvavidas?"
        );

        if (incluyeChaleco == null) {
            return;
        }

        try {
            PaseoLacustre paseo = new PaseoLacustre(
                    codigo,
                    nombre,
                    descripcion,
                    precioBase,
                    duracionHoras,
                    capacidadMaxima,
                    disponible,
                    nombreEmbarcacion,
                    sectorNavegacion,
                    costoEmbarcacion,
                    incluyeChaleco
            );

            gestorServicios.registrarServicio(paseo);

            mostrarMensaje(
                    "Registro exitoso",
                    "Paseo lacustre registrado correctamente.\n\n"
                            + paseo.mostrarResumen()
            );

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException e) {

            mostrarError(e.getMessage());
        }
    }

    /**
     * Registra una reserva validando cada dato
     * inmediatamente despues de ser ingresado.
     */
    private void registrarReserva() {

        String titulo = "Registrar reserva";

        String codigoReserva;

        while (true) {

            codigoReserva =
                    solicitarDato(
                            "Ingrese el código de la reserva:",
                            titulo
                    );

            if (codigoReserva == null) {
                return;
            }

            codigoReserva =
                    codigoReserva.trim().toUpperCase();

            if (!codigoReserva.matches("RES-\\d{3,6}")) {

                mostrarError(
                        "El código de la reserva debe tener el formato "
                                + "RES seguido de tres a seis números.\n"
                                + "Ejemplo: RES-301."
                );

                continue;
            }

            if (gestorReservas.existeCodigo(codigoReserva)) {

                mostrarError(
                        "Ya existe una reserva con el código "
                                + codigoReserva + "."
                );

                continue;
            }

            break;
        }

        Cliente cliente;

        while (true) {

            String codigoCliente =
                    solicitarDato(
                            "Ingrese el código del cliente:",
                            titulo
                    );

            if (codigoCliente == null) {
                return;
            }

            Registrable entidadCliente =
                    gestorEntidades.buscarPorIdentificador(
                            codigoCliente
                    );

            if (!(entidadCliente instanceof Cliente)) {

                mostrarError(
                        "No existe un cliente con el código "
                                + codigoCliente + "."
                );

                continue;
            }

            cliente = (Cliente) entidadCliente;
            break;
        }

        ServicioTuristico servicio;

        while (true) {

            String codigoServicio =
                    solicitarDato(
                            "Ingrese el código del servicio:",
                            titulo
                    );

            if (codigoServicio == null) {
                return;
            }

            servicio =
                    gestorServicios.buscarPorCodigo(
                            codigoServicio
                    );

            if (servicio == null) {

                mostrarError(
                        "No existe un servicio con el código "
                                + codigoServicio + "."
                );

                continue;
            }

            if (!servicio.isDisponible()) {

                mostrarError(
                        "El servicio seleccionado no esta disponible."
                );

                continue;
            }

            break;
        }

        String fecha;

        while (true) {

            fecha =
                    solicitarDato(
                            "Ingrese la fecha en formato DD-MM-AAAA:",
                            titulo
                    );

            if (fecha == null) {
                return;
            }

            if (!fecha.matches("\\d{2}-\\d{2}-\\d{4}")) {

                mostrarError(
                        "La fecha debe tener el formato DD-MM-AAAA.\n"
                                + "Ejemplo: 25-07-2026."
                );

                continue;
            }

            String[] partesFecha =
                    fecha.split("-");

            int dia =
                    Integer.parseInt(
                            partesFecha[0]
                    );

            int mes =
                    Integer.parseInt(
                            partesFecha[1]
                    );

            if (dia < 1 || dia > 31) {

                mostrarError(
                        "El dia debe estar entre 1 y 31."
                );

                continue;
            }

            if (mes < 1 || mes > 12) {

                mostrarError(
                        "El mes debe estar entre 1 y 12."
                );

                continue;
            }

            break;
        }

        GuiaTuristico guia;

        while (true) {

            String codigoGuia =
                    solicitarDato(
                            "Ingrese el código del guia:",
                            titulo
                    );

            if (codigoGuia == null) {
                return;
            }

            Registrable entidadGuia =
                    gestorEntidades.buscarPorIdentificador(
                            codigoGuia
                    );

            if (!(entidadGuia instanceof GuiaTuristico)) {

                mostrarError(
                        "No existe un guia turístico con el código "
                                + codigoGuia + "."
                );

                continue;
            }

            guia =
                    (GuiaTuristico) entidadGuia;

            if (!guia.isDisponible()) {

                mostrarError(
                        "El guia turístico seleccionado "
                                + "no esta disponible."
                );

                continue;
            }

            break;
        }

        Vehiculo vehiculo;

        while (true) {

            String patente =
                    solicitarDato(
                            "Ingrese la patente del vehiculo:",
                            titulo
                    );

            if (patente == null) {
                return;
            }

            Registrable entidadVehiculo =
                    gestorEntidades.buscarPorIdentificador(
                            patente
                    );

            if (!(entidadVehiculo instanceof Vehiculo)) {

                mostrarError(
                        "No existe un vehiculo con la patente "
                                + patente + "."
                );

                continue;
            }

            vehiculo =
                    (Vehiculo) entidadVehiculo;

            if (!vehiculo.isDisponible()) {

                mostrarError(
                        "El vehiculo seleccionado no esta disponible."
                );

                continue;
            }

            break;
        }

        int cantidadPersonas;

        while (true) {

            String cantidadTexto =
                    solicitarDato(
                            "Ingrese la cantidad de personas:",
                            titulo
                    );

            if (cantidadTexto == null) {
                return;
            }

            try {
                cantidadPersonas =
                        Validador.convertirEntero(
                                cantidadTexto,
                                "La cantidad de personas"
                        );

                Validador.validarEnteroPositivo(
                        cantidadPersonas,
                        "La cantidad de personas"
                );

                if (!vehiculo.tieneCapacidadPara(
                        cantidadPersonas
                )) {

                    mostrarError(
                            "El vehiculo no tiene capacidad suficiente "
                                    + "para " + cantidadPersonas
                                    + " personas."
                    );

                    continue;
                }

                int personasReservadas =
                        gestorReservas
                                .calcularPersonasReservadas(
                                        servicio.getCodigoServicio(),
                                        fecha
                                );

                int cuposDisponibles =
                        servicio.getCapacidadMaxima()
                                - personasReservadas;

                if (cantidadPersonas > cuposDisponibles) {

                    mostrarError(
                            "El servicio solo tiene "
                                    + cuposDisponibles
                                    + " cupos disponibles para la fecha "
                                    + fecha + "."
                    );

                    continue;
                }

                break;

            } catch (DatoInvalidoException e) {

                mostrarError(
                        e.getMessage()
                );
            }
        }

        try {
            Reserva reserva =
                    new Reserva(
                            codigoReserva,
                            cliente,
                            servicio,
                            guia,
                            vehiculo,
                            fecha,
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
                        "Ingrese el código o patente:",
                        "Buscar entidad"
                );

        if (identificador == null) {
            return;
        }

        if (identificador.isEmpty()) {

            mostrarError(
                    "Debe ingresar un código o patente."
            );

            return;
        }

        Registrable entidad =
                gestorEntidades.buscarPorIdentificador(
                        identificador
                );

        if (entidad == null) {

            mostrarMensaje(
                    "Resultado de búsqueda",
                    "No se encontró una entidad con el identificador "
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
                        "Resultado de búsqueda",
                        "No se encontró una persona con el RUT indicado."
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
                "Guias turísticos",
                "Colaboradores externos",
                "Vehículos",
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
                titulo = "Guias turísticos registrados";
                break;

            case 2:
                tipo = "colaborador";
                titulo = "Colaboradores externos registrados";
                break;

            case 3:
                tipo = "vehiculo";
                titulo = "Vehículos registrados";
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
            resumen = "No existen servicios turísticos registrados.";
        }

        mostrarMensaje(
                "Servicios turísticos",
                resumen
        );
    }

    /**
     * Busca un servicio por codigo.
     */
    private void buscarServicioPorCodigo() {

        String codigo =
                solicitarDato(
                        "Ingrese el código del servicio:",
                        "Buscar servicio"
                );

        if (codigo == null) {
            return;
        }

        if (codigo.isEmpty()) {

            mostrarError(
                    "Debe ingresar el código del servicio."
            );

            return;
        }

        ServicioTuristico servicio =
                gestorServicios.buscarPorCodigo(
                        codigo
                );

        if (servicio == null) {

            mostrarMensaje(
                    "Resultado de búsqueda",
                    "No se encontró un servicio con el código "
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
                "Rutas gastronómicas",
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
                titulo = "Rutas gastronómicas";
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
                        "Ingrese el código de la reserva:",
                        "Buscar reserva"
                );

        if (codigo == null) {
            return;
        }

        if (codigo.isEmpty()) {

            mostrarError(
                    "Debe ingresar el código de la reserva."
            );

            return;
        }

        Reserva reserva =
                gestorReservas.buscarPorCodigo(
                        codigo
                );

        if (reserva == null) {

            mostrarMensaje(
                    "Resultado de búsqueda",
                    "No se encontró una reserva con el código "
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
                        + "\nIngresos acumulados: "
                        + FormateadorMoneda.formatear(
                        ingresos
                )
        );
    }


    /**
     * Solicita un codigo de entidad, valida su formato
     * y comprueba que no se encuentre registrado.
     */
    private String solicitarCodigoEntidadNuevo(
            String mensaje,
            String titulo,
            String formato,
            String mensajeFormato
    ) {

        while (true) {

            String codigo = solicitarDato(
                    mensaje,
                    titulo
            );

            if (codigo == null) {
                return null;
            }

            String codigoNormalizado =
                    codigo.trim().toUpperCase();

            if (!codigoNormalizado.matches(formato)) {

                mostrarError(mensajeFormato);
                continue;
            }

            if (gestorEntidades.existeIdentificador(
                    codigoNormalizado
            )) {

                mostrarError(
                        "Ya existe una entidad con el identificador "
                                + codigoNormalizado + "."
                );

                continue;
            }

            return codigoNormalizado;
        }
    }

    /**
     * Solicita un codigo de servicio, valida su formato
     * y comprueba que no se encuentre registrado.
     */
    private String solicitarCodigoServicioNuevo(
            String titulo
    ) {

        while (true) {

            String codigo = solicitarDato(
                    "Ingrese el código del servicio:",
                    titulo
            );

            if (codigo == null) {
                return null;
            }

            String codigoNormalizado =
                    codigo.trim().toUpperCase();

            if (!codigoNormalizado.matches("SER-\\d{3,6}")) {

                mostrarError(
                        "El código del servicio debe tener el formato "
                                + "SER seguido de tres a seis números.\n"
                                + "Ejemplo: SER-001."
                );

                continue;
            }

            if (gestorServicios.existeCodigo(
                    codigoNormalizado
            )) {

                mostrarError(
                        "Ya existe un servicio con el código "
                                + codigoNormalizado + "."
                );

                continue;
            }

            return codigoNormalizado;
        }
    }

    /**
     * Solicita un RUT valido que no se encuentre registrado.
     */
    private String solicitarRutNuevo(
            String titulo
    ) {

        while (true) {

            String rut = solicitarDato(
                    "Ingrese el RUT:",
                    titulo
            );

            if (rut == null) {
                return null;
            }

            try {
                String rutNormalizado =
                        Validador.normalizarRut(rut);

                if (gestorEntidades.existeRut(
                        rutNormalizado
                )) {

                    mostrarError(
                            "Ya existe una persona registrada con el RUT "
                                    + rutNormalizado + "."
                    );

                    continue;
                }

                return rutNormalizado;

            } catch (DatoInvalidoException e) {

                mostrarError(e.getMessage());
            }
        }
    }

    /**
     * Solicita un telefono valido y lo retorna normalizado.
     */
    private String solicitarTelefonoValido(
            String titulo
    ) {

        while (true) {

            String telefono = solicitarDato(
                    "Ingrese el teléfono:",
                    titulo
            );

            if (telefono == null) {
                return null;
            }

            try {
                return Validador.normalizarTelefono(
                        telefono
                );

            } catch (DatoInvalidoException e) {

                mostrarError(e.getMessage());
            }
        }
    }

    /**
     * Solicita un correo electronico valido.
     */
    private String solicitarCorreoValido(
            String titulo
    ) {

        while (true) {

            String correo = solicitarDato(
                    "Ingrese el correo electrónico:",
                    titulo
            );

            if (correo == null) {
                return null;
            }

            try {
                Validador.validarCorreo(correo);
                return correo.trim();

            } catch (DatoInvalidoException e) {

                mostrarError(e.getMessage());
            }
        }
    }

    /**
     * Solicita una patente valida que no se encuentre registrada.
     */
    private String solicitarPatenteNueva(
            String titulo
    ) {

        while (true) {

            String patente = solicitarDato(
                    "Ingrese la patente:",
                    titulo
            );

            if (patente == null) {
                return null;
            }

            try {
                String patenteNormalizada =
                        Validador.normalizarPatente(
                                patente
                        );

                if (gestorEntidades.existeIdentificador(
                        patenteNormalizada
                )) {

                    mostrarError(
                            "Ya existe un vehiculo con la patente "
                                    + patenteNormalizada + "."
                    );

                    continue;
                }

                return patenteNormalizada;

            } catch (DatoInvalidoException e) {

                mostrarError(e.getMessage());
            }
        }
    }

    /**
     * Solicita una direccion y valida cada uno de sus campos.
     */
    private Direccion solicitarDireccion(
            String titulo
    ) {

        String calle = solicitarTextoObligatorio(
                "Ingrese la calle:",
                titulo,
                "La calle"
        );

        if (calle == null) {
            return null;
        }

        Integer numero = solicitarEnteroPositivo(
                "Ingrese el numero de la dirección:",
                titulo,
                "El numero de la dirección"
        );

        if (numero == null) {
            return null;
        }

        String comuna = solicitarTextoSinNumeros(
                "Ingrese la comuna:",
                titulo,
                "La comuna"
        );

        if (comuna == null) {
            return null;
        }

        String ciudad = solicitarTextoSinNumeros(
                "Ingrese la ciudad:",
                titulo,
                "La ciudad"
        );

        if (ciudad == null) {
            return null;
        }

        try {
            return new Direccion(
                    calle,
                    numero,
                    comuna,
                    ciudad
            );

        } catch (DatoInvalidoException e) {

            mostrarError(e.getMessage());
            return null;
        }
    }

    /**
     * Solicita un texto obligatorio.
     */
    private String solicitarTextoObligatorio(
            String mensaje,
            String titulo,
            String nombreCampo
    ) {

        while (true) {

            String valor = solicitarDato(
                    mensaje,
                    titulo
            );

            if (valor == null) {
                return null;
            }

            try {
                Validador.validarTextoObligatorio(
                        valor,
                        nombreCampo
                );

                return valor.trim();

            } catch (DatoInvalidoException e) {

                mostrarError(e.getMessage());
            }
        }
    }

    /**
     * Solicita un texto compuesto solamente por letras y espacios.
     */
    private String solicitarTextoSinNumeros(
            String mensaje,
            String titulo,
            String nombreCampo
    ) {

        while (true) {

            String valor = solicitarDato(
                    mensaje,
                    titulo
            );

            if (valor == null) {
                return null;
            }

            try {
                Validador.validarTextoSinNumeros(
                        valor,
                        nombreCampo
                );

                return valor.trim();

            } catch (DatoInvalidoException e) {

                mostrarError(e.getMessage());
            }
        }
    }

    /**
     * Solicita un numero entero mayor que cero.
     */
    private Integer solicitarEnteroPositivo(
            String mensaje,
            String titulo,
            String nombreCampo
    ) {

        while (true) {

            String valor = solicitarDato(
                    mensaje,
                    titulo
            );

            if (valor == null) {
                return null;
            }

            try {
                int numero = Validador.convertirEntero(
                        valor,
                        nombreCampo
                );

                Validador.validarEnteroPositivo(
                        numero,
                        nombreCampo
                );

                return numero;

            } catch (DatoInvalidoException e) {

                mostrarError(e.getMessage());
            }
        }
    }

    /**
     * Solicita un numero entero igual o mayor que cero.
     */
    private Integer solicitarEnteroNoNegativo(
            String mensaje,
            String titulo,
            String nombreCampo
    ) {

        while (true) {

            String valor = solicitarDato(
                    mensaje,
                    titulo
            );

            if (valor == null) {
                return null;
            }

            try {
                int numero = Validador.convertirEntero(
                        valor,
                        nombreCampo
                );

                Validador.validarEnteroNoNegativo(
                        numero,
                        nombreCampo
                );

                return numero;

            } catch (DatoInvalidoException e) {

                mostrarError(e.getMessage());
            }
        }
    }

    /**
     * Solicita un numero decimal mayor que cero.
     */
    private Double solicitarDecimalPositivo(
            String mensaje,
            String titulo,
            String nombreCampo
    ) {

        while (true) {

            String valor = solicitarDato(
                    mensaje,
                    titulo
            );

            if (valor == null) {
                return null;
            }

            try {
                double numero = Validador.convertirDecimal(
                        valor,
                        nombreCampo
                );

                Validador.validarDecimalPositivo(
                        numero,
                        nombreCampo
                );

                return numero;

            } catch (DatoInvalidoException e) {

                mostrarError(e.getMessage());
            }
        }
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
                        "seleccione la disponibilidad:",
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