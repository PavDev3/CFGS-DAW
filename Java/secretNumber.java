
import java.util.Random;


public class secretNumber {
    public static void main(String[] args) {
        Random random = new Random();
        int secretNumber = random.nextInt(100);
        System.out.println("El número es: " + secretNumber);
        

    }
}