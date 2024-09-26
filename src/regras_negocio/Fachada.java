package regras_negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;
import repositorio.Repositorio;

public class Fachada {
	private Fachada() {
	}

	private static Repositorio repositorio = new Repositorio();

	public static ArrayList<Correntista> listarCorrentistas() {
		// listarCorrentistas() retorna todos os correntistas do repositório ordenados
		// pelo cpf
		return repositorio.getCorrentistas();
	}

	public static ArrayList<Conta> listarContas() {
		// listarContas() retorna todas as contas do repositório
		ArrayList<Conta> lista = new ArrayList<>();
		for (Conta c : repositorio.getConta())
			lista.add(c);
		return lista;
	}

	public static void criarCorrentista(String cpf, String nome, String senha) throws Exception {
		// criarCorrentista(cpf,nome,senha) – cria um correntista e o adiciona no
		// repositório
		if (!senha.matches("\\d{4}")) {
			throw new Exception("A senha deve conter 4 dígitos numericos");
		}
		Correntista co = repositorio.localizarCorrentista(cpf);
		if (co != null)
			throw new Exception("Correntista com CPF: " + cpf + " já existe");
		// criar objeto correntista
		co = new Correntista(cpf, nome, senha);
		repositorio.adicionar(co);
		repositorio.salvarObjetos();
	}

	private static String dataAtual() {
		LocalDate hoje = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return hoje.format(formatter);
	}

	public static void criarConta(String cpf) throws Exception {
		// criarConta(cpf) – cria uma conta simples para o correntista (titular) e a
		// adiciona ao
		// repositório
		Correntista cor = repositorio.localizarCorrentista(cpf);
		if (cor == null) {
			throw new Exception("Correntista com CPF: " + cpf + "inexistente");
		}
		for (Conta cExiste : repositorio.getConta()) {
			if (cExiste.getCorrentistas().size() > 0 && cExiste.getCorrentistas().get(0).getCpf().equals(cpf)) {
				throw new Exception("Correntista com CPF: " + cpf + "já possui uma conta como titular");
			}
		}
		int id;
		id = repositorio.gerarIdConta();
		String today = dataAtual();
		Conta c;
		c = new Conta(id, today); // cria uma conta com saldo 0
		c.adicionar(cor);
		repositorio.adicionar(c);
		repositorio.salvarObjetos();
	}

	public static void criarContaEspecial(String cpf, double limite) throws Exception {
		// criarContaEspecial(cpf, limite) – cria uma conta especial para o correntista
		// (titular) e a adiciona ao repositório
		if (limite < 50) {
			throw new Exception("Limite minimo = 50.00 R$");
		}
		Correntista co = repositorio.localizarCorrentista(cpf);
		if (co != null) {
			int id;
			id = repositorio.gerarIdConta();
			String today = dataAtual();
			ContaEspecial ce = new ContaEspecial(id, today, 0, limite);
			repositorio.adicionar(ce);
			ce.adicionar(co);
			repositorio.salvarObjetos();
		} else {
			throw new Exception("Nao foi possivel criar sua conta, tente novamente mais tarde.");
		}
	}

	public static void inserirCorrentistaConta(int id, String cpf) throws Exception {
		// localizar Conta no repositorio, usando o id
		Conta conta = repositorio.localizarConta(id);
		if (conta == null)
			throw new Exception("Conta " + id + " inexistente");
		// localizar Correntista no repositorio, usando cpf
		Correntista correntista = repositorio.localizarCorrentista(cpf);
		if (correntista == null)
			throw new Exception("Correntista " + cpf + " inexistente");

		Conta cont = correntista.localizar(id);
		if (cont != null)
			throw new Exception("Este correntista " + cpf + "ja e titular desta conta");

		// correntista.adicionar(conta);
		conta.adicionar(correntista);
		repositorio.salvarObjetos();
	}

