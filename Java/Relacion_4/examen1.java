package Relacion_4;

import java.util.Scanner;

public class examen1 {
	public static Scanner teclado = new Scanner(System.in);
	public static void main (String[] args) {
	System.out.println("Introduce 5 frases");
	
	
		for ( int i = 1; i <= 5; i++) {
		System.out.println("Frase " + i + ":");
		String frase = teclado.nextLine();
		String resultado = convertirApii(frase);
		System.out.println("Resultado: " + resultado);
		System.out.println();
		}
	
		System.out.println("Conversion completa");
	}
	
	
	// Metodo para convertir la frase a Pii
	
	public static String convertirApii(String frase) {
		String resultado = "";
		
		for (int i = 0; i < frase.length(); i++) {
			char caracter = frase.charAt(i);
			resultado += caracter;
			
			// Si no es e ni E
			
			if (vocalNoE(caracter)) {
				resultado += "Pi";
			}
		}
		return resultado;
	}
	
	public static boolean vocalNoE(char c) {
		// Verificar A
		if (c == 'a' || c == 'A') {
			return true;
		}
		// Verificar I
		if (c == 'i' || c == 'I') {
			return true;
			
		}
		// Verificar O
		if (c == 'o' || c == 'O') {
			return true;
		}
		// Verificar U
		if (c == 'U' || c == 'U') {
			return true;
		}
		// Entonces si es E
		return false;
	}
}
    