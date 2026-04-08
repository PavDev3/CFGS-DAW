package simulacroFinal;


public class Administrativo extends Empleado {

    private int documentosTramitados;

    public Administrativo(String id, String nombre, Departamento departamento,
                          double[][] horasTrabajadas, int documentosTramitados) {
        super(id, nombre, departamento, horasTrabajadas);
        this.documentosTramitados = documentosTramitados;
    }

    public int getDocumentosTramitados() { return documentosTramitados; }

    // productividad = horasTotalesSemana + documentosTramitados * 0.5
    @Override
    public double calcularProductividad() {
        return calcularHorasTotalesSemana() + documentosTramitados * 0.5;
    }

    // Merece reconocimiento si productividad >= 40
    @Override
    public boolean mereceReconocimiento() {
        return calcularProductividad() >= 40;
    }

    @Override
    public String toString() {
        return "Administrativo{id='" + id + "', nombre='" + nombre +
               "', departamento=" + departamento +
               ", documentosTramitados=" + documentosTramitados + "}";
    }
}