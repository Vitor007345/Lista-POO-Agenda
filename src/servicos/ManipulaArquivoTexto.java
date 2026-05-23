package servicos;

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.ArrayList;

public class ManipulaArquivoTexto {
	private String fileName;
	private Formatter gravador;
	private Scanner leitor;
	
	public ManipulaArquivoTexto(String fileName) {
		this.fileName = fileName;
		this.gravador = null;
		this.leitor = null;
	}
	
	public void abrirPraGravacao() throws FileNotFoundException{
		this.gravador = new Formatter(new File(this.fileName));
	}
	
	public <S extends Serializable> void gravar(S serializableObj, Class<S> classe) throws ArquivoNaoAbertoException{
		if (this.gravador == null) {
            throw new ArquivoNaoAbertoException("Erro ao gravar no arquivo");
        }
		this.gravador.format("%s\n", serializableObj.serialize());
		
	}
	
	public <S extends Serializable> void gravarLista(Collection<S> lista, Class<S> classe) throws ArquivoNaoAbertoException{
		if (this.gravador == null) {
            throw new ArquivoNaoAbertoException("Erro ao gravar no arquivo");
		}
		this.gravador.format("%d\n", lista.size());
		for(S obj : lista) {
			this.gravar(obj, classe);
		}
        
	}
	
	public void abrirPraLeitura() throws FileNotFoundException{
		this.leitor = new Scanner(new File(this.fileName));
	}
	
	public <S extends Serializable> S ler(Class<S> classe) throws Exception{
		if (this.leitor == null) {
            throw new ArquivoNaoAbertoException("Erro ao ler no arquivo");
        }
		
		String strObj = leitor.nextLine();
		
		return classe.getConstructor(String.class).newInstance(strObj);
		
	}
	public <S extends Serializable> Collection<S> lerLista(Class<S> classe) throws Exception {
		if (this.leitor == null) {
            throw new ArquivoNaoAbertoException("Erro ao ler no arquivo");
        }
	
		Collection<S> listaResultado = new ArrayList<>();
		
		
		String primeiraLinha = leitor.nextLine();
		int tamanhoLista = Integer.parseInt(primeiraLinha);
		
		
		for (int i = 0; i < tamanhoLista; i++) {
			S objetoDoArquivo = this.ler(classe);
			listaResultado.add(objetoDoArquivo);
		}
		return listaResultado;
	}
	
	public void fecharArquivo() {
		if(this.leitor != null) {
			this.leitor.close();
			this.leitor = null;
		}
		if(this.gravador != null) {
			this.gravador.close();
			this.gravador = null;
		}
	}
	
	
	
}
