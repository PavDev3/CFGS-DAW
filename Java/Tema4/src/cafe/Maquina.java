package cafe;

public class Maquina {
	DepositoCafe depoCafe;
	DepositoLeche depoLeche;
	DepositoVaso depoVaso;
	Monedero monedero;

	
	public Maquina() {
		depoCafe= new DepositoCafe();
		depoLeche= new DepositoLeche();
		depoVaso= new DepositoVaso();
		monedero= new Monedero();
	}
	
	// Metodos
	
	public void servirCafeSolo() {
		int precioCafeSolo = 1;
		if (monedero.getSaldo() >= precioCafeSolo &&
			depoCafe.getCantidad() > 0 &&
			depoLeche.getCantidad() > 0 &&
			depoVaso.getCantidad() > 0) {
			
			monedero.setSaldo(monedero.getSaldo() + precioCafeSolo);
			depoCafe.servirCafe(1);
			depoLeche.servirLeche(1);
			depoVaso.servirVaso(1);
		} 
	}
	
	public void servirLecheSolo() {
		double precioLecheSolo = 0.8;
		if (monedero.getSaldo() >= precioLecheSolo &&
				depoLeche.getCantidad() > 0 &&
				depoVaso.getCantidad() > 0) {
				monedero.setSaldo(monedero.getSaldo() + precioLecheSolo);
				
				monedero.setSaldo(monedero.getSaldo() + precioLecheSolo);
				depoCafe.servirCafe(1);
				depoLeche.servirLeche(1);
				depoVaso.servirVaso(1);
		}
	}
	
	public void servirCafeConLeche() {
		double precioCafeConLeche = 1.5;
		if (monedero.getSaldo() >= precioCafeConLeche &&
				depoCafe.getCantidad() > 0 &&
				depoLeche.getCantidad() > 0 &&
				depoVaso.getCantidad() > 0) {
				monedero.setSaldo(monedero.getSaldo() + precioCafeConLeche);
				
				monedero.setSaldo(monedero.getSaldo() + precioCafeConLeche);
				depoCafe.servirCafe(1);
				depoLeche.servirLeche(1);
				depoVaso.servirVaso(1);
		}
	}
	
	
	public void mostrarMenu() {
		System.out.println("Seleccione una opcion:");
		System.out.println("1. Servir cafe solo");
		System.out.println("2. Servir leche solo");
		System.out.println("3. Servir cafe con leche");
		System.out.println("4. Estado de la maquina");
		System.out.println("5. Salir");
	}
	
	public void ejecutarOpcion(int opcion) {
		switch (opcion) {
			case 1:
				servirCafeSolo();
				System.out.println("Cafe solo servido.");
				break;
			case 2:
				servirLecheSolo();
				System.out.println("Leche sola servida.");
				break;
			case 3:
				servirCafeConLeche();
				System.out.println("Cafe con leche servido.");
				break;
			case 4:
				// Mostrar estado de la maquina
				estadoMaquina();	
				break;
			case 5: 
				System.out.println("Gracias por usar la maquina de cafe.");
				break;
			default:
				System.out.println("Opcion invalida.");
				break;
		}
	}
	public void estadoMaquina() {
		System.out.println("Estado de la maquina:");
		System.out.println("Cafe: " + depoCafe.getCantidad());
		System.out.println("Leche: " + depoLeche.getCantidad());
		System.out.println("Vasos: " + depoVaso.getCantidad());
		System.out.println("Saldo en el monedero: " + monedero.getSaldo());
	}
}
