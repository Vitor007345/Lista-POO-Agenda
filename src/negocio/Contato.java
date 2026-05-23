package negocio;

import servicos.Serializable;

public class Contato implements Serializable{
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	
	
	public Contato(String nome, String telefone, String endereco, String email) {
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}
	
	public Contato(String serialized) {
		String[] atributos = serialized.split(";");
		this.nome = atributos[0];
		this.telefone = atributos[1];
		this.endereco = atributos[2];
		this.email = atributos[3];
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String serialize() {
		return this.getNome() + ";" + this.getTelefone() + ";" + this.getEndereco() + ";" + this.getEmail();
	}
	
	
	
	
}
