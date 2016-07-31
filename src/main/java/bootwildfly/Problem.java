package bootwildfly;

/**
 * Classe que descreve um Problema do Sistema.
 *
 */
public class Problem {
	private String name;
	private String description; 
	private String code; 

	public String getNome() {
		return name;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	public String getDescricao() {
		return description;
	}

	public void setDescricao(String descricao) {
		this.description = descricao;
	}

	public String getCodigo() {
		return code;
	}

	public void setCodigo(String codigo) {
		this.code = codigo;
	}

	public Problem(String nome, String descricao, String codigo) {
		super();
		this.name = nome;
		this.description = descricao;
		this.code = codigo;

	}
	
	public Problem(){
		
	}


}
