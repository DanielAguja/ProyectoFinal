package co.edu.uniquindio.javafxtest.model;

import jdk.jshell.Diag;

import java.time.LocalDate;
import java.util.LinkedList;

public class Hospital {
    private String nombre;
    private String nit;

    private LinkedList<Paciente> listpacientes;
    private LinkedList<Medico> listmedicos;
    private LinkedList<Administrador> listadministradores;

    public Hospital(String nombre, String nit) {
        this.nombre = nombre;
        this.nit = nit;
        listpacientes = new LinkedList<>();
        listmedicos = new LinkedList<>();
        listadministradores = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "nit='" + nit + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public LinkedList<Paciente> getListpacientes() {
        return listpacientes;
    }

    public void setListpacientes(LinkedList<Paciente> listpacientes) {
        this.listpacientes = listpacientes;
    }

    public LinkedList<Medico> getListmedicos() {
        return listmedicos;
    }

    public void setListmedicos(LinkedList<Medico> listmedicos) {
        this.listmedicos = listmedicos;
    }

    public LinkedList<Administrador> getListadministradores() {
        return listadministradores;
    }

    public void setListadministradores(LinkedList<Administrador> listadministradores) {
        this.listadministradores = listadministradores;
    }

    public LinkedList<Paciente> crearPaciente(String nombre, String documento, String email,
    String telefono) {
        Paciente newpaciente = new Paciente(nombre, documento, email, telefono);
        listpacientes.add(newpaciente);
        return listpacientes;
    }

    public LinkedList<Administrador> crearAdmin(String nombre, String documento, String email,
                                              String telefono) {
        Administrador newadmin = new Administrador(nombre, documento, email, telefono);
        listadministradores.add(newadmin);
        return listadministradores;
    }

    public LinkedList<Medico> crearMedico(String nombre, String documento, String email,
                                              String telefono, Especialidades especialidad) {
        Medico newmedico = new Medico(nombre, documento, email, telefono, especialidad);
        listmedicos.add(newmedico);
        return listmedicos;
    }

    public boolean generarDiagnostico(String documento, LocalDate fecha, String observacion, String tratamiento) {
        boolean flag = false;
        Diagnostico diagnostico = null;
        Diagnostico newdiagnostico = new Diagnostico(fecha, observacion, tratamiento);
        diagnostico = newdiagnostico;
        for (Paciente paciente : listpacientes) {
            if(Paciente.getDocumento().equals(documento)) {
                Paciente.agregarHistorial(diagnostico);
            }
            else {break;}
        }
        return flag;
    }

    public LinkedList<Diagnostico> mostrarHistorial(String documento){
        LinkedList<Diagnostico> chichotas = null;
        for (Paciente paciente : listpacientes) {
            if(Paciente.getDocumento().equals(documento)) {
                chichotas = Paciente.getHistorial();
            }
        }
        return chichotas;
    }



}