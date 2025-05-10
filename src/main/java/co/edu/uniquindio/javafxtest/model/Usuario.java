package co.edu.uniquindio.javafxtest.model;

public class Usuario {
    protected String usuario;
    protected String clave;
    protected String nombre;
    protected String documento;
    protected String email;
    protected String telefono;

    public Usuario(String usuario, String clave, String nombre, String documento, String email, String telefono) {
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
