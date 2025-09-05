package modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AsalariadoTest {

    @Test
    void salario_bono_y_nomina() {
        Asalariado a = new Asalariado("1","Ana", 900_000);
        assertEquals(450_000, a.salarioQuincena(), 0.001);  // mensual / 2
        assertEquals(45_000,  a.Bono(),            0.001);  // 5% mensual
        assertEquals(495_000, a.nomina(),          0.001);  // base + bono
    }
}
