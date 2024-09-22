package appconsole;

import modelo.Correntista;
import modelo.Conta;
//import modelo.ContaEspecial;
import regras_negocio.Fachada;

public class Listar {

	public Listar() {
		try {
			System.out.println("\n---------listagem de contas-----");
			for (Conta c : Fachada.listarContas())
				System.out.println(c);

			System.out.println("\n---------listagem de correntistas ----");
			for (Correntista co : Fachada.listarCorrentistas())
				System.out.println(co);

		} catch (Exception e) {
			System.out.println("--->" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Listar();
	}
}
