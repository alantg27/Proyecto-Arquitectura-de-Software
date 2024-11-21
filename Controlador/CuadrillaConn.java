package proyectof.Conn;
import proyectof.Model.*;
import proyectof.View.*;
import java.sql.Connection;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class CuadrillaConn {

    private CuadrillaDAO cuadrillaDAO;

    public CuadrillaConn() {
        this.cuadrillaDAO = new CuadrillaDAO();
    }

    // Método para actualizar el combo box de jefes
    public void actualizarJefe(JComboBox<String> CB_Jefe, Connection conexion) {
    // Obtener el ítem seleccionado (puede ser null)
    String selectedItem = (String) CB_Jefe.getSelectedItem();

    // Limpiar el JComboBox
    CB_Jefe.removeAllItems();

    // Verificar si "Asigna jefe a la cuadrilla" ya está presente
    boolean asignaJefeExiste = false;

    // Si había un ítem seleccionado, volver a agregarlo como primera opción
    if (selectedItem != null) {
        CB_Jefe.addItem(selectedItem);
    }

    // Agregar la opción por defecto si no está presente
    for (int i = 0; i < CB_Jefe.getItemCount(); i++) {
        if (CB_Jefe.getItemAt(i) != null && CB_Jefe.getItemAt(i).equals("Asigna jefe a la cuadrilla")) {
            asignaJefeExiste = true;
            break;
        }
    }

    if (!asignaJefeExiste) {
        CB_Jefe.addItem("Asigna jefe a la cuadrilla");
    }

    // Llamar al método DAO para obtener los jefes disponibles
    List<Object[]> jefes = cuadrillaDAO.obtenerJefesDisponibles(conexion);

    for (Object[] jefe : jefes) {
        int id = (int) jefe[0];
        String nombre = (String) jefe[1];
        CB_Jefe.addItem(nombre + " (ID: " + id + ")");
    }
}

    // Método para actualizar el combo box de nuevos miembros
public void actualizarNuevoM(JComboBox<String> CB_NuevoM, Connection conexion) {
    // Obtener el ítem seleccionado (puede ser null)
    String selectedItem = (String) CB_NuevoM.getSelectedItem();

    // Limpiar el JComboBox
    CB_NuevoM.removeAllItems();

    // Si había un ítem seleccionado previamente, volver a agregarlo como primera opción
    if (selectedItem != null) {
        CB_NuevoM.addItem(selectedItem);
    }

    // Verificar si "Asigna nuevo miembro" ya está presente
    boolean asignaNuevoMExiste = false;

    for (int i = 0; i < CB_NuevoM.getItemCount(); i++) {
        if (CB_NuevoM.getItemAt(i) != null && CB_NuevoM.getItemAt(i).equals("Asigna nuevo miembro")) {
            asignaNuevoMExiste = true;
            break;
        }
    }

    // Si no existe "Asigna nuevo miembro", agregarlo al combo box
    if (!asignaNuevoMExiste) {
        CB_NuevoM.addItem("Asigna nuevo miembro");
    }

    // Obtener los datos desde el DAO y cargarlos en el combo box
    List<Object[]> miembros = cuadrillaDAO.obtenerMiembrosDisponibles(conexion);

    for (Object[] miembro : miembros) {
        int id = (int) miembro[0];
        String nombre = (String) miembro[1];
        CB_NuevoM.addItem(nombre + " (ID: " + id + ")");
    }
}

    // Método para cargar los miembros en la tabla TBL_Miembros
    public void cargarMiembros(JTable TBL_Miembros, Connection conexion, String idCuadrilla) {
        List<Object[]> miembros = cuadrillaDAO.obtenerMiembrosPorCuadrilla(idCuadrilla, conexion);

        // Crear el modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Persona");
        model.addColumn("Nombre");
        model.addColumn("Rol");

        for (Object[] miembro : miembros) {
            model.addRow(miembro);
        }

        TBL_Miembros.setModel(model);
    }
    
    // Método para manejar la selección y creación de una nueva cuadrilla
public void crearCuadrillaDesdeJefe(JComboBox<String> CB_Jefe, JTextField TXT_ID) {
    // Obtener el ítem seleccionado del JComboBox CB_Jefe
    String seleccionado = (String) CB_Jefe.getSelectedItem();

    // Comprobar si la opción seleccionada es válida
    if (seleccionado != null && !seleccionado.equals("Asigna jefe a la cuadrilla")) {
        // Extraer el ID de la persona seleccionada (por ejemplo "Juan Pérez (ID: 123)")
        String[] partes = seleccionado.split(" \\(ID: ");
        int idPersona = Integer.parseInt(partes[1].replace(")", "")); // Obtener el ID de la persona

        // Crear un objeto Persona solo con el ID
        Persona jefe = new Persona(idPersona);  // Usar el constructor que solo toma el ID

        // Crear un objeto Cuadrilla con el jefe
        Cuadrilla cuadrilla = new Cuadrilla(jefe);

        cuadrillaDAO.insertarCuadrilla(cuadrilla);

        // Escribir el ID de la cuadrilla recién creada en el campo TXT_ID
        TXT_ID.setText(String.valueOf(cuadrilla.getId())); // Actualizar el campo con el ID de la cuadrilla

        // Mensaje de éxito
        JOptionPane.showMessageDialog(null, "Cuadrilla creada exitosamente con el jefe.");
    } else {
        // Si la opción seleccionada es inválida, mostrar un mensaje de advertencia
        JOptionPane.showMessageDialog(null, "Por favor, selecciona un jefe válido para la cuadrilla.", 
                                      "Selección inválida", JOptionPane.WARNING_MESSAGE);
    }
}

// Método para manejar la actualización de una cuadrilla con un nuevo jefe
public void actualizarCuadrillaDesdeJefe(JComboBox<String> CB_Jefe, JTextField TXT_ID) {
    // Obtener el ítem seleccionado del JComboBox CB_Jefe
    String seleccionadoJefe = (String) CB_Jefe.getSelectedItem();
    
    // Comprobar si la opción seleccionada es válida
    if (seleccionadoJefe != null && !seleccionadoJefe.equals("Asigna jefe a la cuadrilla")) {
        // Extraer el ID de la persona seleccionada (por ejemplo "Juan Pérez (ID: 123)")
        String[] partes = seleccionadoJefe.split(" \\(ID: ");
        int idPersona = Integer.parseInt(partes[1].replace(")", "")); // Obtener el ID de la persona

        // Obtener el ID de la cuadrilla del campo de texto TXT_ID
        String idCuadrillaStr = TXT_ID.getText();
        int idCuadrilla = 0;
        try {
            idCuadrilla = Integer.parseInt(idCuadrillaStr);  // Convertir a entero el ID de cuadrilla
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID de la cuadrilla no es válido.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;  // Si no es válido, terminamos la ejecución
        }

        // Crear un objeto Persona con el ID del jefe
        Persona jefe = new Persona(idPersona);  // Usar el constructor que solo toma el ID

        // Crear un objeto Cuadrilla con el jefe y el ID de la cuadrilla
        Cuadrilla cuadrilla = new Cuadrilla(jefe);
        cuadrilla.setId(idCuadrilla); // Asignar el ID de la cuadrilla

        // Llamar al DAO para actualizar la cuadrilla en la base de datos
        try {
            cuadrillaDAO.actualizarCuadrilla(cuadrilla);

            // Mensaje de éxito
            JOptionPane.showMessageDialog(null, "Cuadrilla actualizada exitosamente con el nuevo jefe.");
        } catch (Exception e) {
            // Manejar errores si ocurre algún problema al actualizar
            JOptionPane.showMessageDialog(null, "Ocurrió un error al actualizar la cuadrilla.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    } else {
        // Si la opción seleccionada es inválida, mostrar un mensaje de advertencia
        JOptionPane.showMessageDialog(null, "Por favor, selecciona un jefe válido para la cuadrilla.", 
                                      "Selección inválida", JOptionPane.WARNING_MESSAGE);
    }
}


    public void consulta(JTextField TXT_ID, JComboBox<String> CB_Jefe) {
    // Obtener el ID de la cuadrilla desde el campo de texto TXT_ID
    String idCuadrillaStr = TXT_ID.getText();
    int idCuadrilla;

    try {
        idCuadrilla = Integer.parseInt(idCuadrillaStr); // Convertir a entero el ID de cuadrilla
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El ID de la cuadrilla no es válido.", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
        return;  // Si no es válido, terminamos la ejecución
    }

    // Consultar el DAO para obtener la cuadrilla con el ID
    Cuadrilla cuadrilla = cuadrillaDAO.obtenerCuadrilla(idCuadrilla);

    // Si la cuadrilla se encuentra
    if (cuadrilla != null) {
        Persona jefe = cuadrilla.getPersona();  // Obtener el jefe (Persona) asociado

        if (jefe != null) {
            // Crear el formato para mostrar en el ComboBox
            String jefeString = jefe.getNombre() + " (ID: " + jefe.getId() + ")";
            
            // Limpiar el ComboBox antes de agregar el jefe actual
            CB_Jefe.removeAllItems();

            // Agregar el jefe al ComboBox
            CB_Jefe.addItem(jefeString);

            // Si es necesario, seleccionar el primer ítem (el jefe recién agregado)
            CB_Jefe.setSelectedIndex(0);

            // Mensaje de éxito
            JOptionPane.showMessageDialog(null, "Jefe de la cuadrilla cargado correctamente.");
        } else {
            // Si no se encuentra el jefe, mostrar mensaje de error
            JOptionPane.showMessageDialog(null, "No se encontró un jefe para esta cuadrilla.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Si no se encuentra la cuadrilla con el ID proporcionado
        JOptionPane.showMessageDialog(null, "No se encontró la cuadrilla con el ID proporcionado.", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    // Método en el controlador para eliminar una cuadrilla
public void eliminarCuadrilla(JTextField TXT_ID) {
    // Obtener el ID de la cuadrilla desde el campo de texto TXT_ID
    String idCuadrillaStr = TXT_ID.getText();
    int idCuadrilla;

    try {
        idCuadrilla = Integer.parseInt(idCuadrillaStr); // Convertir a entero el ID de cuadrilla
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El ID de la cuadrilla no es válido.", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
        return;  // Si no es válido, terminamos la ejecución
    }

    // Confirmar la eliminación con el usuario
    int confirmacion = JOptionPane.showConfirmDialog(null, 
        "¿Estás seguro de que deseas eliminar la cuadrilla con ID " + idCuadrilla + "?", 
        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

    // Si el usuario confirma la eliminación
    if (confirmacion == JOptionPane.YES_OPTION) {
        // Llamar al método del DAO para eliminar la cuadrilla
        cuadrillaDAO.eliminarCuadrilla(idCuadrilla);

        // Mostrar un mensaje de éxito
        JOptionPane.showMessageDialog(null, "Cuadrilla eliminada exitosamente.");
    } else {
        // Si el usuario cancela la eliminación
        JOptionPane.showMessageDialog(null, "La eliminación de la cuadrilla fue cancelada.");
    }
}


// Método en el controlador para actualizar el IdCuadrilla en la persona
public void agregarMiembro(JTextField TXT_ID, JComboBox<String> CB_NuevoM) {
    // Obtener el ID de la cuadrilla desde el campo de texto TXT_ID
    String idCuadrillaStr = TXT_ID.getText();
    int idCuadrilla;

    try {
        idCuadrilla = Integer.parseInt(idCuadrillaStr); // Convertir a entero el ID de cuadrilla
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El ID de la cuadrilla no es válido.", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
        return;  // Si no es válido, terminamos la ejecución
    }

    // Verificar si el ID de cuadrilla es negativo o cero
    if (idCuadrilla <= 0) {
        JOptionPane.showMessageDialog(null, "El ID de la cuadrilla debe ser un valor positivo.", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener el ítem seleccionado del JComboBox CB_NuevoM
    String seleccionado = (String) CB_NuevoM.getSelectedItem();

    // Comprobar si la opción seleccionada es válida
    if (seleccionado != null && !seleccionado.equals("Seleccione una persona")) {
        // Extraer el ID de la persona seleccionada (por ejemplo "Juan Pérez (ID: 123)")
        String[] partes = seleccionado.split(" \\(ID: ");
        int idPersona;

        try {
            idPersona = Integer.parseInt(partes[1].replace(")", "")); // Obtener el ID de la persona
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID de la persona no es válido.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;  // Si no es válido, terminamos la ejecución
        }

        // Verificar si el ID de la persona es negativo o cero
        if (idPersona <= 0) {
            JOptionPane.showMessageDialog(null, "El ID de la persona debe ser un valor positivo.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Llamar al DAO para actualizar el IdCuadrilla de la persona
        cuadrillaDAO.actualizarIdCuadrillaEnPersona(idPersona, idCuadrilla);

        // Mensaje de éxito
        JOptionPane.showMessageDialog(null, "IdCuadrilla actualizado correctamente para la persona.");
    } else {
        // Si la opción seleccionada es inválida, mostrar un mensaje de advertencia
        JOptionPane.showMessageDialog(null, "Por favor, selecciona una persona válida.", 
                                      "Selección inválida", JOptionPane.WARNING_MESSAGE);
    }
}


// Método para eliminar el miembro seleccionado de la tabla TBL_Miembros
public void eliminarMiembroSeleccionado(JTable TBL_Miembros) {
    // Obtener la fila seleccionada de la tabla
    int filaSeleccionada = TBL_Miembros.getSelectedRow();

    if (filaSeleccionada != -1) {
        // Obtener el ID de la persona desde la tabla
        int idPersona = (int) TBL_Miembros.getValueAt(filaSeleccionada, 0); // Columna 0: ID Persona
        String rol = (String) TBL_Miembros.getValueAt(filaSeleccionada, 2);  // Columna 2: Rol

        // Verificar si el rol es "Empleado" (rol 3)
        if (rol.equals("empleado")) {
            // Llamar al DAO para actualizar el IdCuadrilla a NULL en la tabla personas
            cuadrillaDAO.actualizarIdCuadrillaA_Null(idPersona);

            // Mensaje de éxito
            JOptionPane.showMessageDialog(null, "Miembro eliminado exitosamente.");
        } else {
            // Si el rol no es "Empleado", mostrar mensaje de error
            JOptionPane.showMessageDialog(null, "Solo los empleados pueden ser eliminados.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Si no hay fila seleccionada, mostrar mensaje de advertencia
        JOptionPane.showMessageDialog(null, "Por favor, selecciona un miembro de la tabla para eliminar.",
                                      "Selección inválida", JOptionPane.WARNING_MESSAGE);
    }
}


    
}