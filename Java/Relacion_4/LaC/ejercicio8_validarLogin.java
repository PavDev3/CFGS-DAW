package Relacion_4.LaC;

import java.util.*;

public class ejercicio8_validarLogin {
	public static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		String usuario = pedirUsuario();
		String password = pedirPassword();
		
		System.out.println("Usuario y contraseña válidos");
	}
	
	// Pedir y validar usuario
	public static String pedirUsuario() {
		String usuario;
		boolean usuarioValido = false;
		
		while (!usuarioValido) {
			System.out.println("Introduce tu usuario: ");
			usuario = teclado.nextLine();
			
			// Validar longitud (máximo 30 caracteres)
			if (usuario.length() > 30) {
				System.out.println("El usuario debe tener como máximo 30 caracteres");
				continue;
			}
			
			// Validar que solo tenga caracteres alfabéticos
			boolean soloLetras = true;
			for (int i = 0; i < usuario.length(); i++) {
				if (!esLetra(usuario.charAt(i))) {
					soloLetras = false;
					break;
				}
			}
			
			if (!soloLetras) {
				System.out.println("El usuario debe estar formado solo por caracteres alfabéticos");
				continue;
			}
			
			usuarioValido = true;
			return usuario;
		}
		return "";
	}
	
	// Pedir y validar contraseña
	public static String pedirPassword() {
		String password;
		boolean passwordValida = false;
		
		while (!passwordValida) {
			System.out.println("Introduce tu contraseña: ");
			password = teclado.nextLine();
			
			// Validar longitud mínima (7 caracteres)
			if (password.length() < 7) {
				System.out.println("La contraseña debe tener como mínimo 7 caracteres");
				continue;
			}
			
			// Validar que tenga al menos una letra, un dígito y un carácter no alfanumérico
			boolean tieneLetra = false;
			boolean tieneDigito = false;
			boolean tieneCaracterEspecial = false;
			
			for (int i = 0; i < password.length(); i++) {
				char c = password.charAt(i);
				if (esLetra(c)) {
					tieneLetra = true;
				} else if (esDigito(c)) {
					tieneDigito = true;
				} else {
					tieneCaracterEspecial = true;
				}
			}
			
			if (!tieneLetra) {
				System.out.println("La contraseña debe contener al menos una letra");
				continue;
			}
			if (!tieneDigito) {
				System.out.println("La contraseña debe contener al menos un dígito");
				continue;
			}
			if (!tieneCaracterEspecial) {
				System.out.println("La contraseña debe contener al menos un carácter no alfanumérico");
				continue;
			}
			
			passwordValida = true;
			return password;
		}
		return "";
	}
	
	// Verificar si un carácter es una letra (a-z, A-Z)
	public static boolean esLetra(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}
	
	// Verificar si un carácter es un dígito (0-9)
	public static boolean esDigito(char c) {
		return c >= '0' && c <= '9';
	}
}

