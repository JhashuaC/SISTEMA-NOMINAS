package modelo;

/**
 * Representa un empleado comisionista. Calcula el salario quincenal sumando un
 * salario base y un porcentaje de las ventas realizadas en la quincena.
 */
public class Comisionista extends Empleado {

    private String cedula;

    private String nombre;

    private double base;

    private float porcentaje;

    private double ventasQuincena;

    public Comisionista() {
    }

    public Comisionista(String cedula, String nombre, double base, float porcentaje, double ventasQuincena) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.base = base;
        this.porcentaje = porcentaje;
        this.ventasQuincena = ventasQuincena;
    }

    public Comisionista(String cedula, String nombre, double base, float porcentaje) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.base = base;
        this.porcentaje = porcentaje;
    }

    public Comisionista(String cedula, String nombre, double base) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.base = base;
    }

    @Override
    public double pagarIncentivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Calcula el salario quincenal del comisionista. Combina el salario base
     * (ajustado a la quincena) con la comisión sobre las ventas.
     *
     * @return Salario total de la quincena.
     */
    @Override
    public double salarioQuincena() {
        double fijo = /* ¿base mensual? */ true ? base / 2.0 : base;
        return fijo + (ventasQuincena * porcentaje);
    }

    /**
     * Calcula la bonificación del comisionista. Aplica una política del 2%
     * sobre las ventas de la quincena.
     *
     * @return Monto de la bonificación.
     */
    @Override
    public double Bono() {
        return ventasQuincena * 0.02; 
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getVentasQuincena() {
        return ventasQuincena;
    }

    public void setVentasQuincena(double ventasQuincena) {
        this.ventasQuincena = ventasQuincena;
    }

    @Override
    public String toString() {
        return "Comisionista{" + "nombre=" + nombre + '}';
    }

    @Override
    public String getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
