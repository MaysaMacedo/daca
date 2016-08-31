package bootwildfly;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Classe que descreve a solução fornecida pelo usuario.
 * A solução é comparada com os testes para analisar sua corretude.
 *
 */

//@Entity
public class Solution {
	
	//@Id
    //@GeneratedValue
    private String id;
	
	//@Column
	private String description;
	
	//@Column
	private Map<String,String> resultInputOutput;
	
	//@Column
	private boolean isCorrect;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	private Problem problem;
	

	public Solution(Map<String, String> resultEntradaSaida) {
		super();
		this.resultInputOutput = resultEntradaSaida;
	}
	
	public String getId() {
		return id;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
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
