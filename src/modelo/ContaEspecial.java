package modelo;

public class ContaEspecial extends Conta {
	private double limite;

	public ContaEspecial(int id, String data, double saldo, double limite) {
		super(id, data, saldo);
		this.limite = limite;
	}
	public void debitar(double valor){
		this.saldo = saldo - valor; 
	}
	public double getLimite() {
		return limite;
	}
	public void setLimite(double limite) {
		this.limite = limite;
	}
}
