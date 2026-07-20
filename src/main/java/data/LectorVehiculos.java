package data;

import exception.DatoInvalidoException;
import model.Vehiculo;
import util.Validador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Lee datos de vehiculos desde un archivo de texto
 * y los convierte en objetos del sistema.
 *
 * @author Cristofer Medel
 */
public class LectorVehiculos {

    /**
     * Carga vehiculos desde un archivo ubicado
     * dentro de los recursos del proyecto.
     *
     * @param rutaRecurso ruta del archivo dentro de resources
     * @return coleccion con los vehiculos cargados
     * @throws IOException si el archivo no existe o no puede leerse
     * @throws DatoInvalidoException si una linea contiene datos invalidos
     */
    public ArrayList<Vehiculo> cargarVehiculos(
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
                    "No se encontró el archivo: "
                            + rutaRecurso
            );
        }

        ArrayList<Vehiculo> vehiculos =
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
                    Vehiculo vehiculo =
                            convertirLineaAVehiculo(
                                    lineaLimpia
                            );

                    vehiculos.add(vehiculo);

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

        return vehiculos;
    }

    /**
     * Convierte una linea separada por punto y coma
     * en un objeto Vehiculo.
     *
     * @param linea linea que se desea convertir
     * @return vehiculo creado con los datos de la linea
     * @throws DatoInvalidoException si faltan campos
     * o alguno contiene un dato invalido
     */
    private Vehiculo convertirLineaAVehiculo(
            String linea
    ) throws DatoInvalidoException {

        String[] datos = linea.split(";", -1);

        if (datos.length != 6) {
            throw new DatoInvalidoException(
                    "Se esperaban 6 campos, pero se encontraron "
                            + datos.length + "."
            );
        }

        int capacidadPasajeros =
                Validador.convertirEntero(
                        datos[4],
                        "La capacidad de pasajeros"
                );

        boolean disponible =
                Validador.convertirBooleano(
                        datos[5],
                        "La disponibilidad"
                );

        return new Vehiculo(
                datos[0],
                datos[1],
                datos[2],
                datos[3],
                capacidadPasajeros,
                disponible
        );
    }
}