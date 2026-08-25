package rentingCar;

public class Furgoneta extends Vehiculo {
	private double pma; // peso maximo autorizado

	public Furgoneta(String matricula, String gama, double pma) {
		super(matricula, gama);
		this.pma = pma;
	}

	public double getPma() {
		return pma;
	}

	// precio base + 0.5 * pma por dia
	@Override
	public double calcularAlquiler(int dias) {
		return (getPrecioBase() + 0.5 * pma) * dias;
	}

	@Override
	public String toString() {
		return "Furgoneta [matricula=" + getMatricula() + ", gama=" + getGama() + ", pma=" + pma + "]";
	}
}
