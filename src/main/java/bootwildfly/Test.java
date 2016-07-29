package bootwildfly;

public class Test {
	
	private String name;
	private String tip;
	private String input;
	private String expectedOutput;
	
	public Test(String nome, String dica, String entrada, String saidaEsperada) {
		super();
		this.name = nome;
		this.tip = dica;
		this.input = entrada;
		this.expectedOutput = saidaEsperada;
	}
	
	public Test(){
		
	}

}
