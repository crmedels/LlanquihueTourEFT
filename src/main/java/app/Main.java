package app;

import exception.DatoInvalidoException;
import exception.RegistroDuplicadoException;
import interfaces.Registrable;
import model.Cliente;
import model.ColaboradorExterno;
import model.Direccion;
import model.GuiaTuristico;
import service.GestorEntidades;

/**
 * Punto de entrada principal del sistema Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class Main {

    /**
     * Ejecuta una prueba inicial de integracion
     * entre las entidades y el gestor.
     *
     * @param args argumentos recibidos desde la linea de comandos
     */
    public static void main(String[] args) {

        try {
            GestorEntidades gestor = new GestorEntidades();

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

            gestor.registrarEntidad(cliente);
            gestor.registrarEntidad(guia);
            gestor.registrarEntidad(colaborador);

            System.out.println("=== ENTIDADES REGISTRADAS ===");
            System.out.println(gestor.generarResumenEntidades());

            System.out.println("Cantidad total: "
                    + gestor.obtenerCantidadEntidades());

            System.out.println("\n=== BUSQUEDA POR IDENTIFICADOR ===");

            Registrable entidadEncontrada =
                    gestor.buscarPorIdentificador("GUI-001");

            if (entidadEncontrada != null) {
                System.out.println(
                        entidadEncontrada.mostrarResumen()
                );
            } else {
                System.out.println("Entidad no encontrada.");
            }

            System.out.println("\n=== FILTRO DE GUIAS ===");
            System.out.println(
                    "Guias encontrados: "
                            + gestor.filtrarPorTipo("guia").size()
            );

            System.out.println("\n=== PRUEBA DE DUPLICADO ===");

            try {
                gestor.registrarEntidad(cliente);
            } catch (RegistroDuplicadoException e) {
                System.out.println(
                        "Duplicado detectado correctamente: "
                                + e.getMessage()
                );
            }

        } catch (DatoInvalidoException
                 | RegistroDuplicadoException e) {

            System.out.println(
                    "No fue posible ejecutar la prueba: "
                            + e.getMessage()
            );
        }
    }
}