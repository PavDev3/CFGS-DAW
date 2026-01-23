package tienda;

import java.util.Scanner;

public class Main_Tienda {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Articulo camisa = new Articulo("Camisa", 19.99f, 50);
		Articulo pantalon = new Articulo("Pantalon", 39.99f, 30);
		Articulo zapatos = new Articulo("Zapato", 59.99f, 0);

		int opcion = 0;
		while (opcion != 3) {
			System.out.println("Menu:");
			System.out.println("1. Informacion de los productos");
			System.out.println("2. Vender producto");
			System.out.println("3. Salir");
			System.out.println("Seleccione una opcion:");
			opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				System.out.println(camisa.mostrarInfo());
				System.out.println(pantalon.mostrarInfo());
				System.out.println(zapatos.mostrarInfo());
				break;
			case 2:
				System.out.println("Seleccione un producto para vender:");
				System.out.println("1. Camisa");
				System.out.println("2. Pantalon");
				System.out.println("3. Zapatos");
				int producto = scanner.nextInt();
				switch (producto) {
				case 1:
					System.out.println("Cantidad para vender:");
					int cantidadVenderCamisa = scanner.nextInt();
					if (camisa.getStock() >= cantidadVenderCamisa) {
						camisa.vender(cantidadVenderCamisa);
						System.out.println("Venta realizada.");
					} else {
						System.out.println("No hay suficiente stock.");
					}
					break;
				case 2:
					System.out.println("Cantidad para vender:");
					int cantidadPantalon = scanner.nextInt();
					if (pantalon.getStock() >= cantidadPantalon) {
						pantalon.vender(cantidadPantalon);
						System.out.println("Venta realizada.");
					} else {
						System.out.println("No hay suficiente stock.");
					}
					break;
				case 3:
					System.out.println("Cantidad para vender:");
					int cantidadVenderZapatos = scanner.nextInt();
					if (zapatos.getStock() >= cantidadVenderZapatos) {
						zapatos.vender(cantidadVenderZapatos);
						System.out.println("Venta realizada.");
					} else {
						System.out.println("No hay suficiente stock.");
					}
					break;
				}
				break;
			case 3:
				System.out.println("Fin del programa.");
				break;
			default:
				System.out.println("Opcion no valida.");
				break;
			}
		}
		scanner.close();
	}
}