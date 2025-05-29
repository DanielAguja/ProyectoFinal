package co.edu.uniquindio.javafxtest.model;

import java.time.LocalDate;

public class Diagnostico {
private LocalDate fecha;
private String observacion;
private String tratamiento;
private Medico medico;

public Diagnostico(LocalDate fecha, String observacion, String tratamiento, Medico medico) {
    this.fecha = fecha;
    this.observacion = observacion;
    this.tratamiento = tratamiento;
    this.medico = medico;
}

    @Override
    public String toString() {
        return "Diagnostico{" +
                "fecha=" + fecha +
                ", observacion='" + observacion + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                '}';
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
}
