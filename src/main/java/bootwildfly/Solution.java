package bootwildfly;

import java.util.Map;

/**
 * Classe que descreve a solução fornecida pelo usuario.
 * A solução é comparada com os testes para analisar sua corretude.
 *
 */
public class Solution {
	private String description;
	private Map<String,String> resultInputOutput;
	
	public Solution(Map<String, String> resultEntradaSaida) {
		super();
		this.resultInputOutput = resultEntradaSaida;
	}

}
