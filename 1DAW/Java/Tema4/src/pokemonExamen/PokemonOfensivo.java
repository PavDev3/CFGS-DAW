package pokemonExamen;

public class PokemonOfensivo extends Pokemon {

    private int ataquesFuertes;

    // constructor
    public PokemonOfensivo(int numeroPokedex, String nombre, Tipo tipo, int nivelActual,
                           Movimiento[] movimientos, int[][] aprendizaje, int ataquesFuertes) {
        super(numeroPokedex, nombre, tipo, nivelActual, movimientos, aprendizaje);
        this.ataquesFuertes = ataquesFuertes;
    }

    // getter
    public int getAtaquesFuertes() {
        return ataquesFuertes;
    }

    @Override
    public double calcularIndiceCombate() {
        return calcularPotenciaMediaDisponible() + ataquesFuertes * 2;
    }

    @Override
    public boolean necesitaMejorar() {
        return contarMovimientosDisponibles() < 2 || calcularPotenciaMediaDisponible() < 50;
    }

    @Override
    public String toString() {
        return "PokemonOfensivo [numeroPokedex=" + numeroPokedex + ", nombre=" + nombre +
                ", tipo=" + tipo + ", nivelActual=" + nivelActual +
                ", ataquesFuertes=" + ataquesFuertes + "]";
    }
}