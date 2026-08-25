package saldo;

public class Menu {
	int opcion;
	
	// atributos
	Cuenta cuenta;
	
	// constructor
	public Menu(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	// metodos
	public void mostrarMenu() {
		System.out.println("Seleccione una opcion:");
		System.out.println("1. Mostrar saldo");
		System.out.println("2. Depositar");
		System.out.println("3. Retirar");
		System.out.println("4. Salir");
	}
	
	public void ejecutarOpcion(int opcion, int cantidad) {
		switch (opcion) {
			case 1:
				cuenta.mostrarSaldo(cuenta.obtenerSaldo());
				break;
			case 2:
				cuenta.depositar(cantidad);
				System.out.println("Deposito realizado.");
				System.out.println("Nuevo saldo: " + cuenta.obtenerSaldo());
				break;
			case 3:
				try {
					cuenta.retirar(cantidad);
					System.out.println("Retiro realizado.");
					System.out.println("Nuevo saldo: " + cuenta.obtenerSaldo());
				} catch (SaldoInsuficienteException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Gracias por usar FilipoBank.");
				break;
			default:
				System.out.println("Opcion invalida.");
				break;
		}
	}
	
	

}
