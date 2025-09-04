package modelo;

public class PorHoras extends Empleado {

    private String cedula;

    private String nombre;

    private float tarifaHora;

    private float horasQuincena;
    
      public PorHoras() {
    }

    public PorHoras(String cedula, String nombre, float tarifaHora, float horasQuincena) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tarifaHora = tarifaHora;
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

    public float getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(float tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

   
    public float getHorasQuincena() {
        return horasQuincena;
    }

    public void setHorasQuincena(float horasQuincena) {
        this.horasQuincena = horasQuincena;
    }


    @Override
    public double salarioQuincena() {
        return horasQuincena*tarifaHora;
         }

    @Override
    public double pagarIncentivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 
    @Override public double Bono() {
    return (horasQuincena > 80f) ? (tarifaHora * horasQuincena) * 0.10 : 0.0;
}


    @Override
    public String toString() {
        return "PorHoras{" + "nombre=" + nombre + '}';
    }

    @Override
    public String getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    
}
