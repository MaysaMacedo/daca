package bootwildfly;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import junit.framework.Test;

/**
 * Classe que descreve as requisições REST do sistema.
 *
 */
@RestController
public class ProblemController {
	
	/**
	 * Faz uma consulta de todos os problemas do sistema.
	 * @return 
	 * 		String JSON com todos os problemas do sistemas publicos ao usuario.
	 */
    @RequestMapping(value="/problem", method=RequestMethod.GET)
	public String getProblems(){
    	return "Get some problems";
	}
    
    /**
     * Adiciona um problema à lista de problemas do usuário.
     * @param problema
     * 		Objeto que descreve o problema
     * @return
     * 		String JSON informando se a inserção foi bem sucedida.
     * 		
     */
    @RequestMapping(value="/problem", method=RequestMethod.POST)
	public String addProblem(@RequestBody Problem problema){
    	return "Problem added successfully";
	}
    
    /**
     * Recupera um problema dado seu identificador.
     * @param problemid
     * 		Numero identificador do problema.
     * @return
     * 		String JSON com informações sobre o problema requerido.
     */
    @RequestMapping(value="/problem/{problemid}", method=RequestMethod.GET)
	public String getProblem(@PathVariable String problemid){
    	return "Get a specific problem with id = "+ problemid;
	}
    
    /**
     * Modifica um problema dado seu identificador.
     * @param problemid
     * 		Numero identificador do problema.
     * @param problema
     * 		Objeto que descreve o problema
     * @return
     * 		String JSON informando se o problema em questão foi modificado.
     */
    @RequestMapping(value="/problem/{problemid}", method=RequestMethod.PUT)
	public String modifyProblem(@PathVariable String problemid, @RequestBody Problem problema){
    	return "Problem with id "+problemid+" modified";
	}
    
    /**
     * Recupera todos os testes no sistema que são visíveis ao usuario.
     * @param problemid
     * 		Numero identificador do problema.
     * @return
     * 		String JSON com todos os testes visíveis ao usuario.
     */
    @RequestMapping(value="/problem/{problemid}/test", method=RequestMethod.GET)
	public String getTests(@PathVariable String problemid){
    	return "Get some tests from problem = " + problemid;
	}
    
    /**
     * Adiciona teste a um dado problema.
     * @param problem
     * 		Objeto que descreve o problema a ser modificado.
     * @param teste
     * 		Objeto que descreve o teste a ser inserido.
     * @return
     * 		String JSON informando se a inserção foi bem sucedida.
     */
    @RequestMapping(value="/problem/{problemid}/test", method=RequestMethod.POST)
	public String addTeste(@RequestBody Problem problem, @RequestBody Teste teste){
    	return "New teste added to problem "+problem.getCodigo();
	}
    
    /**
     * Recupera um teste dado seu identificador e o do seu respectivo problema.
     * @param problemid
     * 		Identificador do problema em questão.
     * @param testid
     * 		Identificador do teste em questão.
     * @return
     * 		String JSON descrevendo o teste requerido.
     */
    @RequestMapping(value="/problem/{problemid}/test/{testid}", method=RequestMethod.GET)
	public String getTeste(@PathVariable String problemid,@PathVariable String testid){
    	return " Get a teste = "+ testid + " from problem = " + problemid;
	}
    
    /**
     * Modifica um teste vinculado a um problema.
     * @param problem
     * 		Objeto que descreve o problema em questão.
     * @param test
     * 		Objeto que descrve o teste em questão
     * @return
     */
    @RequestMapping(value="/problem/{problemid}/test/{testid}", method=RequestMethod.PUT)
	public String modifyTeste(@PathVariable Problem problem,@PathVariable Teste test){
    	return " Modify a teste = "+ test.toString() + "from problem = " + problem.getCodigo();
	}
    
    /**
     * Adiciona um a solução a um problema.
     * @param problema
     * 		Objeto que descreve o problema em questão.
     * @param solucao
     * 		Objeto que descreve a solução em questão.
     * @return
     * 		String JSON informando se a operação foi bem sucedida.
     */
    @RequestMapping(value="/problem/{problemid}/solution", method=RequestMethod.POST)
	public String addSolution(@RequestBody Problem problema, @RequestBody Solution solucao){
    	return " Add a solution";
	}
    
    /**
     * Consulta todas as soluções de um problema.
     * @param problemid
     * 		Identificador do problema em questão.
     * @return
     * 		String JSON descrevendo todas as soluções daquele problema.
     */
    @RequestMapping(value="/problem/{problemid}/solution", method=RequestMethod.GET)
	public String getSolutions(@PathVariable String problemid){
    	return " Get all solutions";
	}
    
    /**
     * Consulta uma solução específica de um problema.
     * @param problemid
     * 		Identificador de um problema.
     * @param solutionId
     * 		Identificador de uma solução
     * @return
     * 		String JSON descrevendo a solução requerida.
     */
    @RequestMapping(value="/problem/{problemid}/solution/{solutionId}", method=RequestMethod.GET)
	public String getSolution(@PathVariable String problemid,@PathVariable String solutionId){
    	return " Get a solution = "+ solutionId + " from problem = " + problemid;
	}

}
