package empresa;

public class Directivo extends Empleado {
	private String departamento;

	public Directivo(String nombre, double sueldo, String departamento) {
		super(nombre, sueldo, 3500);
		this.departamento = departamento;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return super.toString() + ", tipo: Directivo, departamento: " + departamento;
	}
}
