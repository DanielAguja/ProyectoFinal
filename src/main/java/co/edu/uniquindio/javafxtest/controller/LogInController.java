package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.viewController.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import java.io.IOException;

public class LogInController {

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

        Main m = new Main();
        if(usuario.getText().equals("sexo") && password.getText().equals("123")){
            wrongLogIn.setText("Success");

            m.changeScene("/co/edu/uniquindio/javafxtest/afterLogin.fxml");
        }

        else if(usuario.getText().isEmpty() && password.getText().isEmpty()){
            wrongLogIn.setText("Please enter your Data");
        }

        else{
            wrongLogIn.setText("Wrong Username or Password");
        }
    }


}
