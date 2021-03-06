package bootwildfly;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Classe que descreve um Problema do Sistema.
 *
 */
@Entity
public class Problem {
	
	@Id
    @GeneratedValue
    private String id;

	@Column
	@NotBlank
	private String name;
	
	@Column
	@NotBlank
	private String description;
	
	@Column
	@NotBlank
	private String code;
	
	@Column
	@NotBlank
	private String tip;
	
	@Column
	@OneToMany(cascade = CascadeType.ALL)
	private List<Teste> tests;
	

	public Problem(String name, String descritption, String code, String tip ) {
		super();
		this.name = name;
		this.description = descritption;
		this.code = code;
		this.tip = tip;

	}

	public Problem(){

	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descricao) {
		this.description = descricao;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String codigo) {
		this.code = codigo;
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public List<Teste> getTests() {
		return tests;
	}

	public void setTests(List<Teste> tests) {
		this.tests = tests;
	}
	
	public void addTeste(Teste test) {
		this.tests.add(test);
	}

}
