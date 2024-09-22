package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			Fachada.criarCorrentista("0001","joao da silva", "1111"); //cpf,nome,senha
			Fachada.criarCorrentista("0002","maria de fatima", "2222");
			Fachada.criarCorrentista("0003","jose de  ribamar", "3333");
			Fachada.criarCorrentista("0004","ana do bessa", "4444");
			System.out.println("Cadastrou correntistas ");

			Fachada.criarConta("0001");	//gera id=1
			Fachada.criarConta("0002");	//gera id=2
			Fachada.criarContaEspecial("0003", 1000); 	//gera id=3
		    System.out.println("Cadastrou contas ");
	

//			Fachada.inserirCorrentistaConta(1, "0002");//insere maria como cotitular na conta de joao
//			Fachada.inserirCorrentistaConta(1, "0003"); //insere ana como cotitular na conta de joao
//		    Fachada.inserirCorrentistaConta(3, "0001"); //insere joao como cotitular na conta de ana
//      	    Fachada.inserirCorrentistaConta(4, "0002");//insere maria como cotitular na conta de manu
//			
//			Fachada.criarContaEspecial("0001", 200);//joao tem uma conta especial
//			Fachada.criarContaEspecial("0003", 300);//ana tem uma conta especial
//			
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
