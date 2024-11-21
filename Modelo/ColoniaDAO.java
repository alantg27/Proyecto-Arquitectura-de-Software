package proyectof.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ColoniaDAO {

    // Método para insertar una nueva colonia
    public void insertarColonia(Colonia colonia) {
        String sql = "INSERT INTO colonias (Nombre, Codigo_postal) VALUES (?, ?)";
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
            statement = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, colonia.getNombre());
            statement.setString(2, colonia.getCodigoPostal());

            statement.executeUpdate();

            // Obtener el ID generado
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                colonia.setId(generatedKeys.getInt(1)); // Establece el ID en el objeto Colonia
            }

            System.out.println("Colonia insertada exitosamente: " + colonia.getNombre());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Aquí podrías cerrar el PreparedStatement si lo deseas
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // No cerramos la conexión aquí ya que queremos mantenerla abierta
        }
    }

    // Método para obtener una colonia por su ID
    public Colonia obtenerColonia(int id) {
        String sql = "SELECT * FROM colonias WHERE IdColonia = ?";
        Colonia colonia = null;
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                colonia = new Colonia(
                    resultSet.getInt("IdColonia"), // Establecer el ID al crear la colonia
                    resultSet.getString("Nombre"),
                    resultSet.getString("Codigo_postal")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerramos el ResultSet y el PreparedStatement, pero no la conexión
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return colonia;
    }

    // Método para actualizar una colonia
    public void actualizarColonia(Colonia colonia) {
        String sql = "UPDATE colonias SET Nombre = ?, Codigo_postal = ? WHERE IdColonia = ?";
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
            statement = conexion.prepareStatement(sql);
            statement.setString(1, colonia.getNombre());
            statement.setString(2, colonia.getCodigoPostal());
            statement.setInt(3, colonia.getId()); // Usa el ID aquí

            statement.executeUpdate();
            System.out.println("Colonia actualizada exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para eliminar una colonia
    public void eliminarColonia(int id) {
        String sql = "DELETE FROM colonias WHERE IdColonia = ?";
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Colonia eliminada exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
