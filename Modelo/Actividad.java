package proyectof.Model;

import java.sql.Date;

public class Actividad {
    private int id; // ID de la actividad
    private String descripcion;
    private Date fecha;
    private String evidencia;
    private Cuadrilla cuadrilla; // Objeto Cuadrilla
    private Colonia colonia;     // Objeto Colonia

    public Actividad(String descripcion, Date fecha, String evidencia, Cuadrilla cuadrilla, Colonia colonia) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.evidencia = evidencia;
        this.cuadrilla = cuadrilla; // Asignar el objeto Cuadrilla
        this.colonia = colonia;     // Asignar el objeto Colonia
    }

    // Getters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public Cuadrilla getCuadrilla() {
        return cuadrilla; // Retornar el objeto Cuadrilla
    }

    public void setCuadrilla(Cuadrilla cuadrilla) {
        this.cuadrilla = cuadrilla; // Asignar el objeto Cuadrilla
    }

    public Colonia getColonia() {
        return colonia;   // Retornar el objeto Colonia
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia; // Asignar el objeto Colonia
    }

    public void mostrarInfoActividad() {
        System.out.println("ID Actividad: " + id);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Fecha: " + fecha);
        System.out.println("Evidencia: " + evidencia);
        System.out.println("Colonia: " + (colonia != null ? colonia.getId() : "No asignada")); // Mostrar ID de la colonia
        System.out.println("Cuadrilla: " + (cuadrilla != null ? cuadrilla.getId() : "No asignada")); // Mostrar ID de la cuadrilla
    }
}
