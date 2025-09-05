package modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PracticanteEmpleadoAdapterTest {

    @Test
    void adapter_funciona_con_tabla_de_Empleado() {
        Practicante p = new Practicante("6","Sofia", 150_000);
        Empleado e = new PracticanteEmpleadoAdapter(p);
        assertEquals("6", e.getCedula());
        assertEquals("Sofia", e.getNombre());
        assertEquals(150_000, e.salarioQuincena(), 0.001);
        assertEquals(0.0,     e.Bono(),            0.001);
        assertEquals(150_000, e.nomina(),          0.001);
    }
}
