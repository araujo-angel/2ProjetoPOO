package modelo;

import java.util.ArrayList;

public class Correntista {
	private String cpf;
	private String nome;
	private String senha;
	private ArrayList<Conta> Contas = new ArrayList<>();

	public Correntista(String cpf, String nome, String senha) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
	}
	public double getSaldoTotal(){
		return getSaldoTotal();
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
		return Contas;
	}
	public void setContas(ArrayList<Conta> contas) {
		Contas = contas;
	}
	
}
