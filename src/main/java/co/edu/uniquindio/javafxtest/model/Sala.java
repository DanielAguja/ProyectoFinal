package co.edu.uniquindio.javafxtest.model;

import java.util.LinkedList;

class Sala {
    private int numeroSala;

    private LinkedList<Medico> medicoSala;
    private LinkedList<Paciente> pacienteSala;

    public Sala(int numeroSala) {

        this.numeroSala = numeroSala;
        medicoSala = new LinkedList<>();
        pacienteSala = new LinkedList<>();
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public LinkedList<Medico> getMedicoSala() {
        return medicoSala;
    }

    public void setMedicoSala(LinkedList<Medico> medicoSala) {
        this.medicoSala = medicoSala;
    }

    public LinkedList<Paciente> getPacienteSala() {
        return pacienteSala;
    }

    public void setPacienteSala(LinkedList<Paciente> pacienteSala) {
        this.pacienteSala = pacienteSala;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "numeroSala=" + numeroSala +
                '}';
    }

//    public agregarPaciente()
//
//    public agregarMedico()
}
