package saldo;

public class Cuenta {
	// atributos
	private int saldo;
	
	// constructor
	public Cuenta(int saldoInicial) {
		saldo = saldoInicial;
	}
	
	//metodos
	public int obtenerSaldo() {
		return saldo;
	}
	
	public void depositar(int cantidad) {
		if (cantidad > 0) {
			saldo += cantidad;
		}
	}
	
	public void retirar(int cantidad) {
		if (cantidad > 0 && cantidad <= saldo) {
			saldo -= cantidad;
		}
		else {
			System.out.println("Fondos insuficientes para retirar " + cantidad);
		}
	}
	
	public void mostrarSaldo() {
		// mostrar movimientos de ingresos y reintegros
		System.out.println("El saldo actual es: " + saldo);

	}

}
