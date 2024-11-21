package proyectof.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import proyectof.Model.*;
import proyectof.View.*;
import javax.swing.JOptionPane;


public class PersonaConn {

    private PersonaDAO dao;

    public PersonaConn() {
        this.dao = new PersonaDAO();
    }
    
    public void actualizarCuadrilla(String rolSeleccionado, JComboBox<String> CB_Cuadrilla, Connection conexion) {
    // Limpia la CB_Cuadrilla antes de cargar nuevos elementos
    CB_Cuadrilla.removeAllItems();

    // Primero, agrega la opción "Crear sin asignar cuadrilla" si es necesario
    if (rolSeleccionado.equals("Jefe de Cuadrilla")) {
        CB_Cuadrilla.addItem("Sin asignar cuadrilla");
    } else if (rolSeleccionado.equals("Empleado")) {
        CB_Cuadrilla.addItem("Sin asignar cuadrilla");
    }

    // Dependiendo del rol, realiza la consulta correspondiente
    if (rolSeleccionado.equals("Administrador")) {
        // Para Admin, se agrega un ítem indicando que no se puede asignar cuadrilla
        CB_Cuadrilla.addItem("No se puede asignar");
    } else if (rolSeleccionado.equals("Jefe de Cuadrilla")) {
        // Obtener cuadrillas disponibles para Jefe de Cuadrilla
        obtenerCuadrillas("Jefe de Cuadrilla", CB_Cuadrilla, conexion);
    } else if (rolSeleccionado.equals("Empleado")) {
        // Obtener todas las cuadrillas para Empleado
        obtenerCuadrillas("Empleado", CB_Cuadrilla, conexion);
    }
}

public void obtenerCuadrillas(String rol, JComboBox<String> comboBox, Connection conexion) {
    // Realizar consulta específica dependiendo del rol
    if (rol.equals("Jefe de Cuadrilla")) {
        // Consulta para obtener cuadrillas donde la IDCuadrilla no está asignada a ninguna persona
        realizarConsulta("SELECT IdCuadrilla FROM cuadrillasdisponibles", comboBox, conexion);
    } else if (rol.equals("Empleado")) {
        // Consulta para obtener todas las cuadrillas
        realizarConsulta("SELECT IdCuadrilla FROM cuadrillas", comboBox, conexion);
    }
}

public void realizarConsulta(String sql, JComboBox<String> comboBox, Connection conexion) {
    try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            // Verifica que el resultado se obtiene correctamente
            String idCuadrilla = rs.getString("IdCuadrilla");
            comboBox.addItem(idCuadrilla);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    
    public void insertar(PersonaView vista) {
    // Obtener los valores de los campos utilizando los métodos getter
    String nombre = vista.getTXT_Nombre().getText();
    String edadStr = vista.getTXT_Edad().getText();
    String correo = vista.getTXT_Correo().getText();
    String contraseña = vista.getTXT_Contraseña().getText();
    String rol = (String) vista.getCB_Rol().getSelectedItem();

    // Inicializar idCuadrilla como null
    Integer idCuadrilla = null;

    // Validar los campos antes de intentar la inserción
    if (nombre.isEmpty() || edadStr.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || rol == null) {
        // Mostrar mensaje de error si algún campo está vacío
        JOptionPane.showMessageDialog(vista, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Convertir edad a entero
    int edad = 0;
    try {
        edad = Integer.parseInt(edadStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(vista, "La edad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Si el rol no es Administrador, obtener y validar la cuadrilla seleccionada
    if (!rol.equals("Administrador")) {
        String cuadrillaSeleccionada = (String) vista.getCB_Cuadrilla().getSelectedItem();
        
        // Permitir que se elija "sin asignar cuadrilla"
        if (cuadrillaSeleccionada != null && !cuadrillaSeleccionada.equals("Seleccione una cuadrilla") &&
                !cuadrillaSeleccionada.equals("Sin asignar cuadrilla")) {
            try {
                idCuadrilla = Integer.parseInt(cuadrillaSeleccionada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(vista, "La ID de Cuadrilla debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    // Llamar a la fábrica adecuada según el rol seleccionado
    Persona persona = null;
    if (rol.equals("Administrador")) {
        persona = new AdminFactory().crearPersona(nombre, edad, correo, contraseña, null);
    } else if (rol.equals("Jefe de Cuadrilla")) {
        persona = new JefeFactory().crearPersona(nombre, edad, correo, contraseña, idCuadrilla);
    } else if (rol.equals("Empleado")) {
        persona = new EmpleadoFactory().crearPersona(nombre, edad, correo, contraseña, idCuadrilla);
    }

    // Si la persona fue creada correctamente, insertarla en la base de datos
    if (persona != null) {
        try {
            // Realizar la inserción en la base de datos a través del DAO
            dao.insertarPersona(persona);
            JOptionPane.showMessageDialog(vista, "Persona insertada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error al insertar la persona: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}



    public void consultarPorId(PersonaView vista) {
    // Obtener el ID de la persona desde el campo de texto de la vista
    String idStr = vista.getTXT_ID().getText();

    // Validar que el ID no esté vacío y sea un número válido
    if (idStr.isEmpty()) {
        JOptionPane.showMessageDialog(vista, "El ID no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int idPersona;
    try {
        // Convertir el ID de String a int
        idPersona = Integer.parseInt(idStr);
    } catch (NumberFormatException e) {
        // Si no es un número válido, mostrar mensaje de error
        JOptionPane.showMessageDialog(vista, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtener la persona por ID usando el DAO
    Persona persona = dao.obtenerPersona(idPersona);
    if (persona == null) {
        // Si no se encuentra la persona, mostrar mensaje de error
        JOptionPane.showMessageDialog(vista, "No se encontró la persona con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Actualizar los campos de texto (JTextField) en la vista con los datos de la persona
    vista.getTXT_Nombre().setText(persona.getNombre());
    vista.getTXT_Edad().setText(String.valueOf(persona.getEdad()));
    vista.getTXT_Correo().setText(persona.getCorreo());
    vista.getTXT_Contraseña().setText(persona.getContraseña());

    // Actualizar el ComboBox de Rol
    int rol = persona.getRol();
    switch (rol) {
        case 1:
            vista.getCB_Rol().setSelectedIndex(1); // Admin
            vista.getCB_Cuadrilla().setEnabled(true); // Habilitar combo box de cuadrillas
            vista.getCB_Cuadrilla().removeAllItems();
            vista.getCB_Cuadrilla().addItem("No se puede asignar");
            break;
        case 2:
            vista.getCB_Rol().setSelectedIndex(2); // Jefe de Cuadrilla
            actualizarCuadrilla("Jefe de Cuadrilla", vista.getCB_Cuadrilla(), ConexionBD.conectar());
            vista.getCB_Cuadrilla().setEnabled(true); // Habilitar combo box
            break;
        case 3:
            vista.getCB_Rol().setSelectedIndex(3); // Empleado
            actualizarCuadrilla("Empleado", vista.getCB_Cuadrilla(), ConexionBD.conectar());
            vista.getCB_Cuadrilla().setEnabled(true); // Habilitar combo box
            break;
        default:
            JOptionPane.showMessageDialog(vista, "Rol desconocido: " + rol, "Error", JOptionPane.ERROR_MESSAGE);
            return;
    }

    // Actualizar el ComboBox de Cuadrilla
Integer idCuadrilla = persona.getIdCuadrilla();

if (rol==1) {
    // Si es admin, la combo box debe deshabilitarse y mostrar "No se puede asignar"
    vista.getCB_Cuadrilla().setEnabled(true);
    vista.getCB_Cuadrilla().removeAllItems();
    vista.getCB_Cuadrilla().addItem("No se puede asignar");
} else {
    // Si no es admin, cargar las cuadrillas correspondientes
    vista.getCB_Cuadrilla().setEnabled(true);
    vista.getCB_Cuadrilla().removeAllItems();

    if (rol==2) {
        // Si el rol es Jefe de Cuadrilla
        if (idCuadrilla != null) {
            // Agregar la cuadrilla asignada al JComboBox
            vista.getCB_Cuadrilla().addItem(String.valueOf(idCuadrilla));
        }
        // Cargar cuadrillas disponibles sin jefe
        realizarConsulta("SELECT IdCuadrilla FROM cuadrillasdisponibles", vista.getCB_Cuadrilla(), ConexionBD.conectar());
    } else if (rol==3) {
        // Si el rol es Empleado, cargar todas las cuadrillas
        realizarConsulta("SELECT IdCuadrilla FROM cuadrillas", vista.getCB_Cuadrilla(), ConexionBD.conectar());
    }

    // Seleccionar la cuadrilla asignada si existe
    if (idCuadrilla != null) {
        vista.getCB_Cuadrilla().setSelectedItem(String.valueOf(idCuadrilla));
    } else {
        vista.getCB_Cuadrilla().setSelectedIndex(-1); // Dejar la ComboBox en blanco
    }
}}
    
    public void eliminar(PersonaView vista) {
    // Obtener el ID desde el campo de texto de la vista
    String idStr = vista.getTXT_ID().getText();

    // Validar que el ID no esté vacío y sea un número válido
    if (idStr.isEmpty()) {
        JOptionPane.showMessageDialog(vista, "El ID no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int idPersona;
    try {
        // Convertir el ID de String a int
        idPersona = Integer.parseInt(idStr);
    } catch (NumberFormatException e) {
        // Si no es un número válido, mostrar mensaje de error
        JOptionPane.showMessageDialog(vista, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Confirmar eliminación
    int confirmacion = JOptionPane.showConfirmDialog(
        vista,
        "¿Está seguro de que desea eliminar este registro?",
        "Confirmación de eliminación",
        JOptionPane.YES_NO_OPTION
    );

    if (confirmacion == JOptionPane.YES_OPTION) {
        try {
            // Llamar al DAO para eliminar la persona
            dao.eliminarPersona(idPersona);

            // Mostrar mensaje de éxito y limpiar la vista
            JOptionPane.showMessageDialog(vista, "Persona eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.getTXT_ID().setText("");
            vista.getTXT_Nombre().setText("");
            vista.getTXT_Edad().setText("");
            vista.getTXT_Correo().setText("");
            vista.getTXT_Contraseña().setText("");
            vista.getCB_Rol().setSelectedIndex(0);
            vista.getCB_Cuadrilla().removeAllItems();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Ocurrió un error al eliminar la persona.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

    
    public void actualizar(PersonaView vista) {
    // Obtener los datos de la vista
    String idStr = vista.getTXT_ID().getText();
    String nombre = vista.getTXT_Nombre().getText();
    String edadStr = vista.getTXT_Edad().getText();
    String correo = vista.getTXT_Correo().getText();
    String contraseña = vista.getTXT_Contraseña().getText();
    int selectedIndex = vista.getCB_Rol().getSelectedIndex();  // Obtener el índice seleccionado del ComboBox
    String idCuadrillaStr = (String) vista.getCB_Cuadrilla().getSelectedItem();

    // Validar que el ID no esté vacío y sea un número válido
    if (idStr.isEmpty()) {
        JOptionPane.showMessageDialog(vista, "El ID no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int idPersona;
    try {
        // Convertir el ID de String a int
        idPersona = Integer.parseInt(idStr);
    } catch (NumberFormatException e) {
        // Si no es un número válido, mostrar mensaje de error
        JOptionPane.showMessageDialog(vista, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar que la edad sea un número
    int edad;
    try {
        edad = Integer.parseInt(edadStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(vista, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar que el rol esté seleccionado
    if (selectedIndex == -1) {
        JOptionPane.showMessageDialog(vista, "Debe seleccionar un rol.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Asignar el valor del rol según el índice seleccionado en el ComboBox
    int rol = 0;
    switch (selectedIndex) {
        case 1:
            rol = 1; // Index 1 = Admin
            break;
        case 2:
            rol = 2; // Index 2 = Jefe de Cuadrilla
            break;
        case 3:
            rol = 3; // Index 3 = Empleado
            break;
        default:
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un rol válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Termina la ejecución si no se seleccionó un rol válido
    }

    // Validar que la contraseña no esté vacía
    if (contraseña.isEmpty()) {
        JOptionPane.showMessageDialog(vista, "La contraseña no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar idCuadrilla y asignar null si es inválido
    Integer idCuadrilla = null;
    try {
        if (idCuadrillaStr != null && !idCuadrillaStr.isEmpty()) {
            idCuadrilla = Integer.parseInt(idCuadrillaStr); // Convertir a Integer si es válido
        }
    } catch (NumberFormatException e) {
        idCuadrilla = null; // Si es inválido, asignar null
    }

    // Crear el objeto persona con los nuevos valores
    Persona persona = new Persona(nombre, edad, correo, contraseña, rol, idCuadrilla);
    persona.setId(idPersona); // Establecer el ID de la persona

    // Si el rol es "admin" y tiene cuadrilla asignada, eliminar la cuadrilla
    if (rol == 1 && idCuadrilla != null) {
        try {
            // Eliminar la cuadrilla asociada si tiene una
            dao.eliminarCuadrilla(idCuadrilla);
            System.out.println("Cuadrilla eliminada porque la persona ahora es admin.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Ocurrió un error al eliminar la cuadrilla.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Confirmar si el usuario realmente desea actualizar
    int confirmacion = JOptionPane.showConfirmDialog(
        vista,
        "¿Está seguro de que desea actualizar la información de esta persona?",
        "Confirmación de actualización",
        JOptionPane.YES_NO_OPTION
    );

    if (confirmacion == JOptionPane.YES_OPTION) {
        try {
            // Llamar al DAO para actualizar la persona
            dao.actualizarPersona(persona);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(vista, "Persona actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            // Manejar errores si ocurre algún problema al actualizar
            JOptionPane.showMessageDialog(vista, "Ocurrió un error al actualizar la persona.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}





    
    public void abrirAdminView(PersonaView vista){
        
    }
}