package modelo;

import java.util.ArrayList;

public class Correntista implements Comparable<Correntista> {
	private String cpf;
	private String nome;
	private String senha;
	private ArrayList<Conta> contas = new ArrayList<>();
	// private ArrayList<String> titulares = new ArrayList<>();

	public Correntista(String cpf, String nome, String senha) throws Exception {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
	}

	public int compareTo(Correntista outro) {
		return this.getCpf().compareTo(outro.getCpf());
	}

//	public void adcTitular(String cpf) {
//		titulares.add(cpf);
//	}
//	public boolean eTitular(String cpf) {
//		if(titulares.contains(cpf))
//			return true;
//		return false;
//	}
	public void adicionar(Conta conta) {
		contas.add(conta);
	}

	public void remover(Conta conta) {
		contas.remove(conta);
	}

	public Conta localizar(int id) {
		for (Conta c : contas)
			if (c.getId() == id) {
				return c;
			}
		return null;
	}

	public boolean possuiConta() {
		if (contas.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public double getSaldoTotal() {
		double saldoTotal = 0;
		for (Conta conta : this.contas) {
			saldoTotal += conta.getSaldo();
		}
		return saldoTotal;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ArrayList<Conta> getContas() {
		return contas;
	}

	public void setContas(ArrayList<Conta> contas) {
		this.contas = contas;
	}

	@Override
	public String toString() {
		return "Correntista [cpf=" + cpf + ", nome=" + nome + ", senha=" + senha + "]";
	}
}
