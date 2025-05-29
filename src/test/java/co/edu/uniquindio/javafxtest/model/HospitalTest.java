package co.edu.uniquindio.javafxtest.model;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
public class HospitalTest {
    /**
     * Instancia para el manejo de logs
     */
    @Test

    public void registroHospitalTest() {
        Hospital newHospital = new Hospital("Ciudad Juarez", "12312312321");

        Paciente hospitalPaciente = newHospital.crearPaciente("Juan",
                "12", "juan@gmail","651212");

        Medico hospitalMedico = newHospital.crearMedico("Ana",
                "122", "yuan@gmail","651211232", Especialidades.CARDIOLOGIA);

        boolean diagnosticoPaciente = newHospital.generarDiagnostico("12",
                LocalDate.of(1232, 10, 15),
                "Resfriado Común", "Acetaminofen Diaria",hospitalMedico);

        LinkedList<Diagnostico> historia = newHospital.mostrarHistorial("12");

        System.out.println(historia);


}}