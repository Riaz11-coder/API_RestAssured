package Day10_;


import Utility.ConfigurationReader;
import Utility.DB_Utility;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class GetTestDataFromSpartanDatabase {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");

        RestAssured.basePath = "/api" ;

        DB_Utility.createConnection("spartan1");
    }

    @DisplayName("Testing")
    @Test
    public void testDataFromDB(){

     String myQuery = "SELECT * FROM SPARTANS ORDER BY SPARTAN_ID DESC" ;

     DB_Utility.runQuery(myQuery)   ;

     Map<String,String> firstRowMap = DB_Utility.getRowMap(1) ;

        System.out.println("firstRowMap = " + firstRowMap);

        System.out.println("SPARTAN_ID key's value = "+ firstRowMap.get("SPARTAN_ID") );

        int idFromDB = Integer.parseInt(firstRowMap.get("SPARTAN_ID"));
        //System.out.println("idFromDB = " + idFromDB);
        String nameFromDB = firstRowMap.get("NAME");
        //System.out.println("nameFromDB = " + nameFromDB);
        String genderFromDB = firstRowMap.get("GENDER");
        //System.out.println("genderFromDB = " + genderFromDB);
        long phoneFromDB = Long.parseLong(firstRowMap.get("PHONE"));
        //System.out.println("phoneFromDB = " + phoneFromDB);


        when()
                .get("/spartans/{id}" , idFromDB ).
        then()
                .log().all()
                .statusCode(200)
                .body("id",  is(idFromDB) )
                .body("name",  is(nameFromDB) )
                .body("gender", is(genderFromDB))
                .body("phone.toLong()", is(phoneFromDB) ) ;





    }

    @DisplayName("Testing GET /Spartans/{id} by getting the RANDOM id from DB")
    @Test
    public void testDataFromDB_RandomLy(){

        String myQuery = "SELECT * FROM SPARTANS ORDER BY SPARTAN_ID DESC" ;
        DB_Utility.runQuery( myQuery ) ;
        // we want to get the rowNum for below method randomly from 1 till the last row count
        // so I can always get different data for my test
        // so first I need to get the row count so I can set the max of my random number
        int rowCount = DB_Utility.getRowCount();
        // get a random number from 1 to rowCount value
        int randomRowNum = new Faker().number().numberBetween(1, rowCount) ;

        Map<String, String> randomRowMap = DB_Utility.getRowMap(randomRowNum);
        System.out.println("CURRENT ROW IS "+ randomRowNum);
        System.out.println("CURRENT ROW DATA IS "+ randomRowMap);

        // EVERYTHING ELSE IS EXACTLY THE SAME OTHER THAN THE MAP NAME
        int idFromDB        = Integer.parseInt( randomRowMap.get("SPARTAN_ID") );
        String nameFromDB   = randomRowMap.get("NAME") ;
        String genderFromDB = randomRowMap.get("GENDER") ;
        long phoneFromDB    = Long.parseLong(randomRowMap.get("PHONE"));

        given()
                .log().uri().
                when()
                .get("/spartans/{id}" , idFromDB ).
                then()
                .log().all()
                .statusCode(200)
                .body("id",  is(idFromDB) )
                .body("name",  is(nameFromDB) )
                .body("gender", is(genderFromDB))
                .body("phone.toLong()", is(phoneFromDB) ) ;



    }


    @AfterAll
    public static void cleanUp(){
        RestAssured.reset();
    }

}
