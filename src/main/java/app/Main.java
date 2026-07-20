package app;

import ui.MenuPrincipal;

import javax.swing.SwingUtilities;

/**
 * Punto de entrada principal del sistema Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class Main {

    /**
     * Inicia la interfaz grafica del sistema.
     *
     * @param args argumentos de ejecución
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menuPrincipal =
                    new MenuPrincipal();

            menuPrincipal.iniciar();
        });
    }
}