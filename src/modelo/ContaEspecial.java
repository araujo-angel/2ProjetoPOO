package modelo;

public class ContaEspecial extends Conta {
	private double limite;

	public ContaEspecial(int id, String data, double saldo, double limite) {
		super(id, data);
		this.limite = limite;
	}

	@Override
	public String toString() {
		return "ContaEspecial [limite=" + limite + "]";
	}
	@Override
	public void debitar(double valor) throws Exception {
		if(saldo - valor < - limite) {
			throw new Exception("Saldo insuficiente, limite da conta especial excedido");
		}
		this.saldo = saldo - valor; 
	}
	public double getLimite() {
		return limite;
	}
	public void setLimite(double limite) {
		this.limite = limite;
	}
	public double getSaldo() {
		return super.getSaldo();
	}
}
