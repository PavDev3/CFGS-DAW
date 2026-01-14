package Relacion_4.LaC;

import java.util.*;

public class ejercicio4_palabraEscondida {
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Introduce frase: ");
		String frase = teclado.nextLine();
		System.out.println("Palabra escondida: ");
		String palabra = teclado.nextLine();
		
		// Convertir a minúsculas para comparación
		String fraseMin = convertirAMinuscula(frase);
		String palabraMin = convertirAMinuscula(palabra);
		
		if (buscarPalabraEscondida(fraseMin, palabraMin)) {
			System.out.println("Encontrada");
		} else {
			System.out.println("No se encuentra");
		}
	}
	
	// Buscar palabra escondida usando solo length() y charAt()
	// La palabra debe aparecer en orden dentro de la frase
	public static boolean buscarPalabraEscondida(String frase, String palabra) {
		if (palabra.length() == 0) {
			return true;
		}
		if (frase.length() < palabra.length()) {
			return false;
		}
		
		int indicePalabra = 0;
		// Recorrer la frase buscando los caracteres de la palabra en orden
		for (int i = 0; i < frase.length(); i++) {
			if (frase.charAt(i) == palabra.charAt(indicePalabra)) {
				indicePalabra++;
				// Si encontramos todos los caracteres, la palabra está escondida
				if (indicePalabra == palabra.length()) {
					return true;
				}
			}
		}
		return false;
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
}

