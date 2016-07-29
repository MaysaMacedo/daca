package src.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import bootwildfly.Application;

import org.junit.Test;

@SpringApplicationConfiguration(classes=Application.class)
@WebIntegrationTest("server.port=0")
@RunWith(SpringJUnit4ClassRunner.class)
public class ControllerTest {

	
	@Value("${local.server.port}")
    private int port;
    
    @Test
    public void basicTest() throws Exception {
    	given().when().get("/problem").then().statusCode(200);	
    }
    
    @Test
    public void addProblem() {
        Map<String,String> problem = new HashMap<>();
        problem.put("name", "binary search");
        problem.put("description", "teste");
        problem.put("code", "12");

        given()
        .contentType("application/json")
        .body(problem)
        .when().post("/problem").then()
        .statusCode(200);
    }
    
    @Test
    public void addTestToProblem() {
        Map<String,String> test = new HashMap<>();
        test.put("name", " Test binary search");
        test.put("tip", "teste");
        test.put("input", "12");
        test.put("expectedOutput", "12");

        given()
        .contentType("application/json")
        .body(test)
        .when().post("/problem/1/test").then()
        .statusCode(200);
    }
    
    @Test
    public void addSolutionToProblem() {
        Map<String,String> solution = new HashMap<>();
        solution.put("description", " Test binary search");
        solution.put("resultInputOutput", "teste");

        given()
        .contentType("application/json")
        .body(solution)
        .when().post("/problem/1/solution").then()
        .statusCode(200);
    }
    
    
    
    
    


}
