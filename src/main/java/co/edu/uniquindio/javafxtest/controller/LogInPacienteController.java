package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.viewController.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class LogInPacienteController {
    @FXML
    private Button button;

    public void userLogOut(javafx.event.ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("/co/edu/uniquindio/javafxtest/hello-view.fxml");
    }
}
