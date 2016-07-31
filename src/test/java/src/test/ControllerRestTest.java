package src.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import bootwildfly.Application;
import bootwildfly.Problem;
import bootwildfly.ProblemController;
import bootwildfly.Solution;
import bootwildfly.Teste;

import org.junit.Test;

@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class ControllerRestTest {
	
	ProblemController problemController;
	
	
	@Value("${local.server.port}")
    private int port;
	
	private Problem problema;
	private Problem problemaNovo;
	private Teste teste;
	private Teste testeNovo;
	private Solution solucao;


	
	@Before
	public void prepara() {
		String name = "Problema facil";
    	String description = "um problema muito facil";
    	String codigo = "42";
    	this.problema = new Problem(name, description, codigo);
    	this.problemaNovo = new Problem("novo problema", description, codigo);
    	
    	String nome = "teste teste";
    	String dica = "um teste";
    	String entrada = "entrada";
    	String saidaEsperada = "saida";
    	this.teste = new Teste(nome, dica, entrada, saidaEsperada);
    	this.testeNovo = new Teste("novo nome", dica, entrada, saidaEsperada);
    	
    	Map<String,String> resultEntradaSaida = new HashMap<>();
        resultEntradaSaida.put("description", " Test binary search");
        resultEntradaSaida.put("resultInputOutput", "teste");
        this.solucao = new Solution(resultEntradaSaida);
	}
	
    
    @Test
    public void testGetAllPublicProblems() throws Exception {
    	given().when().get("/problem").then().statusCode(200);	
    }
    
    @Test
    public void testGetAllPublicTests() throws Exception {
    	problemController.addProblem(problema);
    	problemController.addTeste(problema, teste);
    	problemController.addTeste(problema, testeNovo);
    	problemController.addTeste(problema, new Teste("teste tres","dica","entrada","saida"));
    	given().when().get("/problem/1/test").then().statusCode(200);
    }
    
    @Test
    public void testGetAllPublicSolutions() throws Exception {
    	problemController.addProblem(problema);
    	problemController.addSolution(problema, solucao);
    	given().when().get("/problem/1/solution").then().statusCode(200);
    }
    
    @Test
    public void testAddProblem() {
    	
        given()
        .contentType("application/json")
        .body(problema)
        .when().post("/problem").then()
        .statusCode(200);
    }
    
    @Test
    public void testAddTestToProblem() {
    	
        given()
        .contentType("application/json")
        .body(teste)
        .when().post("/problem/1/test").then()
        .statusCode(200);
    }
    
    @Test
    public void testAddSolutionToProblem() {

    	given()
        .contentType("application/json")
        .body(solucao)
        .when().post("/problem/1/solution").then()
        .statusCode(200);
    }
    
    @Test
    public void testGetProblemById() throws Exception {
    	problemController.addProblem(problema);
    	
    	Long idProblem = 1L;
    	given().when().get("/problem/"+idProblem).then().statusCode(200);	
    }
    
    @Test
    public void testGetTesteById() throws Exception {
    	problemController.addTeste(problema, teste);
    	
    	Long idProblem = 1L;
    	Long idTeste = 1L;
    	given().when().get("/problem/"+idProblem+"/test/"+idTeste).then().statusCode(200);	
    }
    
    @Test
    public void testGetSolutionById() throws Exception {
    	problemController.addSolution(problema, solucao);
    	
    	Long idProblem = 1L;
    	Long idSolution = 1L;
    	given().when().get("/problema/"+idProblem+" /solution/"+idSolution).then().statusCode(200);	
    }
    
    @Test
    public void testModifyProblemById() throws Exception {
    	problemController.addProblem(problema);
    	
    	Long idProblem = 1L;
    	given().when().put("/problem/"+idProblem).then().statusCode(200);
    	given()
        .contentType("application/json")
        .body(problemaNovo)
        .when().post("/problem/"+idProblem).then()
        .statusCode(200);
    }
    
    @Test
    public void testModifyTestById() throws Exception {
    	problemController.addProblem(problema);
    	problemController.addTeste(problema, teste);
    	
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