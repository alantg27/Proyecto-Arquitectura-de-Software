package proyectof.Model;

import java.sql.Connection;
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

        // Validar que los valores de IdCuadrilla y IdColonia no sean null
        if (actividad.getCuadrilla() == null || actividad.getColonia() == null) {
            throw new IllegalArgumentException("Cuadrilla y Colonia son obligatorios.");
        }

        statement.setString(1, actividad.getDescripcion());
        statement.setDate(2, actividad.getFecha());
        statement.setString(3, actividad.getEvidencia());

        // Establecer los IDs de cuadrilla y colonia
        statement.setInt(4, actividad.getCuadrilla().getId());
        statement.setInt(5, actividad.getColonia().getId());

        statement.executeUpdate();

        // Obtener el ID generado
        generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            actividad.setId(generatedKeys.getInt(1)); // Establece el ID en el objeto Actividad
        }

        System.out.println("Actividad insertada exitosamente: " + actividad.getDescripcion());

    } catch (SQLException e) {
        e.printStackTrace();
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
    } finally {
        // No se cierra la conexión
        try {
            if (generatedKeys != null) generatedKeys.close();
            if (statement != null) statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
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

public List<Integer> obtenerCuadrillasConPersona() {
    String sql = "SELECT IdCuadrilla FROM cuadrillas WHERE IdPersona IS NOT NULL";
    Connection conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    List<Integer> cuadrillas = new ArrayList<>();

    try {
        statement = conexion.prepareStatement(sql);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            cuadrillas.add(resultSet.getInt("IdCuadrilla"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // No cerramos la conexión
    }
    return cuadrillas;
}






}