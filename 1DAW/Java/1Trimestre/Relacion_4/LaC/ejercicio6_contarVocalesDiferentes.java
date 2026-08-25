package Relacion_4.LaC;

import java.util.*;

public class ejercicio6_contarVocalesDiferentes {
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Introduce un texto: ");
		String texto = teclado.nextLine();
		
		// Convertir a minúsculas
		String textoMin = convertirAMinuscula(texto);
		
		int vocalesDiferentes = contarVocalesDiferentes(textoMin);
		System.out.println("El texto tiene " + vocalesDiferentes + " vocales diferentes");
	}
	
	// Contar vocales diferentes usando solo length() y charAt()
	public static int contarVocalesDiferentes(String texto) {
		boolean tieneA = false;
		boolean tieneE = false;
		boolean tieneI = false;
		boolean tieneO = false;
		boolean tieneU = false;
		
		// Recorrer el texto
		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);
			if (c == 'a') {
				tieneA = true;
			} else if (c == 'e') {
				tieneE = true;
			} else if (c == 'i') {
				tieneI = true;
			} else if (c == 'o') {
				tieneO = true;
			} else if (c == 'u') {
				tieneU = true;
			}
		}
		
		// Contar cuántas vocales diferentes hay
		int contador = 0;
		if (tieneA) contador++;
		if (tieneE) contador++;
		if (tieneI) contador++;
		if (tieneO) contador++;
		if (tieneU) contador++;
		
		return contador;
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

