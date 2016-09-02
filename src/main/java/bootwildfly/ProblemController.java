package bootwildfly;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;



/**
 * Classe que descreve as requisições REST do sistema.
 *
 */
@RestController
public class ProblemController {
	
	@Autowired
	private ProblemService problemService;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private SolutionService solutionService;
	
	protected static final String  DEFAULT_PAGE_SIZE = "100";
    protected static final String DEFAULT_PAGE_NUM = "0";
	
	
	/**
	 * Faz uma consulta de todos os problemas do sistema.
	 * @return 
	 * 		String JSON com todos os problemas do sistemas publicos ao usuario.
	 */
    @RequestMapping(value="/problem", method=RequestMethod.GET)
	public Page<Problem> getProblems( @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
            @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
    	return problemService.getAllProblems(page, size);
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
	public String addProblem(@RequestBody Problem problem ) {
        
        problemService.addProblem(problem);

        return "creation successful: " + String.valueOf(problem.getId());
	}
    
    /**
     * Recupera um problema dado seu identificador.
     * @param problemid
     * 		Numero identificador do problema.
     * @return
     * 		String JSON com informações sobre o problema requerido.
     */
    @RequestMapping(value="/problem/{problemid}", method=RequestMethod.GET)
	public Problem getProblem(@PathVariable String problemid){
    	return problemService.getProblem(problemid);
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
	public void modifyProblem(@PathVariable String problemid, @RequestBody Problem problem){
    	problem.setId(problemid);
    	problemService.updateProblem(problem);   	
	}
    
    /**
     * Recupera todos os testes no sistema que são visíveis ao usuario.
     * @param problemid
     * 		Numero identificador do problema.
     * @return
     * 		String JSON com todos os testes visíveis ao usuario.
     */
    @RequestMapping(value="/problem/{problemid}/test", method=RequestMethod.GET )
	public List<Teste> getTests(@PathVariable String problemid){
    	return testService.getTests(problemid);
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
	public String addTeste(@PathVariable String problemid, @RequestBody Teste teste){
    	testService.addTest(teste, problemid);
    	return "New teste added to problem "+ problemid;
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
	public Teste getTeste(@PathVariable String problemid,@PathVariable String testid){
    	return testService.getTest(testid);

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
	public Teste modifyTeste(@PathVariable Problem problemid,@PathVariable String testid,@RequestBody Teste test){
    	test.setId(testid);
    	return testService.updateTest(test);
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
	public String addSolution(@PathVariable String problemid, @RequestBody Solution solution){
    	 solutionService.addSolution(problemid, solution);
    	 return "New solution added to problem" + problemid;
	}
    
    /**
     * Consulta todas as soluções de um problema.
     * @param problemid
     * 		Identificador do problema em questão.
     * @return
     * 		String JSON descrevendo todas as soluções daquele problema.
     */
    @RequestMapping(value="/problem/{problemid}/solution", method=RequestMethod.GET)
	public List<Solution> getSolutions(@PathVariable String problemid){
    	return solutionService.getSolutions(problemid);
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
	public Solution getSolution(@PathVariable String problemid,@PathVariable String solutionId){
    	return solutionService.getSolution(solutionId);
	}
    
    @RequestMapping(value="/statistics", method=RequestMethod.GET)
    public String getStatistics(){
    	return "Get all Statistics";
    }

}
