package empresa;

public class Informatico extends Empleado {
	private String especialidad; // DESARROLLO, SISTEMAS o BD

	public Informatico(String nombre, double sueldo, String especialidad) {
		super(nombre, sueldo, 2500);
		setEspecialidad(especialidad);
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		if (especialidad.equals("DESARROLLO") || especialidad.equals("SISTEMAS") || especialidad.equals("BD")) {
			this.especialidad = especialidad;
		}
	}

	@Override
	public String toString() {
		return super.toString() + ", tipo: Informatico, especialidad: " + especialidad;
	}
}
