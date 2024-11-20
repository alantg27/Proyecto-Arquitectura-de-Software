package proyectof.Conn;

import proyectof.Model.ConexionBD;
import proyectof.View.*;

public class AdminConn {
    
    private AdminView vista;

    public AdminConn(AdminView vista) {
        this.vista = vista;
    }
    
    // Método para salir de la aplicación
    public void salir(AdminView vista) {
        ConexionBD.cerrarConexion();
        vista.dispose();
    System.exit(0);  // Termina la aplicación o cierra la ventana
    }
    
    // Método para abrir la ventana de ActividadView
    public void abrirActividadView() {
        vista.dispose();
        ActividadView actividadView = new ActividadView();
        actividadView.setVisible(true);  // Hacer visible la ventana de Actividad
    }

    // Método para abrir la ventana de PersonaView
    public void abrirPersonaView() {
        vista.dispose();
        PersonaView personaView = new PersonaView();
        personaView.setVisible(true);  // Hacer visible la ventana de Persona
    }

    // Método para abrir la ventana de CuadrillaView
    public void abrirCuadrillaView() {
        vista.dispose();
        CuadrillaView cuadrillaView = new CuadrillaView();
        cuadrillaView.setVisible(true);  // Hacer visible la ventana de Cuadrilla
    }

    // Método para abrir la ventana de ColoniaView
    public void abrirColoniaView() {
        vista.dispose();
        ColoniaView coloniaView = new ColoniaView();
        coloniaView.setVisible(true);  // Hacer visible la ventana de Colonia
    }
}
