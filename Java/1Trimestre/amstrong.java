import java.util.*;

public class amstrong {
	public static Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("Dame un número entero positivo: ");
		//Declado n (número principal) y lo leo por teclado
		int n;
		n = teclado.nextInt();
		//Luego declado el numero en si
		int numero;
		numero = n;
		int suma;
		suma = 0;
		// Compruebo que el número es mayor que 0 con un if, si no lo es, no se podrá hacer
		if (n < 0) {
			System.out.println("Debes ingresar un número entero positivo");
		}
		// Si es mayor que 0, se podrá calcular el número Armstrong
		else {
			while (n > 0) {
				int cifra;
				//Hago la fórmula para saber si el número es armstrong o no
				cifra = n % 10;
				suma += cifra * cifra * cifra; // Se pone el cubo del dígito y lo sumo
				n = n / 10;
			}
			//Si este número, es igual al a la suma de sus cifras al cubo sumadas, es número armstrong
			//Ejemplo el mismo 153 porque la suma de sus cifras al cubo son iguales.
			if (suma == numero) {
				System.out.println(numero + " es un número Armstrong.");
			} 
			//Si no es igual, como por ejemplo el numero 100, no es armstrong
			else {
				System.out.println(numero + " no es un número Armstrong.");
			}
		}
	}
}
