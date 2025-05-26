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

    Hospital hospital = new Hospital("Santander", "12312321");

    LinkedList<Paciente> paciente = hospital.crearPaciente("Juan", "123", "juanitoGod@gmail.com", "123123213");
    LinkedList<Administrador> admin = hospital.crearAdmin("Pipe", "133", "juanchitofreefire@gmail.com", "12111123");


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

        // Si los campos están vacíos, manejarlo primero
        if (nombreIngresado.isEmpty() || documentoIngresado.isEmpty()) {
            wrongLogIn.setText("Por favor, ingresa tu Nombre y ID");
            return;
        }

        Usuario usuarioEncontrado = hospital.buscarUsuario(nombreIngresado, documentoIngresado);

        if (usuarioEncontrado != null) {
            Main m = new Main();
            wrongLogIn.setText("Inicio de sesión exitoso");

            if (usuarioEncontrado instanceof Administrador) {
                m.changeScene("/co/edu/uniquindio/javafxtest/loginAdministrador.fxml");
            } else if (usuarioEncontrado instanceof Medico) {
                m.changeScene("/co/edu/uniquindio/javafxtest/loginMedico.fxml");
            } else if (usuarioEncontrado instanceof Paciente) {
                m.changeScene("/co/edu/uniquindio/javafxtest/loginPaciente.fxml");
            }
        }
        else {
            wrongLogIn.setText("Nombre de usuario o ID incorrectos");
        }
    }
}
