import java.util.*;

public class exam_billete {
	
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num, b500, b200, b100, b50, b20, b10, b5, b2, b1;
		int cantidad;
		
		// Pedimos el numero a introducir 
		System.out.println("Ingresar un numero: ");		
		num = scanner.nextInt();
	
		// Cuando el numero sea 0 o menor no salimos del bucle
		while (num <= 0 ) {
			System.out.println("Introduzca un numero mayor de 0");
			num = scanner.nextInt();
		}
		//Comprobamos cuantos billetes de 500
		b500 = num / 500;
		num = num % 500;
		//Comprobamos cuantos billetes de 200
		b200 = num / 200;
		num = num % 200;
		// Comprobamos cuantos billetes de 100
		b100 = num / 100;
		num = num % 100;
		//Comprobamos cuantos billetes de 50
		b50 = num / 50;
		num = num % 50;
		//Comprobamos cuantos billetes de 20
		b20 = num / 20;
		num = num % 20;
		// Comprobamos cuantos billetes de 10
		b10 = num / 10;
		num = num % 10;
		// Comprobamos cuantos billetes de 5
		b5 = num / 5;
		num = num % 5;
		// Comprobamos cuantas monedas de 2
		b2 = num / 2;
		num = num % 2;
		// Comprobamos cuantas monedas de 1
		b1 = num / 1;
		num = num % 1;
		
		// Imprimimos las cantidades 
		// Los if es para no imprimir si es valor 0.	
		if (b500 != 0) {
			System.out.println(b500 + " billete de 500");			
		}
		if (b200 != 0) {
			System.out.println(b200 + " billete de 200");
		}
		if (b100 != 0) {
			System.out.println(b100 + " billete de 100");			
		}
		if (b50 != 0) {
			System.out.println(b50 + " billete de 50");
		} 
		if (b20 != 0) {
			System.out.println(b20 + " billete de 20");
		}
		if (b10 != 0) {
			System.out.println(b10 + " billete de 10");
		}
		if (b5 != 0) {
			System.out.println(b5 + " billete de 5");
		}
		if (b2 != 0) {
			System.out.println(b2 + " moneda de 2");
		}
		if (b1 != 0) {
			System.out.println(b1 + " moneda de 1");
		}
		scanner.close();
	}
}