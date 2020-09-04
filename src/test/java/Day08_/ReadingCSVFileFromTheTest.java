package Day08_;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.*;
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

public class ReadingCSVFileFromTheTest {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = ("/rest/v1");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv",numLinesToSkip = 1)
    public void simpleRead(int num1, int num2){

        System.out.println("num1 = " + num1);

        System.out.println("num2 = " + num2);



    }

    @ParameterizedTest
    @CsvFileSource(resources = "/numbers.csv",numLinesToSkip = 1)
    public void testAddition(int num1, int num2, int Result){

       assertEquals(Result,num1+num2);

    }

    @ParameterizedTest(name = "Row {index} | FirstCol {0} + SecondCol {1} = ThirdCol {2}")
    @CsvFileSource(resources = "/numbers.csv",numLinesToSkip = 1)
    public void testCsv(int num1, int num2, int Result){

        assertEquals(Result,num1+num2);
    }

    @ParameterizedTest(name = "iteration {index} | username: {0} password: {1}")
    @CsvFileSource(resources = "/credentials.csv",numLinesToSkip = 1)
    public void testCredentials(String username, String password){

        given()
                .contentType(ContentType.URLENC)
                .formParam("email",username)
                .formParam("password",password).
                when()
                .post("/login").
                then()
                .log().all()
                .statusCode(200)
                .body("token",notNullValue());

       // System.out.println("username = " + username);
       // System.out.println("password = " + password);
    }

    @ParameterizedTest(name = "iteration {index} | Name: {0} Gender: {1} Phone: {2}")
    @CsvFileSource(resources = "/allSpartans",numLinesToSkip = 1)
    public void testAllSpartans(){


    }

    @AfterAll
    public static void cleanUp(){
        RestAssured.reset();
    }
}
