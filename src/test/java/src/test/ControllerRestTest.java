package src.test;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.context.WebApplicationContext;

import com.jayway.restassured.http.ContentType;

import bootwildfly.Application;
import bootwildfly.Problem;
import bootwildfly.ProblemController;
import bootwildfly.ProblemRepository;
import bootwildfly.Solution;
import bootwildfly.SolutionRepository;
import bootwildfly.TestRepository;
import bootwildfly.Teste;

import java.util.ArrayList;
import java.util.List;
import static com.jayway.restassured.RestAssured.given;


import static org.hamcrest.Matchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
public class ControllerRestTest {

	//@Autowired
	ProblemController problemController;


	@Value("${local.server.port}")
	private int port;

	@Autowired
	private ProblemRepository problemRepository;

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private SolutionRepository solutionRepository;



	private static final Problem FIRST_PROBLEM = new Problem("Binary tree","level medium","42","divide and conquer");

	private static final Problem SECOND_ITEM = new Problem("avl","level medium1","40","divide and conquer");

	private static final Problem THIRD_ITEM = new Problem("heap","level medium1","30","xuxu");

	private static final Teste FIRST_TEST = new Teste("tamanho do retorno","use length","10","2");

	private static final Solution FIRST_SOLUTION = new Solution("solution's body", new ArrayList<String>());
	private Problem firstItem;
	private Problem secondItem;
	private Teste firstTest;
	private Solution solution;



	@Autowired
	WebApplicationContext context;

	@Before
	public void prepara() {

		problemRepository.deleteAll();
		testRepository.deleteAll();
		solutionRepository.deleteAll();


		firstItem = problemRepository.save(FIRST_PROBLEM);
		secondItem = problemRepository.save(SECOND_ITEM);

		firstTest = testRepository.save(FIRST_TEST);

		solution = solutionRepository.save(FIRST_SOLUTION);
	}

	@Test
	public void canFetchAllProblems() {
		given()
		.when()
		.port(port).
		get("/problem").
		then().
		statusCode(HttpStatus.SC_OK).
		body("numberOfElements", is(2));
	}

	@Test
	public void getSpecificProblem() {
		given().when().port(port).
		get("/problem/{id}", firstItem.getId()).
		then().
		statusCode(HttpStatus.SC_OK).
		body("name", Matchers.is("Binary tree")).
		body("description", Matchers.is("level medium")).
		body("code", Matchers.is("42")).
		body("tip",Matchers.is("divide and conquer"));
	}

	@Test
	public void addProblem() {
		given()
		.body(THIRD_ITEM)
		.contentType(ContentType.JSON)
		.when().port(port)
		.post("/problem")
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("description", is(THIRD_ITEM.getDescription()));
	}

	@Test
	public void addProblemShouldReturnNotSupportedMediaTypeIfNonJSON() {
		given()
		.body(THIRD_ITEM)
		.when().port(port)
		.post("/problem")
		.then()
		.statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
	}

	@Test
	public void addProblemShouldReturnBadRequestWithoutBody() {
		Problem pro = new Problem();
		given().
		body(pro)
		.contentType(ContentType.JSON).
		when().port(port)
		.post("/problem")
		.then()
		.statusCode(HttpStatus.SC_BAD_REQUEST);
	}

	@Test
	public void updateProblemShouldReturnUpdatedItem() {
		Problem problem = new Problem("smaller path", "the shortest path between two nodes",
				FIRST_PROBLEM.getCode(), "greedy algorithm");
		given()
		.body(problem)
		.contentType(ContentType.JSON)
		.when().port(port)
		.put("/problem/{id}", firstItem.getId())
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("name", is(problem.getName()))
		.body("description", is(problem.getDescription()))
		.body("code", is(problem.getCode()));
	}

	@Test
	public void updateProblemShouldReturnNotSupportedMediaTypeIfNonJSON() {
		given()
		.body(FIRST_PROBLEM)
		.when().port(port)
		.put("/problem/{id}", firstItem.getId())
		.then()
		.statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
	}

	@Test
	public void canFetchAllTestFromProblem() {

		given()
		.when()
		.port(port).
		get("/problem/{id}/test",firstItem.getId()).
		then().
		statusCode(HttpStatus.SC_OK);

	}

	@Test
	public void canFetchSpecificTestFromProblem() {

		given()
		.when()
		.port(port).
		get("/problem/{id}/test/{id2}",firstItem.getId(),firstTest.getId()).
		then().
		statusCode(HttpStatus.SC_OK).
		body("name", is(FIRST_TEST.getName()));

	}

	@Test
	public void addTest() {

		given()
		.body(FIRST_TEST)
		.contentType(ContentType.JSON)
		.when().port(port)
		.post("/problem/{id}/test", firstItem.getId())
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("name", is(FIRST_TEST.getName()));


	}
	
	@Test
	public void addTestShouldReturnBadRequestWithoutBody() {
		Teste test = new Teste();
		given().
		body(test)
		.contentType(ContentType.JSON).
		when().port(port)
		.post("/problem/{id}/test", firstItem.getId())
		.then()
		.statusCode(HttpStatus.SC_BAD_REQUEST);
	}

	@Test
	public void addTestShouldReturnNotSupportedMediaTypeIfNonJSON() {
		given()
		.body(FIRST_TEST)
		.when().port(port)
		.post("/problem/{id}/test", firstItem.getId())
		.then()
		.statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
	}

	@Test
	public void updateTestShouldReturnUpdatedItem() {

		firstTest.setName("baby");

		given()
		.body(firstTest)
		.contentType(ContentType.JSON)
		.when().port(port)
		.put("/problem/{id}/test/{id2}", firstItem.getId(),firstTest.getId())
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("name", is(firstTest.getName()))
		.body("input", is(firstTest.getInput()));
	}

	@Test
	public void canFetchSpecificSolutionFromProblem() {

		given()
		.when()
		.port(port).
		get("/problem/{id}/solution/{id2}",firstItem.getId(),FIRST_SOLUTION.getId()).
		then().
		statusCode(HttpStatus.SC_OK);


	}

	@Test
	public void addSolution() {

		given()
		.body(FIRST_SOLUTION)
		.contentType(ContentType.JSON)
		.when().port(port)
		.post("/problem/{id}/solution", firstItem.getId())
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("body", is(FIRST_SOLUTION.getBody()));

	}

	@Test
	public void canFetchAllSolutionFromProblem() {

		given()
		.when()
		.port(port).
		get("/problem/{id}/solution",firstItem.getId()).
		then().
		statusCode(HttpStatus.SC_OK);

	}

	@Test
	public void cantFetchSolutionFromProblem() {
		given().
		when().port(port).
		get("/problem/{id}/solution","5")
		.then()
		.statusCode(HttpStatus.SC_NOT_FOUND);
	}

	@Test
	public void addSolutionShouldReturnBadRequestWithoutBody() {
		Solution sol = new Solution();
		given().
		body(sol)
		.contentType(ContentType.JSON).
		when().port(port)
		.post("/problem/{id}/solution",firstItem.getId())
		.then()
		.statusCode(HttpStatus.SC_BAD_REQUEST);
	}

}