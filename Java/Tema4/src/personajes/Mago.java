package personajes;

public class Mago extends Personaje {
	private String[] hechizos; // maximo 4 hechizos

	// inteligencia minima 17, fuerza maxima 15
	public Mago(String nombre, String raza, int fuerza, int inteligencia, int vidaMaxima) throws PersonajeException {
		super(nombre, raza, fuerza, inteligencia, vidaMaxima);
		if (inteligencia < 17) {
			throw new PersonajeException("Un Mago necesita inteligencia minima de 17");
		}
		if (fuerza > 15) {
			throw new PersonajeException("Un Mago no puede tener fuerza mayor que 15");
		}
		hechizos = new String[4];
	}

	// busca hueco libre (null) y guarda el hechizo
	public void aprendeHechizo(String hechizo) {
		for (int i = 0; i < hechizos.length; i++) {
			if (hechizos[i] == null) {
				hechizos[i] = hechizo;
				System.out.println(getNombre() + " aprende: " + hechizo);
				return;
			}
		}
		System.out.println(getNombre() + " no puede aprender mas hechizos (maximo 4)");
	}

	// resta 10 de vida al objetivo y olvida el hechizo
	public void lanzaHechizo(Personaje objetivo, String hechizo) {
		for (int i = 0; i < hechizos.length; i++) {
			if (hechizo.equals(hechizos[i])) {
				objetivo.setVidaActual(objetivo.getVidaActual() - 10);
				hechizos[i] = null;
				System.out.println(getNombre() + " lanza " + hechizo + " sobre " + objetivo.getNombre());
				return;
			}
		}
		System.out.println(getNombre() + " no conoce el hechizo: " + hechizo);
	}

	public String[] getHechizos() {
		return hechizos;
	}

	@Override
	public String toString() {
		String lista = "";
		for (int i = 0; i < hechizos.length; i++) {
			if (hechizos[i] != null) {
				lista += hechizos[i] + " ";
			}
		}
		return "Mago " + super.toString() + " hechizos=[" + lista.trim() + "]";
	}
}
