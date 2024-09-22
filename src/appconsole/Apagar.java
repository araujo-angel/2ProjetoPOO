package appconsole;
import regras_negocio.Fachada;

public class Apagar {

	public Apagar() {
		try {
			Fachada.apagarConta(2);
			System.out.println("Deletou a conta de Maria");
			
			Fachada.removerCorrentistaConta(1, "0001");
			System.out.println("Removeu correntista Joao");

		} catch (Exception e) {
			System.out.println("--->" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Apagar();
	}
}
