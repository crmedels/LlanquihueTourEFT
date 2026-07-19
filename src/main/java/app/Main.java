package app;

import exception.DatoInvalidoException;
import exception.RegistroDuplicadoException;
import interfaces.Registrable;
import model.Cliente;
import model.ColaboradorExterno;
import model.Direccion;
import model.GuiaTuristico;
import model.PaseoLacustre;
import model.RutaGastronomica;
import model.ServicioTuristico;
import model.Vehiculo;
import service.GestorEntidades;
import service.GestorServicios;
import exception.CupoInsuficienteException;
import model.Reserva;
import service.GestorReservas;

/**
 * Punto de entrada principal del sistema Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class Main {

    /**
     * Ejecuta una prueba temporal de integracion
     * entre las entidades, servicios y gestores.
     *
     * @param args argumentos recibidos desde la linea de comandos
     */
    public static void main(String[] args) {

        try {
            probarGestorEntidades();
            probarGestorServicios();
            probarGestorReservas();

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException
                 | CupoInsuficienteException e) {

            System.out.println(
                    "No fue posible ejecutar la prueba: "
                            + e.getMessage()
            );
        }
    }

    /**
     * Prueba el registro, busqueda, filtrado
     * y control de duplicados de las entidades.
     *
     * @throws DatoInvalidoException si algun dato no es valido
     * @throws RegistroDuplicadoException si existe un identificador repetido
     */
    private static void probarGestorEntidades()
            throws DatoInvalidoException,
            RegistroDuplicadoException {

        GestorEntidades gestorEntidades =
                new GestorEntidades();

        Direccion direccionCliente = new Direccion(
                "Los Alerces",
                125,
                "Llanquihue",
                "Puerto Montt"
        );

        Cliente cliente = new Cliente(
                "12.345.678-5",
                "Camila",
                "Soto",
                "912345678",
                "camila.soto@gmail.com",
                direccionCliente,
                "CLI-001",
                "Turismo gastronomico"
        );

        Direccion direccionGuia = new Direccion(
                "Vicente Perez Rosales",
                430,
                "Puerto Varas",
                "Puerto Varas"
        );

        GuiaTuristico guia = new GuiaTuristico(
                "11.111.111-1",
                "Matias",
                "Vargas",
                "923456789",
                "matias.vargas@gmail.com",
                direccionGuia,
                "GUI-001",
                "Rutas culturales",
                8,
                60000,
                true
        );

        Direccion direccionColaborador = new Direccion(
                "Bernardo O Higgins",
                850,
                "Frutillar",
                "Frutillar"
        );

        ColaboradorExterno colaborador =
                new ColaboradorExterno(
                        "22.222.222-2",
                        "Valentina",
                        "Munoz",
                        "934567891",
                        "valentina.munoz@gmail.com",
                        direccionColaborador,
                        "COL-001",
                        "Fotografia turistica",
                        45000,
                        true
                );

        Vehiculo vehiculo = new Vehiculo(
                "ABCD12",
                "Mercedes-Benz",
                "Sprinter",
                "Minibus",
                15,
                true
        );

        gestorEntidades.registrarEntidad(cliente);
        gestorEntidades.registrarEntidad(guia);
        gestorEntidades.registrarEntidad(colaborador);
        gestorEntidades.registrarEntidad(vehiculo);

        System.out.println("=== ENTIDADES REGISTRADAS ===");
        System.out.println(
                gestorEntidades.generarResumenEntidades()
        );

        System.out.println(
                "Cantidad total: "
                        + gestorEntidades.obtenerCantidadEntidades()
        );

        System.out.println(
                "\n=== BUSQUEDA DE ENTIDAD ==="
        );

        Registrable entidadEncontrada =
                gestorEntidades.buscarPorIdentificador(
                        "GUI-001"
                );

        if (entidadEncontrada != null) {
            System.out.println(
                    entidadEncontrada.mostrarResumen()
            );
        } else {
            System.out.println("Entidad no encontrada.");
        }

        System.out.println(
                "\n=== FILTRO DE VEHICULOS ==="
        );

        System.out.println(
                "Vehiculos encontrados: "
                        + gestorEntidades
                        .filtrarPorTipo("vehiculo")
                        .size()
        );

        System.out.println(
                "\n=== PRUEBA DE ENTIDAD DUPLICADA ==="
        );

        try {
            gestorEntidades.registrarEntidad(cliente);

        } catch (RegistroDuplicadoException e) {
            System.out.println(
                    "Duplicado detectado correctamente: "
                            + e.getMessage()
            );
        }
    }

    /**
     * Prueba el registro, busqueda, filtrado
     * y calculo polimorfico de los servicios.
     *
     * @throws DatoInvalidoException si algun dato no es valido
     * @throws RegistroDuplicadoException si existe un codigo repetido
     */
    private static void probarGestorServicios()
            throws DatoInvalidoException,
            RegistroDuplicadoException {

        GestorServicios gestorServicios =
                new GestorServicios();

        RutaGastronomica rutaGastronomica =
                new RutaGastronomica(
                        "SER-001",
                        "Sabores de Llanquihue",
                        "Recorrido por establecimientos de cocina local",
                        25000,
                        4,
                        12,
                        true,
                        "Cocina tradicional",
                        3,
                        8000
                );

        PaseoLacustre paseoLacustre =
                new PaseoLacustre(
                        "SER-002",
                        "Navegacion por el lago",
                        "Paseo turistico por el lago Llanquihue",
                        20000,
                        3,
                        10,
                        true,
                        "Lago Sur",
                        "Lago Llanquihue",
                        10000,
                        true
                );

        gestorServicios.registrarServicio(
                rutaGastronomica
        );

        gestorServicios.registrarServicio(
                paseoLacustre
        );

        System.out.println(
                "\n=== SERVICIOS TURISTICOS ==="
        );

        System.out.println(
                gestorServicios.generarResumenServicios()
        );

        System.out.println(
                "Cantidad total: "
                        + gestorServicios.obtenerCantidadServicios()
        );

        System.out.println(
                "\n=== BUSQUEDA DE SERVICIO ==="
        );

        ServicioTuristico servicioEncontrado =
                gestorServicios.buscarPorCodigo("SER-002");

        if (servicioEncontrado != null) {
            System.out.println(
                    servicioEncontrado.mostrarResumen()
            );
        } else {
            System.out.println("Servicio no encontrado.");
        }

        System.out.println(
                "\n=== CALCULO POLIMORFICO ==="
        );

        double precioRuta =
                gestorServicios.calcularPrecioServicio(
                        "SER-001",
                        4
                );

        double precioPaseo =
                gestorServicios.calcularPrecioServicio(
                        "SER-002",
                        5
                );

        System.out.println(
                "Precio ruta para 4 personas: $"
                        + precioRuta
        );

        System.out.println(
                "Precio paseo para 5 personas: $"
                        + precioPaseo
        );

        System.out.println(
                "\n=== FILTRO DE SERVICIOS ==="
        );

        System.out.println(
                "Rutas encontradas: "
                        + gestorServicios
                        .filtrarPorTipo("ruta")
                        .size()
        );

        System.out.println(
                "Paseos encontrados: "
                        + gestorServicios
                        .filtrarPorTipo("paseo")
                        .size()
        );

        System.out.println(
                "\n=== PRUEBA DE SERVICIO DUPLICADO ==="
        );

        try {
            gestorServicios.registrarServicio(
                    rutaGastronomica
            );

        } catch (RegistroDuplicadoException e) {
            System.out.println(
                    "Duplicado detectado correctamente: "
                            + e.getMessage()
            );
        }
    }

    /**
     * Prueba el registro, busqueda, calculo de ingresos
     * y control de cupos de las reservas.
     *
     * @throws DatoInvalidoException si algun dato no es valido
     * @throws RegistroDuplicadoException si existe un codigo repetido
     * @throws CupoInsuficienteException si no existen cupos suficientes
     */
    private static void probarGestorReservas()
            throws DatoInvalidoException,
            RegistroDuplicadoException,
            CupoInsuficienteException {

        GestorReservas gestorReservas =
                new GestorReservas();

        Cliente cliente = new Cliente(
                "12.345.678-5",
                "Camila",
                "Soto",
                "912345678",
                "camila.soto@gmail.com",
                new Direccion(
                        "Los Alerces",
                        125,
                        "Llanquihue",
                        "Puerto Montt"
                ),
                "CLI-101",
                "Paseos lacustres"
        );

        GuiaTuristico guiaUno = new GuiaTuristico(
                "11.111.111-1",
                "Matias",
                "Vargas",
                "923456789",
                "matias.vargas@gmail.com",
                new Direccion(
                        "Vicente Perez Rosales",
                        430,
                        "Puerto Varas",
                        "Puerto Varas"
                ),
                "GUI-101",
                "Turismo lacustre",
                8,
                60000,
                true
        );

        GuiaTuristico guiaDos = new GuiaTuristico(
                "22.222.222-2",
                "Valentina",
                "Munoz",
                "934567891",
                "valentina.munoz@gmail.com",
                new Direccion(
                        "Bernardo O Higgins",
                        850,
                        "Frutillar",
                        "Frutillar"
                ),
                "GUI-102",
                "Turismo regional",
                5,
                55000,
                true
        );

        Vehiculo vehiculoUno = new Vehiculo(
                "ABCD12",
                "Mercedes-Benz",
                "Sprinter",
                "Minibus",
                15,
                true
        );

        Vehiculo vehiculoDos = new Vehiculo(
                "EFGH34",
                "Hyundai",
                "County",
                "Minibus",
                12,
                true
        );

        PaseoLacustre paseo = new PaseoLacustre(
                "SER-101",
                "Navegacion por el lago",
                "Paseo turistico por el lago Llanquihue",
                20000,
                3,
                10,
                true,
                "Lago Sur",
                "Lago Llanquihue",
                10000,
                true
        );

        Reserva reservaUno = new Reserva(
                "RES-101",
                cliente,
                paseo,
                guiaUno,
                vehiculoUno,
                "25-07-2026",
                6
        );

        Reserva reservaDos = new Reserva(
                "RES-102",
                cliente,
                paseo,
                guiaDos,
                vehiculoDos,
                "25-07-2026",
                4
        );

        gestorReservas.registrarReserva(reservaUno);
        gestorReservas.registrarReserva(reservaDos);

        System.out.println(
                "\n=== RESERVAS REGISTRADAS ==="
        );

        System.out.println(
                gestorReservas.generarResumenReservas()
        );

        System.out.println(
                "Cantidad total de reservas: "
                        + gestorReservas.obtenerCantidadReservas()
        );

        System.out.println(
                "Personas reservadas para SER-101: "
                        + gestorReservas
                        .calcularPersonasReservadas(
                                "SER-101",
                                "25-07-2026"
                        )
        );

        System.out.println(
                "Ingresos totales: $"
                        + gestorReservas.calcularIngresosTotales()
        );

        System.out.println(
                "\n=== BUSQUEDA DE RESERVA ==="
        );

        Reserva reservaEncontrada =
                gestorReservas.buscarPorCodigo("RES-102");

        if (reservaEncontrada != null) {
            System.out.println(
                    reservaEncontrada.mostrarResumen()
            );
        } else {
            System.out.println("Reserva no encontrada.");
        }

        System.out.println(
                "\n=== PRUEBA DE SOBREVENTA ==="
        );

        Reserva reservaSinCupo = new Reserva(
                "RES-103",
                cliente,
                paseo,
                guiaUno,
                vehiculoUno,
                "25-07-2026",
                1
        );

        try {
            gestorReservas.registrarReserva(
                    reservaSinCupo
            );

        } catch (CupoInsuficienteException e) {
            System.out.println(
                    "Sobreventa evitada correctamente: "
                            + e.getMessage()
            );
        }

        System.out.println(
                "\n=== PRUEBA DE RESERVA DUPLICADA ==="
        );

        try {
            gestorReservas.registrarReserva(
                    reservaUno
            );

        } catch (RegistroDuplicadoException e) {
            System.out.println(
                    "Duplicado detectado correctamente: "
                            + e.getMessage()
            );
        }
    }

}