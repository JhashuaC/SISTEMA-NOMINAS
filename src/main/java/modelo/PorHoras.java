package modelo;

public class PorHoras extends Empleado {

    private String cedula;

    private String nombre;

    private float tarifaDiaria;

    private float horasQuincena;
    
      public PorHoras() {
    }

    public PorHoras(String cedula, String nombre, float tarifaDiaria, float horasQuincena) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tarifaDiaria = tarifaDiaria;
        this.horasQuincena = horasQuincena;
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

    public float getTarifaDiaria() {
        return tarifaDiaria;
    }

    public void setTarifaDiaria(float tarifaDiaria) {
        this.tarifaDiaria = tarifaDiaria;
    }

    public float getHorasQuincena() {
        return horasQuincena;
    }

    public void setHorasQuincena(float horasQuincena) {
        this.horasQuincena = horasQuincena;
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

    @Override
    public String toString() {
        return "PorHoras{" + "nombre=" + nombre + '}';
    }
    
    
    
}
