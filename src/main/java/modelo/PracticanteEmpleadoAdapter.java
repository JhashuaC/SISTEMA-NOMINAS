package modelo;

public final class PracticanteEmpleadoAdapter extends Empleado {

    private final Practicante practicante;

    public PracticanteEmpleadoAdapter(Practicante practicante) {
        // No llames a super(cedula,nombre) si Empleado no tiene ese ctor
        this.practicante = practicante;
    }

    // --- Datos de identidad ---
    @Override
    public String getCedula() {
        return practicante.getCedula();
    }

    @Override
    public String getNombre() {
        return practicante.getNombre();
    }

    @Override
    public String getTipo() {
        return "Practicante";
    }

    // --- Cálculos de planilla ---
    @Override
    public double salarioQuincena() {
        return practicante.getApoyoQuincena(); // pago fijo del practicante
    }

    @Override
    public double pagarIncentivo() {
        return 0.0; // sin incentivos por defecto
    }

    @Override
    public double Bono() {
        return 0.0; // sin bonificación por defecto
    }

    @Override
    public String toString() {
        return "Practicante{" + practicante.getNombre() + "}";
    }
}
