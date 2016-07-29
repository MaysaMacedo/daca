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
	public Problem addProblem(@RequestBody Problem resource){
    	return new Problem("nome", "descricao", "codigo");
	}
    
    @RequestMapping(value="/problem/{problemid}", method=RequestMethod.GET)
	public String getProblem(@PathVariable String problemid){
    	return "Get a specific problem with id = "+ problemid;
	}
    
    @RequestMapping(value="/problem/{problemid}", method=RequestMethod.PUT)
	public Problem modifyProblem(@PathVariable String problemid, @RequestBody Problem resource){
    	return new Problem("nome", "descricao", "codigo");
	}
    
    @RequestMapping(value="/problem/{problemid}/test", method=RequestMethod.GET)
	public String getTestes(@PathVariable String problemid){
    	return "Get some tests from problem =" + problemid;
	}
    
    @RequestMapping(value="/problem/{problemid}/test", method=RequestMethod.POST)
	public String addTest(@RequestBody String resource){
    	return "New teste added "+ resource;
	}
    
    @RequestMapping(value="/problem/{problemid}/test/{testid}", method=RequestMethod.PUT)
	public String modifyTest(@PathVariable String problemid,@PathVariable String testid, @RequestBody String resource){
    	return " Modify a teste = "+ testid + "from problem = " + problemid;
	}
    
    @RequestMapping(value="/problem/{problemid}/solution", method=RequestMethod.POST)
	public String addSolution(@RequestBody String resource){
    	return " Add a solution";
	}
    
    @RequestMapping(value="/problem/{problemid}/solution", method=RequestMethod.GET)
	public String getSolutions(@PathVariable String problemid){
    	return " Get all solutions";
	}

}
