package ADO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaSQL {

    private final String url = "jdbc:mysql://localhost/TAP";
    private final String usuario = "root";
    private final String password = "";

    private Connection conectar() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, usuario, password);
    }

    // Método para insertar una persona sin considerar el 'id' porque es autoincrementable
    public boolean insertarPersona(Persona persona) {
        String sql = "INSERT INTO persona (nombre, edad, peso) VALUES (?, ?, ?)";
        try (Connection con = conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, persona.getNombre());
            stmt.setInt(2, persona.getEdad());
            stmt.setDouble(3, persona.getPeso());

            int reg = stmt.executeUpdate();
            System.out.println("Se insertó " + reg + " registro(s)");
            return reg > 0;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error en insertarPersona: " + e.getMessage());
        }
        return false;
    }

    // Método para buscar una persona por su ID
    public Persona buscarPersona(int id) {
        String sql = "SELECT * FROM persona WHERE id = ?";
        try (Connection con = conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getDouble("peso")
                );
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error en buscarPersona: " + e.getMessage());
        }
        return null; // Retorna null si no se encuentra la persona
    }

    // Método para modificar los datos de una persona existente
    public boolean modificarPersona(Persona persona) {
        // Primero buscar la persona por el ID
        Persona personaExistente = buscarPersona(persona.getId());
        if (personaExistente != null) {
            // Si existe, se puede proceder con la actualización
            String sql = "UPDATE persona SET nombre = ?, edad = ?, peso = ? WHERE id = ?";
            try (Connection con = conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, persona.getNombre());
                stmt.setInt(2, persona.getEdad());
                stmt.setDouble(3, persona.getPeso());
                stmt.setInt(4, persona.getId());

                int reg = stmt.executeUpdate();
                return reg > 0;

            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Error en modificarPersona: " + e.getMessage());
            }
        } else {
            System.out.println("La persona con ID " + persona.getId() + " no existe.");
        }
        return false;
    }

    // Método para eliminar una persona por su ID
    public boolean eliminarPersona(int id) {
        String sql = "DELETE FROM persona WHERE id = ?";
        try (Connection con = conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int reg = stmt.executeUpdate();
            return reg > 0;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error en eliminarPersona: " + e.getMessage());
        }
        return false;
    }

    // Método para obtener todas las personas de la base de datos
    public List<Persona> buscarTodasPersonas() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM persona";
        try (Connection con = conectar(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                personas.add(new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getDouble("peso")
                ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error en buscarTodasPersonas: " + e.getMessage());
        }
        return personas;
    }

    // Método para obtener una lista de pesos de las personas
    public List<Double> obtenerPesos() {
        List<Double> pesos = new ArrayList<>();
        String sql = "SELECT peso FROM persona";
        try (Connection con = conectar(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                pesos.add(rs.getDouble("peso"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error en obtenerPesos: " + e.getMessage());
        }
        return pesos;
    }
}


