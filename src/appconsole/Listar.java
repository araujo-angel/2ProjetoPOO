package appconsole;
/**
 * SI - POO - Prof. Fausto Ayres
 * Teste da Fachada
 * 
 */

import modelo.Evento;
import modelo.Participante;
import regras_negocio.Fachada;

public class Listar {

	public Listar() {
		try {
			System.out.println("\n---------listagem de contas (arquivo)-----");
			for(Participante p : Fachada.listarContas("")) 
				System.out.println(p);

			System.out.println("\n---------listagem de correntistas (arquivo) ----");
			for(Evento e : Fachada.listarCorrentistas()) 
				System.out.println(e);
			
			System.out.println("\n---------listagem de contas especiais (arquivo) ----");
			for(Evento e : Fachada.listarContaEspecial()) 
				System.out.println(e);
			
		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}	
	}

	public static void main (String[] args) 
	{
		new Listar();
	}
}


