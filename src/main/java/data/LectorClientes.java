package data;

import exception.DatoInvalidoException;
import model.Cliente;
import model.Direccion;
import util.Validador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Lee datos de clientes desde un archivo de texto
 * y los convierte en objetos del sistema.
 *
 * @author Cristofer Medel
 */
public class LectorClientes {

    /**
     * Carga clientes desde un archivo ubicado
     * dentro de los recursos del proyecto.
     *
     * @param rutaRecurso ruta del archivo dentro de resources
     * @return coleccion con los clientes cargados
     * @throws IOException si el archivo no existe o no puede leerse
     * @throws DatoInvalidoException si una linea contiene datos invalidos
     */
    public ArrayList<Cliente> cargarClientes(
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

        ArrayList<Cliente> clientes =
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
                    Cliente cliente =
                            convertirLineaACliente(
                                    lineaLimpia
                            );

                    clientes.add(cliente);

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

        return clientes;
    }

    /**
     * Convierte una linea separada por punto y coma
     * en un objeto Cliente.
     *
     * @param linea linea que se desea convertir
     * @return cliente creado con los datos de la linea
     * @throws DatoInvalidoException si faltan campos
     * o alguno contiene un dato invalido
     */
    private Cliente convertirLineaACliente(
            String linea
    ) throws DatoInvalidoException {

        String[] datos = linea.split(";", -1);

        if (datos.length != 11) {
            throw new DatoInvalidoException(
                    "Se esperaban 11 campos, pero se encontraron "
                            + datos.length + "."
            );
        }

        int numeroDireccion =
                Validador.convertirEntero(
                        datos[7],
                        "El numero de la dirección"
                );

        Direccion direccion = new Direccion(
                datos[6],
                numeroDireccion,
                datos[8],
                datos[9]
        );

        return new Cliente(
                datos[1],
                datos[2],
                datos[3],
                datos[4],
                datos[5],
                direccion,
                datos[0],
                datos[10]
        );
    }
}