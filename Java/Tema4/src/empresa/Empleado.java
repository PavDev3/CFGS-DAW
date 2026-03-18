package empresa;

public class Empleado {
	private String nombre;
	private double sueldo;
	protected double sueldoMaximo;

	public Empleado(String nombre, double sueldo, double sueldoMaximo) {
		this.nombre = nombre;
		this.sueldoMaximo = sueldoMaximo;
		setSueldo(sueldo);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		if (sueldo <= sueldoMaximo) {
			this.sueldo = sueldo;
		}
	}

	@Override
	public String toString() {
		return "Empleado: " + nombre + ", sueldo: " + sueldo;
	}
}
