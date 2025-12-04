package Relacion_4.LaC;

import java.util.*;

public class ejercicio9_sumarNumeros {
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Introduce una frase: ");
		String frase = teclado.nextLine();
		
		int suma = sumarNumeros(frase);
		System.out.println("El total de la suma de los números comprendidos en la frase es de " + suma);
	}
	
	// Sumar todos los números en la frase usando solo length() y charAt()
	public static int sumarNumeros(String frase) {
		int suma = 0;
		int inicioNumero = -1;
		
		// Recorrer la frase
		for (int i = 0; i < frase.length(); i++) {
			char c = frase.charAt(i);
			
			if (esDigito(c)) {
				// Si empezamos un nuevo número
				if (inicioNumero == -1) {
					inicioNumero = i;
				}
			} else {
				// Si encontramos un no-dígito y teníamos un número en construcción
				if (inicioNumero != -1) {
					// Construir y sumar el número
					int numero = construirNumero(frase, inicioNumero, i - 1);
					suma += numero;
					inicioNumero = -1;
				}
			}
		}
		
		// Si al final queda un número sin procesar
		if (inicioNumero != -1) {
			int numero = construirNumero(frase, inicioNumero, frase.length() - 1);
			suma += numero;
		}
		
		return suma;
	}
	
	// Construir un número desde una cadena usando solo charAt()
	public static int construirNumero(String s, int inicio, int fin) {
		int numero = 0;
		for (int i = inicio; i <= fin; i++) {
			int digito = convertirDigitoAInt(s.charAt(i));
			numero = numero * 10 + digito;
		}
		return numero;
	}
	
	// Convertir un dígito char a int sin usar parseInt()
	public static int convertirDigitoAInt(char c) {
		return c - '0';
	}
	
	// Verificar si un carácter es un dígito (0-9)
	public static boolean esDigito(char c) {
		return c >= '0' && c <= '9';
	}
}

