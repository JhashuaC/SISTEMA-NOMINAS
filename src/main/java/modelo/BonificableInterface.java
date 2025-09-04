package modelo;

/**
 * Interfaz que define la bonificación de un empleado. Permite calcular la
 * bonificación que corresponde agregar al salario.
 */
public interface BonificableInterface {

    /**
     * Calcula la bonificación del empleado.
     *
     * @return Monto de la bonificación.
     */
    public double Bono();
}
