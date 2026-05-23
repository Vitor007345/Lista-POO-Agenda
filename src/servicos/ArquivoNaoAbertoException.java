package servicos;

public class ArquivoNaoAbertoException extends Exception{
	public ArquivoNaoAbertoException() {
		super();
	}
	public ArquivoNaoAbertoException(String msg) {
		super(msg);
	}
}
