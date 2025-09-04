package modelo;
/**
 * Representa un empleado temporal.
 * Calcula el salario quincenal multiplicando la tarifa diaria por los días activos trabajados en la quincena.
 */
public class Temporal extends Empleado {

    private String cedula;

    private double tarifaDiaria;

    private String nombre;

    private int diasActivos;

    public Temporal() {
    }

    public Temporal(String cedula, double tarifaDiaria, String nombre, int diasActivos) {
        this.cedula = cedula;
        this.tarifaDiaria = tarifaDiaria;
        this.nombre = nombre;
        this.diasActivos = diasActivos;
    }

    @Override public double salarioQuincena() {
    int diasPagados = Math.min(diasActivos, 15);
    return tarifaDiaria * diasPagados;
}
    @Override public double Bono() {
    return (diasActivos >= 12) ? salarioQuincena() * 0.05 : 0.0;
   }


    @Override
    public double pagarIncentivo() {
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

    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    public void setTarifaDiaria(double tarifaDiaria) {
        this.tarifaDiaria = tarifaDiaria;
    }

    public int getDiasActivos() {
        return diasActivos;
    }

    public void setDiasActivos(int diasActivos) {
        this.diasActivos = diasActivos;
    }

    

    @Override
    public String toString() {
        return "Temporal{" + "nombre=" + nombre + '}';
    }

    @Override
    public String getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
