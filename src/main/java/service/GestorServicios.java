package service;

import exception.DatoInvalidoException;
import exception.RegistroDuplicadoException;
import model.PaseoLacustre;
import model.RutaGastronomica;
import model.ServicioTuristico;

import java.util.ArrayList;

/**
 * Gestiona los servicios turisticos ofrecidos
 * por Llanquihue Tour.
 *
 * @author Cristofer Medel
 */
public class GestorServicios {

    private final ArrayList<ServicioTuristico> servicios;

    /**
     * Crea un gestor con una coleccion vacia.
     */
    public GestorServicios() {
        servicios = new ArrayList<>();
    }

    /**
     * Registra un servicio si su codigo no existe.
     *
     * @param servicio servicio que se desea registrar
     * @throws DatoInvalidoException si el servicio es nulo
     * @throws RegistroDuplicadoException si el codigo ya existe
     */
    public void registrarServicio(ServicioTuristico servicio)
            throws DatoInvalidoException,
            RegistroDuplicadoException {

        if (servicio == null) {
            throw new DatoInvalidoException(
                    "El servicio turístico no puede ser nulo."
            );
        }

        if (existeCodigo(servicio.getCodigoServicio())) {
            throw new RegistroDuplicadoException(
                    "Ya existe un servicio con el código "
                            + servicio.getCodigoServicio() + "."
            );
        }

        servicios.add(servicio);
    }

    /**
     * Verifica si un codigo ya se encuentra registrado.
     *
     * @param codigo codigo que se desea comprobar
     * @return true si el codigo ya existe
     */
    public boolean existeCodigo(String codigo) {

        if (codigo == null) {
            return false;
        }

        String codigoBuscado = codigo.trim();

        for (ServicioTuristico servicio : servicios) {
            if (servicio.getCodigoServicio()
                    .equalsIgnoreCase(codigoBuscado)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Busca un servicio mediante su codigo.
     *
     * @param codigo codigo del servicio buscado
     * @return servicio encontrado o null si no existe
     */
    public ServicioTuristico buscarPorCodigo(String codigo) {

        if (codigo == null) {
            return null;
        }

        String codigoBuscado = codigo.trim();

        for (ServicioTuristico servicio : servicios) {
            if (servicio.getCodigoServicio()
                    .equalsIgnoreCase(codigoBuscado)) {

                return servicio;
            }
        }

        return null;
    }

    /**
     * Filtra los servicios segun su tipo.
     *
     * @param tipo tipo de servicio que se desea filtrar
     * @return coleccion con los servicios coincidentes
     * @throws DatoInvalidoException si el tipo no es reconocido
     */
    public ArrayList<ServicioTuristico> filtrarPorTipo(String tipo)
            throws DatoInvalidoException {

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "El tipo de servicio no puede estar vacío."
            );
        }

        ArrayList<ServicioTuristico> resultados =
                new ArrayList<>();

        String tipoNormalizado = tipo.trim().toLowerCase();

        for (ServicioTuristico servicio : servicios) {

            if (tipoNormalizado.equals("ruta")
                    && servicio instanceof RutaGastronomica) {

                resultados.add(servicio);

            } else if (tipoNormalizado.equals("paseo")
                    && servicio instanceof PaseoLacustre) {

                resultados.add(servicio);
            }
        }

        if (!tipoNormalizado.equals("ruta")
                && !tipoNormalizado.equals("paseo")) {

            throw new DatoInvalidoException(
                    "El tipo debe ser ruta o paseo."
            );
        }

        return resultados;
    }

    /**
     * Calcula el precio de un servicio segun
     * la cantidad de participantes.
     *
     * @param codigo codigo del servicio
     * @param cantidadPersonas cantidad de participantes
     * @return precio total calculado
     * @throws DatoInvalidoException si el servicio no existe
     * o la cantidad no es valida
     */
    public double calcularPrecioServicio(
            String codigo,
            int cantidadPersonas
    ) throws DatoInvalidoException {

        ServicioTuristico servicio = buscarPorCodigo(codigo);

        if (servicio == null) {
            throw new DatoInvalidoException(
                    "No existe un servicio con el código indicado."
            );
        }

        return servicio.calcularPrecio(cantidadPersonas);
    }

    /**
     * Genera un resumen de todos los servicios registrados.
     *
     * @return resumen polimorfico de los servicios
     */
    public String generarResumenServicios() {

        if (servicios.isEmpty()) {
            return "No existen servicios turísticos registrados.";
        }

        StringBuilder resumen = new StringBuilder();

        for (ServicioTuristico servicio : servicios) {
            resumen.append(servicio.mostrarResumen())
                    .append("\n");
        }

        return resumen.toString();
    }

    /**
     * Retorna una copia de la coleccion de servicios.
     *
     * @return copia de los servicios registrados
     */
    public ArrayList<ServicioTuristico> getServicios() {
        return new ArrayList<>(servicios);
    }

    /**
     * Retorna la cantidad de servicios registrados.
     *
     * @return cantidad total de servicios
     */
    public int obtenerCantidadServicios() {
        return servicios.size();
    }
}