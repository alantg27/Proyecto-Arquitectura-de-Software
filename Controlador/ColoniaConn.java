package proyectof.Conn;
import proyectof.View.*;
import proyectof.Model.*;
import javax.swing.*;

public class ColoniaConn {

    private ColoniaDAO dao;

    public ColoniaConn() {
        this.dao = new ColoniaDAO();
    }

    public void consultar(ColoniaView vista) {
        try {
            int id = Integer.parseInt(vista.getTXT_ID().getText());
            Colonia colonia = dao.obtenerColonia(id);
            if (colonia != null) {
                vista.getTXT_Nombre().setText(colonia.getNombre());
                vista.getTXT_Codigo().setText(colonia.getCodigoPostal());
                JOptionPane.showMessageDialog(vista, "Consulta realizada con éxito.");
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró la colonia con ID: " + id);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "ID inválido.");
        }
    }

    public void actualizar(ColoniaView vista) {
        try {
            int id = Integer.parseInt(vista.getTXT_ID().getText());
            String nombre = vista.getTXT_Nombre().getText();
            String codigoPostal = vista.getTXT_Codigo().getText();

            Colonia colonia = new Colonia(id, nombre, codigoPostal);
            dao.actualizarColonia(colonia);

            JOptionPane.showMessageDialog(vista, "Colonia actualizada correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "ID inválido.");
        }
    }

    public void eliminar(ColoniaView vista) {
        try {
            int id = Integer.parseInt(vista.getTXT_ID().getText());
            dao.eliminarColonia(id);
            JOptionPane.showMessageDialog(vista, "Colonia eliminada correctamente.");
            vista.getTXT_ID().setText("");
            vista.getTXT_Nombre().setText("");
            vista.getTXT_Codigo().setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "ID inválido.");
        }
    }
    
    public void insertar(ColoniaView vista) {
    // Obtenemos los valores de los campos de texto de la vista
    String nombre = vista.getTXT_Nombre().getText();
    String codigoPostal = vista.getTXT_Codigo().getText();

    // Validamos los campos
    if (nombre.isEmpty() || codigoPostal.isEmpty()) {
        JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Creamos un nuevo objeto Colonia
    Colonia nuevaColonia = new Colonia(nombre, codigoPostal);

    try {
        // Insertamos la colonia en la base de datos
        dao.insertarColonia(nuevaColonia);

        // Mostramos un mensaje de éxito
        JOptionPane.showMessageDialog(vista, "Colonia insertada con éxito. ID generado: " + nuevaColonia.getId());
        
        // Limpiamos los campos de texto
        vista.getTXT_ID().setText("");
        vista.getTXT_Nombre().setText("");
        vista.getTXT_Codigo().setText("");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(vista, "Error al insertar la colonia: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}
