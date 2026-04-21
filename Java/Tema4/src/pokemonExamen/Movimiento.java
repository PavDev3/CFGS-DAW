package pokemonExamen;

public class Movimiento {

    private String nombre;
    private Tipo tipo;
    private int potencia;

    // constructor
    public Movimiento(String nombre, Tipo tipo, int potencia) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.potencia = potencia;
    }

    // getters
    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getPotencia() {
        return potencia;
    }

    @Override
    public String toString() {
        return "Movimiento [nombre=" + nombre + ", tipo=" + tipo + ", potencia=" + potencia + "]";
    }
}