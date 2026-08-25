package rentingCar;

import java.util.Scanner;

public class mainAlquiler {

	static Vehiculo[] vehiculos = new Vehiculo[200];
	static int numVehiculos = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;

		while (opcion != 3) {
			System.out.println("1. Alta de vehiculo");
			System.out.println("2. Calcular precio de alquiler");
			System.out.println("3. Salir");
			System.out.print("Opcion: ");
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
				case 1:
					altaVehiculo(sc);
					break;
				case 2:
					calcularAlquiler(sc);
					break;
				case 3:
					System.out.println("Hasta luego.");
					break;
				default:
					System.out.println("Opcion invalida.");
			}
		}
		sc.close();
	}

	private static void altaVehiculo(Scanner sc) {
		if (numVehiculos >= 200) {
			System.out.println("Capacidad maxima alcanzada.");
			return;
		}
		System.out.print("Tipo (coche/microbus/furgoneta): ");
		String tipo = sc.nextLine();
		System.out.print("Matricula: ");
		String matricula = sc.nextLine();
		System.out.print("Gama (alta/media/baja): ");
		String gama = sc.nextLine();

		if (tipo.equals("coche")) {
			System.out.print("Combustible (gasolina/diesel): ");
			String combustible = sc.nextLine();
			vehiculos[numVehiculos] = new Coche(matricula, gama, combustible);
		} else if (tipo.equals("microbus")) {
			System.out.print("Plazas: ");
			int plazas = sc.nextInt();
			sc.nextLine();
			vehiculos[numVehiculos] = new Microbus(matricula, gama, plazas);
		} else if (tipo.equals("furgoneta")) {
			System.out.print("PMA (kg): ");
			double pma = sc.nextDouble();
			sc.nextLine();
			vehiculos[numVehiculos] = new Furgoneta(matricula, gama, pma);
		} else {
			System.out.println("Tipo desconocido.");
			return;
		}
		numVehiculos++;
		System.out.println("Vehiculo dado de alta.");
	}

	private static void calcularAlquiler(Scanner sc) {
		System.out.print("Matricula del vehiculo: ");
		String matricula = sc.nextLine();
		// buscar el vehiculo por matricula
		for (int i = 0; i < numVehiculos; i++) {
			if (vehiculos[i].getMatricula().equals(matricula)) {
				System.out.print("Numero de dias: ");
				int dias = sc.nextInt();
				sc.nextLine();
				System.out.println("Precio del alquiler: " + vehiculos[i].calcularAlquiler(dias) + " euros");
				return;
			}
		}
		System.out.println("Vehiculo no encontrado.");
	}
}
