package co.edu.uniquindio.javafxtest.model;

import jdk.jshell.Diag;

import java.time.LocalDate;
import java.util.Iterator;
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

    public Paciente crearPaciente(String nombre, String documento, String email, String telefono) {
        if (buscarUsuario(null, documento) != null) {
            System.out.println("Error: Ya existe un usuario con el documento " + documento);
            return null; //
        }
        Paciente newpaciente = new Paciente(nombre, documento, email, telefono);
        listpacientes.add(newpaciente);
        return newpaciente;
    }

    public boolean eliminarPaciente(String documento){
        Iterator<Paciente> it = listpacientes.iterator();
        while (it.hasNext()){
            Paciente paciente = it.next();
            if(paciente.getDocumento().equals(documento)){
                it.remove();
                return true;
            }
        }
        return false;
    }


    public LinkedList<Paciente> actualizarDatosPaciente(String documentoBuscar,
                                                        String nombre, String documento,
                                                        String email,
                                                        String telefono){
        boolean flag = false;

        for(Paciente paciente : listpacientes){
            if (paciente.getDocumento().equals(documentoBuscar)){
                paciente.setNombre(nombre);
                paciente.setDocumento(documento);
                paciente.setEmail(email);
                paciente.setTelefono(telefono);
            }
        }
        return listpacientes;
    }

    public Administrador crearAdmin(String nombre, String documento, String email, String telefono) {
        if (buscarUsuario(null, documento) != null) {
            System.out.println("Error: Ya existe un usuario con el documento " + documento);
            return null;
        }
        Administrador newadmin = new Administrador(nombre, documento, email, telefono);
        listadministradores.add(newadmin);
        return newadmin;
    }

    public boolean eliminarAdmin(String documento){
        Iterator<Administrador> it = listadministradores.iterator();
        while (it.hasNext()){
            Administrador administrador = it.next();
            if(administrador.getDocumento().equals(documento)){
                it.remove();
                return true;
            }
        }
        return false; // No se encontró el paciente
    }

    public Medico crearMedico(String nombre, String documento, String email, String telefono, Especialidades especialidad) {
        if (buscarUsuario(null, documento) != null) {
            System.out.println("Error: Ya existe un usuario con el documento " + documento);
            return null;
        }
        Medico newmedico = new Medico(nombre, documento, email, telefono, especialidad);
        listmedicos.add(newmedico);
        return newmedico;
    }

    public boolean eliminarMedico(String documento){
        Iterator<Medico> it = listmedicos.iterator();
        while (it.hasNext()){
            Medico medico = it.next();
            if(medico.getDocumento().equals(documento)){
                it.remove();
                return true;
            }
        }
        return false; // No se encontró el paciente
    }

    public boolean generarDiagnostico(String documento, LocalDate fecha, String observacion, String tratamiento, Medico medico) {
        boolean flag = false;
        Diagnostico diagnostico = null;
        Diagnostico newdiagnostico = new Diagnostico(fecha, observacion, tratamiento, medico);
        diagnostico = newdiagnostico;
        for (Paciente paciente : listpacientes) {
            if(paciente.getDocumento().equals(documento)) {
                paciente.agregarHistorial(diagnostico);
            }
            else {break;}
        }
        return flag;
    }

    public LinkedList<Diagnostico> mostrarHistorial(String documento){
        LinkedList<Diagnostico> chichotas = null;
        for (Paciente paciente : listpacientes) {
            if(paciente.getDocumento().equals(documento)) {
                chichotas = paciente.getHistorial();
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

//    public boolean solicitudCita(String idPaciente, Especialidades especialidad, String nombreMedico,
//                                 int numeroCita, LocalDate fecha, String hora){
//        Cita nuevaCita = new Cita(numeroCita, especialidad , fecha, hora);
//        listCitas.add(nuevaCita);
//
//        boolean flag = false;
//
//        for (Paciente paciente : listpacientes){
//            if (paciente.getDocumento().equals(idPaciente)) {
//                Cita.agregarPaciente(paciente);
//                Paciente.agregarCita(nuevaCita);
//            }
//            else {
//                break;
//            }
//        }
//
//        for (Medico medico : listmedicos){
//            if (medico.getNombre().equals(nombreMedico)){
//                Cita.agregarMedico(medico);
//            }
//            else {
//                break;
//            }
//        }
//
//        return flag;
//    }

    public boolean cancelarCita(int numeroCita){
        boolean flag = false;

        for (Cita cita : listCitas){
            if (cita.getNumeroCita() == numeroCita){
                listCitas.remove(cita);
            }
        }
        return flag;
    }

    public Usuario buscarUsuario(String nombre, String documento) {
        for (Administrador admin : listadministradores) {
            if (admin.getNombre().equals(nombre) && admin.getDocumento().equals(documento)) {
                return admin;
            }
        }

        for (Medico medico : listmedicos) {
            if (medico.getNombre().equals(nombre) && medico.getDocumento().equals(documento)) {
                return medico;
            }
        }

        for (Paciente paciente : listpacientes) {
            if (paciente.getNombre().equals(nombre) && paciente.getDocumento().equals(documento)) {
                return paciente;
            }
        }
        return null;
    }

    public boolean existeNombrePaciente(String nombre) {
        for (Paciente p : listpacientes) {
            if (p.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeDocumentoPaciente(String documento) {
        for (Paciente p : listpacientes) {
            if (p.getDocumento().equals(documento)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeEmailPaciente(String email) {
        for (Paciente p : listpacientes) {
            if (p.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeTelefonoPaciente(String telefono) {
        for (Paciente p : listpacientes) {
            if (p.getTelefono().equals(telefono)) {
                return true;
            }
        }
        return false;
    }
}

