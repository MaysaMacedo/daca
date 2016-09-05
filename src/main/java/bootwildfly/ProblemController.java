package bootwildfly;

import java.util.List;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;



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
	public ResponseEntity<?> addProblem(@Valid @RequestBody Problem problem,
			BindingResult bindingResult ) {
    	if(bindingResult.hasErrors()){
    		return ResponseEntity.badRequest().body("Invalid object");
    	} else {
    		return ResponseEntity.ok(problemService.addProblem(problem));
    	}
    }
    
    
    /**
     * Recupera um problema dado seu identificador.
     * @param problemid
     * 		Numero identificador do problema.
     * @return
     * 		String JSON com informações sobre o problema requerido.
     * @throws ProblemNotFoundException 
     */
    @RequestMapping(value="/problem/{problemid}", method=RequestMethod.GET)
	public Problem getProblem(@PathVariable String problemid){ 
    	Problem pro = problemService.getProblem(problemid);
    	return pro;
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
	public Problem modifyProblem(@PathVariable String problemid, @RequestBody Problem problem){
    	problem.setId(problemid);
    	return problemService.updateProblem(problem);   	
	}
    
    /**
     * Recupera todos os testes no sistema que são visíveis ao usuario.
     * @param problemid
     * 		Numero identificador do problema.
     * @return
     * 		String JSON com todos os testes visíveis ao usuario.
     */
    @RequestMapping(value="/problem/{problemid}/test", method=RequestMethod.GET )
	public ResponseEntity<?> getTests(@PathVariable String problemid){
    	
    	if(problemService.getProblem(problemid) == null)
    		return new ResponseEntity<>("Problem "+problemid+" not found", HttpStatus.NOT_FOUND);
    	else {
    		return ResponseEntity.ok(testService.getTests(problemid));
    	}
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
	public ResponseEntity<?>  addTeste(@PathVariable String problemid,@Valid @RequestBody Teste teste,
			BindingResult bindingResult){

    	if(bindingResult.hasErrors()){
    		return ResponseEntity.badRequest().body("Invalid object");
    	} else {
    		return ResponseEntity.ok(testService.addTest(teste, problemid));
    		}
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
	public ResponseEntity<?>  getTeste(@PathVariable String problemid,@PathVariable String testid){
    	if(problemService.getProblem(problemid) == null)
    		return new ResponseEntity<>("Problem "+problemid+" not found", HttpStatus.NOT_FOUND);
    	else {
    		return ResponseEntity.ok(testService.getTest(testid));
    	}
    	

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
	public ResponseEntity<?> addSolution(@PathVariable String problemid,@Valid @RequestBody Solution solution,
			BindingResult bindingResult){
    	if(bindingResult.hasErrors()){
    		return ResponseEntity.badRequest().body("Invalid object");
    	} else if(problemService.getProblem(problemid) == null) {
    		return new ResponseEntity<>("Problem "+problemid+" not found", HttpStatus.NOT_FOUND);
    	} else {
    		
    		return ResponseEntity.ok(solutionService.addSolution(problemid, solution));
    	}
	}
    
    /**
     * Consulta todas as soluções de um problema.
     * @param problemid
     * 		Identificador do problema em questão.
     * @return
     * 		String JSON descrevendo todas as soluções daquele problema.
     */
    @RequestMapping(value="/problem/{problemid}/solution", method=RequestMethod.GET)
	public ResponseEntity<?> getSolutions(@PathVariable String problemid){
    	if(problemService.getProblem(problemid) == null)
    		return new ResponseEntity<>("Problem "+problemid+" not found", HttpStatus.NOT_FOUND);
    	else {
    		return ResponseEntity.ok(solutionService.getSolutions(problemid));
    	}
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
	public ResponseEntity<?> getSolution(@PathVariable String problemid,@PathVariable String solutionId){
    	if(problemService.getProblem(problemid) == null)
    		return new ResponseEntity<>("Problem "+problemid+" not found", HttpStatus.NOT_FOUND);
    	else {
    		return ResponseEntity.ok(solutionService.getSolution(solutionId));
    	}
	}
    
    @RequestMapping(value="/statistics", method=RequestMethod.GET)
    public String getStatistics(){
    	return "Get all Statistics";
    }
    
}