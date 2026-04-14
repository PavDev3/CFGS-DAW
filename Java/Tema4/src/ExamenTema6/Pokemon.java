package ExamenTema6;

public abstract class Pokemon implements Entrenable {

    protected int numeroPokedex;
    protected String nombre;
    protected Tipo tipo;
    protected int nivelActual;
    protected Movimiento[] movimientos;
    protected int[][] aprendizaje; // [i][0] = nivel, [i][1] = potencia

    // constructor
    public Pokemon(int numeroPokedex, String nombre, Tipo tipo, int nivelActual,
                   Movimiento[] movimientos, int[][] aprendizaje) {
        this.numeroPokedex = numeroPokedex;
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivelActual = nivelActual;
        this.movimientos = movimientos;
        this.aprendizaje = aprendizaje;
    }

    // getters
    public int getNumeroPokedex() {
        return numeroPokedex;
    }

    public String getNombre() {
        return nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public Movimiento[] getMovimientos() {
        return movimientos;
    }

    public int[][] getAprendizaje() {
        return aprendizaje;
    }

    // metodos de logica
    public int contarMovimientosDisponibles() {
        int count = 0;
        for (int i = 0; i < aprendizaje.length; i++) {
            if (aprendizaje[i][0] <= nivelActual) {
                count++;
            }
        }
        return count;
    }

    public double calcularPotenciaMediaDisponible() {
        int count = 0;
        int sumaPotencia = 0;
        for (int i = 0; i < aprendizaje.length; i++) {
            if (aprendizaje[i][0] <= nivelActual) {
                sumaPotencia += aprendizaje[i][1];
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        return (double) sumaPotencia / count;
    }

    public void mostrarMovimientosDisponibles() {
        System.out.println("Movimientos disponibles de " + nombre + " (nivel " + nivelActual + "):");
        boolean hayAlguno = false;
        for (int i = 0; i < aprendizaje.length; i++) {
            if (aprendizaje[i][0] <= nivelActual) {
                System.out.println("  - " + movimientos[i].getNombre() +
                        " | tipo: " + movimientos[i].getTipo() +
                        " | potencia: " + movimientos[i].getPotencia() +
                        " | se aprende en nivel " + aprendizaje[i][0]);
                hayAlguno = true;
            }
        }
        if (!hayAlguno) {
            System.out.println("  No hay movimientos disponibles todavia.");
        }
    }

    public abstract double calcularIndiceCombate();

    @Override
    public String toString() {
        return "Pokemon [numeroPokedex=" + numeroPokedex + ", nombre=" + nombre +
                ", tipo=" + tipo + ", nivelActual=" + nivelActual + "]";
    }
}
