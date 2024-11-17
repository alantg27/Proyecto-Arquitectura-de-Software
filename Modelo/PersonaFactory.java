package proyectof.Model;

public abstract class PersonaFactory {
    public abstract Persona crearPersona(String nombre, int edad, String correo, String contraseña, Integer idCuadrilla);
}

