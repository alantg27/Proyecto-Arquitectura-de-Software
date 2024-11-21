package proyectof.Model;

public class Cuadrilla {
    private int id;          // ID asignado por la base de datos
    private Persona persona; // Persona que es el jefe de la cuadrilla

    // Constructor para una nueva cuadrilla (sin ID asignado)
    public Cuadrilla(Persona persona) {
        this.persona = persona;
    }

    // Constructor para una cuadrilla existente con ID
    public Cuadrilla(int id, Persona persona) {
        this.id = id;
        this.persona = persona;
    }

    // Método para asignar el ID de la cuadrilla (por ejemplo, después de insertar en la BD)
    public void setId(int id) {
        this.id = id;
    }

    public Cuadrilla(int id) {
    this.id = id;
}
    
    public int getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }
    
    public void setPersona(Persona persona) {
    this.persona = persona;
}


    public void mostrarInfoCuadrilla() {
        System.out.println("ID Cuadrilla: " + id);
        if (persona != null) {
            System.out.println("Jefe de Cuadrilla: " + persona.getNombre());
        } else {
            System.out.println("No hay jefe asignado.");
        }
    }
}
