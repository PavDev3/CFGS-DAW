package SimulacroTema5;

public class Programador extends Empleado {

    private int incidenciasResueltas;

    public Programador(String id, String nombre, Departamento departamento,
                       double[][] horasTrabajadas, int incidenciasResueltas) {
        super(id, nombre, departamento, horasTrabajadas);
        this.incidenciasResueltas = incidenciasResueltas;
    }

    public int getIncidenciasResueltas() { return incidenciasResueltas; }

    // productividad = horasTotalesSemana + incidenciasResueltas * 2
    @Override
    public double calcularProductividad() {
        return calcularHorasTotalesSemana() + incidenciasResueltas * 2;
    }

    // Merece reconocimiento si productividad >= 45
    @Override
    public boolean mereceReconocimiento() {
        return calcularProductividad() >= 45;
    }

    @Override
    public String toString() {
        return "Programador{id='" + id + "', nombre='" + nombre +
               "', departamento=" + departamento +
               ", incidenciasResueltas=" + incidenciasResueltas + "}";
    }
}
