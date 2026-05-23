/**********************************************************************************************
 * Cansei de fazer menu toda vez então fiz esse packged com essas classes em que a principal é essa
 * que é o Menu pra nunca mais prescisar gastar tempo codificando menu da aplicação pra testes.
 * Pretendo tranformar isso em uma lib um dia
***********************************************************************************************/


package menu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
	
	private static Scanner sc = new Scanner(System.in);
	
	private String title;
	private ArrayList<Option> options;
		
	public Menu(String title) {
		this.title = title;
		this.options = new ArrayList<>();
	}
	public Menu() {
		this(null);
	}
	
	//setters e getters
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	//Option methods
	public boolean addOption(Option option) {
		return this.options.add(option);
	}
	
	public boolean addOption(String text, Func function) {
		return this.addOption(new Option(text, function));
	}
	
	public boolean addOption(String text, Menu otherMenu) {
		return this.addOption(new Option(text, ()->otherMenu.start()));
	}
	
	public Option removeOption(int index) {
		return this.options.remove(index);
	}
	
	public boolean removeOption(String text) {
		return this.options.removeIf(op -> op.getText().equals(text));
	}
	
	private void runOption(int index) {
		this.options.get(index).run();
	}
	
	
	//menu methods
	
	public String getMenuStr() {
		StringBuilder sb = new StringBuilder();
		if(this.getTitle() != null) {
			sb.append("=============").append(this.getTitle()).append("=============\n");
		}else {
			sb.append("=================\n");
		}
		
		int i = 0;
		for(Option op : this.options) {
			sb.append(i).append(" - ").append(op.getText()).append("\n");
			i++;
		}
		sb.append("Escolha uma opção: ");
		return sb.toString();
	}
	
	public void start() {
		boolean invalido;
		do {
			System.out.println(this.getMenuStr());
			try {
	            int userResponse = sc.nextInt();
	            sc.nextLine(); //clean buffer

	            if (userResponse >= 0 && userResponse < this.options.size()) {
	                this.runOption(userResponse);
	                invalido = false;
	            } else {
	                System.out.print("Essa opção não existe!\nDigite novamente: ");
	                invalido = true;
	            }
	        } catch (InputMismatchException e) {
	            System.out.print("Erro: Digite apenas números!\nDigite novamente: ");
	            sc.nextLine(); //clean buffer
	            invalido = true;
	        }
			
		}while(invalido);
		
	}
	
	
	
	
	
	
	
	
		
		
}
