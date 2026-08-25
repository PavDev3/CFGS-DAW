package testExamMarIA;

// Excepción personalizada para productos inválidos
public class ProductoInvalidoException extends Exception {
	public ProductoInvalidoException(String mensaje) {
		super(mensaje);
	}
}
