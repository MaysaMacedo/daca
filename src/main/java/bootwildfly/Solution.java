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
	private boolean isCorrect;
	
	public Solution(Map<String, String> resultEntradaSaida) {
		super();
		this.resultInputOutput = resultEntradaSaida;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getResultInputOutput() {
		return resultInputOutput;
	}

	public void setResultInputOutput(Map<String, String> resultInputOutput) {
		this.resultInputOutput = resultInputOutput;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}


}
