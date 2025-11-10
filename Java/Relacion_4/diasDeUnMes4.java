package Relacion_4;
import java.util.Scanner;

public class diasDeUnMes4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int anio = solicitarAnio(scanner);
		int mes = solicitarMes(scanner);
		int dias = obtenerDiasDelMes(anio, mes);
		if (esBisiesto(anio)) {
			System.out.println("El anio " + anio + " es bisiesto.");
		} else {
			System.out.println("El anio " + anio + " no es bisiesto.");
		}
		
		
		
		
		scanner.close();
	}
	
	public static int solicitarAnio(Scanner scanner) {
		int anio;
		boolean anioValido = false;
		do {
			System.out.print("Introduce un anio: ");
			anio = scanner.nextInt();
			if (anio > 0) {
				anioValido = true;
			} else {
				System.out.println("El anio debe ser DC ( Despues de Cristo ). Intentalo de nuevo.");
			}
		}
		// Cierra el bucle si el año es valido
		while (!anioValido);
		return anio;       
	}
	
	public static boolean esBisiesto(int anio) {
		return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
	}
	
	public static int solicitarMes(Scanner scanner) {
		int mes;
		boolean mesValido = true;
		do {
			System.out.print("Introduce un mes (1-12): ");
			mes = scanner.nextInt();
			if (mes >= 1 && mes <= 12) {
				mesValido = false;
			} else {
				System.out.println("El mes debe estar entre 1 y 12. Intentalo de nuevo.");
			}
		} while (mesValido);
		return mes;       
	}
		
	public static int obtenerDiasDelMes(int anio, int mes) {
		switch (mes) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				return 31;
			case 4: case 6: case 9: case 11:
				return 30;
			case 2:
				if (esBisiesto(anio)) {
					return 29;
				} else {
					return 28;
				}
			default:
				return 0; // Mes invalido
		}
	}
	
}