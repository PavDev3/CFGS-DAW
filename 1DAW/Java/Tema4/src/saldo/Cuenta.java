package saldo;

public class Cuenta {
	// atributos
	private int saldo;
	private int ingresos;
	private int retiros;
	
	// constructor
	public Cuenta(int saldoInicial, int movimientos) {
		saldo = saldoInicial;
		movimientos = 0;
		ingresos = 0;
		retiros = 0;
	}
	
	//metodos
	// getter saldo
	public int obtenerSaldo() {
		return saldo;
	
	}
	
	public void depositar(int cantidad) {
		if (cantidad > 0) {
			saldo += cantidad;
			ingresos++;
			
		}
	}
	
	public void retirar(int cantidad) throws SaldoInsuficienteException {
		if (cantidad > saldo) {
			throw new SaldoInsuficienteException("Fondos insuficientes para retirar " + cantidad);
		}
		if (cantidad > 0) {
			saldo -= cantidad;
			retiros++;
		}
	}
	
	// Metodo para mostar el saldo y los movimientos
	public void mostrarSaldo(int saldo) {
		System.out.println("=== Estado de la Cuenta ===");
		System.out.println("El saldo actual es: " + saldo);
		System.out.println("Ingresos realizados: " + ingresos);
		System.out.println("Retiros realizados: " + retiros);
	}
	
	// Getters para obtener los contadores
	public int obtenerIngresos() {
		return ingresos;
	}
	
	public int obtenerRetiros() {
		return retiros;
	}
	
	
	// Metodo para mostrar el resumen de movimientos
	public void mostrarResumenMovimientos() {
		System.out.println("=== Resumen de Movimientos ===");
		System.out.println("Ingresos realizados: " + ingresos);
		System.out.println("Retiros realizados: " + retiros);
		System.out.println("Saldo actual: " + saldo);
	}

}
