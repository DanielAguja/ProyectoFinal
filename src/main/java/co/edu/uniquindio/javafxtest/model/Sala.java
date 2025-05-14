package co.edu.uniquindio.javafxtest.model;

import java.util.LinkedList;

class Sala {
    private int numeroSala;
    private EstadoSala estadoSala;

    private LinkedList<Medico> medicoSala;
    private LinkedList<Paciente> pacienteSala;

    public Sala(int numeroSala, EstadoSala estadoSala) {
        this.estadoSala = estadoSala;
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

    public EstadoSala getEstadoSala() {
        return estadoSala;
    }

    public void setEstadoSala(EstadoSala estadoSala) {
        this.estadoSala = estadoSala;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "numeroSala=" + numeroSala +
                ", estadoSala=" + estadoSala +
                ", medicoSala=" + medicoSala +
                ", pacienteSala=" + pacienteSala +
                '}';
    }

    //    public agregarPaciente()
//
//    public agregarMedico()
}
