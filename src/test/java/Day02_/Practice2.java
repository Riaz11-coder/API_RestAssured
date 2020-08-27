package Day02_;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

import static io.restassured.RestAssured.*;

public class Practice2 {

    @BeforeAll
    public static void setUp(){

        baseURI = ("http://api.zippopotam.us");
        basePath = ("/us");

    }

    @Test
    public void zipCodeTest(){

       given()
               .pathParam("zipcode",22033).

               when()
               .get("/{zipcode}").

               then()
               .log().all()
               .contentType(ContentType.JSON)
               .body("country",is("United States"))
               .body("places[0].state",is("Virginia"))
               .body("'post code'",is("22033"))
               .body("places[0].'place name'",is("Fairfax"))
               .statusCode(is(200));




    }

    @Test
    public void testingCityState(){

        given()
                .pathParam("state","VA")
                .pathParam("city", "Fairfax")
                .log().all().
                when()
                .get("{state}/{city}").
                then()
                .log().all()
                .body("places[0].'place name'",is("Fairfax"))
                .statusCode(is(200));
    }


}
