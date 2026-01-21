package excepcion;

public class MainExcepcion {

    static class DivisionPorCeroException extends Exception {
        public DivisionPorCeroException(String mensaje) {
            super(mensaje);
        }
    }

    public static void main(String[] args) {
        
        int x = 10, y = 0, res = 0;

        System.out.println("Inicio del programa");

        try {
            res = dividir(x, y);

            int r = x / y;
            System.out.println("El resultado es: " + r);

        } catch (Exception e) {
            System.out.println("Error personalizado: " + e.getMessage());

        }
        
        
    }
    
    
    public static int dividir(int a, int b) throws DivisionPorCeroException {
		if (b == 0) {
			throw new DivisionPorCeroException("No se puede dividir por cero.");
		}
		return a / b;
	}
}