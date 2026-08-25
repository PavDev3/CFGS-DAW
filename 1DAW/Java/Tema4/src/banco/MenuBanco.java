package banco;

public class MenuBanco {
	// atributos
	cuentaBancaria cuenta;
	
	// constructor
	public MenuBanco(cuentaBancaria cuenta) {
		this.cuenta = cuenta;
	}
	
	// metodos
	public void mostrarMenu() {
		System.out.println("\n--- Menu ---");
		System.out.println("1. Mostrar saldo");
		System.out.println("2. Ingresar dinero");
		System.out.println("3. Retirar dinero");
		System.out.println("4. Salir");
	}
	
	public void ejecutarOpcion(int opcion, double cantidad) {
		switch (opcion) {
			case 1:
				System.out.println(cuenta);
				break;
			case 2:
				try {
					cuenta.ingresar(cantidad);
					System.out.println("Ingreso realizado correctamente.");
				} catch (IllegalArgumentException e) {
					System.out.println("Error: " + e.getMessage());
				} finally {
					System.out.println("Saldo actual: " + cuenta.getSaldo());
				}
				break;
			case 3:
				try {
					cuenta.retirar(cantidad);
					System.out.println("Retiro realizado correctamente.");
				} catch (SaldoInsuficienteException e) {
					System.out.println("Error: " + e.getMessage());
				} catch (IllegalArgumentException e) {
					System.out.println("Error: " + e.getMessage());
				} finally {
					System.out.println("Saldo actual: " + cuenta.getSaldo());
				}
				break;
			case 4:
				System.out.println("Gracias por usar el banco. Hasta luego!");
				break;
			default:
				System.out.println("Opcion invalida.");
				break;
		}
	}
}
