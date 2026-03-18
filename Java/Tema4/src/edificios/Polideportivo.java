package edificios;

public class Polideportivo implements InstalacionDeportiva, Edificio {
	private String nombre;
	private double superficie;
	private int tipo;

	public Polideportivo(String nombre, double superficie, int tipo) {
		this.nombre = nombre;
		this.superficie = superficie;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public int getTipoDeInstalacion() {
		return tipo;
	}

	@Override
	public double getSuperficieEdificio() {
		return superficie;
	}

	@Override
	public String toString() {
		return "Polideportivo: " + nombre + ", superficie=" + superficie + ", tipo=" + tipo;
	}
}
