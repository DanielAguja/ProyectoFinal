package co.edu.uniquindio.javafxtest.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class Medico extends Usuario {
    private Especialidades especialidad;

    private static LinkedList<Horario> horarios;

    public Medico(String nombre, String documento, String email, String telefono,
                  Especialidades especialidad) {
        super(nombre, documento, email, telefono);
        this.especialidad = especialidad;
        horarios = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "Medico{" +
                "especialidad=" + especialidad +
                ", horarios=" + horarios +
                ", nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public Especialidades getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidades especialidad) {
        this.especialidad = especialidad;
    }

    public static LinkedList<Horario> getHorarios() {
        return horarios;
    }

    public static void setHorarios(LinkedList<Horario> horarios) {
        Medico.horarios = horarios;
    }
}
