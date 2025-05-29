package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.model.Diagnostico;
import co.edu.uniquindio.javafxtest.model.Especialidades;
import co.edu.uniquindio.javafxtest.model.Paciente;
import co.edu.uniquindio.javafxtest.viewController.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Optional;

public class PacienteController {

    Main m = new Main();

    private HospitalController hospitalController = AppController.getHospitalController();

    private Paciente pacienteActual = hospitalController.getPacienteLogueado();

    @FXML
    private Button backButton;

    @FXML
    private Button editNombre;

    @FXML
    private Button editCedula;

    @FXML
    private Button editEmail;

    @FXML
    private Button editTelefono;

    @FXML
    private Label nombrePaciente;

    @FXML
    private Label cedulaPaciente;

    @FXML
    private Label correoPaciente;

    @FXML
    private Label telefonoPaciente;

    @FXML
    private TableView<Diagnostico> tablaHistorial;

    @FXML
    private TableColumn<Diagnostico, String> fechaHistorial;

    @FXML
    private TableColumn<Diagnostico, String> medicoHistorial;

    @FXML
    private TableColumn<Diagnostico, String> especialidadHistorial;

    public void initialize() {

        nombrePaciente.setText("Paciente: " + pacienteActual.getNombre());
        cedulaPaciente.setText("Cedula : "+pacienteActual.getDocumento());
        correoPaciente.setText("Correo: "+pacienteActual.getEmail());
        telefonoPaciente.setText("Telefono: "+pacienteActual.getTelefono());

        fechaHistorial.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        medicoHistorial.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMedico().getNombre())
        );

        especialidadHistorial.setCellValueFactory(cellData -> {
            Especialidades especialidad = cellData.getValue().getMedico().getEspecialidad();
            return new SimpleStringProperty(especialidad.name());
        });

        tablaHistorial.setRowFactory(tv -> {
            TableRow<Diagnostico> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Diagnostico diag = row.getItem();
                    mostrarVentanaEmergente(diag);
                }
            });
            return row;
        });

        if (pacienteActual != null) {
            ObservableList<Diagnostico> historial = FXCollections.observableArrayList(pacienteActual.getHistorial());
            tablaHistorial.setItems(historial);
        }
    }

    public void volver(javafx.event.ActionEvent event) throws IOException {
        m.changeScene("/co/edu/uniquindio/javafxtest/loginPaciente.fxml");
    }

    private void mostrarVentanaEmergente(Diagnostico diagnostico) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Detalle del Diagnóstico");
        alerta.setHeaderText("Fecha: " + diagnostico.getFecha());
        alerta.setContentText("Observación: " + diagnostico.getObservacion() +
                "\nTratamiento: " + diagnostico.getTratamiento());
        alerta.showAndWait();
    }

    public void recargarVentana() throws IOException {
        m.changeScene("/co/edu/uniquindio/javafxtest/gestionHistorial.fxml");
    }

    private String solicitarDato(String mensaje) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ingreso de datos");
        dialog.setHeaderText(null);
        dialog.setContentText(mensaje);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);

    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String encabezado, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    public void editarNombre(ActionEvent actionEvent) throws IOException {
        String nuevoNombre = solicitarDato("Ingrese el nuevo nombre:");
        if (nuevoNombre != null && !nuevoNombre.isBlank()) {
            pacienteActual.setNombre(nuevoNombre);
            nombrePaciente.setText("Nombre: " + nuevoNombre);
        }
    }

    public void editarCedula(ActionEvent actionEvent) throws IOException {
        String nuevaCedula = solicitarDato("Ingrese la nueva cedula:");
        if (nuevaCedula != null && !nuevaCedula.isBlank()) {
            pacienteActual.setDocumento(nuevaCedula);
            cedulaPaciente.setText("Cedula: " + nuevaCedula);
        }

    }

    public void editarEmail(ActionEvent actionEvent) {
        String nuevoCorreo = solicitarDato("Ingrese el nuevo correo:");
        if (nuevoCorreo != null && !nuevoCorreo.isBlank()) {
            pacienteActual.setEmail(nuevoCorreo);
            correoPaciente.setText("Correo: " + nuevoCorreo);
        }
    }

    public void editarTelefono(ActionEvent actionEvent) {
        String nuevoTelefono = solicitarDato("Ingrese el nuevo telefono:");
        if (nuevoTelefono != null && !nuevoTelefono.isBlank()) {
            pacienteActual.setTelefono(nuevoTelefono);
            telefonoPaciente.setText("Telefono: " + nuevoTelefono);
        }
    }
}
