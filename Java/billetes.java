import java.util.*;

public class billetes {
	public static Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.print("Dame un número que sea más que el 0: ");
        int num;
        num = teclado.nextInt();
        //Pedimos que el valor sea positivo, sino no se podra hacer.
        if (num <= 0) {
            System.out.print("Por favor, pon un número que sea mayor que 0: ");
            num = teclado.nextInt();
        }
        // Ponemos todos los billetes, cuanto valen y lo que falta para llegar a ese dinero
        int b500;
        //El 500 por ejemplo, solo haria falta un billete de 500, lo que hago es
        //dividir el dinero que hemos puesto que es 500 entre 500 en este caso
        b500 = num / 500;
        num = num % 500;
        //Y muestro el resultado de cuanto haria falta
        System.out.println(b500 + " billete de 500");
        //Y hago exactamente lo mismo con todos los billete y monedas.
        int b200;
        b200 = num / 200;
        num = num % 200;
        System.out.println(b200 + " billete de 200");
        int b100;				
        b100 = num / 100;
        num = num % 100;
        System.out.println(b100 + " billete de 100");
        int b50;
        b50 = num / 50;
        num = num % 50;
        System.out.println(b50 + " billete de 50");
        int b20;
        b20 = num / 20;
        num = num % 20;
        System.out.println(b20 + " billete de 20");
        int b10;
        b10 = num / 10;
        num = num % 10;
        System.out.println(b10 + " billete de 10");
        int b5;
        b5 = num / 5;
        num = num% 5;
        System.out.println(b5 + " billete de 5");
        int m2;
        m2 = num / 2;
        num = num % 2;
        System.out.println(m2 + " moneda de 2");
        int m1;
        m1 = num;
        num = num % 1;
        System.out.println(m1 + " moneda de 1");
	}
}
