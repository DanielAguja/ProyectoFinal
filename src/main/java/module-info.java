module co.edu.uniquindio.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;


    opens co.edu.uniquindio.javafxtest to javafx.fxml;
    exports co.edu.uniquindio.javafxtest;
    exports co.edu.uniquindio.javafxtest.model;
    opens co.edu.uniquindio.javafxtest.model to javafx.fxml;
    exports co.edu.uniquindio.javafxtest.controller;
    opens co.edu.uniquindio.javafxtest.controller to javafx.fxml;
}