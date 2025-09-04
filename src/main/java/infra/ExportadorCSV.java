package infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import modelo.Empleado;

public final class ExportadorCSV {

    private ExportadorCSV() {}

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

    public static int exportarPlanilla(String ruta, List<? extends Empleado> empleados) throws Exception {
        List<String> lineas = generarLineasPlanilla(empleados);
        RepositorioCSV.escribirLineas(ruta, lineas);
        return Math.max(0, lineas.size() - 1);
    }

    private static String format(double v) {
        return String.format(Locale.US, "%.2f", v);
    }

    private static String safe(String s) {
        return s == null ? "" : s.replace(";", ",");
    }

    private static String tipo(Empleado e) {
        try {
            String t = e.getTipo();
            return (t == null || t.isBlank()) ? e.getClass().getSimpleName() : t;
        } catch (Throwable ignore) {
            return e.getClass().getSimpleName();
        }
    }
}
