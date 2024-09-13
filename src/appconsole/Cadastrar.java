package appconsole;
/**
 * SI - POO - Prof. Fausto Ayres
 * Teste da Fachada
 * 
 */

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			Fachada.criarConta("0001");
			Fachada.criarConta("0002");

			Fachada.criarCorrentista("0001", "joao",  1234);
			Fachada.criarCorrentista("0002", "maria",  4567);

			Fachada.inserirCorrentistaConta("0001");
			Fachada.inserirCorrentistaConta("0002");
			
			Fachada.criarContaEspecial("0001", 200);
			Fachada.criarConvidado("0002", 250);
			
			System.out.println("Cadastrou");
		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}		
	}

	public static void main (String[] args) 
	{
		new Cadastrar();
	}
}


