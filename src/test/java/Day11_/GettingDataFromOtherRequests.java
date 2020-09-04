package Day11_;

import POJO.Spartan2;
import Utility.ConfigurationReader;
import Utility.DB_Utility;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GettingDataFromOtherRequests {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://52.86.72.131:8000";

        RestAssured.basePath = "/api" ;

        //DB_Utility.createConnection("spartan1");
    }

    @Test
    public void testingDynamicID(){

        Response response = get("/spartans") ;
        List<Spartan2> spartan2List = response.jsonPath().getList("",Spartan2.class);
        System.out.println("spartan2List = " + spartan2List);

        int firstSpartan = spartan2List.get(0).getId() ;
        String firstSpartanName = spartan2List.get(0).getName() ;
        System.out.println("firstSpartan = " + firstSpartan);

        given()
                .pathParam("id",firstSpartan).
                when()
                .get("/spartan/{id}").
        then()
       .statusCode(200)
                .body("name",is(firstSpartanName));
    }

    @RepeatedTest(10)
    public void gettingRandomIdAndName(){

        Response response = get("/spartans") ;
        List<Spartan2> spartan2List = response.jsonPath().getList("",Spartan2.class);

        Random r = new Random();
        int randomIndex = r.nextInt(spartan2List.size());
        System.out.println("randomIndex = " + randomIndex);

        Spartan2 randomSpartanObject = spartan2List.get(randomIndex);
        System.out.println("randomSpartanObject = " + randomSpartanObject);

        given()
                .pathParam("id",randomSpartanObject.getId()).
                when()
                .get("/spartans/{id}").
                then()
                .log().body()
                .statusCode(200)
                .body("name",is(randomSpartanObject.getName()));



    }

    @Test
    public void testRandom(){

        Random r = new Random();
        int randomNumber = r.nextInt(5) ;
        //System.out.println("randomNumber = " + randomNumber);

        List<String> names = Arrays.asList("Anna", "Akbar","Tony","Miguel","Zuura");

        System.out.println("The lucky name is: "+ names.get(randomNumber));

       /* for(int i=0;i<randomNumber;i++){
            System.out.println(randomNumber);
        }

        */
    }

    @Test
    public void testSchema(){

        given()
                .log().uri().
                when()
                .get("/spartans/{id}",55)
                .prettyPeek().
                then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("singleSpartanSchema.json"));
    }




}
