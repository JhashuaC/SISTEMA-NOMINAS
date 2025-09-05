package infra;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import modelo.*;  

class ExportadorCSVTest {

    @Test
    void generarLineas_y_exportarPlanilla_funcionan() throws Exception {
        List<Empleado> emps = List.of(
            new Asalariado("1", "Ana", 900000),
            new PorHoras("2", "Luis", 5000f, 82f), 
            new PracticanteEmpleadoAdapter(new Practicante("3", "Sofia", 150000))
        );

        var lines = ExportadorCSV.generarLineasPlanilla(emps);
        assertFalse(lines.isEmpty(), "Debe incluir encabezado y filas");
        assertEquals("cedula;nombre;tipo;salarioQuincena;bono;totalAPagar", lines.get(0));

        String ana = lines.stream().filter(s -> s.startsWith("1;Ana")).findFirst().orElseThrow();
        assertTrue(ana.endsWith("450000.00;45000.00;495000.00"),
            "Asalariado: 900000/2; 5% de 900000; total = 495000");

       Path tmp = Files.createTempFile("planilla_quincena", ".csv");
        int escritos = ExportadorCSV.exportarPlanilla(tmp.toString(), emps);
        assertEquals(3, escritos, "Debe escribir 3 registros (sin contar encabezado)");
        assertTrue(Files.size(tmp) > 0, "El archivo no puede estar vacío");

        var persisted = Files.readAllLines(tmp);
        assertEquals(lines, persisted, "El contenido exportado debe coincidir con lo generado");
    }
}
