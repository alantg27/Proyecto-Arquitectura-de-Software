package proyectof;

import proyectof.Model.*;
import java.sql.Connection;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        // Crear las fábricas
        JefeFactory jefeFactory = new JefeFactory();
        EmpleadoFactory empleadoFactory = new EmpleadoFactory();
        AdminFactory adminFactory = new AdminFactory();
        
        // Crear el DAO
        PersonaDAO personaDAO = new PersonaDAO();
        
        // Crear el DAO para colonias
        ColoniaDAO coloniaDAO = new ColoniaDAO();

        // Suponiendo que ya tienes un ID de cuadrilla existente
        Integer idCuadrillaExistente = 1; // Este valor debe ser un ID existente en la base de datos

        // Abrir la conexión a la base de datos
        Connection conexion = ConexionBD.conectar();

        // Crear un jefe con una cuadrilla existente
        Persona jefe = jefeFactory.crearPersona("Ptofe", 20, "profe@example.com", "contraseñaJefe", idCuadrillaExistente);

        // Crear un empleado sin cuadrilla asignada
        Persona empleado = empleadoFactory.crearPersona("Ana Gómez", 30, "ana@example.com", "contraseñaEmpleado", null);

        // Crear un administrador
        Persona admin = adminFactory.crearPersona("Carlos Sánchez", 40, "carlos@example.com", "contraseñaAdmin", idCuadrillaExistente);

        // Insertar las personas en la base de datos
        personaDAO.insertarPersona(jefe);
       // personaDAO.insertarPersona(empleado);
       // personaDAO.insertarPersona(admin);
       
       // Crear una nueva colonia
        Colonia nuevaColonia = new Colonia("Colonia del Sol", "98765");

        // Insertar la nueva colonia en la base de datos
        //coloniaDAO.insertarColonia(nuevaColonia);
        
        // Crear el DAO para las cuadrillas
        CuadrillaDAO cuadrillaDAO = new CuadrillaDAO();

        // Crear una nueva cuadrilla con jefe ID 
        Cuadrilla nuevaCuadrilla = new Cuadrilla(jefe);
        Cuadrilla cuadrillaCompleta =new Cuadrilla(5,jefe);

        // Insertar la nueva cuadrilla en la base de datos
        //cuadrillaDAO.insertarCuadrilla(nuevaCuadrilla);
        
        // Crear una instancia del DAO
        ActividadDAO actividadDAO = new ActividadDAO();

        // Crear una nueva actividad usando el constructor
        Actividad nuevaActividad = new Actividad(
            "Limpieza de parque",         // Descripción
            new Date(System.currentTimeMillis()), // Fecha actual
            "evidencia_parque.jpg",       // Evidencia
            cuadrillaCompleta,
            nuevaColonia
        );

        // Insertar la actividad en la base de datos
       // actividadDAO.insertarActividad(nuevaActividad);
        
        // Obtener una actividad por su ID
        int idActividad = 4; // Cambia este valor por el ID que deseas consultar
        Actividad actividad = actividadDAO.obtenerActividad(idActividad);

        if (actividad != null) {
    System.out.println("Actividad encontrada:");
    System.out.println("ID: " + actividad.getId());
    System.out.println("Descripción: " + actividad.getDescripcion());
    System.out.println("Fecha: " + actividad.getFecha());
    System.out.println("Evidencia: " + actividad.getEvidencia());
    
    // Mostrar información de la cuadrilla
    Cuadrilla cuadrilla = actividad.getCuadrilla();
    if (cuadrilla != null) {
        System.out.println("Cuadrilla ID: " + cuadrilla.getId());
    } else {
        System.out.println("Cuadrilla no encontrada.");
    }

    // Mostrar información de la colonia
    Colonia colonia = actividad.getColonia();
    if (colonia != null) {
        System.out.println("Colonia ID: " + colonia.getId());
        // Si la clase Colonia tiene más atributos, puedes mostrarlos aquí
    } else {
        System.out.println("Colonia no encontrada.");
    }
} else {
    System.out.println("Actividad no encontrada con ID: " + idActividad);
}

        
        // Obtener una cuadrilla por su ID
        int idCuadrilla = 7; // Cambia este valor por el ID que deseas consultar
        Cuadrilla cuadrilla = cuadrillaDAO.obtenerCuadrilla(idCuadrilla);

        if (cuadrilla != null) {
            System.out.println("Cuadrilla encontrada:");
            System.out.println("ID: " + cuadrilla.getId());
            System.out.println("ID del Jefe de Cuadrilla: " + cuadrilla.getPersona().getId());
        } else {
            System.out.println("Cuadrilla no encontrada con ID: " + idCuadrilla);
        }
        
        // Suponiendo que deseas obtener la colonia con ID 1
        int idColonia = 1; // Cambia este valor por el ID que deseas consultar
        Colonia colonia = coloniaDAO.obtenerColonia(idColonia);

        if (colonia != null) {
            System.out.println("Colonia encontrada:");
            System.out.println("ID: " + colonia.getId());
            System.out.println("Nombre: " + colonia.getNombre());
            System.out.println("Código Postal: " + colonia.getCodigoPostal());
            // Imprime otros atributos si los hay
        } else {
            System.out.println("Colonia no encontrada con ID: " + idColonia);
        }
        
        // Consulta de una persona
        int idPersona = 1; // Cambia este valor por el ID que deseas consultar
        Persona personaConsulta = personaDAO.obtenerPersona(idPersona);

        if (personaConsulta != null) {
            System.out.println("Persona encontrada:");
            System.out.println("ID: " + personaConsulta.getId());
            System.out.println("Nombre: " + personaConsulta.getNombre());
            System.out.println("Edad: " + personaConsulta.getEdad());
            System.out.println("Correo: " + personaConsulta.getCorreo());
            System.out.println("Rol: " + personaConsulta.getRol());
            System.out.println("Cuadrilla: "+ personaConsulta.getIdCuadrilla());
            // Imprime otros atributos si los hay

            // Actualizar la persona
            personaConsulta.setEdad(30); // Cambia la edad o cualquier otro atributo
            personaDAO.actualizarPersona(personaConsulta);
        } else {
            System.out.println("Persona no encontrada con ID: " + idPersona);
        }
        
        if (personaConsulta != null) {
            System.out.println("Persona encontrada:");
            System.out.println("ID: " + personaConsulta.getId());
            System.out.println("Nombre: " + personaConsulta.getNombre());
            System.out.println("Edad: " + personaConsulta.getEdad());
            System.out.println("Correo: " + personaConsulta.getCorreo());
            System.out.println("Rol: " + personaConsulta.getRol());
            System.out.println("Cuadrilla: "+ personaConsulta.getIdCuadrilla());
            } else {
            System.out.println("Persona no encontrada con ID: " + idPersona);
        }

        // Cerrar la conexión
        ConexionBD.cerrarConexion();
    }
}

