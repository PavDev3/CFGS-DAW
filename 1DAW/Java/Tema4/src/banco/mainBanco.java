package banco;

import java.util.Scanner;

public class mainBanco {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// Crear la cuenta (repite hasta que sea valida)
		cuentaBancaria cuenta = null;
		System.out.println("=== Bienvenido al Banco ===");
		
		while (cuenta == null) {
			System.out.print("Introduce el nombre del titular: ");
			String titular = scanner.nextLine();
			System.out.print("Introduce el saldo inicial: ");
			double saldoInicial = scanner.nextDouble();
			scanner.nextLine(); // Limpiar el salto de linea que deja nextDouble()
			
			try {
				cuenta = new cuentaBancaria(titular, saldoInicial);
				System.out.println("Cuenta creada: " + cuenta);
			} catch (CuentaSinTitularException e) {
				System.out.println("Error: " + e.getMessage());
				System.out.println("Intentalo de nuevo.");
			} catch (IllegalArgumentException e) {
				System.out.println("Error: " + e.getMessage());
				System.out.println("Intentalo de nuevo.");
			} finally {
				if (cuenta != null) {
					System.out.println("Saldo actual: " + cuenta.getSaldo());
				}
			}
		}
		
		// Menu interactivo
		MenuBanco menu = new MenuBanco(cuenta);
		int opcion = 0;
		
		while (opcion != 4) {
			menu.mostrarMenu();
			System.out.print("Seleccione una opcion: ");
			opcion = scanner.nextInt();
			
			if (opcion == 2 || opcion == 3) {
				System.out.print("Ingrese la cantidad: ");
				double cantidad = scanner.nextDouble();
				menu.ejecutarOpcion(opcion, cantidad);
			} else {
				menu.ejecutarOpcion(opcion, 0);
			}
		}
		
		scanner.close();
	}
}
