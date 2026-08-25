package personajes;

// almacena hasta 100 personajes, implementa estadisticas de vida (ej7)
public class ArrayPersonajes implements CreableEstadisticas {
	private Personaje[] personajes;
	private int numPersonajes;

	public ArrayPersonajes() {
		personajes = new Personaje[100];
		numPersonajes = 0;
	}

	public boolean aniadir(Personaje p) {
		if (numPersonajes >= 100) {
			return false;
		}
		personajes[numPersonajes] = p;
		numPersonajes++;
		return true;
	}

	// busca personaje por nombre, devuelve null si no existe
	public Personaje buscarPorNombre(String nombre) {
		for (int i = 0; i < numPersonajes; i++) {
			if (personajes[i].getNombre().equals(nombre)) {
				return personajes[i];
			}
		}
		return null;
	}

	public int getNumPersonajes() {
		return numPersonajes;
	}

	public Personaje getPersonaje(int i) {
		return personajes[i];
	}

	public void mostrarTodos() {
		for (int i = 0; i < numPersonajes; i++) {
			System.out.println(personajes[i].toString());
		}
	}

	// ordenar por vidaActual de mayor a menor (burbuja)
	public void mostrarOrdenadosPorVida() {
		// copiar referencias para no alterar el array original
		Personaje[] copia = new Personaje[numPersonajes];
		for (int i = 0; i < numPersonajes; i++) {
			copia[i] = personajes[i];
		}
		// burbuja descendente
		for (int i = 0; i < numPersonajes - 1; i++) {
			for (int j = 0; j < numPersonajes - i - 1; j++) {
				if (copia[j].getVidaActual() < copia[j + 1].getVidaActual()) {
					Personaje aux = copia[j];
					copia[j] = copia[j + 1];
					copia[j + 1] = aux;
				}
			}
		}
		for (int i = 0; i < numPersonajes; i++) {
			System.out.println(copia[i].toString());
		}
	}

	// ej7: vida minima de todos los personajes
	@Override
	public double minimo() {
		if (numPersonajes == 0) {
			return 0;
		}
		double min = personajes[0].getVidaActual();
		for (int i = 1; i < numPersonajes; i++) {
			if (personajes[i].getVidaActual() < min) {
				min = personajes[i].getVidaActual();
			}
		}
		return min;
	}

	// ej7: vida maxima de todos los personajes
	@Override
	public double maximo() {
		if (numPersonajes == 0) {
			return 0;
		}
		double max = personajes[0].getVidaActual();
		for (int i = 1; i < numPersonajes; i++) {
			if (personajes[i].getVidaActual() > max) {
				max = personajes[i].getVidaActual();
			}
		}
		return max;
	}

	// ej7: media de vida de todos los personajes
	@Override
	public double media() {
		if (numPersonajes == 0) {
			return 0;
		}
		double suma = 0;
		for (int i = 0; i < numPersonajes; i++) {
			suma += personajes[i].getVidaActual();
		}
		return suma / numPersonajes;
	}
}
