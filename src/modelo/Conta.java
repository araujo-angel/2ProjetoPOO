package modelo;

import java.util.ArrayList;

public class Conta { 
	protected int id;
	protected String data;
	protected double saldo;
	private ArrayList<Correntista> correntistas = new ArrayList<>();
	
	public Conta(int id, String data) {
		super();
		this.id = id;
		this.data = data;
		this.saldo = 0;
	}
	public Conta(int id, String data, double saldo) {
		this.id = id;
		this.data = data;
		this.saldo = saldo;
	}
	public void adicionar(Correntista correntista) {
       correntistas.add(correntista);
       correntista.adicionar(this);
   }
	public void remover(Correntista correntista) {
		correntistas.remove(correntista);
	      correntista.remover(this);
    }
	public void creditar(double valor)  throws Exception{
		if(valor < 0 || valor == 0 ) {
			throw new Exception("Valor negativo. Valor invalido");
		}
		saldo = saldo + valor;
	}
	public void debitar(double valor) throws Exception {
		if(saldo - valor < 0)
			throw new Exception("Saldo insuficiente = " + valor);
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
	public Correntista localizar(String correntista) {
		for(Correntista co: correntistas) {
			if(co.getCpf().equals(correntista))
				return co;
			}
		return null;
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
		return this.saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public ArrayList<Correntista> getCorrentistas() {
		return correntistas;
	}
	public void setCorrentistas(ArrayList<Correntista> correntistas) {
		this.correntistas = correntistas;
	}
	@Override
	public String toString() {
		return "Conta [id=" + id + ", data=" + data + ", saldo=" + saldo + " ]";
	}
	
}
