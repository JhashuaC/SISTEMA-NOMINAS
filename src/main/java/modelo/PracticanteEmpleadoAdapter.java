package modelo;


/**
 * Adaptador que permite tratar un Practicante como un Empleado.
 * Implementa todos los métodos necesarios de la clase Empleado.
 */
public final class PracticanteEmpleadoAdapter extends Empleado {

    private final Practicante practicante;

    
     /**
     * Crea un adaptador de un practicante.
     * @param practicante Instancia del practicante a adaptar.
     */
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
