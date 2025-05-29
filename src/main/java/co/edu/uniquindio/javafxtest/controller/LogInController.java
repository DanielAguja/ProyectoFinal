package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.model.Hospital;
import co.edu.uniquindio.javafxtest.model.Administrador;
import co.edu.uniquindio.javafxtest.model.Medico;
import co.edu.uniquindio.javafxtest.model.Paciente;
import co.edu.uniquindio.javafxtest.model.Usuario;
import co.edu.uniquindio.javafxtest.viewController.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.LinkedList;

public class LogInController {

    private HospitalController hospitalController = AppController.getHospitalController();

    public LogInController() {

    }

    @FXML
    private Button button;

    @FXML
    private Label wrongLogIn;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField password;

    public void userLogIn(javafx.event.ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {

        String nombreIngresado = usuario.getText();
        String documentoIngresado = password.getText();

        if (nombreIngresado.isEmpty() || documentoIngresado.isEmpty()) {
            wrongLogIn.setText("Por favor, ingresa tu Nombre y ID");
            return;
        }

        Administrador administradorEncontrado = hospitalController.buscarAdmin(nombreIngresado, documentoIngresado);

        Medico medicoEncontrado = hospitalController.buscarMedico(nombreIngresado, documentoIngresado);

        Paciente pacienteEncontrado = hospitalController.buscarPaciente(nombreIngresado, documentoIngresado);

        Usuario usuarioEncontrado = hospitalController.buscarUsuario(nombreIngresado, documentoIngresado);

        if (usuarioEncontrado != null) {
            Main m = new Main();
            wrongLogIn.setText("Inicio de sesión exitoso");

            if (usuarioEncontrado instanceof Administrador) {
                hospitalController.setAdminLogueado(administradorEncontrado);
                m.changeScene("/co/edu/uniquindio/javafxtest/loginAdministrador.fxml");
            } else if (usuarioEncontrado instanceof Medico) {
                hospitalController.setMedicoLogueado(medicoEncontrado);
                m.changeScene("/co/edu/uniquindio/javafxtest/loginMedico.fxml");
            } else if (usuarioEncontrado instanceof Paciente) {
                hospitalController.setPacienteLogueado(pacienteEncontrado);
                m.changeScene("/co/edu/uniquindio/javafxtest/loginPaciente.fxml");
            }
        }
        else {
            wrongLogIn.setText("Nombre de usuario o ID incorrectos");
        }
    }
}
