package ej6;

public class EdificioOficinas implements Edificio {
	private int numOficinas;
	private double superficie;

	public EdificioOficinas(double superficie, int numOficinas) {
		this.superficie = superficie;
		this.numOficinas = numOficinas;
	}

	public int getNumOficinas() {
		return numOficinas;
	}

	@Override
	public double getSuperficieEdificio() {
		return superficie;
	}

	@Override
	public String toString() {
		return "EdificioOficinas: superficie=" + superficie + ", oficinas=" + numOficinas;
	}
}
