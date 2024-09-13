package modelo;

import java.util.ArrayList;

public class Conta { 
	protected int id;
	protected String data;
	protected double saldo;
	private ArrayList<Correntista> Correntistas = new ArrayList<>();
	
	public Conta(int id, String data, double saldo) {
		super();
		this.id = id;
		this.data = data;
		this.saldo = saldo;
	}
	public void creditar(double valor) {
		saldo = saldo - valor;
	}
	public void debitar(double valor) throws Exception {
		if (valor < 0)
			throw new Exception("quantia invalida = " + valor);
		if (valor > saldo) {
			throw new Exception("quantia maior que o saldo = "  +  valor);
		}
		saldo = saldo - valor;
	}
	public void transferir(double valor, Conta destino) throws Exception {
		this.debitar(valor);
		destino.creditar(valor);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public ArrayList<Correntista> getCorrentistas() {
		return Correntistas;
	}
	public void setCorrentistas(ArrayList<Correntista> correntistas) {
		Correntistas = correntistas;
	}
	
}
