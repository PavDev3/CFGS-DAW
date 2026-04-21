package pokemonExamen;

public class PokemonDefensivo extends Pokemon {

    private int resistencia;

    // constructor
    public PokemonDefensivo(int numeroPokedex, String nombre, Tipo tipo, int nivelActual,
                            Movimiento[] movimientos, int[][] aprendizaje, int resistencia) {
        super(numeroPokedex, nombre, tipo, nivelActual, movimientos, aprendizaje);
        this.resistencia = resistencia;
    }

    // getter
    public int getResistencia() {
        return resistencia;
    }

    @Override
    public double calcularIndiceCombate() {
        return calcularPotenciaMediaDisponible() + resistencia * 1.5;
    }

    @Override
    public boolean necesitaMejorar() {
        return contarMovimientosDisponibles() == 0 || nivelActual < 20;
    }

    @Override
    public String toString() {
        return "PokemonDefensivo [numeroPokedex=" + numeroPokedex + ", nombre=" + nombre +
                ", tipo=" + tipo + ", nivelActual=" + nivelActual +
                ", resistencia=" + resistencia + "]";
    }
}