package cafe;

import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Maquina maquina = new Maquina();
		
		System.out.println("Bienvenido a la maquina de cafe.");
		maquina.mostrarMenu();
		System.out.println("Seleccione una opcion:");
		int opcion = scanner.nextInt();
		while (opcion != 5) {
			maquina.ejecutarOpcion(opcion);
			maquina.mostrarMenu();
			System.out.println("Seleccione una opcion:");
			opcion = scanner.nextInt();
		}

		scanner.close();
	}

}
