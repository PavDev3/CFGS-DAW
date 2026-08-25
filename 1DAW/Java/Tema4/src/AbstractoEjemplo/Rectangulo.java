package AbstractoEjemplo;

public class Rectangulo extends Figura{

	public int lado1;
	public int lado2;
	

	public Rectangulo(String color, int grosorLinea, int lado1, int lado2) {
		super(color, grosorLinea);
		this.lado1 = lado1;
		this.lado2 = lado2;
	}
	
	public int getLado1() {
		return lado1;
	}

	public void setLado1(int lado1) {
		this.lado1 = lado1;
	}

	public int getLado2() {
		return lado2;
	}

	public void setLado2(int lado2) {
		this.lado2 = lado2;
	}

	@Override
	public void colorearInterior() {
		System.out.println("Colorenado interior del rectangulo");
		
	}

	
	@Override
	public double calcularArea() {
		
		return lado1 * lado2;
	}
	
	public String toString() {
		return super.toString() + "Rectangulo. Lado 1 " + lado1 + " Lado 2 " + lado2;
	}
		
}