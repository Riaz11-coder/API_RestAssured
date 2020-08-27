package Day03_;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.* ;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Practice1 {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://52.86.72.131";
        port = 8000;
        basePath = "/api";


    }

    @DisplayName("simple test")
    @Test
    public void test1(){

        given()
                .log().all()
                .queryParam("gender","Female").
        when()
                .get("/spartans/search").
        then()
                .statusCode(200);

    }

}
