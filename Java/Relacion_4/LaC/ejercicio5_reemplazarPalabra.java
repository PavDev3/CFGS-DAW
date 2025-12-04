package Relacion_4.LaC;

import java.util.*;

public class ejercicio5_reemplazarPalabra {
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.print("Introduce un texto original: ");
		String original = teclado.nextLine();
		
		System.out.print("Introduce el texto a buscar: ");
		String buscar = teclado.nextLine();
		
		System.out.print("Introduce el texto a reemplazar: ");
		String reemplazo = teclado.nextLine();
		
		// Convertir a minúsculas para búsqueda sin distinción
		String originalMin = convertirAMinuscula(original);
		String buscarMin = convertirAMinuscula(buscar);
		
		String resultado = reemplazarPalabra(original, originalMin, buscarMin, reemplazo);
		
		System.out.println("El texto modificado es:");
		System.out.println(resultado);
	}
	
	// Reemplazar palabra usando solo length() y charAt()
	public static String reemplazarPalabra(String original, String originalMin, String buscar, String reemplazo) {
		String resultado = "";
		int i = 0;
		
		while (i < originalMin.length()) {
			boolean coincidencia = false;
			
			// Verificar si la palabra a buscar cabe desde la posición actual
			if (i + buscar.length() <= originalMin.length()) {
				boolean iguales = true;
				// Comparar carácter por carácter
				for (int j = 0; j < buscar.length(); j++) {
					if (originalMin.charAt(i + j) != buscar.charAt(j)) {
						iguales = false;
						break;
					}
				}
				if (iguales) {
					coincidencia = true;
				}
			}
			
			// Si hay coincidencia, agregar el reemplazo
			if (coincidencia) {
				// Agregar el texto de reemplazo
				for (int k = 0; k < reemplazo.length(); k++) {
					resultado += reemplazo.charAt(k);
				}
				i += buscar.length(); // Saltar la palabra encontrada
			} else {
				// Agregar el carácter original
				resultado += original.charAt(i);
				i++;
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
}

