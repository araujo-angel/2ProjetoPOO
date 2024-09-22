package repositorio;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import modelo.ContaEspecial;
import modelo.Correntista;
import modelo.Conta;

public class Repositorio {
	private ArrayList<Conta> contas = new ArrayList<>();
	private ArrayList<Correntista> correntistas = new ArrayList<>();

	public Repositorio() {
		carregarObjetos();
	}

	public Correntista localizarCorrentista(String cpf) {
		for (Correntista co : correntistas)
			if (co.getCpf().equals(cpf))
				return co;
		return null;
	}

	public Conta localizarConta(int id) {
		for (Conta c : contas)
			if (c.getId() == id)
				return c;
		return null;
	}

	public void adicionar(Correntista co) {
		correntistas.add(co);
	}

	public void remover(Correntista co) {
		correntistas.remove(co);
	}

	public void adicionar(Conta c) {
		contas.add(c);
	}

	public void remover(Conta c) {
		contas.remove(c);
	}

	public ArrayList<Conta> getConta() {
		return contas;
	}

	public ArrayList<Correntista> getCorrentistas() {
		Collections.sort(correntistas);
		return correntistas;
	}

	public int getTotalContas() {
		return contas.size();
	}

	public int getTotalCorrentistas() {
		return correntistas.size();
	}

