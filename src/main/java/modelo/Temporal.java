package modelo;

public class Temporal extends Empleado {

    private String cedula;

    private String tarifaDiaria;

    private String nombre;

    private String diasActivos;

    public Temporal() {
    }

    public Temporal(String cedula, String tarifaDiaria, String nombre, String diasActivos) {
        this.cedula = cedula;
        this.tarifaDiaria = tarifaDiaria;
        this.nombre = nombre;
        this.diasActivos = diasActivos;
    }

    @Override
    public double salarioQuincena() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    public String getTarifaDiaria() {
        return tarifaDiaria;
    }

    public void setTarifaDiaria(String tarifaDiaria) {
        this.tarifaDiaria = tarifaDiaria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDiasActivos() {
        return diasActivos;
    }

    public void setDiasActivos(String diasActivos) {
        this.diasActivos = diasActivos;
    }

    @Override
    public String toString() {
        return "Temporal{" + "nombre=" + nombre + '}';
    }
}
