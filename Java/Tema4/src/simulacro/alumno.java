package Tema4.src.simulacro;

public class alumno implements Comparable<alumno> {
	private String nombre;
	private Double nota;
	
	// Constructor
	public alumno(String nombre, Double nota) {
		this.nombre = nombre;
		this.nota = nota;
	}
	
	
	// metodos 

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public Double getNota() {
		return nota;
	}
	
	public void setNota(Double nota) throws NotaInvalidaException {
		if (nota < 0 || nota > 10) {
			throw new NotaInvalidaException("La nota debe estar entre 0 y 10");
		}
		this.nota = nota;
	}

	@Override
	public int compareTo(alumno otro) {	
		return this.getNota().compareTo(otro.getNota());
	}
}