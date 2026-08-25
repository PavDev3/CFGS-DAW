package personajes;

import java.util.Scanner;

public class mainMenuPersonajes {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayPersonajes array = new ArrayPersonajes();
		int opcion = 0;

		while (opcion != 7) {
			System.out.println("1. Alta de personaje");
			System.out.println("2. Mago aprende hechizo");
			System.out.println("3. Mago lanza hechizo");
			System.out.println("4. Clerigo cura a personaje");
			System.out.println("5. Mostrar personajes");
			System.out.println("6. Mostrar ordenados por vida");
			System.out.println("7. Salir");
			System.out.print("Opcion: ");
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
				case 1:
					altaPersonaje(sc, array);
					break;
				case 2:
					magoAprendeHechizo(sc, array);
					break;
				case 3:
					magoLanzaHechizo(sc, array);
					break;
				case 4:
					clerigoChura(sc, array);
					break;
				case 5:
					array.mostrarTodos();
					// ej7: mostrar estadisticas
					System.out.println("Vida minima: " + array.minimo());
					System.out.println("Vida maxima: " + array.maximo());
					System.out.println("Vida media: " + array.media());
					break;
				case 6:
					array.mostrarOrdenadosPorVida();
					break;
				case 7:
					System.out.println("Hasta luego.");
					break;
				default:
					System.out.println("Opcion invalida.");
			}
		}
		sc.close();
	}

	private static void altaPersonaje(Scanner sc, ArrayPersonajes array) {
		System.out.print("Tipo (mago/clerigo): ");
		String tipo = sc.nextLine();
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Raza (humano/elfo/enano/orco): ");
		String raza = sc.nextLine();
		System.out.print("Fuerza (0-20): ");
		int fuerza = sc.nextInt();
		System.out.print("Inteligencia (0-20): ");
		int inteligencia = sc.nextInt();
		System.out.print("Vida maxima (0-100): ");
		int vida = sc.nextInt();
		sc.nextLine();

		try {
			if (tipo.equals("mago")) {
				Mago m = new Mago(nombre, raza, fuerza, inteligencia, vida);
				if (!array.aniadir(m)) {
					System.out.println("Capacidad maxima alcanzada.");
				} else {
					System.out.println("Mago creado.");
				}
			} else if (tipo.equals("clerigo")) {
				System.out.print("Nombre del dios: ");
				String dios = sc.nextLine();
				Clerigo c = new Clerigo(nombre, raza, fuerza, inteligencia, vida, dios);
				if (!array.aniadir(c)) {
					System.out.println("Capacidad maxima alcanzada.");
				} else {
					System.out.println("Clerigo creado.");
				}
			} else {
				System.out.println("Tipo desconocido.");
			}
		} catch (PersonajeException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void magoAprendeHechizo(Scanner sc, ArrayPersonajes array) {
		System.out.print("Nombre del mago: ");
		String nombre = sc.nextLine();
		Personaje p = array.buscarPorNombre(nombre);
		if (p == null || !(p instanceof Mago)) {
			System.out.println("Mago no encontrado.");
			return;
		}
		System.out.print("Nombre del hechizo: ");
		String hechizo = sc.nextLine();
		Mago m = (Mago) p;
		// comprobar que no lo tiene ya
		for (int i = 0; i < m.getHechizos().length; i++) {
			if (hechizo.equals(m.getHechizos()[i])) {
				System.out.println("El mago ya conoce ese hechizo.");
				return;
			}
		}
		m.aprendeHechizo(hechizo);
	}

	private static void magoLanzaHechizo(Scanner sc, ArrayPersonajes array) {
		System.out.print("Nombre del mago: ");
		String nombreMago = sc.nextLine();
		Personaje p = array.buscarPorNombre(nombreMago);
		if (p == null || !(p instanceof Mago)) {
			System.out.println("Mago no encontrado.");
			return;
		}
		System.out.print("Nombre del objetivo: ");
		String nombreObjetivo = sc.nextLine();
		if (nombreMago.equals(nombreObjetivo)) {
			System.out.println("Un mago no puede lanzarse hechizos a si mismo.");
			return;
		}
		Personaje objetivo = array.buscarPorNombre(nombreObjetivo);
		if (objetivo == null) {
			System.out.println("Personaje objetivo no encontrado.");
			return;
		}
		System.out.print("Nombre del hechizo: ");
		String hechizo = sc.nextLine();
		Mago m = (Mago) p;
		m.lanzaHechizo(objetivo, hechizo);
		// si queda con menos de 0 ya lo controla setVidaActual (queda en 0)
		if (objetivo.getVidaActual() == 0) {
			System.out.println(objetivo.getNombre() + " ha muerto.");
		}
	}

	private static void clerigoChura(Scanner sc, ArrayPersonajes array) {
		System.out.print("Nombre del clerigo: ");
		String nombreClerigo = sc.nextLine();
		Personaje p = array.buscarPorNombre(nombreClerigo);
		if (p == null || !(p instanceof Clerigo)) {
			System.out.println("Clerigo no encontrado.");
			return;
		}
		System.out.print("Nombre del personaje a curar: ");
		String nombreObjetivo = sc.nextLine();
		Personaje objetivo = array.buscarPorNombre(nombreObjetivo);
		if (objetivo == null) {
			System.out.println("Personaje no encontrado.");
			return;
		}
		Clerigo c = (Clerigo) p;
		c.curar(objetivo);
	}
}
