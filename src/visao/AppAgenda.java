package visao;

import menu.Menu;
import negocio.*;
import servicos.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class AppAgenda {
	
	
	public static Agenda agenda = null;
	public static ManipulaArquivoTexto manipulaArquivo = new ManipulaArquivoTexto("contatos.txt");
	public static Scanner sc = new Scanner(System.in);
	
	
	public static void carregarAgenda() {
		try {
			manipulaArquivo.abrirPraLeitura();
			agenda = new Agenda(manipulaArquivo.lerLista(Contato.class));
			
		}catch(FileNotFoundException e) {
			agenda = new Agenda();
			System.out.println("Arquivo de contatos não encontrado");
		}catch(Exception e) {
			System.out.println("Erro inesperado ao carregar agenda\n" + e.getMessage());
			e.printStackTrace();
		}finally {
			manipulaArquivo.fecharArquivo();
		}
	}
	
	public static boolean salvar() {
		try {
			manipulaArquivo.abrirPraGravacao();
			manipulaArquivo.gravarLista(agenda.getContatos(), Contato.class);
			System.out.println("Salvo com sucesso");
			return true;
		}catch(FileNotFoundException e) {
			System.out.println("Erro ao salvar, arquivo não encontrado ou erro na criação do arquivo");
			System.out.println(e.getMessage());
			return false;
		}catch(ArquivoNaoAbertoException e) {
			System.out.println("Erro inesperado ao salvar\n" + e.getMessage());
			e.printStackTrace();
			return false;
		}finally {
			manipulaArquivo.fecharArquivo();
		}
	}
	
	
	
	public static Contato cadastrarContato() {
		System.out.println("Digite o nome:");
		String nome = sc.nextLine();
		
		System.out.println("Digite o telefone:");
		String telefone = sc.nextLine();
		
		System.out.println("Digite o endereco:");
		String endereco = sc.nextLine();
		
		System.out.println("Digite o email:");
		String email = sc.nextLine();
		
		return new Contato(nome, telefone, endereco, email);
	}
	
	

	public static void main(String[] args) {
		carregarAgenda();
		if(agenda != null) {
			Menu mainMenu = new Menu("Agenda");
			
			boolean[] sair = new boolean[]{false};
			
			mainMenu.addOption("Sair", ()->{
				if(salvar()) {
					sair[0] = true;
				}else {
					System.out.println("Deseja sair sem salvar?[S/N]");
					String userResponse = sc.nextLine().toUpperCase();
					if(userResponse.equals("S")) {
						sair[0] = true;
					}
				}
				
			});
			
			mainMenu.addOption("Listar contatos", ()->{
				System.out.println("\nContatos:\n");
				System.out.println(agenda);
			});
			
			mainMenu.addOption("Adicionar Contato", ()->{
				agenda.incluirContato(cadastrarContato());
			});
			
			mainMenu.addOption("Remover Contato", ()->{
				System.out.println("Digite o nome do contato que quer remover");
				try {
					Contato c =agenda.removerContato(sc.nextLine());
					System.out.println("Contato removido com sucesso - " + c.toString());
				}catch(ContatoInexistenteException e) {
					System.out.println("Contato não encontrado");
				}
			});
			
			mainMenu.addOption("Buscar Contato", ()->{
				System.out.println("Digite o nome do contato que quer buscar");
				try {
					System.out.println(agenda.buscarContato(sc.nextLine()));
				}catch(ContatoInexistenteException e) {
					System.out.println("Contato não encontrado");
				}
			});
			
			mainMenu.addOption("Salvar manualmente", ()->{salvar();});
			
			
			while(sair[0] == false) {
				mainMenu.start();
			}
			
		}
	}

}
