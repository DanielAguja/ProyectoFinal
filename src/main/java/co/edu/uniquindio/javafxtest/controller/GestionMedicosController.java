package co.edu.uniquindio.javafxtest.controller;

import co.edu.uniquindio.javafxtest.model.Especialidades;
import co.edu.uniquindio.javafxtest.model.Medico;
import co.edu.uniquindio.javafxtest.model.Paciente;
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

public class GestionMedicosController {

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
    private TableView<Medico> tablaMedicos;

    @FXML
    private TableColumn<Medico, String> editNombre;

    @FXML
    private TableColumn<Medico, String> editCedula;

    @FXML
    private TableColumn<Medico, String> editEmail;

    @FXML
    private TableColumn<Medico, String> editTelefono;

    @FXML
    private TableColumn<Medico, Especialidades> editEspecialidad;

    @FXML
    public void initialize() {

        tablaMedicos.setItems(hospitalController.getMedicos());

        editNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        editCedula.setCellValueFactory(new PropertyValueFactory<>("documento"));
        editEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        editTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        editEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));

        editNombre.setResizable(false);
        editCedula.setResizable(false);
        editEmail.setResizable(false);
        editTelefono.setResizable(false);
        editEspecialidad.setResizable(false);

        editNombre.setCellFactory(TextFieldTableCell.forTableColumn());
        editCedula.setCellFactory(TextFieldTableCell.forTableColumn());
        editEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        editTelefono.setCellFactory(TextFieldTableCell.forTableColumn());
        editEspecialidad.setCellFactory(column -> {
            return new TableCell<Medico, Especialidades>() {
                @Override
                protected void updateItem(Especialidades item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.name());
                    }
                }
            };
        });

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

        editEspecialidad.setOnEditCommit(event -> {
            Medico medico = event.getRowValue();
            medico.setEspecialidad(event.getNewValue());
        });

        ObservableList<Medico> medicos = FXCollections.observableArrayList(hospitalController.getMedicos());
        tablaMedicos.setItems(medicos);
    }

    public void recargarVentana() throws IOException {
        m.changeScene("/co/edu/uniquindio/javafxtest/gestionMedicos.fxml");
    }

    public void backLogIn(javafx.event.ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("/co/edu/uniquindio/javafxtest/loginAdministrador.fxml");
    }

    public void crearMedico() {
        String nombre = solicitarDato("Ingrese el Nombre del medico:");
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

        String especialidad = solicitarDato("Ingrese la Especialidad:");
        if (especialidad == null || especialidad.trim().isEmpty()){
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", null, "El teléfono no puede estar vacío.");
            return;
        }

        Especialidades especialidadEnum;
        try {
            especialidadEnum = Especialidades.valueOf(especialidad.trim().toUpperCase()); // Convertir a mayúsculas para coincidir con el enum
        } catch (IllegalArgumentException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Especialidad", null,
                    "La especialidad ingresada no es válida o no existe. Por favor, ingrese una de las siguientes: " + java.util.Arrays.toString(Especialidades.values()));
            return;
        }

        Medico nuevo = new Medico(nombre, documento, email, telefono, especialidadEnum);
        boolean agregadoExitosamente = hospitalController.agregarMedico(nuevo);

        if (!agregadoExitosamente) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al crear medico", null,
                    "Ya existe un medico con el mismo nombre, cédula, email o teléfono.");
        } else {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Medico Creado", null,
                    "El medico ha sido agregado exitosamente.");
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

    public void buscarMedico() {
        String documentoBuscado = solicitarDato("Ingrese el documento a buscar:");
        if (documentoBuscado.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", null, "Ingrese un documento valido.");
            return;
        }

        Medico medicoEncontrado = hospitalController.buscarMedico(null, documentoBuscado);

        if (medicoEncontrado != null) {
            int indice = tablaMedicos.getItems().indexOf(medicoEncontrado);

            if (indice != -1) {
                tablaMedicos.getSelectionModel().select(indice);
                tablaMedicos.scrollTo(indice);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Medico Encontrado", null,
                        "El medico ha sido localizado y seleccionado en la tabla.");
            }
        } else {
            tablaMedicos.getSelectionModel().clearSelection();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Medico No Encontrado", null,
                    "No se encontró ningún medico con el documento: " + documentoBuscado);
        }
    }

    public void eliminarMedico(){

        Medico medicoSeleccionado = tablaMedicos.getSelectionModel().getSelectedItem();

        if (medicoSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", null, "Por favor, seleccione un medico de la tabla para eliminar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Eliminación");
        confirmacion.setHeaderText("¿Está seguro de que desea eliminar a " + medicoSeleccionado.getNombre() + "?");
        confirmacion.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            boolean eliminadoExitosamente = hospitalController.eliminarMedico(medicoSeleccionado);

            if (eliminadoExitosamente) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Eliminación Exitosa", null,
                        "El medico " + medicoSeleccionado.getNombre() + " ha sido eliminado correctamente.");
            }

            else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error al Eliminar", null,
                        "No se pudo eliminar el medico. Inténtelo de nuevo.");
            }
        } else {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Eliminación Cancelada", null,
                    "La eliminación del medico ha sido cancelada.");
        }
        try{
            recargarVentana();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

