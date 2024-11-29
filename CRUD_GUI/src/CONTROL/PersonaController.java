package CONTROL;

import ADO.Persona;
import ADO.PersonaSQL;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de control intermedia entre PersonaSQL y la interfaz gráfica (GUI).
 * Gestiona las operaciones CRUD y las validaciones antes de invocar los métodos
 * de PersonaSQL.
 */
public class PersonaController {

    private final PersonaSQL personaSQL;

    public PersonaController() {
        this.personaSQL = new PersonaSQL();
    }

    /**
     * Inserta una nueva persona en la base de datos si pasa la validación.
     *
     * @param persona La persona a insertar.
     * @return true si la persona fue insertada exitosamente, false en caso
     * contrario.
     */
    public boolean insertarPersona(Persona persona) {
        if (validarPersona(persona)) {
            personaSQL.insertarPersona(persona); // Ahora pasa directamente el objeto Persona
            return true;
        }
        System.out.println("Datos de persona inválidos. No se pudo insertar.");
        return false;
    }

    /**
     * Busca una persona por su ID.
     *
     * @param id ID de la persona a buscar.
     * @return Objeto Persona si se encuentra, null si no se encuentra.
     */
    public Persona buscarPersona(int id) {
        if (Validacion.validarId(String.valueOf(id))) {
            return personaSQL.buscarPersona(id); // Retorna el objeto Persona
        }
        System.out.println("ID inválido.");
        return null; // Retorna null si no se encuentra la persona
    }

    /**
     * Actualiza los datos de una persona existente.
     *
     * @param persona La persona con los datos actualizados.
     * @return true si la persona fue actualizada exitosamente, false en caso
     * contrario.
     */
    public boolean actualizarPersona(Persona persona) {
        if (validarPersona(persona)) {
            return personaSQL.modificarPersona(persona); // Pasa el objeto Persona completo
        }
        System.out.println("Datos de persona inválidos. No se pudo actualizar.");
        return false;
    }

    /**
     * Elimina una persona de la base de datos.
     *
     * @param id ID de la persona a eliminar.
     * @return true si la persona fue eliminada exitosamente, false en caso
     * contrario.
     */
    public boolean eliminarPersona(int id) {
        if (Validacion.validarId(String.valueOf(id))) {
            return personaSQL.eliminarPersona(id);
        }
        System.out.println("ID inválido. No se pudo eliminar.");
        return false;
    }

    /**
     * Obtiene todas las personas de la base de datos.
     *
     * @return Lista de objetos Persona.
     */
    public List<Persona> obtenerTodasLasPersonas() {
        return personaSQL.buscarTodasPersonas(); // Devuelve la lista de personas completas
    }

    /**
     * Valida los datos de una persona.
     *
     * @param persona La persona a validar.
     * @return true si todos los datos son válidos, false en caso contrario.
     */
    private boolean validarPersona(Persona persona) {
        return Validacion.validarId(String.valueOf(persona.getId()))
                && Validacion.validarNombre(persona.getNombre())
                && Validacion.validarEdad(String.valueOf(persona.getEdad()))
                && Validacion.validarPeso(String.valueOf(persona.getPeso()));
    }
}

