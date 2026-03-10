package ArrayAvanzada;

public class Alumno {
	// Atributos 
	private String nombre;
	private double nota;
	private int numAsignatura;
	
	// Constructor
	public Alumno(String nombre, double nota, int numAsignatura) {
		this.nombre = nombre;
		this.nota = nota;
		this.numAsignatura = numAsignatura;
	}
	
	// Getters y Setters
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setNumAsignatura(int numAsignatura) {
		this.numAsignatura = numAsignatura;
	}
	public double getNota() {
		return nota;
	}
	
	public void setNota(double nota) {
		this.nota = nota;
	}
	
	public double media() {
		return nota / numAsignatura; // Suponiendo que la nota es la suma de las notas de las 5 asignaturas
	}

	public int getNumAsignatura() {
		return numAsignatura;
	}

	
	
}
