package bootwildfly;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	@Autowired
	TestRepository testRepo;
	
	@Autowired
	ProblemService problemServ;
	
	public void addTest(Teste test, String id) {
		Problem pro = problemServ.getProblem(id);
		test.setProblem(pro);
		testRepo.save(test);
	}

	public List<Teste> getTests(String problem) {
		Problem pro = problemServ.getProblem(problem);
		return testRepo.findByProblem(pro);
	}

	public Teste getTest(String testid) {
		return testRepo.findOne(testid);
	}

	public Teste updateTest(Teste test) {
		Teste oldTest = testRepo.findOne(test.getId());
		
		oldTest.
		setName(test.getName());
		oldTest.setProblem(test.getProblem());
		oldTest.setInput(test.getInput());
		oldTest.setTip(test.getTip());
		oldTest.setExpectedOutput(test.getExpectedOutput());
		
		return testRepo.save(oldTest);
		
	}

}
