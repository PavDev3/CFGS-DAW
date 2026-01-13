import java.util.*;

public class rombo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int base, espacios;
        char caracter;
        //Creo un bucle para que ingresen un numero positivo impar y aprovecho para pedir el caracter
        do {
            System.out.println("Intoduzca el valor impar de la base");
            base = sc.nextInt();
            if(base<0 || base%2==0) {
                System.out.println("Tiene que ser un numero mayor a 0 o impar");
            }System.out.println("Introduzca el caracte");
             caracter = sc.next().charAt(0);
        }while(base<0 || base%2==0);
        //Hgo un bucle para saber cuantos caracteres hay por linea
        for(int i = 0; i<base; i+=2) {
            //Hgo un bucle para saber cuantos espacios hay por linea
            for(espacios = 0; espacios<(base-i)/2; espacios++) {
                System.out.print("  ");
            }//Hgo un bucle para escribir todos los caracteres
            for(int j = 0; j<=i; j++) {
                System.out.print(caracter + " ");
            }System.out.println();
        }//Hago la piramide a la inversa sin contar la base para no tenerla repetida
        for(int i = base-2; i>=0; i-=2) {
            for(espacios= 0;espacios<(base-i)/2;espacios++) {
                System.out.print("  ");
            }
            for(int j = 0; j<i; j++) {
                System.out.print(caracter + " ");
            }System.out.println();
        }
        sc.close();
    }
}

