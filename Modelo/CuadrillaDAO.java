package proyectof.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuadrillaDAO {

    // Método para insertar una nueva cuadrilla y actualizar la tabla personas
public void insertarCuadrilla(Cuadrilla cuadrilla) {
    String sqlInsertCuadrilla = "INSERT INTO cuadrillas (IdPersona) VALUES (?)";
    String sqlActualizarPersona = "UPDATE personas SET IdCuadrilla = ? WHERE IdPersona = ?";
    Connection conexion = null;
    PreparedStatement statementInsert = null;
    PreparedStatement statementUpdate = null;

    try {
        conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton

        // Iniciar transacción
        conexion.setAutoCommit(false);

        // Insertar la nueva cuadrilla
        statementInsert = conexion.prepareStatement(sqlInsertCuadrilla, PreparedStatement.RETURN_GENERATED_KEYS);
        statementInsert.setInt(1, cuadrilla.getPersona().getId());
        statementInsert.executeUpdate();

        // Obtener el ID de la cuadrilla recién insertada
        ResultSet generatedKeys = statementInsert.getGeneratedKeys();
        int idCuadrilla = 0;
        if (generatedKeys.next()) {
            idCuadrilla = generatedKeys.getInt(1); // Obtener el ID de la cuadrilla
        }

        // Establecer el ID de la cuadrilla en el objeto Cuadrilla
        cuadrilla.setId(idCuadrilla);
        System.out.println("Cuadrilla insertada exitosamente con ID: " + cuadrilla.getId());

        // Ahora, actualizar el registro de la persona asociada con el IdCuadrilla recién generado
        statementUpdate = conexion.prepareStatement(sqlActualizarPersona);
        statementUpdate.setInt(1, idCuadrilla); // Establecer el IdCuadrilla
        statementUpdate.setInt(2, cuadrilla.getPersona().getId()); // Identificar la persona por su IdPersona
        statementUpdate.executeUpdate();

        // Confirmar la transacción (commit)
        conexion.commit();
        System.out.println("Persona actualizada exitosamente con IdCuadrilla: " + idCuadrilla);

    } catch (SQLException e) {
        e.printStackTrace();
        try {
            // Si ocurre algún error, revertir la transacción
            if (conexion != null) {
                conexion.rollback();
            }
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
    } finally {
        try {
            if (statementInsert != null) {
                statementInsert.close();
            }
            if (statementUpdate != null) {
                statementUpdate.close();
            }
            if (conexion != null) {
                conexion.setAutoCommit(true); // Restaurar la configuración de autocommit
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


    public void actualizarCuadrilla(Cuadrilla cuadrilla) {
    String sqlActualizarCuadrilla = "UPDATE cuadrillas SET IdPersona = ? WHERE IdCuadrilla = ?";
    String sqlActualizarPersonaAnterior = "UPDATE personas SET IdCuadrilla = NULL WHERE IdPersona = ?";
    String sqlActualizarPersonaNuevo = "UPDATE personas SET IdCuadrilla = ? WHERE IdPersona = ?";
    
    Connection conexion = null;
    PreparedStatement statement = null;
    PreparedStatement statementNombre = null;
    ResultSet resultSet = null;

    try {
        conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton

        // Iniciar la transacción
        conexion.setAutoCommit(false);

        // Obtener el IdPersona anterior de la cuadrilla antes de actualizar
        int idPersonaAnterior = obtenerIdPersonaDeCuadrilla(cuadrilla.getId(), conexion);

        // Paso 1: Actualizar el IdCuadrilla de la persona anterior (setear a null)
        if (idPersonaAnterior != 0) {
            statement = conexion.prepareStatement(sqlActualizarPersonaAnterior);
            statement.setInt(1, idPersonaAnterior);
            statement.executeUpdate();
        }

        // Paso 2: Actualizar la cuadrilla con el nuevo IdPersona
        statement = conexion.prepareStatement(sqlActualizarCuadrilla);
        statement.setInt(1, cuadrilla.getPersona().getId());
        statement.setInt(2, cuadrilla.getId());
        statement.executeUpdate();

        // Paso 3: Actualizar el IdCuadrilla de la persona nueva en la tabla personas
        statement = conexion.prepareStatement(sqlActualizarPersonaNuevo);
        statement.setInt(1, cuadrilla.getId());
        statement.setInt(2, cuadrilla.getPersona().getId());
        statement.executeUpdate();

    } catch (SQLException e) {
        // Revertir la transacción en caso de error
        if (conexion != null) {
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        e.printStackTrace();
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
            if (statementNombre != null) {
                statementNombre.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            // Ya no cerramos la conexión aquí
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


// Método para obtener el IdPersona de una cuadrilla
private int obtenerIdPersonaDeCuadrilla(int idCuadrilla, Connection conexion) {
    String sql = "SELECT IdPersona FROM cuadrillas WHERE IdCuadrilla = ?";
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    int idPersona = 0;

    try {
        statement = conexion.prepareStatement(sql);
        statement.setInt(1, idCuadrilla);
        resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            idPersona = resultSet.getInt("IdPersona");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    return idPersona;
}


    public Cuadrilla obtenerCuadrilla(int id) {
    String sql = "SELECT IdCuadrilla, IdPersona FROM cuadrillas WHERE IdCuadrilla = ?";
    String sqlObtenerNombrePersona = "SELECT Nombre FROM personas WHERE IdPersona = ?";
    Connection conexion = null;
    PreparedStatement statement = null;
    PreparedStatement statementNombre = null;
    ResultSet resultSet = null;
    ResultSet resultSetNombre = null;
    Cuadrilla cuadrilla = null;

    try {
        conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
        statement = conexion.prepareStatement(sql);
        statement.setInt(1, id);

        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            // Crear instancia de Persona directamente con el constructor de ID
            int idPersona = resultSet.getInt("IdPersona");
            Persona persona = new Persona(idPersona);

            // Obtener el nombre de la persona con el IdPersona
            statementNombre = conexion.prepareStatement(sqlObtenerNombrePersona);
            statementNombre.setInt(1, idPersona);
            resultSetNombre = statementNombre.executeQuery();

            String nombrePersona = null;
            if (resultSetNombre.next()) {
                nombrePersona = resultSetNombre.getString("Nombre");
            }

            // Asignar el nombre a la persona
            persona.setNombre(nombrePersona);

            // Crear la cuadrilla
            cuadrilla = new Cuadrilla(resultSet.getInt("IdCuadrilla"), persona);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (resultSetNombre != null) {
                resultSetNombre.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (statementNombre != null) {
                statementNombre.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return cuadrilla;
}



    // Método para eliminar una cuadrilla por su ID
    public void eliminarCuadrilla(int id) {
        String sql = "DELETE FROM cuadrillas WHERE IdCuadrilla = ?";
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

            System.out.println("Cuadrilla eliminada exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para obtener jefes disponibles (IdRol=2 y sin IdCuadrilla)
    public List<Object[]> obtenerJefesDisponibles(Connection conexion) {
        List<Object[]> jefes = new ArrayList<>();
        String sql = "SELECT IdPersona, Nombre FROM personas WHERE IdRol = 2 AND IdCuadrilla IS NULL";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] jefe = {
                    rs.getInt("IdPersona"),
                    rs.getString("Nombre")
                };
                jefes.add(jefe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener los jefes disponibles.", e);
        }

        return jefes;
    }

    // Método para obtener nuevos miembros disponibles (IdRol=3 y sin IdCuadrilla)
    public List<Object[]> obtenerMiembrosDisponibles(Connection conexion) {
        List<Object[]> miembros = new ArrayList<>();
        String sql = "SELECT IdPersona, Nombre FROM personas WHERE IdRol = 3 AND IdCuadrilla IS NULL";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] miembro = {
                    rs.getInt("IdPersona"),
                    rs.getString("Nombre")
                };
                miembros.add(miembro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener los nuevos miembros.", e);
        }

        return miembros;
    }

    // Método para obtener miembros de una cuadrilla (IdCuadrilla desde vista_cuadrillas)
    public List<Object[]> obtenerMiembrosPorCuadrilla(String idCuadrilla, Connection conexion) {
        List<Object[]> miembros = new ArrayList<>();
        String sql = "SELECT IdPersona, Nombre, NombreRol FROM vista_cuadrillas WHERE IdCuadrilla = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, idCuadrilla);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] miembro = {
                        rs.getInt("IdPersona"),
                        rs.getString("Nombre"),
                        rs.getString("NombreRol")
                    };
                    miembros.add(miembro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener los miembros de la cuadrilla.", e);
        }

        return miembros;
    }
    
    // Método en el DAO para actualizar el campo IdCuadrilla en la tabla personas
public void actualizarIdCuadrillaEnPersona(int idPersona, int idCuadrilla) {
    String sql = "UPDATE personas SET IdCuadrilla = ? WHERE IdPersona = ?";
    Connection conexion = null;
    PreparedStatement statement = null;

    try {
        conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
        statement = conexion.prepareStatement(sql);
        
        // Establecer los valores en el PreparedStatement
        statement.setInt(1, idCuadrilla);
        statement.setInt(2, idPersona);

        // Ejecutar la actualización
        statement.executeUpdate();
        
        System.out.println("IdCuadrilla actualizado en la persona con ID: " + idPersona);
        
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    
    // Método para actualizar el IdCuadrilla de una persona a NULL
public void actualizarIdCuadrillaA_Null(int idPersona) {
    String sql = "UPDATE personas SET IdCuadrilla = NULL WHERE IdPersona = ?";

    Connection conexion = null;
    PreparedStatement statement = null;

    try {
        conexion = ConexionBD.conectar(); // Obtén la conexión desde el Singleton
        statement = conexion.prepareStatement(sql);
        statement.setInt(1, idPersona); // Establecer el IdPersona

        statement.executeUpdate(); // Ejecutar la actualización

        System.out.println("IdCuadrilla actualizado a NULL para la persona con ID: " + idPersona);
        
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    
    
}