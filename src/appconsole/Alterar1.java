package appconsole;
import regras_negocio.Fachada;

public class Alterar1 {

	public Alterar1() {
		try {
			Fachada.inserirCorrentistaConta(2, "0001"); // joao cotitular em maria
			Fachada.inserirCorrentistaConta(3, "0001"); // joao cotitular em jose
			Fachada.inserirCorrentistaConta(1, "0002"); // maria cotitular em joao
			Fachada.inserirCorrentistaConta(3, "0002"); // maria cotitular em jose
			Fachada.inserirCorrentistaConta(1, "0003"); // jose cotitular em joao
			Fachada.inserirCorrentistaConta(2, "0003"); // jose cotitular em maria
			System.out.println("inseriu cotitulares nas contas");
//			//joao 1, 2, 3 
//			//maria 2, 1, 3
//			//jose 3, 1, 2
			Fachada.removerCorrentistaConta(1, "0002"); // maria deixa de ser cotitular em joao
			Fachada.removerCorrentistaConta(3, "0002"); // maria deixa de ser cotitular em jose
			System.out.println("removeu cotitulares das contas");
//			//joao 1, 2, 3 
			//maria 2,
			//jose 3, 1, 2
			Fachada.removerCorrentistaConta(1, "0001");
			
		} catch (Exception e) {
			System.out.println("--->" + e.getMessage());
		}

		try {
			//Fachada.adiarEvento("24/10/2024", "30/10/2024");
			//System.out.println("adiou evento");

		} catch (Exception e) {
			System.out.println("--->" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Alterar1();
	}
}
