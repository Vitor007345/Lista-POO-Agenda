package negocio;

public class ContatoInexistenteException extends Exception{
	public ContatoInexistenteException() {
		super();
	}
	public ContatoInexistenteException(String msg) {
		super(msg);
	} 
}
