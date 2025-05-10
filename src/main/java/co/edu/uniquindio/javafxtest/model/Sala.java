package co.edu.uniquindio.javafxtest.model;

abstract class Sala {
    private int numeroSala;

    public Sala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "numeroSala=" + numeroSala +
                '}';
    }
}
