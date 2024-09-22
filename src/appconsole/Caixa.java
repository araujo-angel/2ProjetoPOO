package appconsole;
import regras_negocio.Fachada;

public class Caixa {

	public Caixa() {
		try {
		Fachada.creditarValor(1, "0001", "1234", 500);//joao
		Fachada.creditarValor(2, "0002", "5678", 200);//maria
		Fachada.creditarValor(3, "0003", "9101", 200);//ana
		
		Fachada.debitarValor(1, "0001", "1234", 200);//joao = 300
		Fachada.debitarValor(3, "0003", "9101", 50);//ana = 150
		
		Fachada.transferirValor(2, "0002", "5678", 100, 3);//de maria (100) para ana (250) 
		
		System.out.println(Fachada.mostraSaldoTotal("0001"));
		System.out.println(Fachada.mostraSaldoTotal("0002"));
		System.out.println(Fachada.mostraSaldoTotal("0003"));
		
		} catch (Exception e) {
			System.out.println("--->" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Caixa();
	}
}