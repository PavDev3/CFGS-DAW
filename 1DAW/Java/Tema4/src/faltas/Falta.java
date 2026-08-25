package faltas;

public class Falta {
	// Atributos
	private int justificadas;
	private int injustificadas;
	private int retrasos;

	// Constructor
	public Falta(int justificadas, int injustificadas, int retrasos) {
		this.justificadas = justificadas;
		this.injustificadas = injustificadas;
		this.retrasos = retrasos;
	}

	// Getters y Setters
	public int getJustificadas() {
		return justificadas;
	}

	public void setJustificadas(int justificadas) {
		this.justificadas = justificadas;
	}

	public int getInjustificadas() {
		return injustificadas;
	}

	public void setInjustificadas(int injustificadas) {
		this.injustificadas = injustificadas;
	}

	public int getRetrasos() {
		return retrasos;
	}

	public void setRetrasos(int retrasos) {
		this.retrasos = retrasos;
	}
}
