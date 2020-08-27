package Day05_;

import POJO.Spartan;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
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


public class SecureSpartan {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://54.160.106.84:8000";
        basePath = "/api";

    }

    @Test
    public void testSecuredSpartan(){


        given()
                .log().all()
                .pathParam("id",174).
                when()
                .get("/spartans/{id}").
                then()
                .log().all()
        .statusCode(is(401));

    }

    @DisplayName("Test GET /spartan/{id} Endpoint with Credentials")
    @Test
    public void testGettingSpartanWithCredentials(){

        int newId = createRandomSpartan();

        given()
                .log().all()
                .auth().basic("admin","admin")
                .pathParam("id",newId).
                when()
                .get("/spartans/{id}").
                then()
                .statusCode(200);
    }

    public static int createRandomSpartan(){
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String gender = faker.demographic().sex();
        long phone = faker.number().numberBetween(1000000000l, 9999999999l);

        Spartan sp = new Spartan(name,gender,phone);

        Response response = given()
                .log().ifValidationFails()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(sp).
                when()
                .post("/spartans");

        return response.jsonPath().getInt("data.id");

    }


}
