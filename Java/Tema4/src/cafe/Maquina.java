package cafe;

public class Maquina {
	DepositoCafe depoCafe;
	DepositoLeche depoLeche;
	DepositoVaso depoVaso;
	Monedero monedero;

	public Maquina() {
		depoCafe = new DepositoCafe();
		depoLeche = new DepositoLeche();
		depoVaso = new DepositoVaso();
		monedero = new Monedero();
	}

	// Metodos

	// Método para verificar si puede dar cambio
	private boolean puedeDarCambio(double dineroIntroducido, double precio) {
		double cambio = dineroIntroducido - precio;
		if (cambio > 0) {
			// Verificar que el monedero tiene suficiente saldo para dar cambio
			// El dinero introducido se suma al monedero, así que el saldo disponible
			// será el saldo actual + el dinero introducido - el cambio necesario
			return (monedero.getSaldo() + dineroIntroducido - cambio) >= 0;
		}
		return true; // No necesita cambio
	}

	public void servirCafeSolo(double dineroIntroducido) {
		double precioCafeSolo = 1.0;

		// Validar dinero suficiente
		if (dineroIntroducido < precioCafeSolo) {
			System.out.println("Error: Cantidad insuficiente. El precio es " + precioCafeSolo + " €");
			return;
		}

		// Validar existencias
		if (depoCafe.getCantidad() <= 0) {
			System.out.println("Error: No hay existencias de café");
			return;
		}
		if (depoVaso.getCantidad() <= 0) {
			System.out.println("Error: No hay existencias de vasos");
			return;
		}

		// Validar cambio
		if (!puedeDarCambio(dineroIntroducido, precioCafeSolo)) {
			System.out.println("Error: La máquina no tiene cambio suficiente");
			return;
		}

		// Servir producto
		// El dinero introducido se suma al monedero
		monedero.setSaldo(monedero.getSaldo() + dineroIntroducido);
		depoCafe.servirCafe(1);
		depoVaso.servirVaso(1);

		// Calcular y mostrar cambio
		double cambio = dineroIntroducido - precioCafeSolo;
		if (cambio > 0) {
			monedero.setSaldo(monedero.getSaldo() - cambio);
			System.out.println("Recoge tu cambio de " + String.format("%.2f", cambio) + " euros");
		}

		System.out.println("Producto servido");
	}

	public void servirLecheSolo(double dineroIntroducido) {
		double precioLecheSolo = 0.8;

		// Validar dinero suficiente
		if (dineroIntroducido < precioLecheSolo) {
			System.out.println("Error: Cantidad insuficiente. El precio es " + precioLecheSolo + " €");
			return;
		}

		// Validar existencias
		if (depoLeche.getCantidad() <= 0) {
			System.out.println("Error: No hay existencias de leche");
			return;
		}
		if (depoVaso.getCantidad() <= 0) {
			System.out.println("Error: No hay existencias de vasos");
			return;
		}

		// Validar cambio
		if (!puedeDarCambio(dineroIntroducido, precioLecheSolo)) {
			System.out.println("Error: La máquina no tiene cambio suficiente");
			return;
		}

		// Servir producto
		// El dinero introducido se suma al monedero
		monedero.setSaldo(monedero.getSaldo() + dineroIntroducido);
		depoLeche.servirLeche(1);
		depoVaso.servirVaso(1);

		// Calcular y mostrar cambio
		double cambio = dineroIntroducido - precioLecheSolo;
		if (cambio > 0) {
			monedero.setSaldo(monedero.getSaldo() - cambio);
			System.out.println("Recoge tu cambio de " + String.format("%.2f", cambio) + " euros");
		}

		System.out.println("Producto servido");
	}

	public void servirCafeConLeche(double dineroIntroducido) {
		double precioCafeConLeche = 1.5;

		// Validar dinero suficiente
		if (dineroIntroducido < precioCafeConLeche) {
			System.out.println("Error: Cantidad insuficiente. El precio es " + precioCafeConLeche + " €");
			return;
		}

		// Validar existencias
		if (depoCafe.getCantidad() <= 0) {
			System.out.println("Error: No hay existencias de café");
			return;
		}
		if (depoLeche.getCantidad() <= 0) {
			System.out.println("Error: No hay existencias de leche");
			return;
		}
		if (depoVaso.getCantidad() <= 0) {
			System.out.println("Error: No hay existencias de vasos");
			return;
		}

		// Validar cambio
		if (!puedeDarCambio(dineroIntroducido, precioCafeConLeche)) {
			System.out.println("Error: La máquina no tiene cambio suficiente");
			return;
		}

		// Servir producto
		// El dinero introducido se suma al monedero
		monedero.setSaldo(monedero.getSaldo() + dineroIntroducido);
		depoCafe.servirCafe(1);
		depoLeche.servirLeche(1);
		depoVaso.servirVaso(1);

		// Calcular y mostrar cambio
		double cambio = dineroIntroducido - precioCafeConLeche;
		if (cambio > 0) {
			monedero.setSaldo(monedero.getSaldo() - cambio);
			System.out.println("Recoge tu cambio de " + String.format("%.2f", cambio) + " euros");
		}

		System.out.println("Producto servido");
	}

	public void mostrarMenu() {
		System.out.println("Seleccione una opcion:");
		System.out.println("1. Servir cafe solo (1.00 €)");
		System.out.println("2. Servir leche solo (0.80 €)");
		System.out.println("3. Servir cafe con leche (1.50 €)");
		System.out.println("4. Consultar estado de la maquina");
		System.out.println("5. Llenar depositos");
		System.out.println("6. Vaciar monedero");
		System.out.println("7. Salir");
	}

	public void ejecutarOpcion(int opcion, double dinero) {
		switch (opcion) {
		case 1:
			servirCafeSolo(dinero);
			break;
		case 2:
			servirLecheSolo(dinero);
			break;
		case 3:
			servirCafeConLeche(dinero);
			break;
		case 4:
			consultarEstado();
			break;
		case 5:
			llenarDepositos();
			break;
		case 6:
			vaciarMonedero();
			break;
		case 7:
			System.out.println("Gracias por usar la maquina de cafe.");
			break;
		default:
			System.out.println("Opcion invalida.");
			break;
		}
	}

	public void estadoMaquina() {
		consultarEstado();
	}

	public void consultarEstado() {
		System.out.println("=== Estado de la maquina ===");
		System.out.println("Cafe: " + depoCafe.getCantidad());
		System.out.println("Leche: " + depoLeche.getCantidad());
		System.out.println("Vasos: " + depoVaso.getCantidad());
		System.out.println("Saldo en el monedero: " + monedero.getSaldoFormateado());
	}

	// Método para llenar todos los depósitos
	public void llenarDepositos() {
		depoCafe.setCantidad(50);
		depoLeche.setCantidad(50);
		depoVaso.setCantidad(80);
		System.out.println("Depósitos llenados correctamente");
	}

	// metodo para vaciar todos los depositvos
	public void vaciarDepositos() {
		depoCafe.setCantidad(0);
		depoLeche.setCantidad(0);
		depoVaso.setCantidad(0);
		System.out.println("Depósitos vacíados correctamente");
	}

	// Método para vaciar el monedero
	public void vaciarMonedero() {
		monedero.setSaldo(25);
		System.out.println("Monedero vaciado correctamente");
	}
}
