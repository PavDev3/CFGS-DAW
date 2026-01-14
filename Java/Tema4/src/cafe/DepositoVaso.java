package cafe;

public class DepositoVaso {
	
	// Atributo
	private int cantidad = 80;
	

		// Constructor por defecto
		public DepositoVaso() {

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
		
		public int servirVaso(int cantidadSolicitada) {
			if (cantidadSolicitada <= cantidad) {
				cantidad -= cantidadSolicitada;
				return cantidadSolicitada;
			} else {
				int servirVaso= cantidad;
				cantidad = 0;
				return servirVaso;
			}
		}

}
