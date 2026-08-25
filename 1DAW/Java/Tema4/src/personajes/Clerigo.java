package personajes;

public class Clerigo extends Personaje {
	private String dios;

	// fuerza minima 18, inteligencia entre 12 y 16
	public Clerigo(String nombre, String raza, int fuerza, int inteligencia, int vidaMaxima, String dios) throws PersonajeException {
		super(nombre, raza, fuerza, inteligencia, vidaMaxima);
		if (fuerza < 18) {
			throw new PersonajeException("Un Clerigo necesita fuerza minima de 18");
		}
		if (inteligencia < 12 || inteligencia > 16) {
			throw new PersonajeException("Un Clerigo necesita inteligencia entre 12 y 16");
		}
		this.dios = dios;
	}

	public String getDios() {
		return dios;
	}

	public void setDios(String dios) {
		this.dios = dios;
	}

	// cura 10 puntos de vida al objetivo
	public void curar(Personaje objetivo) {
		objetivo.setVidaActual(objetivo.getVidaActual() + 10);
		System.out.println(getNombre() + " cura a " + objetivo.getNombre());
	}

	@Override
	public String toString() {
		return "Clerigo " + super.toString() + " dios=" + dios;
	}
}
