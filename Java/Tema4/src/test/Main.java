package test;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello, World!");

		int num1 = 1;
		int num2 = 2;

		int suma = sumar(num1, num2);
		System.out.println("La suma es: " + suma);
	}

	// funcion para restar num1 y num2
	private static int restar(int a, int b) {
		return a - b;
	}

	public static int sumar(int a, int b) {
		return a + b;
	}

	// public static int multiplicar(int a, int b) {
	return a*b;
}}
