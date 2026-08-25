package Conecta4;

public class mainConecta {

	public static void main(String[] args) {

		// Crear el tablero
		Tablero tablero = new Tablero(10, 10);
		// Crear los jugadores
		Jugador jugador1 = new Jugador("Jugador 1", Jugador.Color.ROJO);
		Jugador jugador2 = new Jugador("Jugador 2", Jugador.Color.AZUL);

		// Primer turno aleatorio
		int turno = (int) (Math.random() * 2) + 1;

		// Bucle principal del juego
		boolean juegoTerminado = false;
		// Mientras el juego no haya terminado
		while (!juegoTerminado) {
			tablero.mostrarTablero();
			// Determinar el jugador actual
			Jugador jugadorActual;
			if (turno == 1) {
				jugadorActual = jugador1;
			} else {
				jugadorActual = jugador2;
			}
			// Mostrar el turno del jugador actual
			jugadorActual.turnoJugador(jugadorActual.getNombre(), jugadorActual.getColor().toString());

			// Pedir columna y colocar ficha
			int columna = tablero.preguntarColumna();
			boolean fichaColocada = tablero.colocarFicha(columna, jugadorActual.getColor());

			if (!fichaColocada) {
				System.out.println("Columna llena. Elige otra.");
				continue;
			}

			// Comprobar ganador
			if (tablero.comprobarGanador(jugadorActual.getColor().getSimbolo())) {
				tablero.mostrarTablero();
				System.out.println(jugadorActual.getNombre() + " (" + jugadorActual.getColor() + ") ha ganado!");
				juegoTerminado = true;
			} else if (tablero.tableroLleno()) {
				tablero.mostrarTablero();
				System.out.println("Empate. El tablero esta lleno.");
				juegoTerminado = true;
			} else {
				turno = (turno == 1) ? 2 : 1;
			}
		}
	}
}
