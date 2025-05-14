package co.edu.uniquindio.javafxtest.model;

import java.time.LocalDate;
import java.util.LinkedList;

public class Cita {
    private int numeroCita;
    private LocalDate fecha;
    private String hora;
    private Especialidades especialidad;

    private static LinkedList<Medico> medicoCita;
    private static LinkedList<Paciente> pacienteCita;
    private static LinkedList<Sala> salaCita;

    public Cita(int numeroCita, Especialidades especialidad, LocalDate fecha, String hora) {
        this.numeroCita = numeroCita;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.hora = hora;

        medicoCita = new LinkedList<>();
        pacienteCita = new LinkedList<>();
        salaCita = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "Cita{" +
                "numeroCita=" + numeroCita +
                ", fecha=" + fecha +
                ", hora='" + hora + '\'' +
                ", medico=" + medicoCita +
                ", paciente=" + pacienteCita +
                '}';
    }

    public int getNumeroCita() {
        return numeroCita;
    }

    public void setNumeroCita(int numeroCita) {
        this.numeroCita = numeroCita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public LinkedList<Medico> getMedico() {
        return medicoCita;
    }

    public void setMedico(LinkedList<Medico> medico) {
        this.medicoCita = medico;
    }

    public LinkedList<Paciente> getPaciente() {
        return pacienteCita;
    }

    public static LinkedList<Medico> getMedicoCita() {
        return medicoCita;
    }

    public static void setMedicoCita(LinkedList<Medico> medicoCita) {
        Cita.medicoCita = medicoCita;
    }

    public Especialidades getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidades especialidad) {
        this.especialidad = especialidad;
    }

    public static LinkedList<Paciente> getPacienteCita() {
        return pacienteCita;
    }

    public static void setPacienteCita(LinkedList<Paciente> pacienteCita) {
        Cita.pacienteCita = pacienteCita;
    }

    public static LinkedList<Sala> getSalaCita() {
        return salaCita;
    }

    public static void setSalaCita(LinkedList<Sala> salaCita) {
        Cita.salaCita = salaCita;
    }

    public void setPaciente(LinkedList<Paciente> paciente) {
        this.pacienteCita = paciente;
    }

    public static void agregarPaciente(Paciente paciente){
        pacienteCita.add(paciente);
    }

    public static void agregarMedico(Medico medico){
        medicoCita.add(medico);
    }

}
