package data;

import exception.DatoInvalidoException;
import model.PaseoLacustre;
import model.RutaGastronomica;
import model.ServicioTuristico;
import util.Validador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Lee datos de servicios turisticos desde un archivo
 * de texto y los convierte en objetos del sistema.
 *
 * @author Cristofer Medel
 */
public class LectorServicios {

    /**
     * Carga servicios turisticos desde un archivo ubicado
     * dentro de los recursos del proyecto.
     *
     * @param rutaRecurso ruta del archivo dentro de resources
     * @return coleccion con los servicios cargados
     * @throws IOException si el archivo no existe o no puede leerse
     * @throws DatoInvalidoException si una linea contiene datos invalidos
     */
    public ArrayList<ServicioTuristico> cargarServicios(
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

        ArrayList<ServicioTuristico> servicios =
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
                    ServicioTuristico servicio =
                            convertirLineaAServicio(
                                    lineaLimpia
                            );

                    servicios.add(servicio);

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

        return servicios;
    }

    /**
     * Convierte una linea separada por punto y coma
     * en una subclase de ServicioTuristico.
     *
     * @param linea linea que se desea convertir
     * @return servicio turistico creado con los datos
     * @throws DatoInvalidoException si faltan campos,
     * el tipo no existe o algun dato no es valido
     */
    private ServicioTuristico convertirLineaAServicio(
            String linea
    ) throws DatoInvalidoException {

        String[] datos = linea.split(";", -1);

        if (datos.length != 11) {
            throw new DatoInvalidoException(
                    "Se esperaban 11 campos, pero se encontraron "
                            + datos.length + "."
            );
        }

        String tipoServicio =
                datos[0].trim().toUpperCase();

        double precioBase =
                Validador.convertirDecimal(
                        datos[4],
                        "El precio base"
                );

        int duracionHoras =
                Validador.convertirEntero(
                        datos[5],
                        "La duración en horas"
                );

        int capacidadMaxima =
                Validador.convertirEntero(
                        datos[6],
                        "La capacidad maxima"
                );

        boolean disponible =
                Validador.convertirBooleano(
                        datos[7],
                        "La disponibilidad"
                );

        if (tipoServicio.equals("RUTA")) {

            int cantidadParadas =
                    Validador.convertirEntero(
                            datos[9],
                            "La cantidad de paradas"
                    );

            double costoDegustacion =
                    Validador.convertirDecimal(
                            datos[10],
                            "El costo de degustación"
                    );

            return new RutaGastronomica(
                    datos[1],
                    datos[2],
                    datos[3],
                    precioBase,
                    duracionHoras,
                    capacidadMaxima,
                    disponible,
                    datos[8],
                    cantidadParadas,
                    costoDegustacion
            );
        }

        if (tipoServicio.equals("PASEO")) {

            double costoEmbarcacion =
                    Validador.convertirDecimal(
                            datos[10],
                            "El costo de embarcación"
                    );

            return new PaseoLacustre(
                    datos[1],
                    datos[2],
                    datos[3],
                    precioBase,
                    duracionHoras,
                    capacidadMaxima,
                    disponible,
                    datos[8],
                    datos[9],
                    costoEmbarcacion,
                    true
            );
        }

        throw new DatoInvalidoException(
                "El tipo de servicio debe ser RUTA o PASEO."
        );
    }
}
