package proyectof.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    //Variable global estática para almacenar el rol
    public static int rol; 

    public Object[] validarCredenciales(String correo, String contraseña) {
        String sql = "SELECT COUNT(*), IdRol FROM personas WHERE correo = ? AND contraseña = ?";
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean esValido = false;

        try {
            // Obtén la conexión desde el Singleton
            conexion = ConexionBD.conectar();
            statement = conexion.prepareStatement(sql);

            // Asigna los valores a los parámetros
            statement.setString(1, correo);
            statement.setString(2, contraseña);

            // Ejecuta la consulta
            resultSet = statement.executeQuery();

            // Procesa el resultado
            if (resultSet.next()) {
                // Verifica si existe un registro
                esValido = resultSet.getInt(1) > 0;

                if (esValido) {
                    // Si es válido, guarda el rol en la variable global
                    rol = resultSet.getInt(2);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cierra el ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Cierra el PreparedStatement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // La conexión NO se cierra aquí (es administrada por el Singleton)
        }

        // Devuelve un array de objetos con la validación y el rol
        return new Object[]{esValido, rol};
    }

    // Método para obtener el rol global
    public static int obtenerRol() {
        return rol;
    }
}
