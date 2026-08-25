package personajes;

public class mainPersonajes {

	public static void main(String[] args) {
		try {
			Mago magoA = new Mago("Gandalf", "humano", 10, 19, 80);
			Mago magoB = new Mago("Saruman", "humano", 8, 18, 70);
			Clerigo clerigo = new Clerigo("Templar", "enano", 18, 14, 90, "Paladine");

			// imprimir datos iniciales
			System.out.println(magoA.toString());
			System.out.println(magoB.toString());
			System.out.println(clerigo.toString());
			System.out.println();

			// magoA aprende 2 hechizos
			magoA.aprendeHechizo("Fuego");
			magoA.aprendeHechizo("Hielo");

			// magoB aprende 1 hechizo
			magoB.aprendeHechizo("Rayo");

			System.out.println(magoA.toString());
			System.out.println(magoB.toString());
			System.out.println();

			// magoA lanza sobre magoB
			magoA.lanzaHechizo(magoB, "Fuego");

			// magoB lanza sobre magoA
			magoB.lanzaHechizo(magoA, "Rayo");

			// clerigo cura a magoB
			clerigo.curar(magoB);

			// magoA lanza otro hechizo sobre magoB
			magoA.lanzaHechizo(magoB, "Hielo");

			System.out.println();
			System.out.println(magoA.toString());
			System.out.println(magoB.toString());
			System.out.println(clerigo.toString());

		} catch (PersonajeException e) {
			System.out.println(e.getMessage());
		}
	}
}
