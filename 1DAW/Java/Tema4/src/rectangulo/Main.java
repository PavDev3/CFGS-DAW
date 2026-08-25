package rectangulo;

import java.util.Scanner;
public class Main {
	public static void main (String[] args){
			
		Rectangulo rect1 = new Rectangulo();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el ancho del rectangulo (valor entre 0 y 20): ");
		double anchoInput = scanner.nextDouble();
		System.out.println("Ingrese la longitud del rectangulo (valor entre 0 y 20): ");
		double longitudInput = scanner.nextDouble();
		
		rect1.setAncho(anchoInput);
		rect1.setLongitud(longitudInput);
		
		System.out.println("El ancho del rectangulo es: " + rect1.getAncho());
		System.out.println("La longitud del rectangulo es: " + rect1.getLongitud());
		System.out.println("El area del rectangulo es: " + rect1.calcularArea());
		System.out.println("El perimetro del rectangulo es: " + rect1.calcularPerimetro());
		
		scanner.close();
	}
}