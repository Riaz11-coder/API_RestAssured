package Day01_Junit_Assertions;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssuredMethodChaining {

    String spartanBaseUrl = "http://52.86.72.131:8000";

    @DisplayName("First Attempt for chaining")
    @Test
    public void chainingMethodsTest1() {

       when().
               get(spartanBaseUrl+"/api/hello").
               then().
               statusCode(200).
               header("content-length","17");


        when().
                get(spartanBaseUrl+"/api/hello").
                then().
                statusCode(is(200)).
                header("content-length",is(equalTo("17")));


    }

    @DisplayName("Hamcrest matcher")
    @Test
    public void matcherTest(){

        when().
                get(spartanBaseUrl+"/api/hello").prettyPeek().
                then().
                statusCode(is(200)).
                header("content-length",is(equalTo("17"))).
                header("content-type",is("text/plain;charset=UTF-8")).
                body(is("Hello from Sparta"));

    }

    @Test
    public void test2(){
        given().
                header("Accept","application/xml").
        when().
                get(spartanBaseUrl+"/api/spartans").prettyPeek().
        then().
                statusCode(is(200));


    }


}
