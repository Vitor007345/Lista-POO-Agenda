package menu;

public class Option {
	private String text;
	private Func function;
	
	public Option(String text, Func function) {
		this.text = text;
		this.function = function;
	}

	public String getText() {
		return text;
	}

	public Func getFunction() {
		return function;
	}
	
	public void run() {
		this.function.run();
	}
	
	
}
