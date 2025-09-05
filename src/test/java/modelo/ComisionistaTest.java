package modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ComisionistaTest {

    @Test
    void salario_y_bono_separados() {
        Comisionista c = new Comisionista("5","Carlos", 600_000, 0.05f, 12_000_000);
        double fijo = 300_000;                 // base mensual / 2
        double comision = 12_000_000 * 0.05;   // 600k
        double bono = 12_000_000 * 0.02;       // 240k (tu política)

        assertEquals(fijo + comision, c.salarioQuincena(), 0.001);
        assertEquals(bono,             c.Bono(),            0.001);
        assertEquals(fijo + comision + bono, c.nomina(),    0.001);
    }
}