	public int gerarIdConta() {
		if (contas.isEmpty())
			return 1;
		else {
			Conta ultimo = contas.get(contas.size() - 1);
			return ultimo.getId() + 1;
		}
	}
//	public void carregarObjetos() {
//		try {
//			// caso os arquivos nao existam, serao criados vazios
//			File f1 = new File(new File(".\\correntistas.csv").getCanonicalPath());
//			File f2 = new File(new File(".\\contas.csv").getCanonicalPath());
//			if (!f1.exists() || !f2.exists()) {
//				// System.out.println("criando arquivo .csv vazio");
//				FileWriter arquivo1 = new FileWriter(f1);
//				arquivo1.close();
//				FileWriter arquivo2 = new FileWriter(f2);
//				arquivo2.close();
//				return;
//			}
//		} catch (Exception ex) {
//			throw new RuntimeException("criacao dos arquivos vazios:" + ex.getMessage());
//		}
//
//		String linha;
//		String[] partes;
//		Correntista correntista;
//		Conta conta;
//		// ***** Carregar Contas ********
//		try {
//			String tipo, id, data,saldo, limite;
//			File f = new File(new File(".\\contas.csv").getCanonicalPath());
//			Scanner arquivo2 = new Scanner(f);
//			while (arquivo2.hasNextLine()) {
//				linha = arquivo2.nextLine().trim();
//				partes = linha.split(";");
//				//System.out.println(Arrays.toString(partes));
//				tipo = partes[0];
//				id = partes[1];
//				data = partes[2];
//				saldo = partes[3];
//				if (tipo.equals("CONTA")) {
//					conta = new Conta(Integer.parseInt(id), data, Double.parseDouble(saldo));
//					this.adicionar(conta);
//				} 
//				else {
//					limite = partes[4];
//					conta = new ContaEspecial(Integer.parseInt(id), data, 
//							Double.parseDouble(saldo), Double.parseDouble(limite));
//					this.adicionar(conta);
//				}
//			}
//			arquivo2.close();
//		} catch (Exception ex) {
//			throw new RuntimeException("leitura arquivo de contas:" + ex.getMessage());
//		}
//
//		// ***** Carregar Correntistas ********
//		try {
//			String cpf, nome, senha;
//			File f = new File(new File(".\\correntistas.csv").getCanonicalPath());
//			Scanner arquivo1 = new Scanner(f);
//			while (arquivo1.hasNextLine()) {
//				linha = arquivo1.nextLine().trim();
//				partes = linha.split(";");
//				//System.out.println(Arrays.toString(partes));
//				cpf = partes[0];
//				nome = partes[1];
//				senha = partes[2];
//				correntista = new Correntista(cpf, nome, senha);
//				this.adicionar(correntista);
//
//				if (partes.length > 3) {
//					String ids;
//					ids = partes[3]; 	// ids dos correntistas separados por ","
//					// relacionar correntista com suas contas
//					for (String idconta : ids.split(",")) { // converter string em array
//						conta = this.localizarConta(Integer.parseInt(idconta));
//						conta.adicionar(correntista);
//						correntista.adicionar(conta);
//					}
//				}
//			}
//			arquivo1.close();
//		} catch (Exception ex) {
//			throw new RuntimeException("leitura arquivo de correntistas:" + ex.getMessage());
//		}
//
//	}
//
//	// --------------------------------------------------------------------
//	public void salvarObjetos() {
//		// gravar nos arquivos csv os objetos que est�o no reposit�rio
//		//******** gravar correntistas *****		
//		try {
//			File f = new File(new File(".\\correntistas.csv").getCanonicalPath());
//			FileWriter arquivo1 = new FileWriter(f);
//			String linha, ids;
//			ArrayList<String> listaId;
//			for (Correntista corr : correntistas) {
//				if (corr.getContas().isEmpty()) 
//					linha = corr.getCpf() + ";" + corr.getNome() + ";" + corr.getSenha() + "\n";
//				else {
//					//montar uma lista com os ids das contas
//					listaId = new ArrayList<>();
//					for (Conta cta : corr.getContas()) {
//						listaId.add(cta.getId() + "");
//					}
//					ids = String.join(",", listaId);
//					linha = corr.getCpf() + ";" + corr.getNome() + ";" + corr.getSenha() + ";" + ids + "\n";
//				}
//				arquivo1.write(linha);
//				//System.out.println("gravando:"+linha);
//			}
//			arquivo1.close();
//		}
//		catch (Exception e) {
//			throw new RuntimeException("problema na cria��o do arquivo  correntistas " + e.getMessage());
//		}
//
//		//******** gravar contas *****
//		try {
//			File f = new File(new File(".\\contas.csv").getCanonicalPath());
//			FileWriter arquivo2 = new FileWriter(f);
//			String linha;
//			for (Conta cta : contas) {
//				if (cta instanceof ContaEspecial esp)
//					linha = "CONTAESPECIAL;" + cta.getId() + ";" + 
//							cta.getData() +  ";" + cta.getSaldo() + ";" + esp.getLimite()  + "\n";
//				else
//					linha = "CONTA;" + cta.getId() + ";" +
//							cta.getData() + ";" + cta.getSaldo() + ";" + "\n";
//				
//				arquivo2.write(linha);
//				//System.out.println("gravando:"+linha);
//			}
//			arquivo2.close();
//		} catch (Exception e) {
//			throw new RuntimeException("problema na cria��o do arquivo  contas " + e.getMessage());
//		}
//	}
//}

