package cafe;

public class DepositoLeche {
	// Atributo
	private int cantidad = 50;
	

		// Constructor por defecto
		public DepositoLeche() {

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
		
		public int servirLeche(int cantidadSolicitada) {
			if (cantidadSolicitada <= cantidad) {
				cantidad -= cantidadSolicitada;
				return cantidadSolicitada;
			} else {
				int lecheServida = cantidad;
				cantidad = 0;
				return lecheServida;
			}
		}
		
	}



