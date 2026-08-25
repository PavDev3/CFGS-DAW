package Relacion_4.LaC;

import java.util.Scanner;

public class ejercico13_ivan {
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		// EJERCICIO 1: Simulador de partido
		ejercicio1();
		
		// EJERCICIO 2: Fuerza bruta de claves
		ejercicio2();
	}
	
	// ========== EJERCICIO 1: SIMULADOR DE PARTIDO ==========
	public static void ejercicio1() {
		// Poner nombres de los países
		System.out.print("Primer país: ");
		String pais1 = teclado.nextLine();
		System.out.print("Segundo país: ");
		String pais2 = teclado.nextLine();
		
		// Sorteo para el saque inicial usando solo métodos propios
		String saque = numeroAleatorio() < 0.5 ? pais1 : pais2;
		System.out.println("-> " + saque + " al saque");
		
		// Variables con los puntos en el marcador
		int puntos1 = 0, puntos2 = 0;
		int set1 = 0, set2 = 0;
		
		// Reglas del partido
		int puntosvictoria = 21;
		int puntoscambiocampo = 7;
		
		// Bucle del partido hasta que haya un ganador
		while (set1 < 2 && set2 < 2) {
			System.out.println("Set: " + set1 + " Puntos: " + puntos1 + " [" + pais1 + "]");
			System.out.println("Set: " + set2 + " Puntos: " + puntos2 + " [" + pais2 + "]");
			
			// Bucle del set hasta que haya un ganador
			while (!setpaisganador(puntos1, puntos2, puntosvictoria)) {
				System.out.println("-> " + saque + " al saque");
				System.out.print("¿Quién ganó el punto? (" + pais1 + "/" + pais2 + "): ");
				String ganador = teclado.nextLine();
				
				if (sonIgualesIgnoreCase(ganador, pais1)) {
					puntos1++;
					saque = pais1;
				} else if (sonIgualesIgnoreCase(ganador, pais2)) {
					puntos2++;
					saque = pais2;
				} else {
					System.out.println("Error. Inténtalo de nuevo.");
					continue;
				}
				
				// Cambio de campo
				if ((puntos1 + puntos2) % puntoscambiocampo == 0) {
					System.out.println("-------------------------");
					System.out.println("-----Cambio de campo-----");
					System.out.println("-------------------------");
				}
				
				// Mostrar marcador actualizado
				System.out.println("Set: " + set1 + " Puntos: " + puntos1 + " [" + pais1 + "]");
				System.out.println("Set: " + set2 + " Puntos: " + puntos2 + " [" + pais2 + "]");
			}
			
			// Determinar ganador del set
			if (puntos1 > puntos2) {
				set1++;
				System.out.println("¡Set ganado por " + pais1 + "!");
			} else {
				set2++;
				System.out.println("¡Set ganado por " + pais2 + "!");
			}
			
			// Preparación del nuevo set
			puntos1 = 0;
			puntos2 = 0;
			
			// Ajustes de reglas para el tercer set
			if (set1 == 1 && set2 == 1) {
				puntosvictoria = 15;
				puntoscambiocampo = 5;
			}
		}
		
		// Anuncio del ganador del partido
		if (set1 > set2) {
			System.out.println("Enhorabuena, el ganador es " + pais1 + "!!!!!");
		} else {
			System.out.println("Enhorabuena, el ganador es " + pais2 + "!!!!!");
		}
	}
	
	// Método para verificar si un país ganó el set usando solo comparaciones
	public static boolean setpaisganador(int puntos1, int puntos2, int puntosvictoria) {
		// Verificar si alguno llegó a los puntos de victoria
		boolean algunoGano = (puntos1 >= puntosvictoria) || (puntos2 >= puntosvictoria);
		// Verificar diferencia de al menos 2 puntos usando método propio
		boolean diferenciaSuficiente = valorAbsoluto(puntos1 - puntos2) >= 2;
		return algunoGano && diferenciaSuficiente;
	}
	
	// Método para calcular valor absoluto sin usar Math.abs()
	public static int valorAbsoluto(int numero) {
		if (numero < 0) {
			return -numero;
		}
		return numero;
	}
	
	// Método para comparar strings sin distinción de mayúsculas/minúsculas usando solo charAt() y length()
	public static boolean sonIgualesIgnoreCase(String s1, String s2) {
		// Si las longitudes son diferentes, no son iguales
		if (s1.length() != s2.length()) {
			return false;
		}
		
		// Comparar carácter por carácter sin distinción de mayúsculas/minúsculas
		for (int i = 0; i < s1.length(); i++) {
			char c1 = aMinuscula(s1.charAt(i));
			char c2 = aMinuscula(s2.charAt(i));
			if (c1 != c2) {
				return false;
			}
		}
		return true;
	}
	
	// Método para generar número aleatorio entre 0 y 1 sin usar Math.random()
	public static double numeroAleatorio() {
		// Usar nanoTime para generar un número pseudoaleatorio
		long tiempo = System.nanoTime();
		// Convertir a un valor entre 0 y 1
		return (double)(tiempo % 1000) / 1000.0;
	}
	
	// ========== EJERCICIO 2: FUERZA BRUTA DE CLAVES ==========
	public static void ejercicio2() {
		// Generación de una clave random (aleatoria)
		String clave = generacionclaverandom();
		System.out.println("La combinación es: " + clave);
		
		// Clave generada para fuerza random (aleatoria)
		long comienzorandom = System.nanoTime();
		String claverandom = "";
		while (!sonIguales(clave, claverandom)) {
			claverandom = generacionclaverandom();
		}
		long finrandom = System.nanoTime();
		System.out.println("Encontrado de manera aleatoria: " + claverandom);
		System.out.println("Por fuerza aleatoria tardó: " + (finrandom - comienzorandom) / 1e9 + " segundos");
		
		// Clave generada para fuerza bruta
		long inicioclavebruta = System.nanoTime();
		String clavebruta = "";
		boolean encontrado = false;
		
		for (char c1 = 'A'; c1 <= 'Z' && !encontrado; c1++) {
			for (char c2 = 'A'; c2 <= 'Z' && !encontrado; c2++) {
				for (char c3 = 'A'; c3 <= 'Z' && !encontrado; c3++) {
					for (char c4 = 'A'; c4 <= 'Z' && !encontrado; c4++) {
						// Construir clave usando solo charAt() y length()
						clavebruta = construirCadena(c1, c2, c3, c4);
						if (sonIguales(clave, clavebruta)) {
							encontrado = true;
						}
					}
				}
			}
		}
		
		long finclavebruta = System.nanoTime();
		System.out.println("Encontrado de manera secuencial: " + clavebruta);
		System.out.println("Por fuerza bruta tardó: " + (finclavebruta - inicioclavebruta) / 1e9 + " segundos");
		
		// Clave generada para fuerza justa
		long inicioclavejusta = System.nanoTime();
		String clavejusta = "";
		for (int i = 0; i < 4; i++) {
			for (char letra = 'A'; letra <= 'Z'; letra++) {
				if (clave.charAt(i) == letra) {
					clavejusta = construirCadenaConChar(clavejusta, letra);
					break;
				}
			}
		}
		
		long finclavejusta = System.nanoTime();
		System.out.println("Encontrado letra a letra: " + clavejusta);
		System.out.println("Por fuerza justa tardó: " + (finclavejusta - inicioclavejusta) / 1e9 + " segundos");
	}
	
	// Método para generar una clave aleatoria sin usar Random
	public static String generacionclaverandom() {
		char c1 = generarLetraAleatoria();
		char c2 = generarLetraAleatoria();
		char c3 = generarLetraAleatoria();
		char c4 = generarLetraAleatoria();
		return construirCadena(c1, c2, c3, c4);
	}
	
	// Método para generar una letra aleatoria entre A y Z sin usar Random
	public static char generarLetraAleatoria() {
		// Usar nanoTime para generar un número pseudoaleatorio
		long tiempo = System.nanoTime();
		int numero = (int)(tiempo % 26);
		// Convertir a letra mayúscula
		return (char)('A' + numero);
	}
	
	// Método para construir cadena a partir de caracteres usando solo charAt() y length()
	public static String construirCadena(char c1, char c2, char c3, char c4) {
		String resultado = "";
		resultado += c1;
		resultado += c2;
		resultado += c3;
		resultado += c4;
		return resultado;
	}
	
	// Método para agregar un carácter a una cadena usando solo charAt() y length()
	public static String construirCadenaConChar(String s, char c) {
		String resultado = s;
		resultado += c;
		return resultado;
	}
	
	// Método para comparar dos strings usando solo charAt() y length()
	public static boolean sonIguales(String s1, String s2) {
		// Si las longitudes son diferentes, no son iguales
		if (s1.length() != s2.length()) {
			return false;
		}
		
		// Comparar carácter por carácter
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	// Método auxiliar para convertir un carácter a minúscula usando comparaciones
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
