package proyectof.Model;

public class JefeFactory extends PersonaFactory {
    @Override
    public Persona crearPersona(String nombre, int edad, String correo, String contraseña, Integer idCuadrilla) {
        return new Persona(nombre, edad, correo, contraseña, "jefe", idCuadrilla);
    }
}
