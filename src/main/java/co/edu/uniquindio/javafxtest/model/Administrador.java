package co.edu.uniquindio.javafxtest.model;

public class Administrador extends Usuario {

    public Administrador(String nombre, String documento, String email, String telefono){
        super(nombre, documento, email, telefono);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public static void gestionSalas(){}

    public static void monitoreo(){}

    public static void generarReporte(){}


}
