package proyectof.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    // Método para insertar una nueva persona
    public void insertarPersona(Persona persona) throws SQLException {
    String sql = "INSERT INTO personas (Nombre, Edad, Correo, Contraseña, Rol, IdCuadrilla) VALUES (?, ?, ?, ?, ?, ?)";
    Connection conexion = null;
    PreparedStatement statement = null;

    try {
        conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
        statement = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        statement.setString(1, persona.getNombre());
        statement.setInt(2, persona.getEdad());
        statement.setString(3, persona.getCorreo());
        statement.setString(4, persona.getContraseña());
        statement.setString(5, persona.getRol());
        statement.setObject(6, persona.getIdCuadrilla()); // Usamos setObject para permitir null

        statement.executeUpdate();

        // Obtener el ID generado
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            persona.setId(generatedKeys.getInt(1)); // Establece el ID en el objeto Persona
        }

        System.out.println("Persona insertada exitosamente: " + persona.getNombre());

    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error al insertar persona en la base de datos", e);  // Lanzar la excepción
    } finally {
        // No cerrar la conexión aquí, ya que se utilizará en otro lugar
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



    public Persona obtenerPersona(int id) {
    String sql = "SELECT * FROM personas WHERE IdPersona = ?";
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Persona persona = null;

    try {
        conexion = ConexionBD.conectar(); // Abrimos la conexión
        statement = conexion.prepareStatement(sql); // Preparamos el statement
        statement.setInt(1, id); // Establecemos el parámetro

        resultSet = statement.executeQuery(); // Ejecutamos la consulta

        if (resultSet.next()) {
            persona = new Persona(
                resultSet.getString("Nombre"),
                resultSet.getInt("Edad"),
                resultSet.getString("Correo"),
                resultSet.getString("Contraseña"),
                resultSet.getString("Rol"),
                resultSet.getObject("IdCuadrilla", Integer.class) // Obtiene el ID de la cuadrilla (puede ser null)
            );
            persona.setId(id); // Establecemos el ID de la persona
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Cierre manual de recursos excepto la conexión
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Nota: No cerramos la conexión aquí
    }

    return persona;
}



    // Método para actualizar una persona
    public void actualizarPersona(Persona persona) {
        String sql = "UPDATE personas SET Nombre = ?, Edad = ?, Correo = ?, Contraseña = ?, Rol = ?, IdCuadrilla = ? WHERE IdPersona = ?";
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = ConexionBD.conectar();
            statement = conexion.prepareStatement(sql);
            
            statement.setString(1, persona.getNombre());
            statement.setInt(2, persona.getEdad());
            statement.setString(3, persona.getCorreo());
            statement.setString(4, persona.getContraseña());
            statement.setString(5, persona.getRol());
            statement.setObject(6, persona.getIdCuadrilla()); // Usamos setObject para permitir null
            statement.setInt(7, persona.getId()); // Usa el ID aquí

            statement.executeUpdate();
            System.out.println("Persona actualizada exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // No cerrar la conexión aquí, ya que se utilizará en otro lugar
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Método para eliminar una persona
    public void eliminarPersona(int id) {
        String sql = "DELETE FROM personas WHERE IdPersona = ?";
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = ConexionBD.conectar();
            statement = conexion.prepareStatement(sql);
            
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Persona eliminada exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // No cerrar la conexión aquí, ya que se utilizará en otro lugar
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void eliminarCuadrilla(Integer idCuadrilla) {
    String sql = "DELETE FROM cuadrillas WHERE IdCuadrilla = ?";
    Connection conexion = null;
    PreparedStatement statement = null;

    try {
        conexion = ConexionBD.conectar();
        statement = conexion.prepareStatement(sql);
        statement.setInt(1, idCuadrilla);
        statement.executeUpdate();
        System.out.println("Cuadrilla eliminada exitosamente.");
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // No cerrar la conexión aquí, ya que se utilizará en otro lugar
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


}
