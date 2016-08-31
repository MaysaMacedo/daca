package bootwildfly;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
	private Problem problem;
	
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

}
