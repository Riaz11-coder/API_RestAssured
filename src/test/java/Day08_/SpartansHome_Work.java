package Day08_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.*;

import POJO.Spartan;
import Utility.ConfigurationReader;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.internal.ResponseLogSpecificationImpl;
import io.restassured.internal.common.assertion.Assertion.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class SpartansHome_Work {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://52.86.72.131:8000";
        basePath = "/api";

    }


    @ParameterizedTest(name = "iteration {index} | Name: {0} Gender: {1} Phone: {2}")
    @CsvFileSource(resources = "/allSpartans.csv",numLinesToSkip = 1)
    public void testAllSpartans(String name, String gender, long phone){
        Spartan spBody = new Spartan(name,gender,phone);

        given()
                .contentType(ContentType.JSON)
                .body(spBody).




                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .body("success",is("A Spartan is Born!"))
                .body("data.name",is(name))
                .body("data.gender", is(gender))
                ;


    }

    @Test
    public void test1(){

        String activeEnv = ConfigurationReader.getProperty("active_env");
        System.out.println("activeEnv = " + activeEnv);

        String DBUrl = ConfigurationReader.getProperty(activeEnv+".database.url");
        System.out.println("DBUrl = " + DBUrl);
    }

    @AfterAll
    public static void cleanUp(){
        RestAssured.reset();
    }

}
