package recetario;

public class Ingrediente {

    private String nombre;
    private double cantidad;

    public Ingrediente(String nombre, double cantidad) {
        this.nombre   = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre()   { return nombre; }
    public double getCantidad() { return cantidad; }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingrediente)) return false;
        return nombre.equals(((Ingrediente) o).nombre);
    }

    @Override
    public int hashCode() { return nombre.hashCode(); }

    @Override
    public String toString() {
        return nombre + " (" + cantidad + ")";
    }
}
