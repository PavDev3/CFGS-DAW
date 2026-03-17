package personajes;

public class Personaje {
	private String nombre;
	private String raza; // humano, elfo, enano, orco
	private int fuerza; // 0-20
	private int inteligencia; // 0-20
	private int vidaMaxima; // 0-100
	private int vidaActual; // 0-vidaMaxima

	public Personaje(String nombre, String raza, int fuerza, int inteligencia, int vidaMaxima) throws PersonajeException {
		this.nombre = nombre;
		this.raza = raza;
		setFuerza(fuerza);
		setInteligencia(inteligencia);
		setVidaMaxima(vidaMaxima);
		this.vidaActual = vidaMaxima;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) throws PersonajeException {
		if (fuerza < 0 || fuerza > 20) {
			throw new PersonajeException("Fuerza invalida: debe estar entre 0 y 20");
		}
		this.fuerza = fuerza;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(int inteligencia) throws PersonajeException {
		if (inteligencia < 0 || inteligencia > 20) {
			throw new PersonajeException("Inteligencia invalida: debe estar entre 0 y 20");
		}
		this.inteligencia = inteligencia;
	}

	public int getVidaMaxima() {
		return vidaMaxima;
	}

	public void setVidaMaxima(int vidaMaxima) throws PersonajeException {
		if (vidaMaxima < 0 || vidaMaxima > 100) {
			throw new PersonajeException("Vida maxima invalida: debe estar entre 0 y 100");
		}
		this.vidaMaxima = vidaMaxima;
	}

	public int getVidaActual() {
		return vidaActual;
	}

	public void setVidaActual(int vidaActual) {
		if (vidaActual < 0) {
			this.vidaActual = 0;
		} else if (vidaActual > vidaMaxima) {
			this.vidaActual = vidaMaxima;
		} else {
			this.vidaActual = vidaActual;
		}
	}

	@Override
	public String toString() {
		return nombre + " [" + raza + "] fuerza=" + fuerza + " int=" + inteligencia
				+ " vida=" + vidaActual + "/" + vidaMaxima;
	}
}
