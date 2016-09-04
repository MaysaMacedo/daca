package bootwildfly;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
/**
 * Classe que descreve os Testes do sistema.
 * Os testes estão vinculados a um Problema, que faz uso do
 * seu atributo de expectedOutput para julgar a Solução do usuario.  
 *
 */
@Entity
public class Teste {
	
	@Id
    @GeneratedValue
    private String id;
	
	@Column
	private String name;
	
	@Column
	private String tip;
	
	@Column
	private String input;
	
	@Column
	private String expectedOutput;

	enum TEST_TYPE{test_public, test_private}
	
	public Teste(String name, String tip, String input, String expectedOutput) {
		super();
		this.name = name;
		this.tip = tip;
		this.input = input;
		this.expectedOutput = expectedOutput;
	}
	
	public Teste(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getExpectedOutput() {
		return expectedOutput;
	}

	public void setExpectedOutput(String expectedOutput) {
		this.expectedOutput = expectedOutput;
	}



}
