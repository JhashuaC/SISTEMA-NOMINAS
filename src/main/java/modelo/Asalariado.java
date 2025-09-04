package modelo;

public class Asalariado extends Empleado {

    private String cedula;

    private String nombre;

    private double salarioMensual;

    public Asalariado() {
    }

    public Asalariado(String cedula, String nombre, double salarioMensual) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.salarioMensual = salarioMensual;
    }

    @Override
    public double salarioQuincena() {
      return salarioMensual/2;
 
    }

    @Override
    public double pagarIncentivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double Bono() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    public double getSalarioMensual() {
        return salarioMensual;
    }

    public void setSalarioMensual(double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }

    @Override
    public String toString() {
        return "Asalariado{" + "nombre=" + nombre + '}';
    }
}
