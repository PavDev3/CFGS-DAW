import java.util.*;

public class ejercicio1_contarCaracter {
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Introduce una cadena de texto: ");
		String cadena = teclado.nextLine();
		System.out.println("Introduce un carácter: ");
		String entrada = teclado.nextLine();
		char caracter = entrada.charAt(0);
		
		// Convertir ambos a minúsculas para comparación sin distinción
		String cadenaMin = convertirAMinuscula(cadena);
		char caracterMin = aMinuscula(caracter);
		
		int contador = contarCaracter(cadenaMin, caracterMin);
		System.out.println("El carácter '" + caracter + "' aparece " + contador + " veces en \"" + cadena + "\"");
	}
	
	// Método para contar caracteres usando solo length() y charAt()
	public static int contarCaracter(String cadena, char caracter) {
		int contador = 0;
		for (int i = 0; i < cadena.length(); i++) {
			if (cadena.charAt(i) == caracter) {
				contador++;
			}
		}
		return contador;
	}
	
	// Función auxiliar para convertir un carácter a minúscula
	public static char aMinuscula(char c) {
		// Si es una letra mayúscula (A-Z), convertir a minúscula
		if (c >= 'A' && c <= 'Z') {
			return (char)(c + 32); // Diferencia ASCII entre mayúsculas y minúsculas
		}
		return c;
	}
	
	// Función auxiliar para convertir una cadena a minúscula usando solo charAt()
	public static String convertirAMinuscula(String s) {
		String resultado = "";
		for (int i = 0; i < s.length(); i++) {
			resultado += aMinuscula(s.charAt(i));
		}
		return resultado;
	}
}

