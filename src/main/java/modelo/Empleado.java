package modelo;

public abstract class Empleado implements SalarioQuincenaInterface, EstrategiaIncentivoInterface, BonificableInterface {

    public abstract String getCedula();
    public abstract String getNombre();
    public abstract String getTipo();


public double nomina() {
    // total a pagar = salario quincenal + bono
    return salarioQuincena() + Bono();
}

    public void fabricarEmpleado() {
    }
}
