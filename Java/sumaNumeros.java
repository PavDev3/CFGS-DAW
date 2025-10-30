
import java.util.Scanner;

public class sumaNumeros {
    public static void main(String[] args) {
        int numero;
        int sumaPares = 0;
        int sumaImpares = 0;
        Scanner scanner = new Scanner(System.in);
        //Vamos a introducir 10 numeros y sumarlos si son pares o impares y mostrar el resultado
        for (int i = 0; i < 10; i++) {// bucle para introducir 10 numeros
            System.out.println("Introduce el número " + (i + 1) + ": ");
            numero = scanner.nextInt();
            if (numero % 2 == 0) {// si el numero es par, se suma a la suma de los pares
                sumaPares += numero;
            } else {// si el numero es impar, se suma a la suma de los impares
                sumaImpares += numero;
            }
        }
        System.out.println("La suma de los números pares es: " + sumaPares);// se muestra la suma de los pares
        System.out.println("La suma de los números impares es: " + sumaImpares);// se muestra la suma de los impares
        scanner.close();
    }
}