	public static void removerCorrentistaConta(int id, String cpf) throws Exception {
		// removerCorrentistaConta(id, cpf) – remove relacionamento entre um correntista
		// e uma conta

		Conta c = repositorio.localizarConta(id);
		if (c == null)
			throw new Exception("remover Conta: Conta " + id + " inexistente");

		boolean cotitularEncontrado = c.getCorrentistas().stream().anyMatch(conta -> conta.getCpf().equals(cpf));
		if (!cotitularEncontrado) {
			throw new Exception("Correntista não é cotitular desta conta.");
		}
		Correntista co = repositorio.localizarCorrentista(cpf);
		if (co == null)
			throw new Exception("remover Conta: Correntista " + cpf + " inexistente");

		if (!c.getCorrentistas().isEmpty() && c.getCorrentistas().get(0).getCpf().equals(cpf)) {
			throw new Exception(
					"O correntista titular " + cpf + " não pode ser removido da conta antes que ela seja deletada.");
		}
		c.remover(co);
		co.remover(c);
		repositorio.salvarObjetos();
	}

	public static void apagarConta(int id) throws Exception {
		Conta c = repositorio.localizarConta(id);
		if (c == null)
			throw new Exception("remover Conta: Conta " + id + " inexistente");
		if (c.getSaldo() != 0) {// verificar o saldo da conta e nao de todas as contas do corr
			throw new Exception("Não é possível apagar a conta com saldo diferente de zero.");
		}
		// Remover todos os Contas deste Correntista
		for (Correntista co : c.getCorrentistas()) {
			co.remover(c);
		}
		c.getCorrentistas().clear();
		// remover Conta do repositorio
		repositorio.remover(c);
		// gravar repositorio
		repositorio.salvarObjetos();
	}

	public static void creditarValor(int id, String cpf, String senha, double valor) throws Exception {
		Conta c = repositorio.localizarConta(id);
		if (c == null) {
			throw new Exception("Conta " + id + " inexistente");
		}
		Correntista co = repositorio.localizarCorrentista(cpf);
		if (co == null) {
			throw new Exception("remover Conta: Correntista " + cpf + " inexistente");
		}
		if (!co.getSenha().equals(senha)) {
			throw new Exception("Senha incorreta para o correntista " + cpf);
		}
		c.creditar(valor);
		repositorio.salvarObjetos();
	}

	public static void debitarValor(int id, String cpf, String senha, double valor) throws Exception {
		Correntista co = repositorio.localizarCorrentista(cpf);
		if (co == null) {
			throw new Exception("Correntista " + cpf + " inexistente");
		}
		if (!co.getSenha().equals(senha)) {
			throw new Exception("Senha incorreta para o correntista " + cpf);
		}
		Conta c = repositorio.localizarConta(id);
		if (c == null) {
			throw new Exception("Conta " + id + " inexistente");
		}
		if (c instanceof ContaEspecial) {
			ContaEspecial ce = (ContaEspecial) c;
			if (ce.getSaldo() - valor < -ce.getLimite()) {
				throw new Exception("Saldo insuficiente. Limite excedido.");
			} else {
				ce.debitar(valor);
				repositorio.salvarObjetos();
			}
		} else if (c.getSaldo() - valor < 0) {
			throw new Exception("Saldo insuficiente.");
		} else {
			c.debitar(valor);
			repositorio.salvarObjetos();
		}
	}

	public static void transferirValor(int id1, String cpf, String senha, double valor, int id2) throws Exception {
		Conta c1 = repositorio.localizarConta(id1);
		if (c1 == null) {
			throw new Exception("Conta " + id1 + " inexistente");
		}
		Conta c2 = repositorio.localizarConta(id2);
		if (c2 == null) {
			throw new Exception("Conta " + id2 + " inexistente");
		}
		Correntista co = repositorio.localizarCorrentista(cpf);
		if (co == null)
			throw new Exception("remover Conta: Correntista " + cpf + " inexistente");

		if (!co.getSenha().equals(senha)) {
			throw new Exception("Senha incorreta para o correntista " + cpf);
		}
		c1.transferir(valor, c2);
		repositorio.salvarObjetos();
	}

	public static double mostraSaldoTotal(String cpf) throws Exception {
		Correntista co = repositorio.localizarCorrentista(cpf);
		if (co == null) {
			throw new Exception("remover Conta: Correntista " + cpf + " inexistente");
		}
		return co.getSaldoTotal();
	}
}
