package supermercado;

import java.util.Scanner;

public class mainSupermercado {

    public static void main(String[] args) {
        Supermercado sm = new Supermercado();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menu Supermercado ---");
            System.out.println("1. Abrir caja");
            System.out.println("2. Cerrar caja");
            System.out.println("3. Nuevo cliente");
            System.out.println("4. Atender cliente");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Numero de caja: ");
                        sm.abrirCaja(sc.nextInt());
                        sc.nextLine();
                        break;
                    case 2:
                        System.out.print("Numero de caja: ");
                        sm.cerrarCaja(sc.nextInt());
                        sc.nextLine();
                        break;
                    case 3:
                        sm.nuevoCliente();
                        break;
                    case 4:
                        System.out.print("Numero de caja: ");
                        sm.atenderCliente(sc.nextInt());
                        sc.nextLine();
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 5);

        sc.close();
    }
}
