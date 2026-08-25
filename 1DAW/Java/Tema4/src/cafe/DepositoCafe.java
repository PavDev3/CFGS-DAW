package cafe;

public class DepositoCafe {
	// Atributos
	private int cantidad = 50;
	
	// Constructor por defecto
	public DepositoCafe() {

	}
	
	
	// Metodos
	public void setCantidad(int cantidad) {
		if (cantidad > 0 && cantidad <=100) {
			this.cantidad = cantidad;
		}
	}
	public int getCantidad() {
		return cantidad;
	}
	
	public int servirCafe(int cantidadSolicitada) {
		if (cantidadSolicitada <= cantidad) {
			cantidad -= cantidadSolicitada;
			return cantidadSolicitada;
		} else {
			int cafeServido = cantidad;
			cantidad = 0;
			return cafeServido;
		}
	}
	
}
