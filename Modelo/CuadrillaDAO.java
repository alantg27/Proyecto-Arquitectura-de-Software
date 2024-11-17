package proyectof.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuadrillaDAO {

    // Método para insertar una nueva cuadrilla
    public void insertarCuadrilla(Cuadrilla cuadrilla) {
    String obtenerPersonaIdSQL = "SELECT IdPersona FROM personas WHERE Correo = ?";
    String insertarCuadrillaSQL = "INSERT INTO cuadrillas (IdJefe) VALUES (?)";
    Connection conexion = null;
    PreparedStatement obtenerPersonaIdStatement = null;
    PreparedStatement insertarCuadrillaStatement = null;
    ResultSet generatedKeys = null;
    ResultSet personaResultSet = null;

    try {
        conexion = ConexionBD.conectar();

        // Primero obtenemos el IdPersona basado en el correo
        obtenerPersonaIdStatement = conexion.prepareStatement(obtenerPersonaIdSQL);
        obtenerPersonaIdStatement.setString(1, cuadrilla.getPersona().getCorreo());
        personaResultSet = obtenerPersonaIdStatement.executeQuery();

        Integer idPersona = null;
        if (personaResultSet.next()) {
            idPersona = personaResultSet.getInt("IdPersona");
        }

        // Insertamos la cuadrilla con el IdJefe obtenido (null si no se encontró la persona)
        insertarCuadrillaStatement = conexion.prepareStatement(insertarCuadrillaSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        insertarCuadrillaStatement.setObject(1, idPersona);

        insertarCuadrillaStatement.executeUpdate();

        // Obtener el ID generado para la cuadrilla
        generatedKeys = insertarCuadrillaStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            cuadrilla.setId(generatedKeys.getInt(1));
        }

        System.out.println("Cuadrilla insertada exitosamente con ID: " + cuadrilla.getId());

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (generatedKeys != null) {
                generatedKeys.close();
            }
            if (personaResultSet != null) {
                personaResultSet.close();
            }
            if (obtenerPersonaIdStatement != null) {
                obtenerPersonaIdStatement.close();
            }
            if (insertarCuadrillaStatement != null) {
                insertarCuadrillaStatement.close();
            }
            // No se cierra la conexión aquí
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


    // Método para obtener una cuadrilla por su ID
    public Cuadrilla obtenerCuadrilla(int id) {
        String sql = "SELECT * FROM cuadrillas WHERE IdCuadrilla = ?";
        Cuadrilla cuadrilla = null;
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = ConexionBD.conectar();
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Obtener el jefe como una Persona a partir de su ID
                PersonaDAO personaDAO = new PersonaDAO();
                Persona jefe = personaDAO.obtenerPersona(resultSet.getInt("IdJefe"));

                cuadrilla = new Cuadrilla(resultSet.getInt("IdCuadrilla"), jefe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                // No se cierra la conexión aquí
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cuadrilla;
    }

    // Método para actualizar una cuadrilla
    public void actualizarCuadrilla(Cuadrilla cuadrilla) {
        String sql = "UPDATE cuadrillas SET IdJefe = ? WHERE IdCuadrilla = ?";
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = ConexionBD.conectar();
            statement = conexion.prepareStatement(sql);
            
            // Usa el ID del jefe desde el objeto Persona, o null si no hay jefe asignado
            statement.setObject(1, cuadrilla.getPersona() != null ? cuadrilla.getPersona().getId() : null);
            statement.setInt(2, cuadrilla.getId()); // Usa el ID de la cuadrilla

            statement.executeUpdate();
            System.out.println("Cuadrilla actualizada exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                // No se cierra la conexión aquí
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para obtener todas las cuadrillas
    public List<Cuadrilla> obtenerTodasLasCuadrillas() {
        String sql = "SELECT * FROM cuadrillas";
        List<Cuadrilla> cuadrillas = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = ConexionBD.conectar();
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener el jefe como una Persona a partir de su ID
                PersonaDAO personaDAO = new PersonaDAO();
                Persona jefe = personaDAO.obtenerPersona(resultSet.getInt("IdJefe"));

                Cuadrilla cuadrilla = new Cuadrilla(resultSet.getInt("IdCuadrilla"), jefe);
                cuadrillas.add(cuadrilla);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                // No se cierra la conexión aquí
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cuadrillas;
    }
}
