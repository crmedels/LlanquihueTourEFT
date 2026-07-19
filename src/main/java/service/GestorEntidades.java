package service;

import exception.DatoInvalidoException;
import exception.RegistroDuplicadoException;
import interfaces.Registrable;
import model.Cliente;
import model.ColaboradorExterno;
import model.GuiaTuristico;
import model.Vehiculo;
import java.util.ArrayList;
import model.Persona;
import util.Validador;

/**
 * Gestiona las entidades registrables del sistema
 * mediante una coleccion generica.
 *
 * @author Cristofer Medel
 */
public class GestorEntidades {

    private final ArrayList<Registrable> entidades;

    /**
     * Crea un gestor con una coleccion vacia.
     */
    public GestorEntidades() {
        entidades = new ArrayList<>();
    }

    /**
     * Registra una entidad si su identificador no existe.
     *
     * @param entidad entidad que se desea registrar
     * @throws DatoInvalidoException si la entidad es nula
     * @throws RegistroDuplicadoException si el identificador ya existe
     */
    public void registrarEntidad(Registrable entidad)
            throws DatoInvalidoException, RegistroDuplicadoException {

        if (entidad == null) {
            throw new DatoInvalidoException(
                    "La entidad no puede ser nula."
            );
        }

        if (existeIdentificador(entidad.obtenerIdentificador())) {
            throw new RegistroDuplicadoException(
                    "Ya existe una entidad con el identificador "
                            + entidad.obtenerIdentificador() + "."
            );
        }

        if (entidad instanceof Persona) {
            Persona persona = (Persona) entidad;

            if (existeRut(persona.getRut())) {
                throw new RegistroDuplicadoException(
                        "Ya existe una persona registrada con el RUT "
                                + persona.getRut() + "."
                );
            }
        }

        entidades.add(entidad);
    }

    /**
     * Verifica si un identificador ya se encuentra registrado.
     *
     * @param identificador identificador que se desea comprobar
     * @return true si el identificador ya existe
     */
    public boolean existeIdentificador(String identificador) {

        if (identificador == null) {
            return false;
        }

        for (Registrable entidad : entidades) {
            if (entidad.obtenerIdentificador()
                    .equalsIgnoreCase(identificador.trim())) {

                return true;
            }
        }

        return false;
    }

    /**
     * Busca una entidad mediante su identificador.
     *
     * @param identificador identificador buscado
     * @return entidad encontrada o null si no existe
     */
    public Registrable buscarPorIdentificador(String identificador) {

        if (identificador == null) {
            return null;
        }

        for (Registrable entidad : entidades) {
            if (entidad.obtenerIdentificador()
                    .equalsIgnoreCase(identificador.trim())) {

                return entidad;
            }
        }

        return null;
    }

    /**
     * Filtra las entidades segun el tipo solicitado.
     *
     * @param tipo tipo de entidad que se desea filtrar
     * @return coleccion con las entidades coincidentes
     * @throws DatoInvalidoException si el tipo no es reconocido
     */
    public ArrayList<Registrable> filtrarPorTipo(String tipo)
            throws DatoInvalidoException {

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "El tipo de entidad no puede estar vacio."
            );
        }

        ArrayList<Registrable> resultados = new ArrayList<>();
        String tipoNormalizado = tipo.trim().toLowerCase();

        for (Registrable entidad : entidades) {

            if (tipoNormalizado.equals("cliente")
                    && entidad instanceof Cliente) {

                resultados.add(entidad);

            } else if (tipoNormalizado.equals("guia")
                    && entidad instanceof GuiaTuristico) {

                resultados.add(entidad);

            } else if (tipoNormalizado.equals("colaborador")
                    && entidad instanceof ColaboradorExterno) {

                resultados.add(entidad);

            } else if (tipoNormalizado.equals("vehiculo")
                    && entidad instanceof Vehiculo) {

                resultados.add(entidad);
            }
        }

        if (!tipoNormalizado.equals("cliente")
                && !tipoNormalizado.equals("guia")
                && !tipoNormalizado.equals("colaborador")
                && !tipoNormalizado.equals("vehiculo")) {

            throw new DatoInvalidoException(
                    "El tipo debe ser cliente, guia, colaborador o vehiculo."
            );
        }

        return resultados;
    }

    /**
     * Genera un resumen de todas las entidades registradas.
     *
     * @return resumen polimorfico de las entidades
     */
    public String generarResumenEntidades() {

        if (entidades.isEmpty()) {
            return "No existen entidades registradas.";
        }

        StringBuilder resumen = new StringBuilder();

        for (Registrable entidad : entidades) {
            resumen.append(entidad.mostrarResumen())
                    .append("\n");
        }

        return resumen.toString();
    }

    /**
     * Retorna una copia de la coleccion de entidades.
     *
     * @return copia de las entidades registradas
     */
    public ArrayList<Registrable> getEntidades() {
        return new ArrayList<>(entidades);
    }

    /**
     * Retorna la cantidad de entidades registradas.
     *
     * @return cantidad total de entidades
     */
    public int obtenerCantidadEntidades() {
        return entidades.size();
    }

    /**
     * Comprueba si existe una persona registrada con el RUT indicado.
     *
     * @param rut RUT que se desea comprobar
     * @return true si el RUT ya se encuentra registrado
     * @throws DatoInvalidoException si el RUT no es valido
     */
    public boolean existeRut(
            String rut
    ) throws DatoInvalidoException {

        String rutNormalizado =
                Validador.normalizarRut(rut);

        for (Registrable entidad : entidades) {

            if (entidad instanceof Persona) {
                Persona persona = (Persona) entidad;

                if (persona.getRut().equalsIgnoreCase(
                        rutNormalizado
                )) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Busca una persona registrada mediante su RUT.
     *
     * @param rut RUT de la persona buscada
     * @return persona encontrada o null si no existe
     * @throws DatoInvalidoException si el RUT no es valido
     */
    public Persona buscarPersonaPorRut(
            String rut
    ) throws DatoInvalidoException {

        String rutNormalizado =
                Validador.normalizarRut(rut);

        for (Registrable entidad : entidades) {

            if (entidad instanceof Persona) {
                Persona persona = (Persona) entidad;

                if (persona.getRut().equalsIgnoreCase(
                        rutNormalizado
                )) {
                    return persona;
                }
            }
        }

        return null;
    }

}