package Day02_;

import org.junit.jupiter.api.BeforeAll;

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


import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class Spartans {

    @BeforeAll
    public static void setUpClass() {

        baseURI="http://52.86.72.131:8000";
        basePath = "/api";
    }


    @Test
    public void testAllSpartans(){



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

                .accept(ContentType.JSON).
                when()
                .get("/spartans").
                then()
                .statusCode(is(200))
                .contentType(ContentType.JSON);







    }

    @DisplayName("hello")
    @Test
    public void testHelloSpartan() {
        when()
                .get("/hello").
                prettyPeek().
                then()
                .statusCode(is(200));
    }

    @Test
    public void testHello(){

        given()
                .accept(ContentType.TEXT).

        when()
                .get("/hello").
        then()
                .statusCode(is(200))
                .contentType(ContentType.TEXT)
                .body(equalTo("Hello from Sparta"));
    }

    @Test
    public void anotherTest(){
        given()
                //.log().all()
                .log().uri().

        when()

                .get("/spartans/18").
                //prettyPeek().
        then()
                .log().all()
                //.log().body()
                //.log().ifValidationFails()
                .statusCode(is(200));


    }
    @DisplayName("Path Parameters/{id}")
    @Test
    public void anotherOne(){

        given()
                .log().all()
                .pathParam("id",18).
        when()

                .get("/spartans/{id}").
        then()
                .log().all()
                .statusCode(is(200))   ;
    }

    @DisplayName("Path Parameters/{id}")
    @Test
    public void anotherOneAgain(){

        given()
                .log().all().

                when()

                .get("/spartans/{id}",18).
                then()
                .log().all()
                .statusCode(is(200))   ;
    }

    @Test
    public void testSingleSpartanBody(){


        given()
                .log().all()
                .pathParam("id",18).
                when()

                .get("/spartans/{id}").
                then()
                .log().all()
                .statusCode(is(200))
                //.body("JSON PATH",is("THE VALUE"))
                .body("id",is(18))
                .body("name",is("Allen"))
                .body("gender",is("Male"));


    }







}
