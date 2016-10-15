package bootwildfly;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kafka.Application;

@Service
public class SolutionService {
	
	@Autowired
	SolutionRepository solutionRepo;

	@Autowired
	ProblemService problemServ;
	
	@Transactional
	public Solution addSolution(String problemid, Solution solution) {
		solution.setProblem(problemid);
		return solutionRepo.save(solution);
	}

	public List<Solution> getSolutions(String problemid) {
		return solutionRepo.findByProblemid(problemid);
	}

	public Solution getSolution(String solutionId) {		
		return solutionRepo.findOne(solutionId);
	}

	public Long countSolved() {
		Long totalSolved = 0L;
		Iterable<Problem> allProblems = problemServ.getAllProblems(100, 0);
		for (Iterator iterator = allProblems.iterator(); iterator.hasNext();) {
			Problem problema = (Problem) iterator.next();
			String problemId = problema.getId();
			List <Solution> allSolutions = this.getSolutions(problemId);
			for (Iterator iterator2 = allSolutions.iterator(); iterator2.hasNext();) {
				Solution solution = (Solution) iterator2.next();
				if (solution.isCorrect())
				{
					totalSolved++;
					break;//next problem
				}
			}
		}
		return totalSolved;
	}
	
	/**
	 * Send a Solution to Kafka
	 * @param solution
	 */
	public void sendToKafka(Solution solution) {
		ConfigurableApplicationContext context
		= new SpringApplicationBuilder(Application.class)
		.web(false)
		.run();
		MessageChannel toKafka = context.getBean("toKafka", MessageChannel.class);
		for (int i = 0; i < 10; i++) {
			toKafka.send(new GenericMessage<>(solution.toString()));
		}
		toKafka.send(new GenericMessage<>(KafkaNull.INSTANCE));

		context.close();
	}
}
