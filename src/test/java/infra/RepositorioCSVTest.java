package infra;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import modelo.Empleado;

class RepositorioCSVTest {

    @Test
    void carga_todos_los_tipos_desde_csv_temporal() throws Exception {
        Path tmp = Files.createTempFile("empleados", ".csv");
        List<String> lines = List.of(
            "ASALARIADO;10101010;Ana;900000",
            "PORHORAS;20202020;Luis;5000;82",
            "TEMPORAL;30303030;Maria;20000;18",
            "COMISIONISTA;40404040;Carlos;600000;5;12000000",
            "PRACTICANTE;50505050;Sofia;150000"
        );
        Files.write(tmp, lines);

        List<String> errores = new ArrayList<>();
        List<Empleado> lista = RepositorioCSV.cargarEmpleados(tmp.toString(), errores);

        assertEquals(5, lista.size());
        assertTrue(errores.isEmpty(), "Errores: " + errores);
    }
}
