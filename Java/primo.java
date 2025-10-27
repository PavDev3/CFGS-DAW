//Númerosprimosenunrango○Pidedosnúmerosenteros(inicioyfin)ymuestratodoslosnúmerosprimoseneserango

import java.util.Scanner;

public class primo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int inicio, fin;
        System.out.println("Introduce el inicio del rango: ");
        inicio = sc.nextInt();
        System.out.println("Introduce el fin del rango: ");
        fin = sc.nextInt();
        for (int i = inicio; i <= fin; i++) {
            if (i % 2 != 0) {
                System.out.println(i);
            }
        }   
        sc.close();
    }
}