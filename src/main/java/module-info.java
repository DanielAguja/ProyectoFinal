module co.edu.uniquindio.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jshell;
    requires java.desktop;


    exports co.edu.uniquindio.javafxtest.model;
    opens co.edu.uniquindio.javafxtest.model to javafx.fxml;
    exports co.edu.uniquindio.javafxtest.controller;
    opens co.edu.uniquindio.javafxtest.controller to javafx.fxml;
    exports co.edu.uniquindio.javafxtest.viewController;
    opens co.edu.uniquindio.javafxtest.viewController to javafx.fxml;
}