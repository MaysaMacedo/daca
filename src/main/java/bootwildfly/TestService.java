package bootwildfly;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
	
	@Autowired
	TestRepository testRepo;
	
	@Autowired
	ProblemService problemServ;
	
	@Transactional
	public Teste addTest(Teste test, String id) {
		Problem pro = problemServ.getProblem(id);
		Teste testinho = testRepo.save(test);
		pro.addTeste(testinho);
		problemServ.updateProblem(pro);
		return testinho;
	}

	public List<Teste> getTests(String problemid) {
		Problem pro = problemServ.getProblem(problemid);
		return pro.getTests();
	}

	public Teste getTest(String testid) {
		return testRepo.findOne(testid);
	}

	@Transactional
	public Teste updateTest(Teste test) {
		Teste oldTest = testRepo.findOne(test.getId());
		
		oldTest.setName(test.getName());
		oldTest.setInput(test.getInput());
		oldTest.setTip(test.getTip());
		oldTest.setExpectedOutput(test.getExpectedOutput());
		
		return testRepo.save(oldTest);
	}

}
