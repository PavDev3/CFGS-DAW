package rentingCar;

public class Coche extends Vehiculo {
	private String combustible; // gasolina o diesel

	public Coche(String matricula, String gama, String combustible) {
		super(matricula, gama);
		this.combustible = combustible;
	}

	public String getCombustible() {
		return combustible;
	}

	// gasolina +3.5 euros/dia, diesel +2 euros/dia
	@Override
	public double calcularAlquiler(int dias) {
		double extra = combustible.equals("gasolina") ? 3.5 : 2.0;
		return (getPrecioBase() + extra) * dias;
	}

	@Override
	public String toString() {
		return "Coche [matricula=" + getMatricula() + ", gama=" + getGama() + ", combustible=" + combustible + "]";
	}
}
