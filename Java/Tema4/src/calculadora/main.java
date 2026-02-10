package calculadora;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double num1 = leerEntero(scanner, "Ingrese el primer número entero: ");
		double num2 = leerEntero(scanner, "Ingrese el segundo número entero: ");
		char operacion = leerOperacion(scanner, "Ingrese la operación (+, -, *, /): ");
		 //double resultado =
		calcular(num1, num2, operacion);
		 double resultado = calcular(num1, num2, operacion);
		 System.out.println(toString(resultado));
		 scanner.close();
	}

	public static double leerEntero(Scanner scanner, String mensaje) {
	/*	boolean validInput = false;
		int numero = 0;
		while (!validInput) {
			System.out.print(mensaje);
			try {
				 scanner.nextLine(); // Limpiar el buffer antes de leer la entrada
				 numero = scanner.nextInt();
				 validInput = true;
			} catch (NumberFormatException e) {
				System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
				scanner.nextLine(); // Limpiar la entrada no válida
			}
			
			
		}
		return numero; */
		
		
		System.out.print(mensaje);
		while (!scanner.hasNextInt()) {
			System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
			scanner.next(); // Limpiar la entrada no válida
		}
		return scanner.nextInt();
	}
	
	public static char leerOperacion(Scanner scanner, String mensaje) {
		System.out.print(mensaje);
		while (!scanner.hasNext("[+\\-*/]")) {
			System.out.println("Operación no válida. Por favor, ingrese una operación válida (+, -, *, /).");
			scanner.next(); // Limpiar la entrada no válida
		}
		return scanner.next().charAt(0);
	}
	
	public static double calcular(double num1, double num2, char operacion) {
		double resultado = 0;
		switch (operacion) {
			case '+':
				resultado = num1 + num2;
				break;
			case '-':
				resultado = num1 - num2;
				break;
			case '*':
				resultado = num1 * num2;
				break;
			case '/':
				if (num2 != 0) {
					resultado = num1 / num2;
				} else {
			System.out.println("Error: División por cero.");
				return 0;
				}
				break;
			default:
			System.out.println("Operación no válida.");
			return 0;
		}
		return resultado;
	}
	
	
	// metodo toString para mostrar el resultado de la operación
	public static String toString(double resultado) {
		return "El resultado de la operación es: " + resultado;	
	}
	

	
}



