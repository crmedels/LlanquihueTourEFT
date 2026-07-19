package data;

import exception.DatoInvalidoException;
import model.Direccion;
import model.GuiaTuristico;
import util.Validador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Lee datos de guias turisticos desde un archivo de texto
 * y los convierte en objetos del sistema.
 *
 * @author Cristofer Medel
 */
public class LectorGuias {

    /**
     * Carga guias turisticos desde un archivo ubicado
     * dentro de los recursos del proyecto.
     *
     * @param rutaRecurso ruta del archivo dentro de resources
     * @return coleccion con los guias cargados
     * @throws IOException si el archivo no existe o no puede leerse
     * @throws DatoInvalidoException si una linea contiene datos invalidos
     */
    public ArrayList<GuiaTuristico> cargarGuias(
            String rutaRecurso
    ) throws IOException, DatoInvalidoException {

        Validador.validarTextoObligatorio(
                rutaRecurso,
                "La ruta del archivo"
        );

        InputStream flujoEntrada =
                getClass()
                        .getClassLoader()
                        .getResourceAsStream(rutaRecurso);

        if (flujoEntrada == null) {
            throw new IOException(
                    "No se encontro el archivo: "
                            + rutaRecurso
            );
        }

        ArrayList<GuiaTuristico> guias =
                new ArrayList<>();

        try (BufferedReader lector =
                     new BufferedReader(
                             new InputStreamReader(
                                     flujoEntrada,
                                     StandardCharsets.UTF_8
                             )
                     )) {

            String linea;
            int numeroLinea = 0;

            while ((linea = lector.readLine()) != null) {
                numeroLinea++;

                String lineaLimpia = linea.trim();

                if (lineaLimpia.isEmpty()
                        || lineaLimpia.startsWith("#")) {

                    continue;
                }

                try {
                    GuiaTuristico guia =
                            convertirLineaAGuia(
                                    lineaLimpia
                            );

                    guias.add(guia);

                } catch (DatoInvalidoException e) {
                    throw new DatoInvalidoException(
                            "Error en la linea "
                                    + numeroLinea
                                    + " de "
                                    + rutaRecurso
                                    + ": "
                                    + e.getMessage()
                    );
                }
            }
        }

        return guias;
    }

    /**
     * Convierte una linea separada por punto y coma
     * en un objeto GuiaTuristico.
     *
     * @param linea linea que se desea convertir
     * @return guia turistico creado con los datos de la linea
     * @throws DatoInvalidoException si faltan campos
     * o alguno contiene un dato invalido
     */
    private GuiaTuristico convertirLineaAGuia(
            String linea
    ) throws DatoInvalidoException {

        String[] datos = linea.split(";", -1);

        if (datos.length != 14) {
            throw new DatoInvalidoException(
                    "Se esperaban 14 campos, pero se encontraron "
                            + datos.length + "."
            );
        }

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

        boolean disponible =
                Validador.convertirBooleano(
                        datos[13],
                        "La disponibilidad"
                );

        Direccion direccion = new Direccion(
                datos[6],
                numeroDireccion,
                datos[8],
                datos[9]
        );

        return new GuiaTuristico(
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
    }
}