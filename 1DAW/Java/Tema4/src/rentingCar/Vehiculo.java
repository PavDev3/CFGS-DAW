package rentingCar;

public abstract class Vehiculo {
	private String matricula;
	private String gama; // alta, media, baja

	public Vehiculo(String matricula, String gama) {
		this.matricula = matricula;
		this.gama = gama;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getGama() {
		return gama;
	}

	// precio base segun gama: baja 30, media 40, alta 50
	public double getPrecioBase() {
		if (gama.equals("alta")) {
			return 50;
		} else if (gama.equals("media")) {
			return 40;
		} else {
			return 30;
		}
	}

	public abstract double calcularAlquiler(int dias);
}
