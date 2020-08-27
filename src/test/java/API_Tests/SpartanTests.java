package API_Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpartanTests {

    String spartanBaseUrl = "http://52.86.72.131:8000";

    @Test
    public void viewSpartanTest1(){

        Response response = RestAssured.get(spartanBaseUrl+"/api/spartans");

        System.out.println(response.statusCode());

        response.body().prettyPrint();
    }

    @Test
    public void viewSpartanTest2(){

        Response response = RestAssured.get(spartanBaseUrl+"/api/spartans");

        assertEquals(response.statusCode(),200);

        assertTrue(response.body().asString().contains("Allen"));
    }

    @Test
    public void viewSpartanTest3(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(spartanBaseUrl+"/api/spartans");

       assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json;charset=UTF-8");
    }

}
