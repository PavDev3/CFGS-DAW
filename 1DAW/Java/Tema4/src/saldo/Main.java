package saldo;

import java.util.Scanner;
public class Main {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		// generar saldo inicial aleatorio entre 100 y 1000
		int saldoInicial = (int)(Math.random() * 901) + 100;
		Cuenta cuenta = new Cuenta(saldoInicial, 0);
		
		cuenta.mostrarSaldo(saldoInicial);
		Menu menu = new Menu(cuenta);
		
		// crear menu
		System.out.println("Bienvenido al sistema de gestion de cuenta de FilipoBank.");
		// mostrar menu
		menu.mostrarMenu();
		System.out.println("Seleccione una opcion:");
		int opcion = scanner.nextInt();
		// ejecutar opcion
		while (opcion != 4) {
			// Opciones para depositar o retirar
			if (opcion == 2 || opcion == 3) {
				System.out.println("Ingrese la cantidad:");
				int cantidad = scanner.nextInt();
				menu.ejecutarOpcion(opcion, cantidad);
			} else {
				// Opciones para mostrar salir
				menu.ejecutarOpcion(opcion, 0);
			}
			// mostrar menu nuevamente
			menu.mostrarMenu();
			System.out.println("Seleccione una opcion:");
			opcion = scanner.nextInt();
		}		
		scanner.close();
	}
}
