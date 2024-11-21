package proyectof.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaDAO {

    // Método para insertar una nueva persona y actualizar la cuadrilla si es necesario
public void insertarPersona(Persona persona) throws SQLException {
    String sqlPersona = "INSERT INTO personas (Nombre, Edad, Correo, Contraseña, IdRol, IdCuadrilla) VALUES (?, ?, ?, ?, ?, ?)";
    String sqlCuadrilla = "UPDATE cuadrillas SET IdPersona = ? WHERE IdCuadrilla = ?";  // Para actualizar la cuadrilla con el nuevo IdPersona
    Connection conexion = null;
    PreparedStatement statementPersona = null;
    PreparedStatement statementCuadrilla = null;

    try {
        conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
        statementPersona = conexion.prepareStatement(sqlPersona, PreparedStatement.RETURN_GENERATED_KEYS);
        
        statementPersona.setString(1, persona.getNombre());
        statementPersona.setInt(2, persona.getEdad());
        statementPersona.setString(3, persona.getCorreo());
        statementPersona.setString(4, persona.getContraseña());
        statementPersona.setInt(5, persona.getRol());
        statementPersona.setObject(6, persona.getIdCuadrilla()); // Usamos setObject para permitir null

        // Ejecutar la inserción de la persona
        statementPersona.executeUpdate();

        // Obtener el ID generado
        ResultSet generatedKeys = statementPersona.getGeneratedKeys();
        if (generatedKeys.next()) {
            persona.setId(generatedKeys.getInt(1)); // Establece el ID en el objeto Persona
        }

        System.out.println("Persona insertada exitosamente: " + persona.getNombre());

        // Si el IdCuadrilla no es null y el rol es 2, actualizar la tabla cuadrillas
        if (persona.getIdCuadrilla() != null && persona.getRol() == 2) {
            statementCuadrilla = conexion.prepareStatement(sqlCuadrilla);
            statementCuadrilla.setInt(1, persona.getId()); // Establecer el nuevo IdPersona
            statementCuadrilla.setInt(2, persona.getIdCuadrilla()); // Filtrar por IdCuadrilla
            statementCuadrilla.executeUpdate();
            System.out.println("Cuadrilla actualizada exitosamente.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error al insertar persona en la base de datos", e);  // Lanzar la excepción
    } finally {
        // No cerrar la conexión aquí, ya que se utilizará en otro lugar
        if (statementPersona != null) {
            try {
                statementPersona.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statementCuadrilla != null) {
            try {
                statementCuadrilla.close();
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
                resultSet.getInt("IdRol"),
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



    // Método para actualizar una persona y la cuadrilla correspondiente (solo si el rol es 2)
public void actualizarPersona(Persona persona) {
    String sqlPersona = "UPDATE personas SET Nombre = ?, Edad = ?, Correo = ?, Contraseña = ?, IdRol = ?, IdCuadrilla = ? WHERE IdPersona = ?";
    String sqlCuadrilla = "UPDATE cuadrillas SET IdPersona = ? WHERE IdCuadrilla = ?";  // Para actualizar la cuadrilla con el nuevo IdPersona
    Connection conexion = null;
    PreparedStatement statementPersona = null;
    PreparedStatement statementCuadrilla = null;

    try {
        conexion = ConexionBD.conectar();

        // Actualizar la persona
        statementPersona = conexion.prepareStatement(sqlPersona);
        statementPersona.setString(1, persona.getNombre());
        statementPersona.setInt(2, persona.getEdad());
        statementPersona.setString(3, persona.getCorreo());
        statementPersona.setString(4, persona.getContraseña());
        statementPersona.setInt(5, persona.getRol());
        statementPersona.setObject(6, persona.getIdCuadrilla()); // Usamos setObject para permitir null
        statementPersona.setInt(7, persona.getId()); // Usa el ID aquí
        statementPersona.executeUpdate();
        System.out.println("Persona actualizada exitosamente.");

        // Si el rol es 2 y la persona tiene un IdCuadrilla, actualizar la cuadrilla con el nuevo IdPersona
        if (persona.getRol() == 2 && persona.getIdCuadrilla() != null) {
            statementCuadrilla = conexion.prepareStatement(sqlCuadrilla);
            statementCuadrilla.setInt(1, persona.getId()); // Establecer el nuevo IdPersona
            statementCuadrilla.setInt(2, persona.getIdCuadrilla()); // Filtrar por IdCuadrilla
            statementCuadrilla.executeUpdate();
            System.out.println("Cuadrilla actualizada exitosamente.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // No cerrar la conexión aquí, ya que se utilizará en otro lugar
        if (statementPersona != null) {
            try {
                statementPersona.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statementCuadrilla != null) {
            try {
                statementCuadrilla.close();
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
