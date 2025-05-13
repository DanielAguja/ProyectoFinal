package co.edu.uniquindio.javafxtest.model;

import jdk.jshell.Diag;

import java.util.LinkedList;

public class Paciente extends Usuario{
    private static LinkedList<Diagnostico> historialP;

    public Paciente(String nombre, String documento, String email, String telefono) {
        super(nombre, documento, email, telefono);
       historialP = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "Paciente{" +
                ", nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public static LinkedList<Diagnostico> getHistorial() {
        return historialP;
    }

    public static void setHistorial(LinkedList<Diagnostico> historial) {
        Paciente.historialP = historial;
    }

    public static void agregarHistorial(Diagnostico diagnostico) {
        Paciente.historialP.add(diagnostico);
    }


}

