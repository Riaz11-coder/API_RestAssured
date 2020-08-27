package Day05_;

import POJO.Spartan;
import POJO.Spartan;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class Practice1 {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";

    }

    @Test
    public void testLoginEndPoint(){


        given()
                .log().all()
                .formParams("email","librarian69@library")
                .formParams("password", "KNPXrm3S").
                when()
                .post("/login").
                then()
                .log().all()
                .statusCode(200)
        .body("token", is(notNullValue()));

    }

    public static String LoginAndGetToken(String username, String password){

        String token =  "";

        Response response = given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParams("email",username)
                .formParams("password", password).
                when()
                .post("/login");

        token = response.jsonPath().getString("token");


       return token ;

    }

    @Test
    public void testMethodToken(){

        System.out.println(LoginAndGetToken("librarian69@library","KNPXrm3S"));
    }



}
