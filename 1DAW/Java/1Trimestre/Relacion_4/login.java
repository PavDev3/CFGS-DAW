package Relacion_4;

import java.util.Scanner;

public class login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String usuario = pedirUsuario(scanner);
        String password = pedirPassword(scanner);
        if (usuario.equals(usuario) && password.equals(password)) {
            System.out.println("Login correcto");
        } else {
            System.out.println("Login incorrecto");
        }
        scanner.close();
    }

    private static String pedirUsuario(Scanner scanner) {
        System.out.println("Introduce tu usuario: ");
        String usuario = scanner.nextLine();
        boolean usuarioValido = false;
        while (!usuarioValido) {
            if (usuario.length() >= 3 && usuario.length() <= 30) {
                boolean sololetras = true;
                for (int i = 0; i < usuario.length(); i++) {
                    if (!Character.isLetter(usuario.charAt(i))) {
                        sololetras = false;
                    }
                }
                if (sololetras) {
                    usuarioValido = true;
                } else {
                    System.out.println("El usuario debe tener solo letras");
                    usuario = scanner.nextLine();
                }
            } else {
                System.out.println("El usuario debe tener entre 3 y 30 caracteres");
                usuario = scanner.nextLine();
            }

        }
        return usuario;
    }
    
    private static String pedirPassword(Scanner scanner) {
        boolean passwordValida = false;
        String password = "";
        while (!passwordValida) {
            System.out.println("Introduce tu password: ");
            password = scanner.nextLine();
            if (password.length() >= 7) {
                boolean tieneLetra = false;
                boolean tieneDigito = false;
                boolean tieneCaracterEspecial = false;
                for (int i = 0; i < password.length(); i++) {
                    if (Character.isLetter(password.charAt(i))) {
                        tieneLetra = true;
                    }
                    if (Character.isDigit(password.charAt(i))) {
                        tieneDigito = true;
                    }
                    if (!Character.isLetterOrDigit(password.charAt(i))) {
                        tieneCaracterEspecial = true;
                    }
                }
                if (tieneLetra && tieneDigito && tieneCaracterEspecial) {
                    passwordValida = true;
                } else {
                    System.out.println("La contraseña debe tener al menos una letra, un digito y un caracter especial");
                }
            } else {
                System.out.println("La contraseña debe tener al menos 7 caracteres");
            }
    
        }
        return password;
    }
}