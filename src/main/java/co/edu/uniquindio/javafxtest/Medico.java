package co.edu.uniquindio.javafxtest;

public class Medico extends Usuario {
    private Especialidades especialidad;
    private List<Horarios> horarios;

    public Medico(String nombre, String documento, String email, String telefono,
                  Especialidades especialidad, List<Horarios> horarios) {
        super(nombre, documento, email, telefono);
        this.especialidad = especialidad;
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "especialidad=" + especialidad +
                ", horarios=" + horarios +
                ", nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public Especialidades getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidades especialidad) {
        this.especialidad = especialidad;
    }

    public List<Horarios> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }
}
