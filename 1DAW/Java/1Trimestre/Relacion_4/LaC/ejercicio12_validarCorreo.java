package Relacion_4.LaC;

import java.util.*;

public class ejercicio12_validarCorreo {
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Introduce una dirección de correo electrónico para validar:");
		String correo = teclado.nextLine();
		
		// Validar la dirección de correo
		if (esValida(correo)) {
			System.out.println("VÁLIDA: " + correo);
		} else {
			System.out.println("INVÁLIDA: " + correo);
		}
	}
	
	// Método que valida una dirección de correo usando solo length() y charAt()
	// Formato: usuario@organizacion.fin
	public static boolean esValida(String correo) {
		// Longitud mínima: 1 (usuario) + 1 (@) + 1 (organizacion) + 1 (.) + 2 (fin) = 6
		if (correo.length() < 6) {
			return false;
		}
		
		// Buscar la posición del símbolo @
		int posArroba = -1;
		for (int i = 0; i < correo.length(); i++) {
			if (correo.charAt(i) == '@') {
				posArroba = i;
				break;
			}
		}
		
		// Si no se encuentra el @, la dirección no es válida
		if (posArroba == -1) {
			return false;
		}
		
		// Verificar que haya al menos un carácter antes del @ (usuario)
		if (posArroba < 1) {
			return false;
		}
		
		// Validar el usuario (parte antes del @)
		// Debe tener longitud >= 1 y comenzar por una letra
		// Puede contener letras, números o punto
		if (!validarUsuario(correo, 0, posArroba - 1)) {
			return false;
		}
		
		// Buscar la posición del punto que separa organización y fin
		int posPunto = -1;
		for (int i = posArroba + 1; i < correo.length(); i++) {
			if (correo.charAt(i) == '.') {
				posPunto = i;
				break;
			}
		}
		
		// Si no se encuentra el punto, la dirección no es válida
		if (posPunto == -1) {
			return false;
		}
		
		// Verificar que haya al menos un carácter entre @ y . (organización)
		if (posPunto <= posArroba + 1) {
			return false;
		}
		
		// Validar la organización (parte entre @ y .)
		// Debe tener longitud >= 1, comenzar por letra, y contener solo letras y números
		if (!validarOrganizacion(correo, posArroba + 1, posPunto - 1)) {
			return false;
		}
		
		// Validar el fin (parte después del punto)
		// Debe tener longitud 2 o 3 y contener solo letras
		if (!validarFin(correo, posPunto + 1, correo.length() - 1)) {
			return false;
		}
		
		return true;
	}
	
	// Método para validar el usuario (letras, números o punto, longitud >= 1, comienza por letra)
	public static boolean validarUsuario(String correo, int inicio, int fin) {
		// Verificar que haya al menos un carácter
		if (fin < inicio) {
			return false;
		}
		
		// El primer carácter debe ser una letra
		char primerChar = correo.charAt(inicio);
		if (!esLetra(primerChar)) {
			return false;
		}
		
		// Verificar el resto de caracteres (pueden ser letras, números o punto)
		for (int i = inicio + 1; i <= fin; i++) {
			char c = correo.charAt(i);
			if (!esLetra(c) && !esNumero(c) && c != '.') {
				return false;
			}
		}
		
		return true;
	}
	
	// Método para validar la organización (letras y números, longitud >= 1, comienza por letra)
	public static boolean validarOrganizacion(String correo, int inicio, int fin) {
		// Verificar que haya al menos un carácter
		if (fin < inicio) {
			return false;
		}
		
		// El primer carácter debe ser una letra
		char primerChar = correo.charAt(inicio);
		if (!esLetra(primerChar)) {
			return false;
		}
		
		// Verificar el resto de caracteres (solo letras y números)
		for (int i = inicio + 1; i <= fin; i++) {
			char c = correo.charAt(i);
			if (!esLetra(c) && !esNumero(c)) {
				return false;
			}
		}
		
		return true;
	}
	
	// Método para validar el fin (solo letras, longitud 2 o 3)
	public static boolean validarFin(String correo, int inicio, int fin) {
		// Calcular la longitud del fin
		int longitud = fin - inicio + 1;
		
		// La longitud debe ser 2 o 3
		if (longitud != 2 && longitud != 3) {
			return false;
		}
		
		// Todos los caracteres deben ser letras
		for (int i = inicio; i <= fin; i++) {
			char c = correo.charAt(i);
			if (!esLetra(c)) {
				return false;
			}
		}
		
		return true;
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

