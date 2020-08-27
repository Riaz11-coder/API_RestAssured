package Day03_;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequest {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://52.86.72.131:8000";
        basePath = "/api";

    }

    @Test
    public void testAddSpartan(){


        String myBodyData = "{\n" +
                "  \"name\"  : \"Adam\",\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"phone\": 6234567890\n" +
                "}";

        System.out.println("myBodyData = " + myBodyData);

        given()
                .contentType(ContentType.JSON)
                .body(myBodyData)
                .log().all().
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success",is("A Spartan is Born!"))
                .body("data.name",is("Adam"));



    }

    @Test
    public void testPostExtractData(){

         String myBodyData = "{\n" +
                "  \"name\"  : \"Adam\",\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"phone\": 6234567890\n" +
                "}";

        Response response = given()
                                    .contentType(ContentType.JSON)
                                    .body(myBodyData)
                                    .log().all().
                when()
                                    .post("/spartans")
                                    .prettyPeek();

        JsonPath jp = response.jsonPath();

        System.out.println("jp.getInt(\"data.id\") = " + jp.getInt("data.id"));
        System.out.println("jp = " + jp.getString("data.name"));

        Map<String,Object> result = jp.getMap("");
        System.out.println("result = " + result);
        
    }

    @Test
    public void putRequest(){

       String update = "{\n" +
                "  \"name\"  : \"Reginald\",\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"phone\": 6234567890\n" +
                "}";



        given()
                .contentType(ContentType.JSON)
                .body(update)
                .log().all().
                when()
                .put("/spartans/{id}",263).

                then()
                .log().all()
                .statusCode(204);

    }

    @Test
    public void deleteRequest(){

        when()
                .delete("/spartans/{id}",263).
                then()
                .log().all();

    }

    @Test
    public void deleteAssertion(){
    when()
    .get("/spartans/{id}",263).
            then()
            .log().all()
            .statusCode(404);
    }


}
