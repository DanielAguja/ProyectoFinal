package co.edu.uniquindio.javafxtest.model;

import java.time.LocalDate;

public class Diagnostico {
private LocalDate fecha;
private String observacion;
private String tratamiento;

public Diagnostico(LocalDate fecha, String observacion, String tratamiento) {
    this.fecha = fecha;
    this.observacion = observacion;
    this.tratamiento = tratamiento;
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
