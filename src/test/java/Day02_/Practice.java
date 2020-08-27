package Day02_;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Practice {

    String BreakingBadBaseUrl = "https://www.breakingbadapi.com/api/characters";
    //String spartanBaseUrl = "http://52.86.72.131:8000";

    // given() Request Specification / Query parameter, headers, authentication, path variables, log

    // when() HTTP Methods GET, POST, PUT, DELETE
    @BeforeAll
    public void setUpClass() {

        baseURI="http://52.86.72.131:8000";
        basePath = "/api";
    }



    @Test
    public void BreakingBadTest(){


        when()
         .get(BreakingBadBaseUrl).prettyPeek().
        then()
                .statusCode(is(200))
                .header("content-type",is("application/json; charset=utf-8"));





    }

    @Test
    public void testAllSpartans(){

        baseURI="http://52.86.72.131:8000";
        basePath = "/api";

        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans").prettyPeek().
        then()
                .statusCode(is(200));
    }

    @Test
    public void testAllSpartans2(){

        given()
                .baseUri("http://52.86.72.131:8000")
                .basePath("/api")
                .accept(ContentType.JSON).
                when()
                .get("/spartans").
                then()
                .statusCode(is(200))
                .contentType(ContentType.JSON);







    }




}
