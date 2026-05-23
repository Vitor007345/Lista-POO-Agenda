package negocio;

import java.util.HashMap;
public class Agenda{
	private HashMap<String, Contato> contatos;
	
	public Agenda() {
		this.contatos = new HashMap<>();
	}
	
	
	
	
	public boolean incluirContato(Contato contato) {
		return this.contatos.putIfAbsent(contato.getNome(), contato) == null;
	}
	
	public boolean existeContato(String nome) {
		return this.contatos.containsKey(nome);
	}
	
	public Contato buscarContato(String nome) throws ContatoInexistenteException{
		Contato contato = this.contatos.get(nome);
		if(contato == null)throw new ContatoInexistenteException();
		return contato;
	}
	
	public Contato removerContato(String nome) throws ContatoInexistenteException{
		Contato contato = this.contatos.remove(nome);
		if(contato == null)throw new ContatoInexistenteException();
		return contato;
	}
	
}
