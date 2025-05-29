package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.model.Diagnostico;
import co.edu.uniquindio.javafxtest.model.Especialidades;
import co.edu.uniquindio.javafxtest.model.Medico;
import co.edu.uniquindio.javafxtest.model.Paciente;
import co.edu.uniquindio.javafxtest.viewController.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import jdk.jshell.Diag;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class GestionHistorialMedicoController {

    Main m = new Main();

    private HospitalController hospitalController = AppController.getHospitalController();

    private Paciente pacienteActual;

    private Medico medicoLogueado = hospitalController.getMedicoLogueado();

    @FXML
    private Button backButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button diagnosticButton;

    @FXML
    private TableView<Diagnostico> tablaHistorial;

    @FXML
    private TableColumn<Diagnostico, LocalDate> fechaHistorial;

    @FXML
    private TableColumn<Paciente, String> pacienteHistorial;

    @FXML
    private TableColumn<Diagnostico, String> medicoHistorial;

    @FXML
    private TableColumn<Diagnostico, String> especialidadMedico;



    public void initialize() {
        fechaHistorial.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        medicoHistorial.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMedico().getNombre())
        );

        especialidadMedico.setCellValueFactory(cellData -> {
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
    }

    public void volverInicio(javafx.event.ActionEvent actionEvent) throws IOException {
        m.changeScene("/co/edu/uniquindio/javafxtest/loginMedico.fxml");
    }

    public void asignarPaciente(javafx.event.ActionEvent actionEvent) throws IOException {
        String documento = solicitarDato("Ingrese el documento del paciente:");
        Paciente paciente = hospitalController.buscarPaciente(null, documento);

        if (paciente != null) {
            pacienteActual = paciente;
            ObservableList<Diagnostico> historial = FXCollections.observableArrayList(paciente.getHistorial());
            tablaHistorial.setItems(historial);
            pacienteHistorial.setText(pacienteActual.getNombre());
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Paciente no encontrado", null, "No se encontró un paciente con ese documento.");
        }
    }

    public void registroDiagnostico(javafx.event.ActionEvent actionEvent) throws IOException {
        if (pacienteActual == null) {
            mostrarAlerta(Alert.AlertType.WARNING,"Error", null, "Debe seleccionar un paciente antes.");
            return;
        }

        TextInputDialog observacionDialog = new TextInputDialog();
        observacionDialog.setHeaderText("Ingrese la observación:");
        Optional<String> obsResult = observacionDialog.showAndWait();

        if (obsResult.isEmpty()) return;

        TextInputDialog tratamientoDialog = new TextInputDialog();
        tratamientoDialog.setHeaderText("Ingrese el tratamiento:");
        Optional<String> tratResult = tratamientoDialog.showAndWait();

        if (tratResult.isEmpty()) return;

        Diagnostico nuevo = new Diagnostico(
                LocalDate.now(),
                obsResult.get(),
                tratResult.get(),
                medicoLogueado
        );

        pacienteActual.agregarHistorial(nuevo);

        tablaHistorial.getItems().add(nuevo);
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

}
