package co.edu.uniquindio.javafxtest.model;

import java.time.LocalDate;

public class Cita {
    private int numeroCita;
    private LocalDate fecha;
    private String hora;
    private Medico medico;
    private Paciente paciente;

    public Cita(int numeroCita, LocalDate fecha, String hora, Medico medico) {
        this.numeroCita = numeroCita;
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "numeroCita=" + numeroCita +
                ", fecha=" + fecha +
                ", hora='" + hora + '\'' +
                ", medico=" + medico +
                ", paciente=" + paciente +
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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
