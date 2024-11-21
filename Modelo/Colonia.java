package proyectof.Model;

public class Colonia {
    private int id; // ID de la colonia
    private String nombre;
    private String codigo_postal;

    // Constructor para una nueva colonia sin ID (al insertar en la base de datos)
    public Colonia(String nombre, String codigo_postal) {
        this.nombre = nombre;
        this.codigo_postal = codigo_postal;
    }

    // Constructor para una colonia existente con ID
    public Colonia(int id, String nombre, String codigo_postal) {
        this.id = id;
        this.nombre = nombre;
        this.codigo_postal = codigo_postal;
    }

    public Colonia(int id) {
    this.id = id;
}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigoPostal() {
        return codigo_postal;
    }

    public void mostrarInfoColonia() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Código Postal: " + codigo_postal);
    }
}
