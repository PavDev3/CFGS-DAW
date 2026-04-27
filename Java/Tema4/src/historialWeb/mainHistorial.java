package historialWeb;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class mainHistorial {

    public static void main(String[] args) {
        Historial historial = new Historial();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menu Historial Web ---");
            System.out.println("1. Nueva pagina consultada");
            System.out.println("2. Consultar historial completo");
            System.out.println("3. Consultar historial de un dia");
            System.out.println("4. Borrar historial");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("URL: ");
                    String url = sc.nextLine();
                    try {
                        historial.nuevaPagina(url);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    historial.consultarHistorial();
                    break;
                case 3:
                    System.out.print("Fecha (dd/MM/yyyy): ");
                    String fechaStr = sc.nextLine();
                    LocalDate fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    historial.consultarDia(fecha);
                    break;
                case 4:
                    historial.borrarHistorial();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 5);

        sc.close();
    }
}
