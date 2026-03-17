package AbstractoEjemplo;

public class Triangulo extends Figura{
	private int base;
	private int altura;
	
	
	public Triangulo(String color, int grosorLinea, int base, int altura) {
		super(color, grosorLinea);
		this.base=base;
		this.altura=altura;
	}


	@Override
	public void colorearInterior() {
		System.out.println("Coloreando el triangulo");
		
	}


	@Override
	public double calcularArea() {
		return (base* altura)/2;
	}
	
}