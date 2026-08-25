package rentingCar;

public class Microbus extends Vehiculo {
	private int plazas;

	public Microbus(String matricula, String gama, int plazas) {
		super(matricula, gama);
		this.plazas = plazas;
	}

	public int getPlazas() {
		return plazas;
	}

	// precio base + 5 euros por plaza al dia
	@Override
	public double calcularAlquiler(int dias) {
		return (getPrecioBase() + 5 * plazas) * dias;
	}

	@Override
	public String toString() {
		return "Microbus [matricula=" + getMatricula() + ", gama=" + getGama() + ", plazas=" + plazas + "]";
	}
}
