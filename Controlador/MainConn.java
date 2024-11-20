package proyectof.Conn;

import java.sql.Connection;
import proyectof.Model.ConexionBD;
import proyectof.View.*;

public class MainConn {
    public void salir(MainView vista) {
        // Cerrar la conexión
        ConexionBD.cerrarConexion();
        vista.dispose();
    System.exit(0);  // Termina la aplicación o cierra la ventana
}
    
    public void iniciar(MainView vista) {
        
    // Abrir la conexión a la base de datos
        Connection conexion = ConexionBD.conectar();
        
    vista.dispose(); // Cierra la ventana actual (LoginView, si es que se pasa como parámetro)

    // Crear una nueva instancia de LoginView
    LoginView loginView = new LoginView();  
    loginView.setVisible(true);  // Hace visible la nueva ventana
}
}
