package empresa;

public class Operario extends Empleado {
	private int nave; // 1 a 5

	public Operario(String nombre, double sueldo, int nave) {
		super(nombre, sueldo, 1200);
		setNave(nave);
	}

	public int getNave() {
		return nave;
	}

	public void setNave(int nave) {
		if (nave >= 1 && nave <= 5) {
			this.nave = nave;
		}
	}

	@Override
	public String toString() {
		return super.toString() + ", tipo: Operario, nave: " + nave;
	}
}
