package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;

public class HospitalController {

    private Administrador administradorLogueado;
    private Medico medicoLogueado;
    private Paciente pacienteLogueado;
    private final Hospital hospital;
    private final ObservableList<Paciente> listaPacientes = FXCollections.observableArrayList();
    private final ObservableList<Medico> listaMedicos = FXCollections.observableArrayList();
    private final ObservableList<Administrador> listaAdministradores = FXCollections.observableArrayList();

    public HospitalController() {
        hospital = new Hospital("Santander", "12312321");
        cargarDatosPrueba();
    }

    private void cargarDatosPrueba() {
        Paciente juan = hospital.crearPaciente("Juan", "123", "juan@correo.com", "3101234567");
        if (juan != null) {
            listaPacientes.add(juan);
        }
        Medico ana = hospital.crearMedico("Ana", "124", "ana@correo.com", "3107654321", Especialidades.CARDIOLOGIA);
        if (ana != null){
            listaMedicos.add(ana);
        }
        Medico axel = hospital.crearMedico("Axel", "111", "ax@correo.com", "3107676421", Especialidades.PEDIATRIA);
        if (axel != null) {
            listaMedicos.add(axel);
        }
        Administrador pipe = hospital.crearAdmin("Pipe", "133", "julian@correo.com", "3111111111");
        if (pipe != null){
            listaAdministradores.add(pipe);
        }
    }

    public void setAdminLogueado(Administrador administrador) {
        this.administradorLogueado = administrador;
    }

    public Administrador getAdminLogueado() {
        return administradorLogueado;
    }

    public void setMedicoLogueado(Medico medico) {
        this.medicoLogueado = medico;
    }

    public Medico getMedicoLogueado() {
        return medicoLogueado;
    }

    public void setPacienteLogueado(Paciente paciente) {
        this.pacienteLogueado = paciente;
    }

    public Paciente getPacienteLogueado() {
        return pacienteLogueado;
    }

    public ObservableList<Paciente> getPacientes() {
        return listaPacientes;
    }

    public ObservableList<Medico> getMedicos() {
        return listaMedicos;
    }

    public ObservableList<Administrador> getAdministradores() {
        return listaAdministradores;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public Usuario buscarUsuario(String nombre, String documento) {
        return hospital.buscarUsuario(nombre, documento);
    }

    public Paciente buscarPaciente(String nombre, String documento) {
        for (Paciente p : listaPacientes) {
            if (p.getDocumento().equals(documento)) {
                return p;
            }
        }
        return null;
    }


    public Medico buscarMedico(String nombre, String documento) {
        for (Medico m : listaMedicos) {
            if (m.getDocumento().equals(documento)) {
                return m;
            }
        }
        return null;
    }


    public Administrador buscarAdmin(String nombre, String documento) {
        for (Administrador a : listaAdministradores) {
            if (a.getDocumento().equals(documento)) {
                return a;
            }
        }
        return null;
    }

    public boolean eliminarPaciente(Paciente paciente) {
        boolean eliminadoDelModelo = hospital.eliminarPaciente(paciente.getDocumento());

        if (eliminadoDelModelo) {
            listaPacientes.remove(paciente);
            return true;
        }
        return false;
    }

    public boolean eliminarMedico(Medico medico) {
        boolean eliminadoDelModelo = hospital.eliminarMedico(medico.getDocumento());

        if (eliminadoDelModelo) {
            listaMedicos.remove(medico);
            return true;
        }
        return false;
    }

    public boolean eliminarAdmin(Administrador administrador) {
        boolean eliminadoDelModelo = hospital.eliminarAdmin(administrador.getDocumento());

        if (eliminadoDelModelo) {
            listaAdministradores.remove(administrador);
            return true;
        }
        return false;
    }

    public boolean agregarMedico(Medico medico) {
        if (hospital.buscarUsuario(null, medico.getDocumento()) != null) {

            if (hospital.existeEmailPaciente(medico.getEmail())) {

                return false;
            }

            if (hospital.existeTelefonoPaciente(medico.getTelefono())) {

                return false;
            }

            if (hospital.existeNombrePaciente(medico.getNombre())) {

                return false;
            }

            if (hospital.existeDocumentoPaciente(medico.getDocumento())) {

                return false;
            }

            return false;
        }

        Medico medicoCreadoEnHospital = hospital.crearMedico(medico.getNombre(), medico.getDocumento(), medico.getEmail(), medico.getTelefono(), medico.getEspecialidad());

        if (medico != null) {

            listaMedicos.add(medicoCreadoEnHospital);
            return true;
        }
        return false;
    }

    public boolean agregarPaciente(Paciente paciente) {
        if (hospital.buscarUsuario(null, paciente.getDocumento()) != null) {

            if (hospital.existeEmailPaciente(paciente.getEmail())) {

                return false;
            }

            if (hospital.existeTelefonoPaciente(paciente.getTelefono())) {

                return false;
            }

            if (hospital.existeNombrePaciente(paciente.getNombre())) {

                return false;
            }

            if (hospital.existeDocumentoPaciente(paciente.getDocumento())) {

                return false;
            }

            return false;
        }

        Paciente pacienteCreadoEnHospital = hospital.crearPaciente(paciente.getNombre(), paciente.getDocumento(), paciente.getEmail(), paciente.getTelefono());

        if (paciente != null) {

            listaPacientes.add(pacienteCreadoEnHospital);
            return true;
        }
        return false;
    }

    public boolean agregarAdmin(Administrador administrador) {
        if (hospital.buscarUsuario(null, administrador.getDocumento()) != null) {

            if (hospital.existeEmailPaciente(administrador.getEmail())) {

                return false;
            }

            if (hospital.existeTelefonoPaciente(administrador.getTelefono())) {

                return false;
            }

            if (hospital.existeNombrePaciente(administrador.getNombre())) {

                return false;
            }

            if (hospital.existeDocumentoPaciente(administrador.getDocumento())) {

                return false;
            }

            return false;
        }

        Administrador administradorCreadoEnHospital = hospital.crearAdmin(administrador.getNombre(), administrador.getDocumento(), administrador.getEmail(), administrador.getTelefono());

        if (administrador != null) {

            listaAdministradores.add(administradorCreadoEnHospital);
            return true;
        }
        return false;
    }

//    public void agregarPaciente(Paciente paciente) {
//        listaPacientes.add(paciente);
//    }

    public void crearPaciente(String nombre, String documento, String email, String telefono) {

        Paciente paciente = hospital.crearPaciente(nombre, documento, email, telefono);

        if (paciente != null) {
            listaPacientes.add(paciente);
        }
    }

    public void crearMedico(String nombre, String documento, String email, String telefono, Especialidades especialidad) {

        Medico medico= hospital.crearMedico(nombre, documento, email, telefono, especialidad);

        if (medico != null) {
            listaMedicos.add(medico);
        }
    }

    public void crearAdministrador(String nombre, String documento, String email, String telefono) {

        Administrador administrador = hospital.crearAdmin(nombre, documento, email, telefono);

        if (administrador != null) {
            listaAdministradores.add(administrador);
        }
    }
}
