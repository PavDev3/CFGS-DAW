package cuentaAvanzado;

import java.util.Scanner;

public class MenuCuentaCredito {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CuentaCredito cuenta = new CuentaCredito();
		int opcion = 0;

		while (opcion != 4) {
			System.out.println("1. Ingresar dinero");
			System.out.println("2. Sacar dinero");
			System.out.println("3. Mostrar saldo y credito");
			System.out.println("4. Salir");
			System.out.print("Opcion: ");
			opcion = sc.nextInt();

			switch (opcion) {
				case 1:
					System.out.print("Cantidad a ingresar: ");
					double ingresar = sc.nextDouble();
					cuenta.ingresarDinero(ingresar);
					System.out.println("Saldo actual: " + cuenta.getSaldo());
					break;
				case 2:
					System.out.print("Cantidad a sacar: ");
					double sacar = sc.nextDouble();
					try {
						cuenta.sacarDinero(sacar);
						System.out.println("Saldo actual: " + cuenta.getSaldo());
					} catch (SaldoInsuficienteException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					System.out.println("Saldo: " + cuenta.getSaldo());
					System.out.println("Credito disponible: " + cuenta.getCredito());
					break;
				case 4:
					System.out.println("Hasta luego.");
					break;
				default:
					System.out.println("Opcion invalida.");
			}
		}
		sc.close();
	}
}
