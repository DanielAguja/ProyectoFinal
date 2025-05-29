package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.viewController.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class LogInPacienteController {

    Main m = new Main();

    @FXML
    private Button button;

    @FXML
    private Button historialPaciente;

    public void verHistorial(javafx.event.ActionEvent actionEvent) throws IOException {
        m.changeScene("/co/edu/uniquindio/javafxtest/historialPaciente.fxml");
    }

    public void userLogOut(javafx.event.ActionEvent event) throws IOException {
        m.changeScene("/co/edu/uniquindio/javafxtest/hello-view.fxml");
    }
}
