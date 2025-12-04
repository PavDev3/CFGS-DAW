package Relacion_4.LaC;

import java.util.*;

public class ejercicio2_contarMinusMayusNum {
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Introduce una cadena de texto: ");
		String cadena = teclado.nextLine();
		
		int minusculas = contarMinusculas(cadena);
		int mayusculas = contarMayusculas(cadena);
		int numeros = contarNumeros(cadena);
		
		System.out.println("La cadena de texto tiene " + minusculas + " minúsculas");
		System.out.println("La cadena de texto tiene " + mayusculas + " mayúsculas");
		System.out.println("La cadena de texto tiene " + numeros + " números");
	}
	
	// Contar letras minúsculas usando solo charAt() y comparaciones
	public static int contarMinusculas(String cadena) {
		int contador = 0;
		for (int i = 0; i < cadena.length(); i++) {
			if (esLetraMinuscula(cadena.charAt(i))) {
				contador++;
			}
		}
		return contador;
	}
	
	// Contar letras mayúsculas usando solo charAt() y comparaciones
	public static int contarMayusculas(String cadena) {
		int contador = 0;
		for (int i = 0; i < cadena.length(); i++) {
			if (esLetraMayuscula(cadena.charAt(i))) {
				contador++;
			}
		}
		return contador;
	}
	
	// Contar números usando solo charAt() y comparaciones
	public static int contarNumeros(String cadena) {
		int contador = 0;
		for (int i = 0; i < cadena.length(); i++) {
			if (esDigito(cadena.charAt(i))) {
				contador++;
			}
		}
		return contador;
	}
	
	// Verificar si un carácter es una letra minúscula (a-z)
	public static boolean esLetraMinuscula(char c) {
		return c >= 'a' && c <= 'z';
	}
	
	// Verificar si un carácter es una letra mayúscula (A-Z)
	public static boolean esLetraMayuscula(char c) {
		return c >= 'A' && c <= 'Z';
	}
	
	// Verificar si un carácter es un dígito (0-9)
	public static boolean esDigito(char c) {
		return c >= '0' && c <= '9';
	}
}

