package API_Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestwithPathParameters {

    @BeforeEach
    public void setUpClass(){

         RestAssured.baseURI="http://52.86.72.131:8000";
    }

    @Test
    public void PathTest1(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",18)
                .when().get("api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        assertTrue(response.body().asString().contains("Allen"));

        response.body().prettyPrint();
    }

    @Test
    public void negativePathParamTest(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id",500)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),404);

        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        assertFalse(response.body().asString().contains("Spartan not Found"));

        response.body().prettyPrint();
    }


}
