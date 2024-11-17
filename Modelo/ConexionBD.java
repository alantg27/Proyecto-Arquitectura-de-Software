package proyectof.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/limpieza";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    // Variable para almacenar la única instancia de la conexión
    private static Connection conexion;

    // Constructor privado para evitar instanciación externa
    private ConexionBD() {}

    // Método para obtener la instancia de la conexión
    public static Connection conectar() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos.");
                e.printStackTrace();
            }
        }
        return conexion;
    }
    
    // Método opcional para cerrar la conexión
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
}
