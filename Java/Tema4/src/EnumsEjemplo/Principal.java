package EnumsEjemplo;

import java.util.Arrays;
import java.util.Scanner;

public class Principal {
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		
		
		Persona persona1=new Persona("22", "LOLA ",
				TipoEstadoCivil.VIUDO);

		Persona persona = crearPersona();

		System.out.println(persona);
		
		switch ( persona.getEstadoCivil() ) {
		case CASADO:
			break;
		
		case SEPARADO:
			break;
		case SOLTERO:
			break;
		case VIUDO:
			break;
	
		
		}

	}

	private static Persona crearPersona() {

		String dni, nombre;
		TipoEstadoCivil estadoCivil;
		Persona persona;

		dni = "111";
		nombre = "pepe perez";
		estadoCivil = solicitarEstadoCivil();
		persona = new Persona(dni, nombre, estadoCivil);
		return persona;

	}

	private static TipoEstadoCivil solicitarEstadoCivil() {
		String cadenaEstadoCivil;
		TipoEstadoCivil estadoCivil=null;
		boolean correcto = false;

		do {
			try {
				System.out.println("Introduce el estado civil: " +Arrays.toString(TipoEstadoCivil.values()));
				cadenaEstadoCivil = teclado.nextLine();

				cadenaEstadoCivil=cadenaEstadoCivil.toUpperCase();
				// 
				estadoCivil = TipoEstadoCivil.valueOf(cadenaEstadoCivil);
				correcto = true;
			} catch (IllegalArgumentException e) {
				System.out.println("Estado civil incorrecto");
				correcto = false;
			}
		} while (!correcto);

		return estadoCivil;

	}

}