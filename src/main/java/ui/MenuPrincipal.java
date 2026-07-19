package ui;

import data.CargadorDatosIniciales;
import exception.DatoInvalidoException;
import exception.RegistroDuplicadoException;
import service.GestorEntidades;
import service.GestorReservas;
import service.GestorServicios;
import interfaces.Registrable;
import model.Persona;
import java.util.List;
import javax.swing.JOptionPane;
import java.io.IOException;

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
     * Crea el menu principal y sus gestores.
     */
    public MenuPrincipal() {
        gestorEntidades = new GestorEntidades();
        gestorServicios = new GestorServicios();
        gestorReservas = new GestorReservas();
    }

    /**
     * Carga los datos iniciales y muestra el menu principal.
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
     * Muestra las opciones principales hasta que
     * el usuario decida cerrar el sistema.
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

            int opcionSeleccionada =
                    JOptionPane.showOptionDialog(
                            null,
                            "Seleccione una opcion:",
                            "Llanquihue Tour",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opciones,
                            opciones[0]
                    );

            switch (opcionSeleccionada) {

                case 0:
                    mostrarResumenGeneral();
                    break;

                case 1:
                    mostrarMenuEntidades();
                    break;

                case 2:
                    mostrarModuloPendiente(
                            "Gestion de servicios"
                    );
                    break;

                case 3:
                    mostrarModuloPendiente(
                            "Gestion de reservas"
                    );
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

        JOptionPane.showMessageDialog(
                null,
                "El sistema se ha cerrado correctamente.",
                "Llanquihue Tour",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Muestra los datos almacenados en los tres gestores.
     */
    private void mostrarResumenGeneral() {

        String resumen =
                "=== ENTIDADES REGISTRADAS ===\n"
                        + gestorEntidades
                        .generarResumenEntidades()
                        + "\n\n=== SERVICIOS TURISTICOS ===\n"
                        + gestorServicios
                        .generarResumenServicios()
                        + "\n\n=== RESERVAS ===\n"
                        + gestorReservas
                        .generarResumenReservas();

        JOptionPane.showMessageDialog(
                null,
                resumen,
                "Resumen general",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Muestra las opciones disponibles para consultar
     * las entidades registradas en el sistema.
     */
    private void mostrarMenuEntidades() {

        String[] opciones = {
                "Listar todas",
                "Buscar por identificador",
                "Buscar persona por RUT",
                "Filtrar por tipo",
                "Volver"
        };

        boolean continuar = true;

        while (continuar) {

            int opcionSeleccionada =
                    JOptionPane.showOptionDialog(
                            null,
                            "Seleccione una opcion:",
                            "Gestion de entidades",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opciones,
                            opciones[0]
                    );

            switch (opcionSeleccionada) {

                case 0:
                    mostrarTodasLasEntidades();
                    break;

                case 1:
                    buscarEntidadPorIdentificador();
                    break;

                case 2:
                    buscarPersonaPorRut();
                    break;

                case 3:
                    filtrarEntidadesPorTipo();
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
    }

    /**
     * Muestra todas las entidades registradas.
     */
    private void mostrarTodasLasEntidades() {

        String resumen =
                gestorEntidades.generarResumenEntidades();

        if (resumen == null || resumen.isBlank()) {
            resumen = "No existen entidades registradas.";
        }

        JOptionPane.showMessageDialog(
                null,
                resumen,
                "Entidades registradas",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Busca una persona registrada mediante su RUT.
     */
    private void buscarPersonaPorRut() {

        String rut =
                JOptionPane.showInputDialog(
                        null,
                        "Ingrese el RUT de la persona:",
                        "Buscar persona",
                        JOptionPane.QUESTION_MESSAGE
                );

        if (rut == null) {
            return;
        }

        try {

            Persona personaEncontrada =
                    gestorEntidades.buscarPersonaPorRut(
                            rut
                    );

            if (personaEncontrada == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "No se encontro una persona con el RUT indicado.",
                        "Resultado de busqueda",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            JOptionPane.showMessageDialog(
                    null,
                    personaEncontrada.toString(),
                    "Persona encontrada",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (DatoInvalidoException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    /**
     * Permite seleccionar un tipo de entidad
     * y muestra los registros correspondientes.
     */
    private void filtrarEntidadesPorTipo() {

        String[] tipos = {
                "Clientes",
                "Guias turisticos",
                "Colaboradores externos",
                "Vehiculos",
                "Cancelar"
        };

        int opcionSeleccionada =
                JOptionPane.showOptionDialog(
                        null,
                        "Seleccione el tipo de entidad:",
                        "Filtrar entidades",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        tipos,
                        tipos[0]
                );

        String tipoBuscado;
        String titulo;

        switch (opcionSeleccionada) {

            case 0:
                tipoBuscado = "cliente";
                titulo = "Clientes registrados";
                break;

            case 1:
                tipoBuscado = "guia";
                titulo = "Guias turisticos registrados";
                break;

            case 2:
                tipoBuscado = "colaborador";
                titulo = "Colaboradores externos registrados";
                break;

            case 3:
                tipoBuscado = "vehiculo";
                titulo = "Vehiculos registrados";
                break;

            case 4:
            case JOptionPane.CLOSED_OPTION:
                return;

            default:
                return;
        }

        try {

            List<Registrable> entidadesFiltradas =
                    gestorEntidades.filtrarPorTipo(
                            tipoBuscado
                    );

            mostrarEntidadesFiltradas(
                    entidadesFiltradas,
                    titulo
            );

        } catch (DatoInvalidoException e) {

            mostrarError(
                    e.getMessage()
            );
        }
    }

    /**
     * Muestra una coleccion de entidades filtradas.
     *
     * @param entidades entidades que se desean mostrar
     * @param titulo titulo de la ventana
     */
    private void mostrarEntidadesFiltradas(
            List<Registrable> entidades,
            String titulo
    ) {

        if (entidades.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "No se encontraron registros.",
                    titulo,
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        StringBuilder resumen =
                new StringBuilder();

        for (Registrable entidad : entidades) {

            resumen.append(
                    entidad.mostrarResumen()
            );

            resumen.append("\n");
        }

        JOptionPane.showMessageDialog(
                null,
                resumen.toString(),
                titulo,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Informa que un modulo sera implementado
     * en los siguientes pasos.
     *
     * @param nombreModulo nombre del modulo seleccionado
     */
    private void mostrarModuloPendiente(
            String nombreModulo
    ) {

        JOptionPane.showMessageDialog(
                null,
                nombreModulo
                        + "\n\nEl modulo sera habilitado proximamente.",
                "Llanquihue Tour",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Muestra un mensaje de error.
     *
     * @param mensaje informacion del error
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

    /**
     * Busca una entidad mediante su codigo o patente.
     */
    private void buscarEntidadPorIdentificador() {

        String identificador =
                JOptionPane.showInputDialog(
                        null,
                        "Ingrese el codigo o patente:",
                        "Buscar entidad",
                        JOptionPane.QUESTION_MESSAGE
                );

        if (identificador == null) {
            return;
        }

        identificador = identificador.trim();

        if (identificador.isEmpty()) {
            mostrarError(
                    "Debe ingresar un codigo o patente."
            );
            return;
        }

        Registrable entidadEncontrada =
                gestorEntidades.buscarPorIdentificador(
                        identificador
                );

        if (entidadEncontrada == null) {

            JOptionPane.showMessageDialog(
                    null,
                    "No se encontro una entidad con el identificador "
                            + identificador + ".",
                    "Resultado de busqueda",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        JOptionPane.showMessageDialog(
                null,
                entidadEncontrada.mostrarResumen(),
                "Entidad encontrada",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

}