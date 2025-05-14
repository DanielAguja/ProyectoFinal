package co.edu.uniquindio.javafxtest.model;

import jdk.jshell.Diag;

import java.time.LocalDate;
import java.util.LinkedList;

public class Hospital {
    private String nombre;
    private String nit;

    private LinkedList<Cita> listCitas;
    private LinkedList<Sala> listsalas;
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

    public LinkedList<Cita> getListCitas() {
        return listCitas;
    }

    public void setListCitas(LinkedList<Cita> listCitas) {
        this.listCitas = listCitas;
    }

    public LinkedList<Sala> getListsalas() {
        return listsalas;
    }

    public void setListsalas(LinkedList<Sala> listsalas) {
        this.listsalas = listsalas;
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

    public boolean actualizarDatosPaciente(String documentoBuscar,
                                                        String nombre, String documento,
                                                        String email,
                                                        String telefono){
        boolean flag = false;

        for(Paciente paciente : listpacientes){
            if (Paciente.getDocumento().equals(documentoBuscar)){
                paciente.setNombre(nombre);
                paciente.setDocumento(documento);
                paciente.setEmail(email);
                paciente.setTelefono(telefono);
            }
        }
        return flag;
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


    public boolean crearSala(int numeroSala, EstadoSala estadoSala){
        boolean flag = false;
        Sala nuevaSala = new Sala(numeroSala, estadoSala);
        listsalas.add(nuevaSala);
        return flag;
    }

    public boolean solicitudCita(String idPaciente, Especialidades especialidad, String nombreMedico,
                                 int numeroCita, LocalDate fecha, String hora){
        Cita nuevaCita = new Cita(numeroCita, especialidad , fecha, hora);
        listCitas.add(nuevaCita);

        boolean flag = false;

        for (Paciente paciente : listpacientes){
            if (Paciente.getDocumento().equals(idPaciente)) {
                Cita.agregarPaciente(paciente);
                Paciente.agregarCita(nuevaCita);
            }
            else {
                break;
            }
        }

        for (Medico medico : listmedicos){
            if (Medico.getNombre().equals(nombreMedico)){
                Cita.agregarMedico(medico);
            }
            else {
                break;
            }
        }

        return flag;
    }

    public boolean cancelarCita(int numeroCita){
        boolean flag = false;

        for (Cita cita : listCitas){
            if (cita.getNumeroCita() == numeroCita){
                listCitas.remove(cita);
            }
        }
        return flag;
    }
}
