package proyectof.Conn;
import proyectof.Model.ConexionBD;
import proyectof.View.*;
public class LimpiezaConn {
    private LimpiezaView vista;

    public LimpiezaConn(LimpiezaView vista) {
        this.vista = vista;
    }

    // Método para salir de la aplicación
    public void salir(LimpiezaView vista) {
        ConexionBD.cerrarConexion();
        vista.dispose();
    System.exit(0);  // Termina la aplicación o cierra la ventana
    }

    // Método para abrir la ventana de ActividadView
    public void abrirActividadView() {
        vista.dispose();
        ActividadView actividadView = new ActividadView();
        actividadView.setVisible(true);  // Hacer visible la ventana
    }
}
