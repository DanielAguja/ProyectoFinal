package co.edu.uniquindio.javafxtest.model;

import jdk.jshell.Diag;

import java.util.LinkedList;

public class Paciente extends Usuario{

    private LinkedList<Diagnostico> historialP;

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

    public LinkedList<Diagnostico> getHistorial() {
        return historialP;
    }

    public void setHistorialP(LinkedList<Diagnostico> historialP) {
        this.historialP = historialP;
    }

    public void agregarHistorial(Diagnostico diagnostico) {
        historialP.add(diagnostico);
    }

}

