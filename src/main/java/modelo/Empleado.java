package modelo;

public abstract class Empleado implements SalarioQuincenaInterface, EstrategiaIncentivoInterface, BonificableInterface {

    public abstract String getCedula();
    public abstract String getNombre();
    public abstract String getTipo();

    public double nomina() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public void fabricarEmpleado() {
    }
}
