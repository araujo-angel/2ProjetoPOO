package appconsole;

/**
 * SI - POO - Prof. Fausto Ayres
 * Teste da Fachada
 * 
 */

import regras_negocio.Fachada;

public class Apagar {

	public Apagar() {
		try {
			Fachada.apagarConta("0002");
			System.out.println("Deletou a conta de Maria");
			
			Fachada.removerCorrentista("0001");
			System.out.println("Removeu correntista Joao");

		} catch (Exception e) {
			System.out.println("--->" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Apagar();
	}
}
