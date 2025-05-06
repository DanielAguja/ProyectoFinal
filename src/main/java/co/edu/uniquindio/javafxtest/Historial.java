package co.edu.uniquindio.javafxtest;

import co.edu.uniquindio.Diagnostico;

public class Historial {
    private Diagnostico diagnostico;

    public Historial(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    @Override
    public String toString() {
        return "Historial{" +
                "diagnostico=" + diagnostico +
                '}';
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }
}
