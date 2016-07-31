package bootwildfly;

/**
 * Classe que descreve os Testes do sistema.
 * Os testes estão vinculados a um Problema, que faz uso do
 * seu atributo de expectedOutput para julgar a Solução do usuario.  
 *
 */
public class Teste {
	
	private String name;
	private String tip;
	private String input;
	private String expectedOutput;
	
	public Teste(String nome, String dica, String entrada, String saidaEsperada) {
		super();
		this.name = nome;
		this.tip = dica;
		this.input = entrada;
		this.expectedOutput = saidaEsperada;
	}
	
	public Teste(){
		
	}

	

}
