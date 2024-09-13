package regras_negocio;

import java.util.ArrayList;

import modelo.ContaEspecial;
import modelo.Correntista;
import modelo.Conta;
import repositorio.Repositorio;

public class Fachada {
	private Fachada() {}		
	private static Repositorio repositorio = new Repositorio();	
	
	public static ArrayList<Correntista> listarCorrentistas() 	{
		//listarCorrentistas() retorna todos os correntistas do repositório ordenados pelo cpf
		//o sort é nesta classe?
		return repositorio.getCorrentistas();
	}
	public static ArrayList<Conta> listarContas() {
		//listarContas() retorna todas as contas do repositório
		ArrayList<Conta> lista = new ArrayList<>();
		for(Conta c : repositorio.getContas())
				lista.add(c);
		return lista;
	}
	public static void criarCorrentista (String cpf, String nome, String senha) throws Exception {
		//criarCorrentista(cpf,nome,senha) – cria um correntista e o adiciona no repositório
		data = data.trim();
		descricao = descricao.trim();

		//localizar Correntista no repositorio, usando a data 
		Correntista ev = repositorio.localizarCorrentista(data);
		if (ev!=null)
			throw new Exception("criar Correntista: " + descricao + " ja existe nesta data "+data);
		
		if (preco <0)
			throw new Exception("criar Correntista: " + descricao + " preco nao pode ser negativo " + preco);

		//gerar id no repositorio
		int id = repositorio.gerarIdCorrentista();
		ev = new Correntista(id, descricao, data, preco);	
		
		//adicionar Correntista no reposit�rio
		repositorio.adicionar(ev);
		//gravar reposit�rio
		repositorio.salvarObjetos();
	}
	public static void criarConta(String cpf) throws Exception {
		//criarConta(cpf) – cria uma conta simples para o correntista (titular) e a adiciona ao
		//repositório
		
		email = email.trim();
		nome = nome.trim();

		//localizar Conta no repositorio, usando o nome 
		Conta p = repositorio.localizarConta(nome);
		if (p!=null)
			throw new Exception("N�o criou Conta: " + nome + " ja cadastrado(a)");

		//criar objeto Conta 
		p = new Conta (email, nome, idade);

		//adicionar Conta no reposit�rio
		repositorio.adicionar(p);
		//gravar reposit�rio
		repositorio.salvarObjetos();
	}	

	public static void criarContaEspecial(String cpf, double limite) throws Exception {
		//criarContaEspecial(cpf, limite) – cria uma conta especial para o correntista e a adiciona
		//ao repositório
		
		email = email.trim();
		nome = nome.trim();
		empresa = empresa.trim();

		//localizar Conta no repositorio, usando o nome 
		Conta p = repositorio.localizarConta(nome);
		if (p!=null)
			throw new Exception("criar ContaEspecial: " + nome + " ja cadastrado(a)");

		//a empresa � obrigatoria 
		if (empresa.isEmpty())
			throw new Exception("criar ContaEspecial: " + nome + " empresa � obrigatoria");

		//criar objeto ContaEspecial 
		ContaEspecial c = new ContaEspecial (email, nome, idade, empresa);

		//adicionar ContaEspecial no reposit�rio
		repositorio.adicionar(c);
		//gravar reposit�rio
		repositorio.salvarObjetos();
	}
	public static void InserirCorrentistaConta(int id, String cpf) throws Exception {
		nome = nome.trim();

		//localizar Conta no repositorio, usando o nome 
		Conta p = repositorio.localizarConta(nome);
		if(p == null) 
			throw new Exception("adicionar Conta:  " + nome + " inexistente");

		//localizar Correntista no repositorio, usando id 
		Correntista ev = repositorio.localizarCorrentista(id);
		if(ev == null) 
			throw new Exception("adicionar Conta: Correntista " + id + " inexistente");

		//localizar o Conta no Correntista, usando o nome
		Conta paux = ev.localizar(nome);
		if(paux != null) 
			throw new Exception("N�o adicionou Conta: " + nome + " j� participa do Correntista " + id);

		//adicionar o Conta ao Correntista
		ev.adicionar(p);
		//adicionar o Correntista ao Conta
		p.adicionar(ev);
		//gravar reposit�rio
		repositorio.salvarObjetos();
	}

	public static void removerCorrentistaConta(int id, String cpf) throws Exception {
		//removerCorrentistaConta(id, cpf) – remove relacionamento entre um correntista e uma
		//conta
		
		nome = nome.trim();

		//localizar Conta no repositorio, usando o nome 
		Conta p = repositorio.localizarConta(nome);
		if(p == null) 
			throw new Exception("remover Conta: Conta " + nome + " inexistente");


		//localizar Correntista no repositorio, usando id 
		Correntista ev = repositorio.localizarCorrentista(id);
		if(ev == null) 
			throw new Exception("remover Conta: Correntista " + id + " inexistente");


		//localizar o Conta no Correntista, usando o nome
		Conta paux = ev.localizar(nome);
		if(paux == null) 
			throw new Exception("remover Conta: " + nome + " nao participa do Correntista " + id);

		//remover o Conta do Correntista
		ev.remover(p);
		//remover o Correntista do Conta
		p.remover(ev);
		//gravar reposit�rio
		repositorio.salvarObjetos();
	}

	public static void apagarConta(int id) throws Exception	{
		//localizar Correntista no repositorio, usando id 
		Correntista ev = repositorio.localizarCorrentista(data);
		if (ev == null)
			throw new Exception("apagar Correntista: data " + data + " inexistente");

		//Remover todos os Contas deste Correntista
		for(Conta p : ev.getContas()) {
			p.remover(ev);
		}
		ev.getContas().clear();
		
		//remover Correntista do reposit�rio
		repositorio.remover(ev);
		//gravar reposit�rio
		repositorio.salvarObjetos();
	}
	public void creditarValor(int id, String cpf, String senha,  double valor) {
		saldo = saldo + valor;
	}
	
	public void debitarValor(int id, String cpf, String senha,  double valor) throws Exception {
		if (valor < 0)
			throw new Exception("quantia invalida="+valor);
		if (valor > saldo) {
			// System.out.println("quantia incorreta=" +valor +" ultrapassou o saldo="+saldo);
			// return;
			throw new Exception("quantia maior que o saldo=" + valor);
		}
		saldo = saldo - valor;
	}
	public void transferirValor(int id1, String cpf, String senha,  double valor, int id2) throws Exception {
		this.debitar(valor);
		destino.creditar(valor);
	}
}
