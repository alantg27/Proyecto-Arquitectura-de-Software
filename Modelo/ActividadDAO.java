package proyectof.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActividadDAO {

    // Método para insertar una nueva actividad
    public void insertarActividad(Actividad actividad) {
    String sql = "INSERT INTO registroactividades (Descripcion, Fecha, Evidencia, IdCuadrilla, IdColonia) VALUES (?, ?, ?, ?, ?)";
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet generatedKeys = null;

    try {
        conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
        statement = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        statement.setString(1, actividad.getDescripcion());
        statement.setDate(2, actividad.getFecha());
        statement.setString(3, actividad.getEvidencia());

        // Obtener el ID de la cuadrilla desde el objeto Cuadrilla
        int idCuadrilla = actividad.getCuadrilla() != null ? actividad.getCuadrilla().getId() : 0;
        statement.setInt(4, idCuadrilla);

        // Obtener el ID de la colonia desde el objeto Colonia usando el código postal
        if (actividad.getColonia() != null) {
            int idColonia = obtenerIdColoniaPorCodigoPostal(actividad.getColonia().getCodigoPostal());
            statement.setInt(5, idColonia);
        } else {
            statement.setNull(5, java.sql.Types.INTEGER); // Si no hay colonia, establecer como NULL
        }

        statement.executeUpdate();

        // Obtener el ID generado
        generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            actividad.setId(generatedKeys.getInt(1)); // Establece el ID en el objeto Actividad
        }

        System.out.println("Actividad insertada exitosamente: " + actividad.getDescripcion());

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Cerrar recursos
        try {
            if (generatedKeys != null) generatedKeys.close();
            if (statement != null) statement.close();
            // No cerramos la conexión aquí
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Método para obtener el ID de la colonia a partir del código postal
private int obtenerIdColoniaPorCodigoPostal(String codigoPostal) {
    String sql = "SELECT IdColonia FROM colonias WHERE Codigo_postal = ?";
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    int idColonia = 0;

    try {
        conexion = ConexionBD.conectar();
        statement = conexion.prepareStatement(sql);
        statement.setString(1, codigoPostal);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            idColonia = resultSet.getInt("IdColonia"); // Obtiene el ID de la colonia
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            // No se cierra la conexión aquí
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return idColonia;
}


    // Método para obtener una actividad por su ID
    public Actividad obtenerActividad(int id) {
    String sql = "SELECT * FROM registroactividades WHERE IdActividad = ?";
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Actividad actividad = null;

    try {
        conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
        statement = conexion.prepareStatement(sql);
        
        statement.setInt(1, id);
        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Obtener los IDs de cuadrilla y colonia
            int idCuadrilla = resultSet.getInt("IdCuadrilla");
            int idColonia = resultSet.getInt("IdColonia");

            // Aquí deberías tener un DAO para obtener las cuadrillas y colonias
            CuadrillaDAO cuadrillaDAO = new CuadrillaDAO();
            ColoniaDAO coloniaDAO = new ColoniaDAO();

            // Obtener los objetos Cuadrilla y Colonia
            Cuadrilla cuadrilla = cuadrillaDAO.obtenerCuadrilla(idCuadrilla);
            Colonia colonia = coloniaDAO.obtenerColonia(idColonia); // Asegúrate de tener este método en el DAO de Colonias

            // Crear la actividad con los objetos Cuadrilla y Colonia
            actividad = new Actividad(
                resultSet.getString("Descripcion"),
                resultSet.getDate("Fecha"),
                resultSet.getString("Evidencia"),
                cuadrilla, // Usar objeto Cuadrilla
                colonia   // Usar objeto Colonia
            );
            actividad.setId(resultSet.getInt("IdActividad")); // Establece el ID al crear la actividad
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Cerrar recursos
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            // No cerramos la conexión aquí
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return actividad;
}


    // Método para actualizar una actividad
    public void actualizarActividad(Actividad actividad) {
    String sql = "UPDATE registroactividades SET Descripcion = ?, Fecha = ?, Evidencia = ?, IdCuadrilla = ?, IdColonia = ? WHERE IdActividad = ?";
    Connection conexion = null;
    PreparedStatement statement = null;

    try {
        conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
        statement = conexion.prepareStatement(sql);
        
        statement.setString(1, actividad.getDescripcion());
        statement.setDate(2, actividad.getFecha());
        statement.setString(3, actividad.getEvidencia());
        
        // Obtener el ID de la Cuadrilla y de la Colonia
        int idCuadrilla = actividad.getCuadrilla() != null ? actividad.getCuadrilla().getId() : 0; // Cambia 0 por null si IdCuadrilla puede ser nulo
        int idColonia = actividad.getColonia() != null ? actividad.getColonia().getId() : 0; // Cambia 0 por null si IdColonia puede ser nulo
        
        statement.setInt(4, idCuadrilla);
        statement.setInt(5, idColonia);
        statement.setInt(6, actividad.getId()); // Usa el ID de la actividad

        statement.executeUpdate();
        System.out.println("Actividad actualizada exitosamente.");

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Cerrar recursos
        try {
            if (statement != null) statement.close();
            // No cerramos la conexión aquí
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


    // Método para eliminar una actividad
    public void eliminarActividad(int id) {
        String sql = "DELETE FROM registroactividades WHERE IdActividad = ?";
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
            statement = conexion.prepareStatement(sql);
            
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Actividad eliminada exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (statement != null) statement.close();
                // No cerramos la conexión aquí
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para obtener todas las actividades
//    public List<Actividad> obtenerTodasLasActividades() {
//    String sql = "SELECT * FROM registroactividades";
//    List<Actividad> actividades = new ArrayList<>();
//    Connection conexion = null;
//    PreparedStatement statement = null;
//    ResultSet resultSet = null;
//    Cuadrilla 
//
//    try {
//        conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
//        statement = conexion.prepareStatement(sql);
//        resultSet = statement.executeQuery();
//        
//        while (resultSet.next()) {
//            Actividad actividad = new Actividad(
//                resultSet.getString("Descripcion"),
//                resultSet.getDate("Fecha"),
//                resultSet.getString("Evidencia"),
//                resultSet.getInt("IdCuadrilla"), // Utiliza el ID de la cuadrilla directamente
//                resultSet.getInt("IdColonia")    // Utiliza el ID de la colonia directamente
//            );
//            actividad.setId(resultSet.getInt("IdActividad")); // Establece el ID
//            actividades.add(actividad);
//        }
//
//    } catch (SQLException e) {
//        e.printStackTrace();
//    } finally {
//        // Cerrar recursos
//        try {
//            if (resultSet != null) resultSet.close();
//            if (statement != null) statement.close();
//            // No cerramos la conexión aquí
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    return actividades;
//}


}
