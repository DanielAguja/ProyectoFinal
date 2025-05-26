package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;

public class HospitalController {

    private final Hospital hospital;
    private final ObservableList<Paciente> listaPacientes = FXCollections.observableArrayList();

    public HospitalController() {
        hospital = new Hospital("Santander", "12312321");
        cargarDatosPrueba();
    }

    private void cargarDatosPrueba() {
        Paciente juan = hospital.crearPaciente("Juan", "123", "juan@correo.com", "3101234567");
        if (juan != null) {
            listaPacientes.add(juan);
        }
        hospital.crearMedico("Ana", "124", "ana@correo.com", "3107654321", Especialidades.CARDIOLOGIA);
        hospital.crearAdmin("Pipe", "133", "julian@correo.com", "3111111111");
    }

    public ObservableList<Paciente> getPacientes() {
        return listaPacientes;
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

    public boolean eliminarPaciente(Paciente paciente) {
        boolean eliminadoDelModelo = hospital.eliminarPaciente(paciente.getDocumento());

        if (eliminadoDelModelo) {
            listaPacientes.remove(paciente);
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

        if (pacienteCreadoEnHospital != null) {

            listaPacientes.add(pacienteCreadoEnHospital);
            return true;
        }
        return false;
    }

//    public void agregarPaciente(Paciente paciente) {
//        listaPacientes.add(paciente);
//    }

    public void crearPaciente(String nombre, String documento, String email, String telefono) {

        Paciente pacienteCreadoEnHospital = hospital.crearPaciente(nombre, documento, email, telefono);

        if (pacienteCreadoEnHospital != null) {
            listaPacientes.add(pacienteCreadoEnHospital);
        }
    }
}
