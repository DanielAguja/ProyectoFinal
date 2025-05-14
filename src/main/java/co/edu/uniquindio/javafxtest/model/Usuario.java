package co.edu.uniquindio.javafxtest.model;

public class Usuario {
    protected static String nombre;
    protected static String documento;
    protected static String email;
    protected static String telefono;

    public Usuario(String nombre, String documento, String email, String telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
    }


    public static String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
