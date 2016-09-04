package bootwildfly;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe que descreve a solução fornecida pelo usuario.
 * A solução é comparada com os testes para analisar sua corretude.
 *
 */

@Entity
public class Solution {
	
	@Id
    @GeneratedValue
    private String id;
	
	@Column
	@NotBlank
	private String body;
	
	@ElementCollection
	@Column
	private List<String> outputs;
	
	@Column
	private boolean isCorrect;
	
//	@ManyToOne()
//    @JoinColumn(name = "problem_id")
	@Column
	private String problemid;

	public String getProblem() {
		return problemid;
	}

	public void setProblem(String problem) {
		this.problemid = problem;
	}

	public Solution(String body, List<String> outputs ) {
		super();
		this.body = body;
		this.outputs = outputs;
	}
	
	public Solution() {
		
	}
	
	public String getId() {
		return id;
	}

//	public Problem getProblem() {
//		return problem;
//	}
//
//	public void setProblem(Problem problem) {
//		this.problem = problem;
//	}
	

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<String> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<String> outputs) {
		this.outputs = outputs;
	}

	public void setId(String id) {
		this.id = id;
	}


}
