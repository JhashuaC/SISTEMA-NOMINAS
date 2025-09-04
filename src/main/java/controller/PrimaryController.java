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

public class PrimaryController {

    @FXML private Button btnCargar1;
    @FXML private Button btnCalcular1;
    @FXML private Button btnExportar1;
    @FXML private Button btnSalir;

    @FXML private TableView<Empleado> tablaUsuarios1;
    @FXML private TableColumn<Empleado, String> colCedula1;
    @FXML private TableColumn<Empleado, String> colNombre1;
    @FXML private TableColumn<Empleado, String> colTipo1;
    @FXML private TableColumn<Empleado, Double> colSalario1;
    @FXML private TableColumn<Empleado, Double> colBono1;
    @FXML private TableColumn<Empleado, Double> colTotal1;

    private final ObservableList<Empleado> empleados = FXCollections.observableArrayList();

    // ¡OJO!: el método correcto para FXML es initialize(), no "intitialize"
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

    private void applyMoneyCellFactory(TableColumn<Empleado, Double> col) {
        col.setCellFactory(tc -> new TableCell<Empleado, Double>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                setText(empty || value == null ? null : String.format(Locale.US, "%,.2f", value));
            }
        });
    }

    // ================== CARGAR CSV ==================
@FXML
private void onCargar(ActionEvent event) {
    try {
        String ruta = seleccionarArchivoCSV();          // 1) seleccionar archivo
        if (ruta == null) {                             // si canceló, no seguimos
            info("Selección requerida", "Debes seleccionar un archivo CSV.");
            return;
        }
        cargarDesdeCSV(ruta);                           // 2) cargar y poblar la tabla
    } catch (Exception ex) {
        error("Error al cargar CSV", ex.getMessage());  // 3) manejar errores
        ex.printStackTrace();
    }
}

    private String seleccionarArchivoCSV() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Selecciona empleados.csv");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos CSV", "*.csv", "*.txt"));
        File dataDir = new File("data");
        if (dataDir.exists() && dataDir.isDirectory()) {
            fc.setInitialDirectory(dataDir);
        }
        File chosen = fc.showOpenDialog(tablaUsuarios1.getScene().getWindow());
        return chosen != null ? chosen.getAbsolutePath() : null;
    }

    private void cargarDesdeCSV(String ruta) throws Exception {
        List<String> lineas = RepositorioCSV.LeerLineas(ruta);
        List<Empleado> nuevos = new ArrayList<>();
        List<String> errores = new ArrayList<>();

        int n = 0;
        for (int i = 0; i < lineas.size(); i++) {
            String raw = lineas.get(i).trim();
            if (raw.isEmpty() || raw.startsWith("#")) continue;

            try {
                Optional<Empleado> maybe = parseEmpleado(raw);
                if (maybe.isPresent()) {
                    nuevos.add(maybe.get());
                    n++;
                } else {
                    // PRACTICANTE no extiende Empleado: lo ignoramos hasta que ajustes la clase
                    errores.add("Línea " + (i + 1) + ": tipo no compatible con TableView (¿Practicante no extiende Empleado?).");
                }
            } catch (IllegalArgumentException ex) {
                errores.add("Línea " + (i + 1) + ": " + ex.getMessage());
            }
        }

        empleados.setAll(nuevos);
        tablaUsuarios1.refresh();

        if (!errores.isEmpty()) {
            info("CSV cargado con advertencias",
                "Registros cargados: " + n + "\nAdvertencias:\n- " + String.join("\n- ", errores));
        } else {
            info("CSV cargado", "Registros cargados: " + n);
        }
    }

    private Optional<Empleado> parseEmpleado(String line) {
        // Formato: TIPO;campo1;campo2;...
        String[] p = line.split(";", -1);
        if (p.length < 2) throw new IllegalArgumentException("Fila sin los campos mínimos.");

        String tipo = p[0].trim().toUpperCase(Locale.ROOT);
        switch (tipo) {
            case "ASALARIADO": {
                if (p.length != 4) throw new IllegalArgumentException("ASALARIADO requiere 4 campos.");
                String ced = p[1].trim();
                String nom = p[2].trim();
                double salarioMensual = parseNumber(p[3]);
                return Optional.of(new Asalariado(ced, nom, salarioMensual));
            }
            case "PORHORAS": {
                if (p.length != 5) throw new IllegalArgumentException("PORHORAS requiere 5 campos.");
                String ced = p[1].trim();
                String nom = p[2].trim();
                float tarifaHora = (float) parseNumber(p[3]);
                float horasQuincena = (float) parseNumber(p[4]);
                return Optional.of(new PorHoras(ced, nom, tarifaHora, horasQuincena));
            }
            case "TEMPORAL": {
                if (p.length != 5) throw new IllegalArgumentException("TEMPORAL requiere 5 campos.");
                String ced = p[1].trim();
                String nom = p[2].trim();
                double tarifaDiaria = parseNumber(p[3]);
                int diasActivos = parseInt(p[4]);
                return Optional.of(new Temporal(ced, tarifaDiaria, nom, diasActivos));
            }
            case "COMISIONISTA": {
                if (p.length != 6) throw new IllegalArgumentException("COMISIONISTA requiere 6 campos.");
                String ced = p[1].trim();
                String nom = p[2].trim();
                double base = parseNumber(p[3]);
                float porcentaje = (float) parseNumber(p[4]); // admite 0.05 o 5
                if (porcentaje > 1f) porcentaje = porcentaje / 100f;
                double ventasQuincena = parseNumber(p[5]);
                return Optional.of(new Comisionista(ced, nom, base, porcentaje, ventasQuincena));
            }
           case "PRACTICANTE": {
    if (p.length != 4) throw new IllegalArgumentException("PRACTICANTE requires 4 fields");
    String ced = p[1].trim();
    String nom = p[2].trim();
    double apoyo = parseNumber(p[3]); // supports "1234,56" or "1234.56"
    Practicante pr = new Practicante(ced, nom, apoyo);
    return Optional.of(new PracticanteEmpleadoAdapter(pr));
}

            default:
                throw new IllegalArgumentException("Tipo desconocido: " + tipo);
        }
    }

    // Permite 12,50 o 12.50
    private double parseNumber(String s) {
        String t = s.trim().replace(",", ".");
        return Double.parseDouble(t);
    }

    private int parseInt(String s) {
        return Integer.parseInt(s.trim());
    }

    // ================== Acciones extra ==================
    @FXML
    private void onCalcular(ActionEvent event) {
   tablaUsuarios1.refresh();
        info("Cálculo", "Totales recalculados.");
    }

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
        if (!outDir.exists()) outDir.mkdirs();
        fc.setInitialDirectory(outDir);
        fc.setInitialFileName("planilla_quincena.csv");

        File chosen = fc.showSaveDialog(tablaUsuarios1.getScene().getWindow());
        if (chosen == null) return;

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

    // ================== Helpers UI ==================
    private void info(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    private void error(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setHeaderText(title);
        a.setContentText(msg);
        a.showAndWait();
    }
}

