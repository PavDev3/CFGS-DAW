package diccionario;

import java.util.Scanner;

public class mainDiccionario {

    public static void main(String[] args) {
        Diccionario dic = new Diccionario();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menu Diccionario ---");
            System.out.println("1. Anadir palabra");
            System.out.println("2. Buscar palabra");
            System.out.println("3. Borrar palabra");
            System.out.println("4. Listar palabras que empiecen por...");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Palabra: ");
                        String palabra = sc.nextLine();
                        System.out.print("Significado: ");
                        String sig = sc.nextLine();
                        dic.annadirPalabra(palabra, sig);
                        break;
                    case 2:
                        System.out.print("Palabra: ");
                        dic.buscarPalabra(sc.nextLine());
                        break;
                    case 3:
                        System.out.print("Palabra: ");
                        dic.borrarPalabra(sc.nextLine());
                        break;
                    case 4:
                        System.out.print("Prefijo: ");
                        dic.listarPorPrefijo(sc.nextLine());
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
