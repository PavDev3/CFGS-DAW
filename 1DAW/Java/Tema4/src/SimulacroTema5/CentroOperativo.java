package SimulacroTema5;

public abstract class CentroOperativo implements Supervisable {

    protected String codigo;
    protected Zona zona;
    protected Responsable responsable;
    protected int[][] operaciones; // 5 dias x 2 turnos
    protected int[][] incidencias; // 5 dias x 2 turnos

    // constructor
    public CentroOperativo(String codigo, Zona zona, Responsable responsable, int[][] operaciones, int[][] incidencias) {
        this.codigo = codigo;
        this.zona = zona;
        this.responsable = responsable;
        this.operaciones = operaciones;
        this.incidencias = incidencias;
    }

    // getters
    public String getCodigo() {
        return codigo;
    }

    public Zona getZona() {
        return zona;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public int[][] getOperaciones() {
        return operaciones;
    }

    public int[][] getIncidencias() {
        return incidencias;
    }

    // metodos de calculo
    public int calcularTotalOperaciones() {
        int total = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                total += operaciones[i][j];
            }
        }
        return total;
    }

    public int calcularTotalIncidencias() {
        int total = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                total += incidencias[i][j];
            }
        }
        return total;
    }

    public int calcularOperacionesDia(int dia) {
        return operaciones[dia][0] + operaciones[dia][1];
    }

    public int calcularIncidenciasDia(int dia) {
        return incidencias[dia][0] + incidencias[dia][1];
    }

    public double calcularTasaIncidencias() {
        int totalOp = calcularTotalOperaciones();
        if (totalOp == 0) {
            return 0;
        }
        return (calcularTotalIncidencias() * 100.0) / totalOp;
    }

    public void mostrarResumenSemanal() {
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        System.out.println("--- Resumen semanal de " + codigo + " ---");
        for (int i = 0; i < 5; i++) {
            System.out.println(dias[i] + ":");
            System.out.println("  Operaciones manana: " + operaciones[i][0]);
            System.out.println("  Operaciones tarde:  " + operaciones[i][1]);
            System.out.println("  Incidencias manana: " + incidencias[i][0]);
            System.out.println("  Incidencias tarde:  " + incidencias[i][1]);
        }
    }

    // metodo abstracto - cada subclase lo implementa a su manera
    public abstract double calcularIndiceEficiencia();

    @Override
    public String toString() {
        return "CentroOperativo [codigo=" + codigo + ", zona=" + zona + ", responsable=" + responsable + "]";
    }
}
