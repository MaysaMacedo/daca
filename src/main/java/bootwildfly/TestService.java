package bootwildfly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	@Autowired
	TestRepository testRepo;
	
	public void addTest(Teste test) {
		testRepo.save(test); 
	}

	public Teste getTests(Integer page, Integer size) {
		return new Teste();
		//return testRepo.findAll(new PageRequest(page,size));
	}

}
