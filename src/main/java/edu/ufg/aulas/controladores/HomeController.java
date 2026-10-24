package edu.ufg.aulas.controladores;

import edu.ufg.aulas.dao.AulaDAO;
import edu.ufg.aulas.dao.CatalogoDAO;
import edu.ufg.aulas.entidades.Aula;
import edu.ufg.aulas.entidades.EstadoAula;
import edu.ufg.aulas.entidades.TipoAula;
import edu.ufg.aulas.util.Navegador;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class HomeController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtEdificio;
    @FXML private ComboBox<Integer> cmbPlanta;
    @FXML private TextField txtCapacidad;
    @FXML private ComboBox<TipoAula> cmbTipoAula;
    @FXML private TextField txtResponsable;
    @FXML private ComboBox<EstadoAula> cmbEstado;

    private final CatalogoDAO catalogoDAO = new CatalogoDAO();
    private final AulaDAO aulaDAO = new AulaDAO();

    @FXML
    private void initialize() {
        try {
            cmbPlanta.getItems().setAll(catalogoDAO.listarPlantas());
            cmbTipoAula.getItems().setAll(catalogoDAO.listarTipos());
            cmbEstado.getItems().setAll(catalogoDAO.listarEstados());
        } catch (Exception e) {
            mostrar(Alert.AlertType.ERROR, "No se pudieron cargar los catalogos " + e.getMessage());
        }
    }

    @FXML
    private void guardar() {
        String nombre = txtNombre.getText().trim();
        String edificio = txtEdificio.getText().trim();
        String capacidadTexto = txtCapacidad.getText().trim();
        String responsable = txtResponsable.getText().trim();
        Integer planta = cmbPlanta.getValue();
        TipoAula tipo = cmbTipoAula.getValue();
        EstadoAula estado = cmbEstado.getValue();

        List<String> errores = new ArrayList<>();
        if (nombre.isEmpty()) errores.add("El nombre es obligatorio");
        if (edificio.isEmpty()) errores.add("El edificio es obligatorio");
        if (responsable.isEmpty()) errores.add("El responsable es obligatorio");
        if (tipo == null) errores.add("Seleccione el tipo de aula");
        if (estado == null) errores.add("Seleccione el estado");

        if (planta == null) {
            errores.add("Seleccione la planta");
        } else if (planta <= 0) {
            errores.add("La planta debe ser un entero mayor que 0");
        }

        int capacidad = 0;
        if (capacidadTexto.isEmpty()) {
            errores.add("La capacidad es obligatoria");
        } else {
            try {
                capacidad = Integer.parseInt(capacidadTexto);
                if (capacidad <= 0) errores.add("La capacidad debe ser un entero mayor que 0");
            } catch (NumberFormatException e) {
                errores.add("La capacidad debe ser un numero entero");
            }
        }

        if (!errores.isEmpty()) {
            mostrar(Alert.AlertType.WARNING, String.join("\n", errores));
            return;
        }

        try {
            if (aulaDAO.existe(nombre, edificio)) {
                mostrar(Alert.AlertType.WARNING,
                        "Ya existe un aula " + nombre + " en el edificio " + edificio);
                return;
            }
            aulaDAO.guardar(new Aula(nombre, edificio, planta, capacidad, tipo, responsable, estado));
            mostrar(Alert.AlertType.INFORMATION, "Aula guardada correctamente");
            limpiar();
        } catch (Exception e) {
            mostrar(Alert.AlertType.ERROR, "No se pudo guardar el aula " + e.getMessage());
        }
    }

    @FXML
    private void limpiar() {
        txtNombre.clear();
        txtEdificio.clear();
        txtCapacidad.clear();
        txtResponsable.clear();
        cmbPlanta.getSelectionModel().clearSelection();
        cmbTipoAula.getSelectionModel().clearSelection();
        cmbEstado.getSelectionModel().clearSelection();
        txtNombre.requestFocus();
    }

    @FXML
    private void cerrarSesion() {
        try {
            Navegador.cambiar(txtNombre, "/vistas/Login.fxml", "Inicio de sesion");
        } catch (Exception e) {
            mostrar(Alert.AlertType.ERROR, "No se pudo volver al login " + e.getMessage());
        }
    }

    private void mostrar(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo, mensaje);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}
