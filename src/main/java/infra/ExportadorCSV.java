package infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import modelo.Empleado;

/**
 * Clase utilitaria para exportar datos de empleados a formato CSV.
 *
 * Genera planillas con encabezado y filas con información de los empleados,
 * incluyendo salario quincenal, bono y total a pagar.
 *
 * Ejemplo de salida:
 * <pre>
 * cedula;nombre;tipo;salarioQuincena;bono;totalAPagar
 * 10101010;Ana Perez;Asalariado;500000.00;50000.00;550000.00
 * 20202020;Juan Soto;PorHoras;180000.00;0.00;180000.00
 * </pre>
 *
 * @author marco
 */
public final class ExportadorCSV {

    private ExportadorCSV() {
    }

    /**
     * Genera las líneas de texto de la planilla de empleados, lista para
     * escribir en un archivo CSV.
     *
     * @param empleados Lista de empleados a exportar.
     * @return Lista de cadenas con las líneas del archivo CSV.
     */
    public static List<String> generarLineasPlanilla(List<? extends Empleado> empleados) {
        List<String> out = new ArrayList<>();
        out.add("cedula;nombre;tipo;salarioQuincena;bono;totalAPagar");
        for (Empleado e : empleados) {
            String line = String.join(";",
                    safe(e.getCedula()),
                    safe(e.getNombre()),
                    tipo(e),
                    format(e.salarioQuincena()),
                    format(e.Bono()),
                    format(e.nomina())
            );
            out.add(line);
        }
        return out;
    }

    /**
     * Exporta la planilla de empleados a un archivo CSV.
     *
     * @param ruta Ruta del archivo destino.
     * @param empleados Lista de empleados a exportar.
     * @return Número de empleados exportados (no cuenta el encabezado).
     * @throws Exception si ocurre un error al escribir el archivo.
     */
    public static int exportarPlanilla(String ruta, List<? extends Empleado> empleados) throws Exception {
        List<String> lineas = generarLineasPlanilla(empleados);
        RepositorioCSV.escribirLineas(ruta, lineas);
        return Math.max(0, lineas.size() - 1);
    }

    /**
     * Da formato a un número decimal en notación con punto y dos decimales.
     *
     * @param v Valor numérico.
     * @return Texto formateado (ejemplo: {@code "1234.50"}).
     */
    private static String format(double v) {
        return String.format(Locale.US, "%.2f", v);
    }

    /**
     * Escapa un texto para evitar errores de formato en CSV. Si es {@code null}
     * devuelve cadena vacía y reemplaza los puntos y coma con comas.
     *
     * @param s Texto original.
     * @return Texto seguro para CSV.
     */
    private static String safe(String s) {
        return s == null ? "" : s.replace(";", ",");
    }

    /**
     * Determina el tipo de empleado. Si {@link Empleado#getTipo()} devuelve
     * nulo o vacío, se usa el nombre simple de la clase.
     *
     * @param e Empleado.
     * @return Tipo del empleado.
     */
    private static String tipo(Empleado e) {
        try {
            String t = e.getTipo();
            return (t == null || t.isBlank()) ? e.getClass().getSimpleName() : t;
        } catch (Throwable ignore) {
            return e.getClass().getSimpleName();
        }
    }
}
