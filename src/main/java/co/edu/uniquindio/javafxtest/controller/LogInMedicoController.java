package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.viewController.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class LogInMedicoController {

    @FXML
    private Button logoutButton;

    @FXML
    private Button registerButton;

    public void historialPacientesMedicos(javafx.event.ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("/co/edu/uniquindio/javafxtest/gestionHistorial.fxml");
    }

    public void logOut(javafx.event.ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("/co/edu/uniquindio/javafxtest/hello-view.fxml");
    }
}

