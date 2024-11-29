package CONTROL;

/**
 * Clase para validar los datos de la persona.
 */
public class Validacion {

    /**
     * Valida que el ID sea un número positivo.
     * @param id ID en formato de cadena.
     * @return true si el ID es un número entero positivo, false en caso contrario.
     */
    public static boolean validarId(String id) {
        try {
            int value = Integer.parseInt(id);
            return value > 0;
        } catch (NumberFormatException e) {
            System.out.println("Error: ID debe ser un número entero positivo.");
            return false;
        }
    }

    /**
     * Valida que la edad esté en un rango razonable.
     * @param edad Edad en formato de cadena.
     * @return true si la edad es un número entero entre 0 y 150, false en caso contrario.
     */
    public static boolean validarEdad(String edad) {
        try {
            int value = Integer.parseInt(edad);
            return value >= 0 && value <= 150;
        } catch (NumberFormatException e) {
            System.out.println("Error: Edad debe ser un número entero.");
            return false;
        }
    }

    /**
     * Valida que el peso sea un número positivo.
     * @param peso Peso en formato de cadena.
     * @return true si el peso es un número positivo, false en caso contrario.
     */
    public static boolean validarPeso(String peso) {
        try {
            double value = Double.parseDouble(peso);
            return value > 0;
        } catch (NumberFormatException e) {
            System.out.println("Error: Peso debe ser un número positivo.");
            return false;
        }
    }

    /**
     * Valida que el nombre no esté vacío y contenga solo letras.
     * @param nombre Nombre de la persona.
     * @return true si el nombre contiene solo letras y no está vacío, false en caso contrario.
     */
    public static boolean validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: Nombre no puede estar vacío.");
            return false;
        }
        if (!nombre.matches("[a-zA-Z\\s]+")) {
            System.out.println("Error: Nombre debe contener solo letras.");
            return false;
        }
        return true;
    }
}
