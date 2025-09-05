package modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TemporalTest {

    @Test
    void tope_15_dias_y_bono_desde_12() {
        Temporal t1 = new Temporal("3", 20_000, "Maria", 18);
        assertEquals(300_000, t1.salarioQuincena(), 0.001); // 15 * 20k
        assertEquals(15_000,  t1.Bono(),            0.001); // 5% de 300k
        assertEquals(315_000, t1.nomina(),          0.001);

        Temporal t2 = new Temporal("4", 20_000, "Laura", 11);
        assertEquals(220_000, t2.salarioQuincena(), 0.001);
        assertEquals(0.0,     t2.Bono(),            0.001);
        assertEquals(220_000, t2.nomina(),          0.001);
    }
}
