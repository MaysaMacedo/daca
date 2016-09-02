package src.test;

import static com.jayway.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bootwildfly.Application;
import bootwildfly.Problem;
import bootwildfly.ProblemController;
import bootwildfly.Solution;
import bootwildfly.Teste;

@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class ControllerRestTest {
	
	ProblemController problemController;
	
	
	@Value("${local.server.port}")
    private int port;
	
	private Problem problem;
	private Problem newProblem;
	private Teste teste;
	private Teste testeNovo;
	private Solution solucao;


	/**
	 * Cria objetos para uso nos testes antes de inicia-los. 
	 */
	@Before
	public void prepara() {
		String name = "Binary tree";
    	String description = "level medium";
    	String codigo = "42";
    	String tip = "divide and conquer";
    	this.problem = new Problem(name, description, codigo, tip);
    	this.newProblem = new Problem("novo problema", description, codigo, tip);
    	
    	String nome = "teste teste";
    	String dica = "um teste";
    	String entrada = "entrada";
    	String saidaEsperada = "saida";
    	this.teste = new Teste(nome, dica, entrada, saidaEsperada);
    	this.testeNovo = new Teste("novo nome", dica, entrada, saidaEsperada);
    	
    	List<String> outputs = new ArrayList<String>();
    	outputs.add(" Test binary search");
    	outputs.add("teste");
        this.solucao = new Solution("Solution body",outputs);
	}
	
    /**
     * Testa uma requisição GET consultando todos os problemas publicos
     * ao usuario.
     * @throws Exception
     */
    @Test
    public void testGetAllPublicProblems() throws Exception {
    	given().when().get("/problem").then().statusCode(200);	
    }
    
    /**
     * Testa uma requisição GET consultando todos os testes publicos
     * ao usuario.
     * @throws Exception
     */
    @Test
    public void testGetAllPublicTests() throws Exception {
    	problemController.addProblem(problem);
    	problemController.addTeste(problem.getId(), teste);
    	problemController.addTeste(problem.getId(), testeNovo);
    	problemController.addTeste(problem.getId(), new Teste("teste tres","dica","entrada","saida"));
    	given().when().get("/problem/1/test").then().statusCode(200);
    }
    
    /**
     * Testa uma requisição GET consultando todas as soluções publicas
     * ao usuario.
     * @throws Exception
     */
    @Test
    public void testGetAllPublicSolutions() throws Exception {
    	problemController.addProblem(problem);
    	problemController.addSolution(problem.getId(), solucao);
    	given().when().get("/problem/1/solution").then().statusCode(200);
    }
    
    /**
     * Testa adicionar um problema aos problemas do usuario.
     */
    @Test
    public void testAddProblem() {
    	
        given()
        .contentType("application/json")
        .body(problem)
        .when().post("/problem").then()
        .statusCode(200);
    }
    
    /**
     * Testa adicionar um teste a um problema do usuario.
     */
    @Test
    public void testAddTestToProblem() {
    	
        given()
        .contentType("application/json")
        .body(teste)
        .when().post("/problem/1/test").then()
        .statusCode(200);
    }
    
    /**
     * Testa adicionar uma solução a um problema do usuario.
     */
    @Test
    public void testAddSolutionToProblem() {

    	given()
        .contentType("application/json")
        .body(solucao)
        .when().post("/problem/1/solution").then()
        .statusCode(200);
    }
    
    /**
     * Testa recuperar um problema pelo seu ID.
     * @throws Exception
     */
    @Test
    public void testGetProblemById() throws Exception {
    	problemController.addProblem(problem);
    	
    	Long idProblem = 1L;
    	given().when().get("/problem/"+idProblem).then().statusCode(200);	
    }
    
    /**
     * Testa recuperar um teste pelo seu ID e do seu problema.
     * @throws Exception
     */
    @Test
    public void testGetTesteById() throws Exception {
    	problemController.addTeste(problem.getId(), teste);
    	
    	Long idProblem = 1L;
    	Long idTeste = 1L;
    	given().when().get("/problem/"+idProblem+"/test/"+idTeste).then().statusCode(200);	
    }
    
    /**
     * Testa recuperar uma solução pelo seu ID e do seu problema.
     * @throws Exception
     */
    @Test
    public void testGetSolutionById() throws Exception {
    	problemController.addSolution(problem.getId(), solucao);
    	
    	Long idProblem = 1L;
    	Long idSolution = 1L;
    	given().when().get("/problema/"+idProblem+" /solution/"+idSolution).then().statusCode(200);	
    }
    
    /**
     * Testa modificar um problema pelo seu ID.
     * @throws Exception
     */
    @Test
    public void testModifyProblemById() throws Exception {
    	problemController.addProblem(problem);
    	
    	Long idProblem = 1L;
    	given().when().put("/problem/"+idProblem).then().statusCode(200);
    	given()
        .contentType("application/json")
        .body(newProblem)
        .when().post("/problem/"+idProblem).then()
        .statusCode(200);
    }
    
    /**
     * Testa Modificar um teste pelo seu ID.
     * @throws Exception
     */
    @Test
    public void testModifyTestById() throws Exception {
    	problemController.addProblem(problem);
    	problemController.addTeste(problem.getId(), teste);
    	
    	Long idProblem = 1L;
    	Long idTeste = 1L;
    	given().when().put("/problem/"+idProblem).then().statusCode(200);
    	given()
        .contentType("application/json")
        .body(testeNovo)
        .when().post("/problem/"+idProblem+"/test/"+idTeste).then()
        .statusCode(200);
    }

}
