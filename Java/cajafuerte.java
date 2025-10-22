
import java.util.*;

public class cajafuerte {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int passwordOk = random.nextInt(9999);
        String passwordOkString = String.format("%04d", passwordOk);
        System.out.println("La contrasena es: " + passwordOkString);
        
        int intentos = 0;
        int maxIntentos = 5;
 
        
        while (intentos < maxIntentos) {
            System.out.println("Introduce la contrasena: ");  
            int password = scanner.nextInt();
            String passwordToString = String.format("%04d", password);
            if (passwordToString.equals(passwordOkString)) {
                System.out.println("Contrasena correcta");
                break;
            } else {
                System.out.println("Contrasena incorrecta");
            }
            intentos++;
        }
        scanner.close();
    }
}