	public void carregarObjetos() {
		// carregar para o repositorio os objetos dos arquivos csv
		try {
			// caso os arquivos nao existam, serao criados vazios
			File f1 = new File(new File(".\\correntistas.csv").getCanonicalPath());
			File f2 = new File(new File(".\\contas.csv").getCanonicalPath());
			if (!f1.exists() || !f2.exists()) {
				FileWriter arquivo1 = new FileWriter(f1);
				arquivo1.close();
				FileWriter arquivo2 = new FileWriter(f2);
				arquivo2.close();
				return;
			}
		} catch (Exception ex) {
			throw new RuntimeException("criacao dos arquivos vazios: " + ex.getMessage());
		}

		String linha;
		String[] partes;
		Correntista co = null;
		Conta c = null;

		// Carregar correntistas
		try {
			String cpf, nome, senha, contas;
			File f = new File(new File(".\\correntistas.csv").getCanonicalPath());
			Scanner arquivo1 = new Scanner(f);
			while (arquivo1.hasNextLine()) {
				linha = arquivo1.nextLine().trim();
				partes = linha.split(";");
				cpf = partes[0];
				nome = partes[1];
				senha = partes[2];
				contas = partes[3];
				co = new Correntista(cpf, nome, senha);
				this.adicionar(co);
			}
			arquivo1.close();
		} catch (Exception ex) {
			throw new RuntimeException("leitura arquivo de correntistas: " + ex.getMessage());
		}

		// Carregar contas
		try {
			String tipo, id, data, saldo, limite, correntistas;
			File f = new File(new File(".\\contas.csv").getCanonicalPath());
			Scanner arquivo2 = new Scanner(f);
			while (arquivo2.hasNextLine()) {
				linha = arquivo2.nextLine().trim();
				partes = linha.split(";");
				tipo = partes[0];
				id = partes[1];
				data = partes[2];
				saldo = partes[3];
				correntistas = "";

				if (tipo.equals("CONTA")) {
					c = new Conta(Integer.parseInt(id), data, Double.parseDouble(saldo));
					this.adicionar(c);
					if (partes.length > 4) {
						correntistas = partes[4]; // cpfs dos correntistas
						if (!correntistas.isEmpty() && c != null) {
							for (String correntistaCpf : correntistas.split(",")) {
								co = this.localizarCorrentista(correntistaCpf);
//								co.adicionar(c);
								c.adicionar(co);
							}
						}
					}
				} else if (tipo.equals("CONTA ESPECIAL")) {
					limite = partes[4];
					ContaEspecial ce = new ContaEspecial(Integer.parseInt(id), data, Double.parseDouble(saldo),
							Double.parseDouble(limite));
					this.adicionar(ce);
					if (partes.length > 5) {
						correntistas = partes[5]; // cpfs dos correntistas
						// relacionar correntista com as suas contas
						if (!correntistas.isEmpty() && ce != null) {
							for (String cpfCorrentista : correntistas.split(",")) { // converter string em array
								co = this.localizarCorrentista(cpfCorrentista);
//								co.adicionar(ce);
								ce.adicionar(co);
							}
						}
					}
				}
			}
			arquivo2.close();
		} catch (Exception ex) {
			throw new RuntimeException("leitura arquivo de contas: " + ex.getMessage());
		}
	}

	// --------------------------------------------------------------------
	public void salvarObjetos() {
		// Gravar nos arquivos csv os objetos que estao no repositorio
		try {
			File f = new File(new File(".\\correntistas.csv").getCanonicalPath());
			FileWriter arquivo1 = new FileWriter(f);
			for (Correntista co : correntistas) {
				arquivo1.write(co.getCpf() + ";" + co.getNome() + ";" + co.getSenha() + ";" + co.getContas() + "\n");
			}
			arquivo1.close();
		} catch (Exception e) {
			throw new RuntimeException("problema na criação do arquivo correntistas: " + e.getMessage());
		}

		try {
			File f = new File(new File(".\\contas.csv").getCanonicalPath());
			FileWriter arquivo2 = new FileWriter(f);
			ArrayList<String> lista;
			String listaCpf;
			for (Conta c : contas) {
				lista = new ArrayList<>();
				for (Correntista co : c.getCorrentistas()) {
					lista.add(co.getCpf() + "");
				}
				listaCpf = String.join(",", lista);

				if (c instanceof ContaEspecial ce) {
					arquivo2.write("CONTA ESPECIAL;" + c.getId() + ";" + c.getData() + ";" + c.getSaldo() + ";"
							+ ce.getLimite() + ";" + listaCpf + "\n");
				} else {
					arquivo2.write(
							"CONTA;" + c.getId() + ";" + c.getData() + ";" + c.getSaldo() + ";" + listaCpf + "\n");

				}
			}
			arquivo2.close();
		} catch (Exception e) {
			throw new RuntimeException("problema na criação do arquivo contas: " + e.getMessage());
		}
	}
}
