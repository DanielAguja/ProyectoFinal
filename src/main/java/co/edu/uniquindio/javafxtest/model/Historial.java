package co.edu.uniquindio.javafxtest.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Historial {
    private LinkedList<Diagnostico> diagnostico;

    public Historial() {
       diagnostico = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "Historial{" +
                "diagnostico=" + diagnostico +
                '}';
    }

    public LinkedList<Diagnostico> getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(LinkedList<Diagnostico> diagnostico) {
        this.diagnostico = diagnostico;
    }

    public static void agregarDiagnostico(Diagnostico diagnostico) {
        this.diagnostico.add(diagnostico);
    }
}
