package co.edu.uniquindio.javafxtest.model;

import com.sun.source.tree.BreakTree;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

abstract class Hospital {
    private String nombre;
    private String nit;

    private LinkedList<Paciente> listpacientes;
    private LinkedList<Medico> listmedicos;
    private LinkedList<Administrador> listadministradores;

    Hospital(String nombre, String nit) {
        this.nombre = nombre;
        this.nit = nit;
        listpacientes = new LinkedList<>();
        listmedicos = new LinkedList<>();
        listadministradores = new LinkedList<>();
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
    String telefono, Historial historial) {
        Paciente newpaciente = new Paciente(nombre, documento, email, telefono, historial);
        listpacientes.add(newpaciente);
        return listpacientes;
    }

    public LinkedList<Administrador> crearAdmin(String nombre, String documento, String email,
                                              String telefono, Historial historial) {
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

    public void generarDiagnostico(LocalDate fecha, String observacion, String tratamiento) {
        Diagnostico diagnostico = null;
        Diagnostico newdiagnostico = new Diagnostico(fecha, observacion, tratamiento);
        diagnostico = newdiagnostico;
        Historial.agregarDiagnostico(diagnostico);
    }

}
