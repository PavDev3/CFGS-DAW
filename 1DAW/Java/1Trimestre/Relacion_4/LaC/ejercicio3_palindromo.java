package Relacion_4.LaC;

import java.util.*;

public class ejercicio3_palindromo {
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Ingrese una palabra o frase: ");
		String texto = teclado.nextLine();
		
		// Convertir a minúsculas y eliminar espacios
		String textoLimpio = limpiarTexto(texto);
		
		if (esPalindromo(textoLimpio)) {
			System.out.println("La palabra o frase es un palíndromo");
		} else {
			System.out.println("La palabra o frase no es un palíndromo");
		}
	}
	
	// Método para verificar si es palíndromo usando solo length() y charAt()
	public static boolean esPalindromo(String texto) {
		int longitud = texto.length();
		// Comparar caracteres desde ambos extremos
		for (int i = 0; i < longitud / 2; i++) {
			if (texto.charAt(i) != texto.charAt(longitud - 1 - i)) {
				return false;
			}
		}
		return true;
	}
	
	// Limpiar texto: convertir a minúsculas y eliminar espacios usando solo charAt()
	public static String limpiarTexto(String texto) {
		String resultado = "";
		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);
			// Ignorar espacios
			if (c != ' ') {
				// Convertir a minúscula si es mayúscula
				resultado += aMinuscula(c);
			}
		}
		return resultado;
	}
	
	// Convertir un carácter a minúscula usando comparaciones
	public static char aMinuscula(char c) {
		// Verificar si es una letra (mayúscula o minúscula)
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
			// Si es mayúscula, convertir a minúscula
			if (c >= 'A' && c <= 'Z') {
				int diferencia = 'a' - 'A';
				return (char)(c + diferencia);
			}
		}
		return c;
	}
}

