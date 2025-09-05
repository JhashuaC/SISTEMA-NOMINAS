package modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PorHorasTest {

    @Test
    void sin_bono_hasta_80h() {
        PorHoras p = new PorHoras("2","Luis", 5000f, 80f);
        assertEquals(400_000, p.salarioQuincena(), 0.001);
        assertEquals(0.0,     p.Bono(),            0.001);
        assertEquals(400_000, p.nomina(),          0.001);
    }

    @Test
    void con_bono_sobre_80h() {
        PorHoras p = new PorHoras("2","Luis", 5000f, 82f);
        double base = 410_000;
        double bono = base * 0.10;
        assertEquals(base, p.salarioQuincena(), 0.001);
        assertEquals(bono, p.Bono(),            0.001);
        assertEquals(base + bono, p.nomina(),   0.001);
    }
}
