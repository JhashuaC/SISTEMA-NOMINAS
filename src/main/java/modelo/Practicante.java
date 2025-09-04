package modelo;

public class Practicante implements BonificableInterface {

    private String cedula;

    private String nombre;

    private double apoyoQuincena;

    public Practicante() {
    }

    public Practicante(String cedula, String nombre, double apoyoQuincena) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apoyoQuincena = apoyoQuincena;
    }

  @Override public double Bono() { return 0.0; } // “sin bonificación por defecto”

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

    public double getApoyoQuincena() {
        return apoyoQuincena;
    }

    public void setApoyoQuincena(double apoyoQuincena) {
        this.apoyoQuincena = apoyoQuincena;
    }

    @Override
    public String toString() {
        return "Practicante{" + "cedula=" + cedula + ", nombre=" + nombre + ", apoyoQuincena=" + apoyoQuincena + '}';
    }
}
