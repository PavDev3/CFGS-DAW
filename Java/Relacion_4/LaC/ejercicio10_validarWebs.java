package Relacion_4.LaC;

import java.util.*;

public class ejercicio10_validarWebs {
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Introduce 10 direcciones web para validar:");
		
		// Bucle para solicitar 10 direcciones
		for (int i = 1; i <= 10; i++) {
			System.out.print("Dirección " + i + ": ");
			String direccion = teclado.nextLine();
			
			// Validar la dirección
			if (esValida(direccion)) {
				System.out.println("VÁLIDA: " + direccion);
			} else {
				System.out.println("INVÁLIDA: " + direccion);
			}
		}
	}
	
	// Método que valida una dirección web usando solo length() y charAt()
	public static boolean esValida(String url) {
		// Longitud mínima: "http://www." (11) + 1 letra + ".com" (4) = 15
		// O "http://www." (11) + 1 letra + ".es" (3) = 14
		if (url.length() < 14) {
			return false;
		}
		
		// Verificar que empiece con "http://www."
		String prefijo = "http://www.";
		if (url.length() < prefijo.length()) {
			return false;
		}
		
		// Verificar carácter por carácter el prefijo usando charAt()
		for (int i = 0; i < prefijo.length(); i++) {
			if (url.charAt(i) != prefijo.charAt(i)) {
				return false;
			}
		}
		
		// El primer carácter del dominio está en la posición 11 (después de "http://www.")
		int posInicioDominio = 11;
		
		// Verificar que haya al menos un carácter después de "www."
		if (url.length() <= posInicioDominio) {
			return false;
		}
		
		// Verificar que el primer carácter del dominio sea una letra
		char primerChar = url.charAt(posInicioDominio);
		if (!esLetra(primerChar)) {
			return false;
		}
		
		// Buscar el punto que separa el dominio de la extensión
		// Empezamos desde la posición siguiente al primer carácter del dominio
		int posPunto = -1;
		for (int i = posInicioDominio + 1; i < url.length(); i++) {
			if (url.charAt(i) == '.') {
				posPunto = i;
				break;
			}
			// Verificar que los caracteres intermedios sean letras o números
			char c = url.charAt(i);
			if (!esLetra(c) && !esNumero(c)) {
				return false;
			}
		}
		
		// Si no se encontró el punto, la dirección no es válida
		if (posPunto == -1) {
			return false;
		}
		
		// Verificar que haya al menos un carácter en el dominio antes del punto
		// (el dominio debe tener al menos 1 carácter, que ya verificamos que es letra)
		// El punto debe estar al menos en la posición 12 (posInicioDominio + 1)
		if (posPunto <= posInicioDominio) {
			return false; // No hay dominio antes del punto
		}
		
		// Verificar la extensión: debe ser ".com" o ".es"
		int longitud = url.length();
		
		// Verificar extensión ".com" (4 caracteres)
		if (longitud >= posPunto + 4) {
			// Verificar que termine en ".com" usando charAt()
			if (url.charAt(posPunto) == '.' && 
			    url.charAt(posPunto + 1) == 'c' && 
			    url.charAt(posPunto + 2) == 'o' && 
			    url.charAt(posPunto + 3) == 'm' &&
			    posPunto + 4 == longitud) {
				return true;
			}
		}
		
		// Verificar extensión ".es" (3 caracteres)
		if (longitud >= posPunto + 3) {
			// Verificar que termine en ".es" usando charAt()
			if (url.charAt(posPunto) == '.' && 
			    url.charAt(posPunto + 1) == 'e' && 
			    url.charAt(posPunto + 2) == 's' &&
			    posPunto + 3 == longitud) {
				return true;
			}
		}
		
		return false;
	}
	
	// Método auxiliar para verificar si un carácter es una letra (usando solo comparaciones)
	public static boolean esLetra(char c) {
		// Verificar si es una letra minúscula (a-z) o mayúscula (A-Z)
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}
	
	// Método auxiliar para verificar si un carácter es un número (usando solo comparaciones)
	public static boolean esNumero(char c) {
		// Verificar si es un dígito (0-9)
		return c >= '0' && c <= '9';
	}
}

