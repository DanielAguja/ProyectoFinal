module co.edu.uniquindio.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.javafxtest to javafx.fxml;
    exports co.edu.uniquindio.javafxtest;
}