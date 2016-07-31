package bootwildfly;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProblemController {
	
    @RequestMapping(value="/problem", method=RequestMethod.GET)
	public String getProblems(){
    	return "Get some problems";
	}
    
    @RequestMapping(value="/problem", method=RequestMethod.POST)
	public String addProblem(@RequestBody Problem resource){
    	return "Problem added successfully";
	}
    
    @RequestMapping(value="/problem/{problemid}", method=RequestMethod.GET)
	public String getProblem(@PathVariable String problemid){
    	return "Get a specific problem with id = "+ problemid;
	}
    
    @RequestMapping(value="/problem/{problemid}", method=RequestMethod.PUT)
	public String modifyProblem(@PathVariable String problemid, @RequestBody Problem resource){
    	return "Problem with id "+problemid+" modified";
	}
    
    @RequestMapping(value="/problem/{problemid}/test", method=RequestMethod.GET)
	public String getTests(@PathVariable String problemid){
    	return "Get some tests from problem = " + problemid;
	}
    
    @RequestMapping(value="/problem/{problemid}/test", method=RequestMethod.POST)
	public String addTeste(@RequestBody Problem problem, @RequestBody Teste teste){
    	return "New teste added to problem "+problem.getCodigo();
	}
    
    @RequestMapping(value="/problem/{problemid}/test/{testid}", method=RequestMethod.GET)
	public String getTeste(@PathVariable String problemid,@PathVariable String testid){
    	return " Get a teste = "+ testid + " from problem = " + problemid;
	}
    
    @RequestMapping(value="/problem/{problemid}/test/{testid}", method=RequestMethod.PUT)
	public String modifyTeste(@PathVariable String problemid,@PathVariable String testid, @RequestBody String resource){
    	return " Modify a teste = "+ testid + "from problem = " + problemid;
	}
    
    @RequestMapping(value="/problem/{problemid}/solution", method=RequestMethod.POST)
	public String addSolution(@RequestBody Problem problema, @RequestBody Solution solucao){
    	return " Add a solution";
	}
    
    @RequestMapping(value="/problem/{problemid}/solution", method=RequestMethod.GET)
	public String getSolutions(@PathVariable String problemid){
    	return " Get all solutions";
	}
    
    
    @RequestMapping(value="/problem/{problemid}/solution/{solutionId}", method=RequestMethod.GET)
	public String getSolution(@PathVariable String problemid,@PathVariable String solutionId){
    	return " Get a solution = "+ solutionId + " from problem = " + problemid;
	}

}
