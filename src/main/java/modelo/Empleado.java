package modelo;

/**
 * Clase abstracta que representa un empleado genérico de la empresa. Todas las
 * clases de empleados deben heredar de esta clase. Implementa interfaces para
 * cálculo de salario, incentivos y bonificaciones.
 *
 * @author marco y jhashua
 */
public abstract class Empleado implements SalarioQuincenaInterface, EstrategiaIncentivoInterface, BonificableInterface {

    /**
     * Devuelve la cédula del empleado.
     *
     * @return Cédula como String.
     */
    public abstract String getCedula();

    /**
     * Devuelve el nombre del empleado.
     *
     * @return Nombre como String.
     */
    public abstract String getNombre();

    /**
     * Devuelve el tipo de empleado.
     *
     * @return Tipo como String.
     */
    public abstract String getTipo();

    /**
     * Calcula la nómina del empleado sumando salario quincenal y bonificación.
     *
     * @return Total de la nómina.
     */
    public double nomina() {
        return salarioQuincena() + Bono();
    }

    /**
     * Método que permite realizar acciones de construcción o inicialización del
     * empleado. Por defecto no hace nada.
     */
    public void fabricarEmpleado() {
    }
}
