package cafe;

import java.util.Scanner;

public class Main_Cafe {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Maquina maquina = new Maquina();
		
		//
		System.out.println("Bienvenido a la maquina de cafe.");
		maquina.mostrarMenu();
		System.out.println("Seleccione una opcion:");
		int opcion = scanner.nextInt();
		while (opcion != 7) {
			// Opciones que requieren dinero (1, 2, 3)
			if (opcion >= 1 && opcion <= 3) {
				System.out.println("Introduzca el dinero:");
				double dinero = scanner.nextDouble();
				maquina.ejecutarOpcion(opcion, dinero);
			} else if (opcion == 5) {
				// Llenar depósitos
				maquina.llenarDepositos();
			} else if (opcion == 6) {
				// Vaciar monedero
				maquina.vaciarMonedero();
			} else {
				// Opciones que no requieren dinero (4, 7)
				maquina.ejecutarOpcion(opcion, 0);
			}

			// Mostrar el menú y solicitar la siguiente opción
			maquina.mostrarMenu();
			System.out.println("Seleccione una opcion:");
			// Repetir hasta que se seleccione la opción de salir (7)
			opcion = scanner.nextInt();
		}

		scanner.close();
	}

}
