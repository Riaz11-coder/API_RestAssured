package Day02_;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class movie_API {

    @BeforeAll
    public static void setUp(){

        baseURI = "http://www.omdbapi.com/";
    }

    @Test
    public void bossBaby(){

        given().log().all()
                .queryParam("apikey","26aa5b74")
                .queryParam("t","Boss Baby")
                .queryParam("Plot","full").
                when()
                .get().


        then()
                .log().all()
                .body("Title",is("The Boss Baby"))
                .statusCode(is(200));
    }
}
