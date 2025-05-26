package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.viewController.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitMenuButton;

import java.io.IOException;

public class LogInAdministradorController {

    Main m = new Main();

    public LogInAdministradorController() {}

    @FXML
    private SplitMenuButton gestionUsuarios;

    public void gestionPacientes(ActionEvent event) throws IOException {
        m.changeScene("/co/edu/uniquindio/javafxtest/gestionPacientes.fxml");
    }

    public void gestionMedicos(ActionEvent event) throws IOException {
        m.changeScene("/co/edu/uniquindio/javafxtest/gestionMedicos.fxml");
    }

    public void gestionAdministradores(ActionEvent event) throws IOException {
        m.changeScene("/co/edu/uniquindio/javafxtest/gestionAdministradores.fxml");
    }







}
