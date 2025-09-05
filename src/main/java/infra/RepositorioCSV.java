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

/**
 * Utilidades para gestionar archivos CSV relacionados con empleados.
 *
 * Provee métodos para leer, escribir y parsear empleados desde un archivo CSV,
 * así como abrir un selector de archivos para que el usuario elija el archivo
 * de datos.
 *
 * Los formatos esperados son:
 * <ul>
 * <li>ASALARIADO;cedula;nombre;salarioMensual</li>
 * <li>PORHORAS;cedula;nombre;tarifaHora;horasQuincena</li>
 * <li>TEMPORAL;cedula;nombre;tarifaDiaria;diasActivos</li>
 * <li>COMISIONISTA;cedula;nombre;base;porcentaje;ventasQuincena</li>
 * <li>PRACTICANTE;cedula;nombre;apoyoQuincena</li>
 * </ul>
 *
 * @author marco y jhashua
 */
public class RepositorioCSV {

    /**
     * Lee todas las líneas de un archivo de texto (CSV).
     *
     * @param ruta Ruta del archivo a leer.
     * @return Lista de líneas de texto del archivo.
     * @throws Exception si ocurre un error de lectura.
     */
    public static List<String> LeerLineas(String ruta) throws Exception {
        return Files.readAllLines(Path.of(ruta));
    }

    /**
     * Escribe una lista de líneas de texto en un archivo. Si el directorio no
     * existe, lo crea automáticamente.
     *
     * @param ruta Ruta del archivo de salida.
     * @param lineas Líneas de texto a escribir.
     * @throws Exception si ocurre un error de escritura.
     */
    public static void escribirLineas(String ruta, List<String> lineas) throws Exception {
        Path p = Path.of(ruta);
        Path parent = p.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Files.write(p, lineas);
    }

    /**
     * Abre un cuadro de diálogo para seleccionar un archivo CSV desde el
     * sistema. El directorio inicial por defecto es "data".
     *
     * @param ownerWindow Ventana padre para el cuadro de diálogo.
     * @return Ruta absoluta del archivo seleccionado, o {@code null} si se
     * cancela.
     */
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

    /**
     * Carga y parsea empleados desde un archivo CSV.
     *
     * @param ruta Ruta del archivo CSV.
     * @param erroresOut Lista opcional para acumular errores encontrados en las
     * líneas.
     * @return Lista de objetos {@link Empleado} parseados exitosamente.
     * @throws Exception si ocurre un error general de lectura.
     */
    public static List<Empleado> cargarEmpleados(String ruta, List<String> erroresOut) throws Exception {
        List<String> lineas = LeerLineas(ruta);
        List<Empleado> nuevos = new ArrayList<>();
        List<String> errores = (erroresOut != null) ? erroresOut : new ArrayList<>();

        for (int i = 0; i < lineas.size(); i++) {
            String raw = lineas.get(i).trim();
            if (raw.isEmpty() || raw.startsWith("#")) {
                continue;
            }

            try {
                Empleado e = parseEmpleado(raw);
                nuevos.add(e);
            } catch (IllegalArgumentException ex) {
                errores.add("Línea " + (i + 1) + ": " + ex.getMessage());
            }
        }
        return nuevos;
    }

    /**
     * Convierte una línea de texto en un objeto {@link Empleado}.
     *
     * @param line Línea CSV con los campos de un empleado.
     * @return Un objeto {@link Empleado} correspondiente al tipo especificado.
     * @throws IllegalArgumentException si el formato no corresponde al
     * esperado.
     */
    private static Empleado parseEmpleado(String line) {
        String[] p = line.split(";", -1);
        if (p.length < 2) {
            throw new IllegalArgumentException("Fila sin los campos mínimos.");
        }

        String tipo = p[0].trim().toUpperCase(Locale.ROOT);
        switch (tipo) {
            case "ASALARIADO": {
                if (p.length != 4) {
                    throw new IllegalArgumentException("ASALARIADO requiere 4 campos.");
                }
                String ced = p[1].trim();
                String nom = p[2].trim();
                double salarioMensual = parseNumber(p[3]);
                return new Asalariado(ced, nom, salarioMensual);
            }
            case "PORHORAS": {
                if (p.length != 5) {
                    throw new IllegalArgumentException("PORHORAS requiere 5 campos.");
                }
                String ced = p[1].trim();
                String nom = p[2].trim();
                float tarifaHora = (float) parseNumber(p[3]);
                float horasQuincena = (float) parseNumber(p[4]);
                return new PorHoras(ced, nom, tarifaHora, horasQuincena);
            }
            case "TEMPORAL": {
                if (p.length != 5) {
                    throw new IllegalArgumentException("TEMPORAL requiere 5 campos.");
                }
                String ced = p[1].trim();
                String nom = p[2].trim();
                double tarifaDiaria = parseNumber(p[3]);
                int diasActivos = parseInt(p[4]);
                return new Temporal(ced, tarifaDiaria, nom, diasActivos);
            }
            case "COMISIONISTA": {
                if (p.length != 6) {
                    throw new IllegalArgumentException("COMISIONISTA requiere 6 campos.");
                }
                String ced = p[1].trim();
                String nom = p[2].trim();
                double base = parseNumber(p[3]);
                float porcentaje = (float) parseNumber(p[4]); // admite 0.05 o 5
                if (porcentaje > 1f) {
                    porcentaje = porcentaje / 100f;
                }
                double ventasQuincena = parseNumber(p[5]);
                return new Comisionista(ced, nom, base, porcentaje, ventasQuincena);
            }
            case "PRACTICANTE": {
                if (p.length != 4) {
                    throw new IllegalArgumentException("PRACTICANTE requiere 4 campos.");
                }
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

    /**
     * Convierte un número representado como String en {@code double}, aceptando
     * tanto coma como punto decimal.
     *
     * @param s Texto con el número.
     * @return Valor numérico convertido.
     * @throws NumberFormatException si el texto no es un número válido.
     */
    private static double parseNumber(String s) {
        String t = s.trim().replace(",", ".");
        return Double.parseDouble(t);
    }

    /**
     * Convierte un número entero representado como String en {@code int}.
     *
     * @param s Texto con el número entero.
     * @return Valor numérico convertido.
     * @throws NumberFormatException si el texto no es un entero válido.
     */
    private static int parseInt(String s) {
        return Integer.parseInt(s.trim());
    }
}
