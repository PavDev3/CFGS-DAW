package Relacion_4;

import java.util.*;
public class trabajopositivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cantidad = 0;
        int suma = 0;
        int pares = 0;
        int impares = 0;
        int mayor = 0;
        int menor = 0;
        int numero;
        boolean primerNumero = true;

        do {
            System.out.print("Introduce un número entero positivo (0 para finalizar): ");
            numero = pedirNumeroPositivo(sc);
            if (numero != 0) {
                if (primerNumero) {
                    mayor = numero;
                    menor = numero;
                    primerNumero = false;
                } else {
                    if (numero > mayor) {
                        mayor = numero;
                    }
                    if (numero < menor) {
                        menor = numero;
                    }
                }
                cantidad++;
                suma += numero;
                if (esPar(numero)) {
                    pares++;
                } else {
                    impares++;
                }
            }
        } while (numero != 0);

        if(cantidad == 0){
            System.out.println("No se introdujeron números.");
        } else {
            System.out.println("Números introducidos: " + cantidad);
            System.out.println("Suma total: " + suma);
            System.out.println("Cantidad de números pares: " + pares);
            System.out.println("Cantidad de números impares: " + impares);
            System.out.println("El mayor número introducido: " + mayor);
            System.out.println("El menor número introducido: " + menor);
        }
    }

    public static boolean esPar(int num) {
        return num % 2 == 0;
    }

    public static int pedirNumeroPositivo(java.util.Scanner sc) {
        int n;
        while (true) {
            while (!sc.hasNextInt()) {
                System.out.print("Por favor, introduce un número entero: ");
                sc.next();
            }
            n = sc.nextInt();
            if (n < 0) {
                System.out.print("El número debe ser positivo o 0 para salir. Intenta de nuevo: ");
            } else {
                break;
            }
        }
        return n;
    }
}
