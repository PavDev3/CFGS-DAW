import java.util.Scanner;

public class multiplicar {
	public static void main(String[]args) {
		Scanner sc=new Scanner(System.in);
		int numero1;
		int contador;
		
		System.out.print("Ingrese un número entero a multiplicar: ");
		numero1=sc.nextInt();
		
		contador=0;
		while(contador<=10) {
			System.out.println(numero1+" x "+contador+" = "+(numero1*contador));
			contador++;
	}
		sc.close();
	}
}