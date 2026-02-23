package ArrayBasic;

import java.util.*;

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
	/*	Scanner scanner = new Scanner(System.in);
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
		}*/
		
		
		// Ejercicio 5
		// crear un array de 10 numeros enteros y muestra el mayor
		int number5[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int mayor = 0;
			
		for (int i = 0; i < number5.length; i++) {
			if (number5[i] > mayor) {
				mayor = number5[i];
			}
			
		}
		//System.out.println("El número mayor es: " + mayor);
		
		// Ejercicio 6
		// Sacar de una array de enteros pares e impares
		int number6[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		// Pares 
	//	System.out.print("Números pares: ");
		for (int i = 0; i < number6.length; i++) {
			if (number6[i] % 2 == 0) {
	//			System.out.print(number6[i] + " ");
			}
		}
	//	System.out.println();
		// Impares
	//	System.out.print("Los numeros impares son: ");
		for (int i = 0; i < number6.length; i++) {
			if (number6[i] % 2 != 0) {
	//			System.out.print(number6[i] + " ");
			}
		}
		
		// Ejercicio 7
		// Invertir Array
		
		int number7[] = {1, 2, 3, 4, 5};
		
		// Recorre la mitad del array por eso se divide entre 2, para evitar volver a invertir el array
		for (int i = 0; i < number7.length / 2; i++) {
			// Variable temporal para almacenar
			int temp = number7[i];
			// Intercambiar el elemento actual por la poisicion simetrica
			number7[i] = number7[number7.length - 1 - i];
			// Colocar el valor almacenado en la posición simetrica
			number7[number7.length - 1 - i] = temp;
		}
		// Print para el array invertido
	//	System.out.print("Array invertido: ");
		// Recorre el array y muestra los elementos
		for (int i = 0; i < number7.length; i++) {
		//	System.out.print(number7[i] + " ");
			
		}
		
		// Ejercicio 8
		//Clonar array sin clone()
		int number8[] = {1,2,3,4};
		int clone[] = new int[number8.length];
		// Recorro array original y le asigno al indice el valor a la copia
		for (int i = 0; i < number8.length; i++) {
			clone[i] = number8[i];
			
		}
		/*
		System.out.print("Array original: ");
		System.out.print((Arrays.toString(number8)) + " ");
		System.out.println();
		System.out.print("Array clonado: ");
		System.out.print(Arrays.toString(clone) + " ");
		*/
		
		// Ejercicio 9
		// Comparar arrays usando equals()
		int number9[] = {1,2,3};
		int number10[] = {1,2,3};
		
		if (number9.equals(number10)) {
	//		System.out.println("Los arrays son iguales");
		} else {
	//		System.out.println("Los arrays no son iguales");
		}
		// usando Arrays.equals() para comparar los arrays
		if (Arrays.equals(number9, number10)) {
	//		System.out.println("Los arrays son iguales");
		} else {
	//		System.out.println("Los arrays no son iguales");
		}
		
	
		
	
	
	

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
