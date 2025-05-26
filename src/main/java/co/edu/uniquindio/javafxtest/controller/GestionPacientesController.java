package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.model.Paciente;
import co.edu.uniquindio.javafxtest.model.Usuario;
import co.edu.uniquindio.javafxtest.viewController.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.util.Optional;

public class GestionPacientesController {

    Main m = new Main();

    private HospitalController hospitalController = AppController.getHospitalController();

    @FXML
    private Button backButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button createButton;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Paciente> tablaPacientes;

    @FXML
    private TableColumn<Paciente, String> editNombre;

    @FXML
    private TableColumn<Paciente, String> editCedula;

    @FXML
    private TableColumn<Paciente, String> editEmail;

    @FXML
    private TableColumn<Paciente, String> editTelefono;

    @FXML
    public void initialize() {

        tablaPacientes.setItems(hospitalController.getPacientes());

        editNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        editCedula.setCellValueFactory(new PropertyValueFactory<>("documento"));
        editEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        editTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        editNombre.setResizable(false);
        editCedula.setResizable(false);
        editEmail.setResizable(false);
        editTelefono.setResizable(false);

        editNombre.setCellFactory(TextFieldTableCell.forTableColumn());
        editCedula.setCellFactory(TextFieldTableCell.forTableColumn());
        editEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        editTelefono.setCellFactory(TextFieldTableCell.forTableColumn());

        editNombre.setOnEditCommit(event -> {
            Usuario usuario = event.getRowValue();
            usuario.setNombre(event.getNewValue());
        });

        editCedula.setOnEditCommit(event -> {
            Usuario usuario = event.getRowValue();
            usuario.setDocumento(event.getNewValue());
        });

        editEmail.setOnEditCommit(event -> {
            Usuario usuario = event.getRowValue();
            usuario.setEmail(event.getNewValue());
        });

        editTelefono.setOnEditCommit(event -> {
            Usuario usuario = event.getRowValue();
            usuario.setTelefono(event.getNewValue());
        });

        ObservableList<Paciente> pacientes = FXCollections.observableArrayList(hospitalController.getPacientes());
        tablaPacientes.setItems(pacientes);
    }

    public void recargarVentana() throws IOException {
        m.changeScene("/co/edu/uniquindio/javafxtest/gestionPacientes.fxml");
    }

    public void backLogIn(javafx.event.ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("/co/edu/uniquindio/javafxtest/loginAdministrador.fxml");
    }

    public void crearPaciente() {
        String nombre = solicitarDato("Ingrese el Nombre del paciente:");
        if (nombre == null || nombre.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", null, "El nombre no puede estar vacío.");
            return;
        }

        String documento = solicitarDato("Ingrese el Documento (cédula):");
        if (documento == null || documento.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", null, "El documento no puede estar vacío.");
            return;
        }

        String email = solicitarDato("Ingrese el Email:");
        if (email == null || email.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", null, "El email no puede estar vacío.");
            return;
        }

        String telefono = solicitarDato("Ingrese el Teléfono:");
        if (telefono == null || telefono.trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", null, "El teléfono no puede estar vacío.");
            return;
        }

        Paciente nuevo = new Paciente(nombre, documento, email, telefono);
        boolean agregadoExitosamente = hospitalController.agregarPaciente(nuevo);

        if (!agregadoExitosamente) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al crear paciente", null,
                    "Ya existe un paciente con el mismo nombre, cédula, email o teléfono.");
        } else {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Paciente Creado", null,
                    "El paciente ha sido agregado exitosamente.");
        }

        try{
            recargarVentana();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public void buscarPaciente() {
        String documentoBuscado = solicitarDato("Ingrese el documento a buscar:");
        if (documentoBuscado.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", null, "Ingrese un documento valido.");
            return;
        }

        Paciente pacienteEncontrado = hospitalController.buscarPaciente(null, documentoBuscado);

        if (pacienteEncontrado != null) {
            int indice = tablaPacientes.getItems().indexOf(pacienteEncontrado);

            if (indice != -1) {
                tablaPacientes.getSelectionModel().select(indice);
                tablaPacientes.scrollTo(indice);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Paciente Encontrado", null,
                        "El paciente ha sido localizado y seleccionado en la tabla.");
            }
        } else {
            tablaPacientes.getSelectionModel().clearSelection();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Paciente No Encontrado", null,
                    "No se encontró ningún paciente con el documento: " + documentoBuscado);
        }
    }

    public void eliminarPaciente(){

        Paciente pacienteSeleccionado = tablaPacientes.getSelectionModel().getSelectedItem();

        if (pacienteSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", null, "Por favor, seleccione un paciente de la tabla para eliminar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Eliminación");
        confirmacion.setHeaderText("¿Está seguro de que desea eliminar a " + pacienteSeleccionado.getNombre() + "?");
        confirmacion.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            boolean eliminadoExitosamente = hospitalController.eliminarPaciente(pacienteSeleccionado);

            if (eliminadoExitosamente) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Eliminación Exitosa", null,
                        "El paciente " + pacienteSeleccionado.getNombre() + " ha sido eliminado correctamente.");
            }

            else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error al Eliminar", null,
                        "No se pudo eliminar el paciente. Inténtelo de nuevo.");
            }
        } else {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Eliminación Cancelada", null,
                    "La eliminación del paciente ha sido cancelada.");
        }
        try{
            recargarVentana();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

