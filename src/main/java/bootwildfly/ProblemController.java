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

import io.swagger.annotations.*;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;



/**
 * Classe que descreve as requisições REST do sistema.
 *
 */
@Api(value="problem", description="Operações sobre Problem")
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
	
    
    @RequestMapping(value="/problem", method=RequestMethod.GET)
    @ApiOperation(value = "Retorna todos os problemas do sistema", notes = "Retorna todos os problemas do sistema")
	public Page<Problem> getProblems( @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
            @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
    	return problemService.getAllProblems(page, size);
	}
    
    @ApiImplicitParams({
        @ApiImplicitParam(
        	name = "name", value = "Nome do Problema", required = true, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "description", value = "Descrição do Problema", required = true, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "code", value = "Código do Problema", required = true, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "tip", value = "Dica do Problema", required = true, 
            	dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "tests", value = "Testes do Problema", required = false, 
            	dataType = "array of objects {}", paramType = "body", 
            	defaultValue = "[{desc: “teste ex”,tip: “dica ex”,code: “codigo ex”}]")
      })
    @RequestMapping(value="/problem", method=RequestMethod.POST)
    @ApiOperation(value = "Salva um problema no Sistema.")
	public ResponseEntity<?> addProblem(@Valid @RequestBody Problem problem,
			BindingResult bindingResult ) {
    	if(bindingResult.hasErrors()){
    		return ResponseEntity.badRequest().body("Invalid object");
    	} else {
    		return ResponseEntity.ok(problemService.addProblem(problem));
    	}
    }
    
    
    @ApiOperation(value = "Retorna um problema pelo seu Id")
    @RequestMapping(value="/problem/{problemid}", method=RequestMethod.GET)
	public Problem getProblem(@PathVariable String problemid){ 
    	Problem pro = problemService.getProblem(problemid);
    	return pro;
	}
    
    @ApiImplicitParams({
        @ApiImplicitParam(
        	name = "name", value = "Novo nome do Problema", required = false, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "desc", value = "Nova Descrição do Problema", required = false, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "tip", value = "Nova dica do Problema", required = false, 
            	dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "tests", value = "New problem's test(s)", required = false, 
            	dataType = "array of objects {}", paramType = "body", 
            	defaultValue = "[{desc: “teste 1”,tip: “dica 1”,input: “entrada 1”,output: “saida 1”}]")
      })	
    @RequestMapping(value="/problem/{problemid}", method=RequestMethod.PUT)
    @ApiOperation(value = "Atualiza um problema pelo seu Id")
	public Problem modifyProblem(@PathVariable String problemid, @RequestBody Problem problem){
    	problem.setId(problemid);
    	return problemService.updateProblem(problem);   	
	}
    
    @RequestMapping(value="/problem/{problemid}/test", method=RequestMethod.GET )
    @ApiOperation(value = "Retorna todos os Testes de um Problema do sistema", notes = "Retorna todos os Testes de um Problema do sistema")
	public ResponseEntity<?> getTests(@PathVariable String problemid){
    	
    	if(problemService.getProblem(problemid) == null)
    		return new ResponseEntity<>("Problem "+problemid+" not found", HttpStatus.NOT_FOUND);
    	else {
    		return ResponseEntity.ok(testService.getTests(problemid));
    	}
	}
    
    @ApiImplicitParams({
        @ApiImplicitParam(
        	name = "name", value = "Nome do Teste", required = true, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "tip", value = "Dica do Teste", required = true, 
            	dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "input", value = "Entrada do Teste", required = true, 
            	dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "expectedOutput", value = "Saida esperada do Teste", required = true, 
            	dataType = "string", paramType = "body")
      })
    @RequestMapping(value="/problem/{problemid}/test", method=RequestMethod.POST)
    @ApiOperation(value = "Retorna todos os Testes de um Problema do sistema", notes = "Retorna todos os Testes de um Problema do sistema")
	public ResponseEntity<?>  addTeste(@PathVariable String problemid,@Valid @RequestBody Teste teste,
			BindingResult bindingResult){

    	if(bindingResult.hasErrors()){
    		return ResponseEntity.badRequest().body("Invalid object");
    	} else {
    		return ResponseEntity.ok(testService.addTest(teste, problemid));
    		}
    	}
    	

    
    @RequestMapping(value="/problem/{problemid}/test/{testid}", method=RequestMethod.GET)
    @ApiOperation(value = "Retorna um determinado Teste de um Problema do sistema", notes = "Retorna um determinado Teste de um Problema do sistema")
	public ResponseEntity<?>  getTeste(@PathVariable String problemid,@PathVariable String testid){
    	if(problemService.getProblem(problemid) == null)
    		return new ResponseEntity<>("Problem "+problemid+" not found", HttpStatus.NOT_FOUND);
    	else {
    		return ResponseEntity.ok(testService.getTest(testid));
    	}
    	

	}
    
    @ApiImplicitParams({
        @ApiImplicitParam(
        	name = "name", value = "Novo Nome do Teste", required = true, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "tip", value = "Nova Dica do Teste", required = true, 
            	dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "input", value = "Nova Entrada do Teste", required = true, 
            	dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "expectedOutput", value = "Nova Saida esperada do Teste", required = true, 
            	dataType = "string", paramType = "body")
      })
    @RequestMapping(value="/problem/{problemid}/test/{testid}", method=RequestMethod.PUT)
    @ApiOperation(value = "Atualiza um determinado Teste de um Problema do sistema", notes = "Atualiza um determinado Teste de um Problema do sistema")
	public Teste modifyTeste(@PathVariable Problem problemid,@PathVariable String testid,@RequestBody Teste test){
    	test.setId(testid);
    	return testService.updateTest(test);
	}
    

	@ApiImplicitParams({
			@ApiImplicitParam(
					name = "body", value = "Código da Solução", required = true,
					dataType = "string", paramType = "body"),
			@ApiImplicitParam(
					name = "outputs", value = "Saidas da Solução", required = true,
					dataType = "string", paramType = "body"),
			@ApiImplicitParam(
					name = "isCorrect", value = "Flag indicando se a solução está correta", required = true,
					dataType = "string", paramType = "body")
	})
    @RequestMapping(value="/problem/{problemid}/solution", method=RequestMethod.POST)
	@ApiOperation(value = "Adiciona uma solução de um problema no Sistema", notes = "Adiciona uma solução de um problema no Sistema")
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
    
    @RequestMapping(value="/problem/{problemid}/solution", method=RequestMethod.GET)
    @ApiOperation(value = "Retorna todas as soluções de um problema", notes = "Retorna todas as soluções de um problema")
	public ResponseEntity<?> getSolutions(@PathVariable String problemid){
    	if(problemService.getProblem(problemid) == null)
    		return new ResponseEntity<>("Problem "+problemid+" not found", HttpStatus.NOT_FOUND);
    	else {
    		return ResponseEntity.ok(solutionService.getSolutions(problemid));
    	}
	}
    
    @RequestMapping(value="/problem/{problemid}/solution/{solutionId}", method=RequestMethod.GET)
    @ApiOperation(value = "Retorna uma solução específica de um problema", notes = "Retorna uma solução específica de um problema")
	public ResponseEntity<?> getSolution(@PathVariable String problemid,@PathVariable String solutionId){
    	if(problemService.getProblem(problemid) == null)
    		return new ResponseEntity<>("Problem "+problemid+" not found", HttpStatus.NOT_FOUND);
    	else {
    		return ResponseEntity.ok(solutionService.getSolution(solutionId));
    	}
	}
    
    @RequestMapping(value="/statistics", method=RequestMethod.GET)
    @ApiOperation(value = "Retorna o numero de problemas resolvidos", notes = "Retorna o numero de problemas resolvidos")
    public String getStatistics(){
    	Long totalSolved = solutionService.countSolved();
    	return totalSolved.toString();
    }
    
}