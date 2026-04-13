package SimulacroTema5;

public class CentroSeguridad extends CentroOperativo {

    private int inspeccionesEspeciales;

    // constructor
    public CentroSeguridad(String codigo, Zona zona, Responsable responsable,
            int[][] operaciones, int[][] incidencias, int inspeccionesEspeciales) {
        super(codigo, zona, responsable, operaciones, incidencias);
        this.inspeccionesEspeciales = inspeccionesEspeciales;
    }

    public int getInspeccionesEspeciales() {
        return inspeccionesEspeciales;
    }

    @Override
    public double calcularIndiceEficiencia() {
        return calcularTotalOperaciones() - calcularTotalIncidencias() * 0.5 + inspeccionesEspeciales * 2;
    }

    @Override
    public boolean necesitaAuditoria() {
        // condicion 1: existe algun turno con 0 operaciones
        // condicion 2: existe algun turno con mas incidencias que operaciones
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                if (operaciones[i][j] == 0) {
                    return true;
                }
                if (incidencias[i][j] > operaciones[i][j]) {
                    return true;
                }
            }
        }

        // condicion 3: total semanal de operaciones es menor que 35
        if (calcularTotalOperaciones() < 35) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "CentroSeguridad [codigo=" + codigo + ", zona=" + zona
                + ", responsable=" + responsable + ", inspeccionesEspeciales=" + inspeccionesEspeciales + "]";
    }
}
