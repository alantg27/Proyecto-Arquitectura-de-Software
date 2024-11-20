package proyectof.Conn;
import javax.swing.JOptionPane;
import proyectof.Model.ConexionBD;
import proyectof.View.*;
import proyectof.Model.Login; // Asegúrate de tener el modelo adecuado

public class LoginConn {

    public void validarCredenciales(String correo, String contraseña, LoginView vista) {
    Login login = new Login();  // Crear instancia del modelo Login
    Object[] resultado = login.validarCredenciales(correo, contraseña); // Validar las credenciales

    boolean esValido = (boolean) resultado[0];  // El primer valor es si es válido
    String rol = (String) resultado[1];        // El segundo valor es el rol

    if (esValido) {
        // Verificar el rol y abrir la vista correspondiente
        if ("admin".equals(rol)) {
            // Crear e ir a la vista AdminView
            AdminView adminView = new AdminView();
            adminView.setVisible(true);  // Mostrar la vista AdminView
            vista.dispose();  // Cerrar la vista de Login
        } else {
            // Crear e ir a la vista LimpiezaView
            LimpiezaView limpiezaView = new LimpiezaView();
            limpiezaView.setVisible(true);  // Mostrar la vista LimpiezaView
            vista.dispose();  // Cerrar la vista de Login
        }
    } else {
        // Mostrar un mensaje de error si las credenciales son incorrectas
        JOptionPane.showMessageDialog(vista, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    public void salir(LoginView vista) {
        // Cerrar la conexión
        ConexionBD.cerrarConexion();
        vista.dispose();
    System.exit(0);  // Termina la aplicación o cierra la ventana
}


}