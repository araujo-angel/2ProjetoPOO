package appconsole;
import regras_negocio.Fachada;

public class TestarExcecao {

	public static void main (String[] args) {

		System.out.println("\n-------TESTE DE EXCECOES LANCADAS PELOS METODOS DA FACHADA--------");
		try {
			Fachada.criarCorrentista("01/01/2030","e1", 100);
			Fachada.criarCorrentista("01/01/2030","e1", 100);
			System.out.println("*************3--->Nao lancou excecao para: criar evento existente "); 
			
		}catch (Exception e) {System.out.println("1ok--->"+e.getMessage());}
		
		try {
			Fachada.criarConta("p1@gmail.com","p1", 10);
			Fachada.criarConta("p1@gmail.com","p1", 10);
			System.out.println("*************1--->Nao lancou excecao para: criar participante existente "); 
		}catch (Exception e) {System.out.println("3ok--->"+e.getMessage());}
		try {
			Fachada.criarContaEspecial("c1@gmail.com","c1", 30, "empresa");
			Fachada.criarContaEspecial("c1@gmail.com","c1", 30, "empresa");
			System.out.println("*************2--->Nao lancou excecao para: criar convidado existente "); 
		}catch (Exception e) {System.out.println("2ok--->"+e.getMessage());}
		
		try {
			Fachada.criarCorrentista("02/01/2030","e2", -10.0);
			System.out.println("*************4--->Nao lancou excecao para: criar evento preco negativo "); 
		}catch (Exception e) {System.out.println("4ok--->"+e.getMessage());}

		try {
			Fachada.criarCorrentista("03/01/2030","e3", 0.0);
			System.out.println("*************5--->Nao lancou excecao para: adiar evento data existente"); 
		}catch (Exception e) {System.out.println("5ok--->"+e.getMessage());}

		try 
		{
			Fachada.adicionarParticipanteEvento("p1", 1);	
			Fachada.adicionarParticipanteEvento("p1", 1);	
			System.out.println("*************6--->Nao lancou excecao: adicionar participante evento que participa"); 
		}catch (Exception e) {System.out.println("6ok--->"+e.getMessage());}

		try 
		{
			Fachada.removerCorrentistaConta("p1", 1);	
			Fachada.removerCorrentistaConta("p1", 1);	
			System.out.println("*************7--->Nao lancou exce��o: remover participante evento que nao participa"); 
		}catch (Exception e) {System.out.println("7ok--->"+e.getMessage());}

		try 
		{
			Fachada.removerCorrentistaConta("p2", 1);	
			System.out.println("*************9--->Nao lancou exce��o: remover participante inexistente "); 
		}catch (Exception e) {System.out.println("9ok--->"+e.getMessage());}

		try 
		{
			Fachada.removerCorrentistaConta("03/01/2030");	
			Fachada.removerCorrentistaConta("03/01/2030");	
			System.out.println("*************10--->Nao lan�ou exce��o: apagar evento inexistente"); 
		}catch (Exception e) {System.out.println("10ok--->"+e.getMessage());}

		try 
		{
			Fachada.apagarConta("p2");	
			System.out.println("*************11--->Nao lan�ou exce��o: apagar participante inexistente"); 
		}catch (Exception e) {System.out.println("11ok--->"+e.getMessage());}

		//apagar dados usados no teste
		try {Fachada.removerCorrentistaConta("01/01/2030");}	catch (Exception e){}		
		try {Fachada.removerCorrentistaConta("02/01/2030");}	catch (Exception e){}		
		try {Fachada.removerCorrentistaConta("03/01/2030");}	catch (Exception e) {}		
		try {Fachada.apagarConta("p1");} catch (Exception e){}
		try {Fachada.apagarConta("p2");} catch (Exception e){}
		try {Fachada.apagarConta("c1");} catch (Exception e){}
	}
}


