package Day05_;

import POJO.Spartan;
import POJO.Spartan2;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class JsonToPOJOPractice {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://54.160.106.84:8000";
        basePath = "/api";

    }

    @Test
    public void testSpartanJsonToObject(){

        int newID = SecureSpartan.createRandomSpartan();

        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .auth().basic("admin","admin")
                .pathParam("id",newID).
                when()
                .get("/spartans/{id}")
                .prettyPeek() ;

        Spartan2 sp = response.as(Spartan2.class) ;

        System.out.println("sp = " + sp);
    }

    @Test
    public void testSearchRequest() {

        Response response = given()
                .auth().basic("admin","admin")
                .queryParam("gender","Male").
                when()
                .get("/spartans/search");

        System.out.println("response.statusCode() = " + response.statusCode());

        JsonPath jp = response.jsonPath() ;
        System.out.println("First ID " + jp.getInt("content[0].id"));
        System.out.println("First name " + jp.getString("content[0].name"));

       Spartan2 firstMaleSpartan =  jp.getObject("content[0]",Spartan2.class);
        System.out.println("firstMaleSpartan = " + firstMaleSpartan);

        System.out.println("The Spartan ID is "+firstMaleSpartan.getId());
        System.out.println("The Spartan name is "+firstMaleSpartan.getName());
        System.out.println("The Spartan gender is "+firstMaleSpartan.getGender());
        System.out.println("The Spartan number is "+firstMaleSpartan.getPhone());







    }

    @Test
    public void saveSpartanAsList(){

        Response response = given()
                .log().all()
                .auth().basic("admin","admin")
                .queryParam("gender","Female").
                        when()
                .get("/spartans/search");
        JsonPath jp = response.jsonPath();
        List<Integer> SpartanID = jp.getList("content.id");
        System.out.println(SpartanID);

        List<Spartan2> SpartanObj = jp.getList("content",Spartan2.class);
        System.out.println("SpartanObj = " + SpartanObj);

        /*for(Spartan2 each:SpartanObj){
            System.out.println(each);
        }

         */

        SpartanObj.forEach(each-> System.out.println(each));
    }




}


