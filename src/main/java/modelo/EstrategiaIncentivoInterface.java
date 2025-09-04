package modelo;
/**
 * Interfaz que define la estrategia de incentivos de un empleado.
 * Permite calcular el monto de incentivos que debe recibir.
 */
public interface EstrategiaIncentivoInterface {

    /**
     * Calcula el incentivo a pagar al empleado.
     * @return Monto del incentivo.
     */
    public double pagarIncentivo();
}
