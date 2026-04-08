package simulacroFinal;

public class Programador extends Empleado {
	// atributos
	private int incidenciasResueltas;
	
	// constructor
	public Programador ( String nombre, String id, Departamento departamento, double [][] horasTrabajadas, int incidenciasResueltas) {
		super(nombre, id, departamento, horasTrabajadas);
		this.incidenciasResueltas = incidenciasResueltas;
	}
	
	public int getIncidenciasResueltas() {
		return incidenciasResueltas;
	}
	
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
	
	// toString
	@Override
	public String toString() {
		return "Programador{id='" + getId() + "', nombre='" + getNombre() +
				"', departamento=" + getDepartamento() +
				", incidenciasResueltas=" + incidenciasResueltas + "}";
	}


}
