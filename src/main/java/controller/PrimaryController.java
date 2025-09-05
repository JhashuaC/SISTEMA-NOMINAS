package controller;

import infra.ExportadorCSV;
import infra.RepositorioCSV;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import modelo.Asalariado;
import modelo.Comisionista;
import modelo.Empleado;
import modelo.PorHoras;
import modelo.Practicante;   // <- OJO: debe EXTENDER Empleado para entrar a la tabla
import modelo.PracticanteEmpleadoAdapter;
import modelo.Temporal;

/**
 * Controlador principal de la interfaz gráfica JavaFX.
 *
 * Administra la carga de empleados desde CSV, el cálculo de salarios, la
 * exportación de la planilla a un archivo y la visualización en la tabla.
 *
 * Se asocia directamente al archivo {@code Primary.fxml}.
 *
 * @author marco y jhashua
 */
public class PrimaryController {

    @FXML
    private Button btnCargar1;
    @FXML
    private Button btnCalcular1;
    @FXML
    private Button btnExportar1;
    @FXML
    private Button btnSalir;

    @FXML
    private TableView<Empleado> tablaUsuarios1;
    @FXML
    private TableColumn<Empleado, String> colCedula1;
    @FXML
    private TableColumn<Empleado, String> colNombre1;
    @FXML
    private TableColumn<Empleado, String> colTipo1;
    @FXML
    private TableColumn<Empleado, Double> colSalario1;
    @FXML
    private TableColumn<Empleado, Double> colBono1;
    @FXML
    private TableColumn<Empleado, Double> colTotal1;

    /**
     * Lista observable con los empleados cargados en memoria.
     */
    private final ObservableList<Empleado> empleados = FXCollections.observableArrayList();

    /**
     * Inicializa la tabla y configura las columnas para mostrar empleados. Este
     * método es invocado automáticamente por JavaFX al cargar el FXML.
     */
    @FXML
    public void initialize() {
        tablaUsuarios1.setItems(empleados);
        tablaUsuarios1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colCedula1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCedula()));
        colNombre1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        // Si no tienes getTipo() en Empleado, usa el nombre de la clase:
        colTipo1.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getClass().getSimpleName()));

        colSalario1.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().salarioQuincena()).asObject());
        colBono1.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().Bono()).asObject());
        colTotal1.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().nomina()).asObject());

        applyMoneyCellFactory(colSalario1);
        applyMoneyCellFactory(colBono1);
        applyMoneyCellFactory(colTotal1);
    }

    /**
     * Aplica un formato monetario (con dos decimales) a una columna numérica de
     * la tabla.
     *
     * @param col Columna a la que se aplicará el formato.
     */
    private void applyMoneyCellFactory(TableColumn<Empleado, Double> col) {
        col.setCellFactory(tc -> new TableCell<Empleado, Double>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                setText(empty || value == null ? null : String.format(Locale.US, "%,.2f", value));
            }
        });
    }

    /**
     * Acción asociada al botón "Cargar". Permite al usuario seleccionar un
     * archivo CSV y cargar empleados en la tabla.
     *
     * @param event Evento de acción disparado por JavaFX.
     */
    @FXML
    private void onCargar(ActionEvent event) {
        try {
            String ruta = RepositorioCSV.seleccionarArchivoCSV(tablaUsuarios1.getScene().getWindow());
            if (ruta == null) {
                info("Selección requerida", "Debes seleccionar un archivo CSV.");
                return;
            }

            List<String> errores = new ArrayList<>();
            List<Empleado> nuevos = RepositorioCSV.cargarEmpleados(ruta, errores);

            empleados.setAll(nuevos);
            tablaUsuarios1.refresh();

            if (!errores.isEmpty()) {
                info("CSV cargado con advertencias",
                        "Registros cargados: " + nuevos.size() + "\nAdvertencias:\n- " + String.join("\n- ", errores));
            } else {
                info("CSV cargado", "Registros cargados: " + nuevos.size());
            }
        } catch (Exception ex) {
            error("Error al cargar CSV", ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Acción asociada al botón "Calcular". Recalcula los valores de la tabla
     * (salario, bono, total) y actualiza la vista.
     *
     * @param event Evento de acción disparado por JavaFX.
     */
    @FXML
    private void onCalcular(ActionEvent event) {
        tablaUsuarios1.refresh();
        info("Cálculo", "Totales recalculados.");
    }

    /**
     * Acción asociada al botón "Exportar". Permite guardar la planilla de
     * empleados en un archivo CSV.
     *
     * @param event Evento de acción disparado por JavaFX.
     */
    @FXML
    private void OnExportar(ActionEvent event) {
        try {
            if (empleados.isEmpty()) {
                info("Sin datos", "No hay registros para exportar. Carga el CSV primero.");
                return;
            }

            FileChooser fc = new FileChooser();
            fc.setTitle("Guardar planilla_quincena.csv");
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
            File outDir = new File("out");
            if (!outDir.exists()) {
                outDir.mkdirs();
            }
            fc.setInitialDirectory(outDir);
            fc.setInitialFileName("planilla_quincena.csv");

            File chosen = fc.showSaveDialog(tablaUsuarios1.getScene().getWindow());
            if (chosen == null) {
                return;
            }

            int escritos = ExportadorCSV.exportarPlanilla(chosen.getAbsolutePath(), empleados);
            info("Exportación exitosa",
                    "Se exportaron " + escritos + " registros a:\n" + chosen.getAbsolutePath());
        } catch (Exception ex) {
            error("Error al exportar", ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void onSalir(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Muestra un cuadro de diálogo informativo.
     *
     * @param title Título de la ventana.
     * @param msg Mensaje a mostrar.
     */
    private void info(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    /**
     * Muestra un cuadro de diálogo de error.
     *
     * @param title Título de la ventana.
     * @param msg Mensaje a mostrar.
     */
    private void error(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setHeaderText(title);
        a.setContentText(msg);
        a.showAndWait();
    }
}
