package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.model.Administrador;
import co.edu.uniquindio.javafxtest.model.Usuario;
import co.edu.uniquindio.javafxtest.viewController.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.util.Optional;

public class GestionAdministradoresController {

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
    private TableView<Administrador> tablaAdministradores;

    @FXML
    private TableColumn<Administrador, String> editNombre;

    @FXML
    private TableColumn<Administrador, String> editCedula;

    @FXML
    private TableColumn<Administrador, String> editEmail;

    @FXML
    private TableColumn<Administrador, String> editTelefono;

    @FXML
    public void initialize() {

        tablaAdministradores.setItems(hospitalController.getAdministradores());

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

        ObservableList<Administrador> administradores = FXCollections.observableArrayList(hospitalController.getAdministradores());
        tablaAdministradores.setItems(administradores);
    }

    public void recargarVentana() throws IOException {
        m.changeScene("/co/edu/uniquindio/javafxtest/gestionAdministradores.fxml");
    }

    public void backLogIn(javafx.event.ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("/co/edu/uniquindio/javafxtest/loginAdministrador.fxml");
    }

    public void crearAdministrador() {
        String nombre = solicitarDato("Ingrese el Nombre del administrador:");
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

        Administrador nuevo = new Administrador(nombre, documento, email, telefono);
        boolean agregadoExitosamente = hospitalController.agregarAdmin(nuevo);

        if (!agregadoExitosamente) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al crear el administrador", null,
                    "Ya existe un administrador con el mismo nombre, cédula, email o teléfono.");
        } else {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Administrador Creado", null,
                    "El administrador ha sido agregado exitosamente.");
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

    public void buscarAdministrador() {
        String documentoBuscado = solicitarDato("Ingrese el documento a buscar:");
        if (documentoBuscado.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", null, "Ingrese un documento valido.");
            return;
        }

        Administrador administradorEncontrado = hospitalController.buscarAdmin(null, documentoBuscado);

        if (administradorEncontrado != null) {
            int indice = tablaAdministradores.getItems().indexOf(administradorEncontrado);

            if (indice != -1) {
                tablaAdministradores.getSelectionModel().select(indice);
                tablaAdministradores.scrollTo(indice);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Administrador Encontrado", null,
                        "El administrador ha sido localizado y seleccionado en la tabla.");
            }
        } else {
            tablaAdministradores.getSelectionModel().clearSelection();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Administrador No Encontrado", null,
                    "No se encontró ningún administrador con el documento: " + documentoBuscado);
        }
    }

    public void eliminarAdmin() throws IOException {

        Administrador adminSeleccionado = tablaAdministradores.getSelectionModel().getSelectedItem();

        if (adminSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", null, "Por favor, seleccione un administrador de la tabla para eliminar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Eliminación");
        confirmacion.setHeaderText("¿Está seguro de que desea eliminar a " + adminSeleccionado.getNombre() + "?");
        confirmacion.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            boolean eliminadoExitosamente = hospitalController.eliminarAdmin(adminSeleccionado);

            if (eliminadoExitosamente) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Eliminación Exitosa", null,
                        "El administrador " + adminSeleccionado.getNombre() + " ha sido eliminado correctamente.");
            }
            else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error al Eliminar", null,
                        "No se pudo eliminar el administrador. Inténtelo de nuevo.");
            }
        } else {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Eliminación Cancelada", null,
                    "La eliminación del administrador ha sido cancelada.");
        }

        if (adminSeleccionado.equals(hospitalController.getAdminLogueado())) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Cierre de sesión", null,
                    "Se eliminará su propia cuenta. La sesión se cerrará.");
            hospitalController.setAdminLogueado(null);
            m.changeScene("/co/edu/uniquindio/javafxtest/hello-view.fxml");
            return;
        }

        try{
            recargarVentana();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

