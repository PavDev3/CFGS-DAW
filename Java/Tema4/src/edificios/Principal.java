package ej6;

public class Principal {

	public static void main(String[] args) {
		// array de Edificio con 3 polideportivos y 2 edificios de oficinas
		Edificio[] edificios = new Edificio[5];
		edificios[0] = new Polideportivo("Palacio de los Deportes", 5000, 1);
		edificios[1] = new Polideportivo("Pabellon Municipal", 2000, 2);
		edificios[2] = new Polideportivo("Centro Deportivo Norte", 1500, 1);
		edificios[3] = new EdificioOficinas(800, 20);
		edificios[4] = new EdificioOficinas(1200, 35);

		for (int i = 0; i < edificios.length; i++) {
			System.out.println(edificios[i].toString());
		}
	}
}
