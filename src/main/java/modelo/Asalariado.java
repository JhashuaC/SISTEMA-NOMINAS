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
     return 0;
    }

<<<<<<< HEAD
    @Override
    public double Bono() {
      return 0;
    }
=======
   
   @Override public double Bono() { return salarioMensual * 0.05; } // exacto al enunciado

>>>>>>> b8c0f2aad2946c0d6bc1afff3b7ac9625df67388

    @Override
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
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

    @Override
    public String getTipo() {
        return "SEA SAPO";
    }
}
