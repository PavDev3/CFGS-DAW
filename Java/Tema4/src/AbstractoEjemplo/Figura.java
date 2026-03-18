package AbstractoEjemplo;

public abstract class Figura {
	private String color;
	private int grosorLinea;
	
	public abstract void colorearInterior();
	public abstract double calcularArea();

	
	public Figura(String color, int grosorLinea) {
		this.color = color;
		this.grosorLinea = grosorLinea;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getGrosorLinea() {
		return grosorLinea;
	}

	public void setGrosorLinea(int grosorLinea) {
		this.grosorLinea = grosorLinea;
	}

	@Override
	public String toString() {
		return "Figura [color=" + color + ", grosorLinea=" + grosorLinea + "]";
	}
	
}