package Relacion_4.LaC;

import java.util.*;

public class ejercicio7_ordenarConsYVocales {
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Introduce un texto: ");
		String texto = teclado.nextLine();
		
		// Eliminar espacios y convertir a minúsculas
		String textoSinEspacios = eliminarEspacios(texto);
		String textoMin = convertirAMinuscula(textoSinEspacios);
		
		// Separar consonantes y vocales
		String consonantes = guardarConsonantes(textoMin);
		String vocales = guardarVocales(textoMin);
		
		// Mostrar resultado: consonantes primero, luego vocales
		System.out.println("El texto ordenado es: " + consonantes + vocales);
	}
	
	// Eliminar espacios usando solo length() y charAt()
	public static String eliminarEspacios(String texto) {
		String resultado = "";
		for (int i = 0; i < texto.length(); i++) {
			if (texto.charAt(i) != ' ') {
				resultado += texto.charAt(i);
			}
		}
		return resultado;
	}
	
	// Convertir cadena a minúscula usando solo charAt()
	public static String convertirAMinuscula(String s) {
		String resultado = "";
		for (int i = 0; i < s.length(); i++) {
			resultado += aMinuscula(s.charAt(i));
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
	
	// Guardar solo las consonantes usando solo length() y charAt()
	public static String guardarConsonantes(String texto) {
		String consonantes = "";
		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);
			if (!esVocal(c)) {
				consonantes += c;
			}
		}
		return consonantes;
	}
	
	// Guardar solo las vocales usando solo length() y charAt()
	public static String guardarVocales(String texto) {
		String vocales = "";
		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);
			if (esVocal(c)) {
				vocales += c;
			}
		}
		return vocales;
	}
	
	// Verificar si un carácter es vocal
	public static boolean esVocal(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}

