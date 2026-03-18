package cuentaAvanzado;

public class CuentaCredito extends Cuenta {
	private double credito;

	// saldo inicial 0, credito inicial 100
	public CuentaCredito() {
		super();
		credito = 100;
	}

	public CuentaCredito(double credito) {
		super();
		setCredito(credito);
	}

	public double getCredito() {
		return credito;
	}

	// credito maximo 300, y no puede superar lo que ya se debe
	public void setCredito(double credito) {
		if (credito > 300) {
			credito = 300;
		}
		// el credito nuevo no puede ser menor que lo que ya se debe
		if (saldo < 0 && credito < -saldo) {
			credito = -saldo;
		}
		this.credito = credito;
	}

	// sobreescribimos sacarDinero para permitir numeros rojos hasta el limite de credito
	@Override
	public void sacarDinero(double cantidad) throws SaldoInsuficienteException {
		if (cantidad > saldo + credito) {
			throw new SaldoInsuficienteException("Limite de credito superado");
		}
		saldo -= cantidad;
	}
}
