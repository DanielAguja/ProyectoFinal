package co.edu.uniquindio.javafxtest.model;

public class Paciente extends Usuario{
    private Historial historial;

    public Paciente(String usuario, String clave, String nombre, String documento, String email, String telefono, Historial historial) {
        super(usuario, clave, nombre, documento, email, telefono);
        this.historial = historial;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "historial=" + historial +
                ", nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public Historial getHistorial() {
        return historial;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }
}

