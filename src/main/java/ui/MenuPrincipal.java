package ui;

import data.CargadorDatosIniciales;
import exception.DatoInvalidoException;
import exception.RegistroDuplicadoException;
import service.GestorEntidades;
import service.GestorReservas;
import service.GestorServicios;

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
                    mostrarModuloPendiente(
                            "Gestion de entidades"
                    );
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
}