package Relacion_4.LaC;

import java.util.*;

public class ejercicio11_ahorcado {
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Introduce una palabra: ");
		String palabra = teclado.nextLine();
		
		// Convertir a minúsculas
		String palabraMin = convertirAMinuscula(palabra);
		
		// Ocultar palabra
		String palabraOculta = ocultarPalabra(palabraMin);
		System.out.println(palabraOculta);
		
		// Jugar
		jugar(palabraMin, palabraOculta);
	}
	
	// Ocultar palabra con asteriscos usando solo length() y charAt()
	public static String ocultarPalabra(String palabra) {
		String palabraOculta = "";
		for (int i = 0; i < palabra.length(); i++) {
			palabraOculta += "*";
		}
		// Limpiar pantalla (imprimir líneas en blanco)
		for (int i = 0; i < 30; i++) {
			System.out.println("");
		}
		return palabraOculta;
	}
	
	// Jugar al ahorcado
	public static void jugar(String palabra, String palabraOculta) {
		int intentos = 7;
		
		// El juego continúa mientras haya intentos y queden asteriscos
		while (intentos > 0 && tieneAsteriscos(palabraOculta)) {
			System.out.println("Introduce una letra: ");
			String entrada = teclado.nextLine();
			char letra = entrada.charAt(0);
			
			// Convertir letra a minúscula
			char letraMin = aMinuscula(letra);
			
			// Buscar la letra en la palabra
			boolean letraEncontrada = false;
			String nuevaPalabraOculta = "";
			
			for (int i = 0; i < palabra.length(); i++) {
				if (palabra.charAt(i) == letraMin) {
					// Revelar la letra
					nuevaPalabraOculta += letraMin;
					letraEncontrada = true;
				} else {
					// Mantener lo que había (letra revelada o asterisco)
					nuevaPalabraOculta += palabraOculta.charAt(i);
				}
			}
			
			if (letraEncontrada) {
				palabraOculta = nuevaPalabraOculta;
				System.out.println("Letra encontrada!");
			} else {
				intentos--;
				System.out.println("Letra no encontrada");
			}
			
			System.out.println("Palabra: " + palabraOculta);
			System.out.println("Intentos restantes: " + intentos);
			System.out.println();
		}
		
		// Verificar resultado final
		if (!tieneAsteriscos(palabraOculta)) {
			System.out.println("¡Has ganado! La palabra era: " + palabra);
		} else {
			System.out.println("Has perdido. La palabra era: " + palabra);
		}
	}
	
	// Verificar si una cadena tiene asteriscos usando solo length() y charAt()
	public static boolean tieneAsteriscos(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '*') {
				return true;
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

