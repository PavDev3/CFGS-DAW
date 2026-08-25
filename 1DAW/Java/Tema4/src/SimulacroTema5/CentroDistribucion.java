package SimulacroTema5;

public class CentroDistribucion extends CentroOperativo {

    private int paquetesUrgentes;

    // constructor
    public CentroDistribucion(String codigo, Zona zona, Responsable responsable,
            int[][] operaciones, int[][] incidencias, int paquetesUrgentes) {
        super(codigo, zona, responsable, operaciones, incidencias);
        this.paquetesUrgentes = paquetesUrgentes;
    }

    public int getPaquetesUrgentes() {
        return paquetesUrgentes;
    }

    @Override
    public double calcularIndiceEficiencia() {
        return calcularTotalOperaciones() - calcularTotalIncidencias() + paquetesUrgentes * 1.5;
    }

    @Override
    public boolean necesitaAuditoria() {
        // condicion 1: tasa de incidencias supera el 20%
        if (calcularTasaIncidencias() > 20) {
            return true;
        }

        // condicion 2: algun dia tiene menos de 8 operaciones totales
        for (int i = 0; i < 5; i++) {
            if (calcularOperacionesDia(i) < 8) {
                return true;
            }
        }

        // condicion 3: total semanal de incidencias supera 15
        if (calcularTotalIncidencias() > 15) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "CentroDistribucion [codigo=" + codigo + ", zona=" + zona
                + ", responsable=" + responsable + ", paquetesUrgentes=" + paquetesUrgentes + "]";
    }
}
