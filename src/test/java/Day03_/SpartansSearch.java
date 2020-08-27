package Day03_;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartansSearch {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://52.86.72.131:8000";
        basePath = "/api";

    }

    @Test
    public void searchTest(){
        Response response =
        given()
                .log().all()
                .queryParam("gender", "Female").


                when()
                .get("/spartans/search").prettyPeek();


        JsonPath jp = response.jsonPath();
        
        int numOfFemales = jp.getInt("numberOfElements");
        System.out.println("numOfFemales = " + numOfFemales);

        List<Integer> result = jp.getList("content.id");
        System.out.println("result = " + result);

        List<String> result2 = jp.getList("content.name");
        System.out.println("result = " + result2);

        List<Integer> result3 = jp.getList("content.phone");
        System.out.println("result = " + result3);


    }
}
