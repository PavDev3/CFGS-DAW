package Conecta4;

public class Jugador {
	// Atributos
	private String nombre;
	private Color color;

	public enum Color {
		ROJO("R"), AZUL("A");

		private String simbolo;

		Color(String simbolo) {
			this.simbolo = simbolo;
		}

		public String getSimbolo() {
			return simbolo;
		}
	}

	// Constructor
	public Jugador(String nombre, Color color) {
		this.nombre = nombre;
		this.color = color;
	}

	// Getters y setters
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void turnoJugador(String jugador, String color) {
		// Metodo para mostrar el turno del jugador por pantalla
		System.out.println("Turno de " + jugador + " (" + color + ")");
	}
}
