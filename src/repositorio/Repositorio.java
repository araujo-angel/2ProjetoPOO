package repositorio;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.ContaEspecial;
import modelo.Correntista;
import modelo.Conta;

public class Repositorio {
	private ArrayList<Conta> Contas = new ArrayList<>();
	private ArrayList<Correntista> Correntistas = new ArrayList<>(); 

	public Repositorio() {
		carregarObjetos();
	}
	public void adicionar(Conta c)	{
		Contas.add(c);
	}

	public void remover(Conta c)	{
		Contas.remove(c);
	}

	public Conta localizarConta(String nome)	{
		for(Conta p : Contas)
			if(p.getNome().equals(nome))
				return p;
		return null;
	}

	public void adicionar(Correntista e)	{
		Correntistas.add(e);
	}
	public void remover(Correntista e)	{
		Correntistas.remove(e);
	}

	public Correntista localizarCorrentista(int id)	{
		for(Correntista e : Correntistas)
			if(e.getId() == id)
				return e;
		return null;
	}
	public Correntista localizarCorrentista(String data)	{
		for(Correntista e : Correntistas)
			if(e.getData().equals(data))
				return e;
		return null;
	}

	public ArrayList<Conta> getContas() 	{
		return Contas;
	}
	
	public ArrayList<Correntista> getCorrentistas() 	{
		return Correntistas;
	}

	public int getTotalConta()	{
		return Contas.size();
	}

	public int getTotalCorrentistas()	{
		return Correntistas.size();
	}

	public int gerarIdCorrentista() {
		if (Correntistas.isEmpty())
			return 1;
		else {
			Correntista ultimo = Correntistas.get(Correntistas.size()-1);
			return ultimo.getId() + 1;
		}
	}
	public void carregarObjetos()  	{
		// carregar para o repositorio os objetos dos arquivos csv
		try {
			//caso os arquivos nao existam, serao criados vazios
			File f1 = new File( new File(".\\correntistas.csv").getCanonicalPath() ) ; 
			File f2 = new File( new File(".\\contas.csv").getCanonicalPath() ) ; 
			if (!f1.exists() || !f2.exists() ) {
				//System.out.println("criando arquivo .csv vazio");
				FileWriter arquivo1 = new FileWriter(f1); arquivo1.close();
				FileWriter arquivo2 = new FileWriter(f2); arquivo2.close();
				return;
			}
		}
		catch(Exception ex)		{
			throw new RuntimeException("criacao dos arquivos vazios:"+ex.getMessage());
		}

		String linha;	
		String[] partes;	
		Correntista ev;
		Conta p;

		try	{
			String data, descricao, id, preco ;
			File f = new File( new File(".\\correntistas.csv").getCanonicalPath() )  ;
			Scanner arquivo1 = new Scanner(f);	 
			while(arquivo1.hasNextLine()) 	{
				linha = arquivo1.nextLine().trim();		
				partes = linha.split(";");	
				//System.out.println(Arrays.toString(partes));
				id = partes[0];
				data = partes[1];
				descricao = partes[2];
				preco = partes[3];
				ev = new Correntista(Integer.parseInt(id), descricao, data, Double.parseDouble(preco));
				this.adicionar(ev);
			} 
			arquivo1.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de Correntistas:"+ex.getMessage());
		}

		try	{
			String tipo,nome, email, empresa, idade, ids;
			File f = new File( new File(".\\contas.csv").getCanonicalPath())  ;
			Scanner arquivo2 = new Scanner(f);	 
			while(arquivo2.hasNextLine()) 	{
				linha = arquivo2.nextLine().trim();	
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				tipo = partes[0];
				email = partes[1];
				nome = partes[2];
				idade = partes[3];
				ids="";
				if(tipo.equals("Conta")) {
					p = new Conta(email,nome,Integer.parseInt(idade));
					this.adicionar(p);
					if(partes.length>4)
						ids = partes[4];		//ids dos Correntistas separados por ","
				}
				else {
					empresa = partes[4];
					p = new ContaEspecial(email,nome,Integer.parseInt(idade),empresa);
					this.adicionar(p);
					if(partes.length>5)
						ids = partes[5];		//ids dos Correntistas separados por ","
				}

				//relacionar Conta com os seus Correntistas
				if(!ids.isEmpty()) {	
					for(String idCorrentista : ids.split(",")){	//converter string em array
						ev = this.localizarCorrentista(Integer.parseInt(idCorrentista));
						ev.adicionar(p);
						p.adicionar(ev);
					}
				}
			}
			arquivo2.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de Contas:"+ex.getMessage());
		}
	}

	//--------------------------------------------------------------------
	public void	salvarObjetos()  {
		//gravar nos arquivos csv os objetos que est�o no reposit�rio
		try	{
			File f = new File( new File(".\\correntistas.csv").getCanonicalPath())  ;
			FileWriter arquivo1 = new FileWriter(f); 
			for(Correntista e : Correntistas) 	{
				arquivo1.write(e.getId()+";"+e.getData()+";"+e.getDescricao()+";"+e.getPreco()+"\n");	
			} 
			arquivo1.close();
		}
		catch(Exception e){
			throw new RuntimeException("problema na criacao do arquivo  Correntistas "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\contas.csv").getCanonicalPath())  ;
			FileWriter arquivo2 = new FileWriter(f) ; 
			ArrayList<String> lista ;
			String listaId;
			for(Conta p : Contas) {
				//montar uma lista com os id dos Correntistas do Conta
				lista = new ArrayList<>();
				for(Correntista e : p.getCorrentistas()) {
					lista.add(e.getId()+"");
				}
				listaId = String.join(",", lista);

				if(p instanceof ContaEspecial c )
					arquivo2.write("ContaEspecial;" +p.getEmail() +";" + p.getNome() +";" 
							+ p.getIdade() +";"+ c.getEmpresa() +";"+ listaId +"\n");	
				else
					arquivo2.write("Conta;" +p.getEmail() +";" + p.getNome() +";" 
							+ p.getIdade() +";"+ listaId +"\n");	

			} 
			arquivo2.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na criacao do arquivo  Contas "+e.getMessage());
		}

	}
}

