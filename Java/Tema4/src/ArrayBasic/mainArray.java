package ArrayBasic;

import java.util.Scanner;

public class mainArray {

	public static void main(String[] args) {
		
		
		// Ejercicio 1	
		int number[] = new int[5];
		
	
		for (int i = 0; i < number.length; i++) {
			number[i] = i + 1;
		//	System.out.println(number[i]);
		}
		
		for (int num : number) {
		//	System.out.println(num);
		}
		
		// Ejercicio 2
		
		int number2[] = {1, 2, 3, 4, 5, 6};
		
		// calcula la suma de los elementos del array
		int suma = 0;
		for (int i = 0; i < number2.length; i++) {
			suma += number2[i];
			//System.out.println(suma);
		}
		// la media de los elementos del array
		double media = (double) suma / number2.length;
			//System.out.println("La media es: " + media);
		
		// Ejercico 3
		int [] number3 = {4, -3, 7, 0, -2, 8};
		// contar numeros positivos del array y decir cuantos positivos hay e imprimer el resultado
		int countPositivos = 0;
		for (int i = 0; i < number3.length; i++) {
			if (number3[i] > 0) {
				countPositivos++;		
			}	
		}
	//	System.out.println("Cantidad de números positivos: " + countPositivos);
		
		// Ejercicio 4 
		// pedir numero entero y comprobar si esta en la array
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ingrese un número entero para buscar en el array: ");
		int numeroBuscado = scanner.nextInt();

		int number4[] = {1, 2, 3, 4, 5, 6};
		boolean encontrado = false;
		int posicion = -1;

		for (int i = 0; i < number4.length; i++) {
		    if (number4[i] == numeroBuscado) {
		        encontrado = true;
		        posicion = i;
		        break;
		    }
		}

		if (encontrado) {
		    System.out.println("El número " + numeroBuscado + " está en el array en la posición " + posicion);
		} else {
		    System.out.println("El número " + numeroBuscado + " no está en el array");
		}
	}
}
