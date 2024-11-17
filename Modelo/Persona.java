package proyectof.Model;

public class Persona {
    private int id; // Atributo para el ID
    private String nombre;
    private int edad;
    private String correo;
    private String contraseña;
    private String rol;
    private Integer idCuadrilla; // Cambiado a Integer para permitir valores null

    // Constructor para roles (Administrador, Jefe de Cuadrilla, Empleado)
    public Persona(String nombre, int edad, String correo, String contraseña, String rol, Integer idCuadrilla) {
        validarEdad(edad);
        validarRol(rol);
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
        this.idCuadrilla = idCuadrilla;
    }
    
    // Métodos de validación
    private void validarEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser un número positivo.");
        }
    }

    private void validarRol(String rol) {
        if (!rol.equals("admin") && !rol.equals("jefe") && !rol.equals("empleado")) {
            throw new IllegalArgumentException("El rol debe ser 'admin', 'jefe' o 'empleado'.");
        }
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
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getIdCuadrilla() {
        return idCuadrilla;
    }
    
    public void setIdCuadrilla(Integer idCuadrilla) {
        this.idCuadrilla = idCuadrilla;
    }

    public void mostrarInfo() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Correo: " + correo);
        System.out.println("Contraseña: " + contraseña);
        System.out.println("Rol: " + rol);
        System.out.println("ID Cuadrilla: " + (idCuadrilla != null ? idCuadrilla : "Ninguna"));
    }
}
