package infra;

import java.io.File;
import java.nio.file.*;
import java.util.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import modelo.Asalariado;
import modelo.Comisionista;
import modelo.Empleado;
import modelo.PorHoras;
import modelo.Practicante;
import modelo.PracticanteEmpleadoAdapter;
import modelo.Temporal;

public class RepositorioCSV {

    // ===== IO básico =====
    public static List<String> LeerLineas(String ruta) throws Exception {
        return Files.readAllLines(Path.of(ruta));
    }

    public static void escribirLineas(String ruta, List<String> lineas) throws Exception {
        Path p = Path.of(ruta);
        Path parent = p.getParent();
        if (parent != null) Files.createDirectories(parent);
        Files.write(p, lineas);
    }

    // ===== UI: seleccionar archivo =====
    public static String seleccionarArchivoCSV(Window ownerWindow) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Selecciona empleados.csv");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos CSV", "*.csv", "*.txt"));

        File dataDir = new File("data");
        if (dataDir.exists() && dataDir.isDirectory()) {
            fc.setInitialDirectory(dataDir);
        }

        File chosen = fc.showOpenDialog(ownerWindow);
        return (chosen != null) ? chosen.getAbsolutePath() : null;
    }

    // ===== Carga y parseo =====
    public static List<Empleado> cargarEmpleados(String ruta, List<String> erroresOut) throws Exception {
        List<String> lineas = LeerLineas(ruta);
        List<Empleado> nuevos = new ArrayList<>();
        List<String> errores = (erroresOut != null) ? erroresOut : new ArrayList<>();

        for (int i = 0; i < lineas.size(); i++) {
            String raw = lineas.get(i).trim();
            if (raw.isEmpty() || raw.startsWith("#")) continue;

            try {
                Empleado e = parseEmpleado(raw);
                nuevos.add(e);
            } catch (IllegalArgumentException ex) {
                errores.add("Línea " + (i + 1) + ": " + ex.getMessage());
            }
        }
        return nuevos;
    }

    private static Empleado parseEmpleado(String line) {
        String[] p = line.split(";", -1);
        if (p.length < 2) throw new IllegalArgumentException("Fila sin los campos mínimos.");

        String tipo = p[0].trim().toUpperCase(Locale.ROOT);
        switch (tipo) {
            case "ASALARIADO": {
                if (p.length != 4) throw new IllegalArgumentException("ASALARIADO requiere 4 campos.");
                String ced = p[1].trim();
                String nom = p[2].trim();
                double salarioMensual = parseNumber(p[3]);
                return new Asalariado(ced, nom, salarioMensual);
            }
            case "PORHORAS": {
                if (p.length != 5) throw new IllegalArgumentException("PORHORAS requiere 5 campos.");
                String ced = p[1].trim();
                String nom = p[2].trim();
                float tarifaHora = (float) parseNumber(p[3]);
                float horasQuincena = (float) parseNumber(p[4]);
                return new PorHoras(ced, nom, tarifaHora, horasQuincena);
            }
            case "TEMPORAL": {
                if (p.length != 5) throw new IllegalArgumentException("TEMPORAL requiere 5 campos.");
                String ced = p[1].trim();
                String nom = p[2].trim();
                double tarifaDiaria = parseNumber(p[3]);
                int diasActivos = parseInt(p[4]);
                return new Temporal(ced, tarifaDiaria, nom, diasActivos);
            }
            case "COMISIONISTA": {
                if (p.length != 6) throw new IllegalArgumentException("COMISIONISTA requiere 6 campos.");
                String ced = p[1].trim();
                String nom = p[2].trim();
                double base = parseNumber(p[3]);
                float porcentaje = (float) parseNumber(p[4]); // admite 0.05 o 5
                if (porcentaje > 1f) porcentaje = porcentaje / 100f;
                double ventasQuincena = parseNumber(p[5]);
                return new Comisionista(ced, nom, base, porcentaje, ventasQuincena);
            }
            case "PRACTICANTE": {
                if (p.length != 4) throw new IllegalArgumentException("PRACTICANTE requiere 4 campos.");
                String ced = p[1].trim();
                String nom = p[2].trim();
                double apoyo = parseNumber(p[3]);
                Practicante pr = new Practicante(ced, nom, apoyo);
                return new PracticanteEmpleadoAdapter(pr); // adapter para la TableView<Empleado>
            }
            default:
                throw new IllegalArgumentException("Tipo desconocido: " + tipo);
        }
    }

    // ===== helpers numéricos =====
    private static double parseNumber(String s) {
        String t = s.trim().replace(",", ".");
        return Double.parseDouble(t);
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s.trim());
    }
}
