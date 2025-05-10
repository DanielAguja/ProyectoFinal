package co.edu.uniquindio.javafxtest.model;

import java.time.LocalDate;

public class Horario {
    private LocalDate fechaDia;
    private String horaInicio;
    private String horaFin;
    private Sala sala;

    public Horario(LocalDate fecha, String horaInicio,
                   String horaFin, Sala sala) {
        this.fechaDia = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "fechaDia=" + fechaDia +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaFin='" + horaFin + '\'' +
                ", sala=" + sala +
                '}';
    }

    public LocalDate getFechaDia() {
        return fechaDia;
    }

    public void setFechaDia(LocalDate fechaDia) {
        this.fechaDia = fechaDia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
