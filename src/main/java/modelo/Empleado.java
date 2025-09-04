package modelo;

public abstract class Empleado implements SalarioQuincenaInterface, EstrategiaIncentivoInterface, BonificableInterface {

    public Empleado() {
    }

    public double nomina() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public void fabricarEmpleado() {
    }
}
