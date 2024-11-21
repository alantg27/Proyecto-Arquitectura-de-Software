package proyectof.Conn;
import proyectof.Model.Actividad;
import proyectof.Model.ActividadDAO;
import proyectof.View.ActividadView;
import proyectof.Model.Cuadrilla;
import proyectof.Model.Colonia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class ActividadConn {

    public void insertarActividad(String descripcion, String fechaString, String evidencia, String idCuadrillaStr, String idColoniaStr) {
        try {
            // Convierte los valores obtenidos de la vista a los tipos correspondientes
            int idCuadrilla = Integer.parseInt(idCuadrillaStr);
            Integer idColonia = idColoniaStr.isEmpty() ? null : Integer.parseInt(idColoniaStr);

            // Convierte la fecha de String a java.util.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaUtil = sdf.parse(fechaString);

            // Convierte java.util.Date a java.sql.Date
            java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());

            // Crear las instancias de Cuadrilla y Colonia
            Cuadrilla cuadrilla = new Cuadrilla(idCuadrilla);
            Colonia colonia = (idColonia != null) ? new Colonia(idColonia) : null;

            // Crear la actividad
            Actividad actividad = new Actividad(descripcion, fechaSql, evidencia, cuadrilla, colonia);

            // Insertar la actividad en la base de datos usando el DAO
            ActividadDAO actividadDAO = new ActividadDAO();
            actividadDAO.insertarActividad(actividad);

            // Aquí puedes manejar cualquier lógica posterior, como mostrar un mensaje de éxito

        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("Error al parsear la fecha");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.err.println("Error en los campos numéricos");
        }
    }
    
    // Método para consultar la actividad por ID y actualizar la vista
    public void consultarActividad(ActividadView vista) {
        try {
            // Obtener el ID de la actividad desde el campo de texto de la vista
            int id = Integer.parseInt(vista.getTXT_ID().getText());

            // Llamar al DAO para obtener la actividad
            ActividadDAO dao = new ActividadDAO();
            Actividad actividad = dao.obtenerActividad(id);

            // Si la actividad existe, mostrar la información en los campos de la vista
            if (actividad != null) {
                vista.getTXT_Descripcion().setText(actividad.getDescripcion());
                vista.getFormattedFiel_Fecha().setText(actividad.getFecha().toString());
                vista.getTXT_Evidencia().setText(actividad.getEvidencia());
                vista.getTXT_Cuadrilla().setText(String.valueOf(actividad.getCuadrilla().getId()));
    vista.getTXT_Colonia().setText(String.valueOf(actividad.getColonia().getId()));

                JOptionPane.showMessageDialog(vista, "Consulta realizada con éxito.");
            } else {
                // Si la actividad no se encuentra, mostrar mensaje de error
                JOptionPane.showMessageDialog(vista, "No se encontró la actividad con ID: " + id);
            }

        } catch (NumberFormatException e) {
            // Si el ID no es un número válido, mostrar mensaje de error
            JOptionPane.showMessageDialog(vista, "ID inválido.");
        }
    }
    
    public void actualizarActividad(String idStr, String descripcion, String fechaString, String evidencia, String idCuadrillaStr, String idColoniaStr) {
    try {
        // Convierte los valores obtenidos de la vista a los tipos correspondientes
        int id = Integer.parseInt(idStr);  // ID de la actividad
        int idCuadrilla = Integer.parseInt(idCuadrillaStr);
        Integer idColonia = idColoniaStr.isEmpty() ? null : Integer.parseInt(idColoniaStr);

        // Convierte la fecha de String a java.util.Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaUtil = sdf.parse(fechaString);

        // Convierte java.util.Date a java.sql.Date
        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());

        // Crear las instancias de Cuadrilla y Colonia
        Cuadrilla cuadrilla = new Cuadrilla(idCuadrilla);
        Colonia colonia = (idColonia != null) ? new Colonia(idColonia) : null;

        // Crear la actividad con el ID ya presente para la actualización
        Actividad actividad = new Actividad(descripcion, fechaSql, evidencia, cuadrilla, colonia);
        actividad.setId(id);  // Establecer el ID de la actividad

        // Usar el DAO para actualizar la actividad en la base de datos
        ActividadDAO actividadDAO = new ActividadDAO();
        actividadDAO.actualizarActividad(actividad);

        // Aquí puedes manejar cualquier lógica posterior, como mostrar un mensaje de éxito
        System.out.println("Actividad actualizada correctamente");

    } catch (ParseException e) {
        e.printStackTrace();
        System.err.println("Error al parsear la fecha");
    } catch (NumberFormatException e) {
        e.printStackTrace();
        System.err.println("Error en los campos numéricos");
    }
}
public void eliminar(ActividadView vista) {
    try {
        // Obtener el ID de la actividad desde el campo de texto
        int id = Integer.parseInt(vista.getTXT_ID().getText());

        // Llamar al método DAO para eliminar la actividad
        ActividadDAO actividadDAO = new ActividadDAO();
        actividadDAO.eliminarActividad(id);

        // Mostrar un mensaje de éxito
        JOptionPane.showMessageDialog(vista, "Actividad eliminada correctamente.");

        // Limpiar los campos de la vista
        vista.getTXT_ID().setText("");
        vista.getDescripcion().setText("");
        vista.getFecha().setText("");
        vista.getEvidencia().setText("");
        vista.getIdCuadrilla().setText("");
        vista.getIdColonia().setText("");

    } catch (NumberFormatException e) {
        // Si el ID no es válido, mostrar un mensaje de error
        JOptionPane.showMessageDialog(vista, "ID inválido.");
    } catch (Exception e) {
        // Manejo de otros posibles errores
        e.printStackTrace();
        JOptionPane.showMessageDialog(vista, "Ocurrió un error al eliminar la actividad.");
    }
}

}