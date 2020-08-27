package Day02_;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Practice3 {

    @BeforeAll
    public static void setUp() {
       baseURI = "http://52.86.72.131:8000";
       basePath = "/api";

    }

    @Test
    public void queryParam(){

        given().log().all()
                .queryParam("gender", "male")
                .queryParam("nameContains", "ll").

        when()
                .get("/spartans/search").
        then()
                .log().body()
                .body("numberOfElements",is(2));
    }



}
