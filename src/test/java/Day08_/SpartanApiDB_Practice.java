package Day08_;

import Utility.ConfigurationReader;
import Utility.DB_Utility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanApiDB_Practice {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://100.25.192.231" ;
        RestAssured.port = 8000;
        RestAssured.basePath = "/api" ;


        DB_Utility.createConnection(ConfigurationReader.getProperty("spartan1.database.url"),ConfigurationReader.getProperty("spartan1.database.username"),ConfigurationReader.getProperty("spartan1.database.password"));
    }

    @DisplayName("Testing /spartans/search Endpoint and Validate against DB")
    @Test
    public void testSearch(){

        // make a request to GET /spartans/search
        // using query parameter gender Female  nameContains a

        Response response = given()
                .log().all()
                .queryParam("gender","Female")
                .queryParam("nameContains", "a").
                        when()
                .get("/spartans/search")
                .prettyPeek();

        int resultCount =  response.path("numberOfElements") ;
        System.out.println("resultCount = " + resultCount);

        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";

        DB_Utility.runQuery(query);
        int expectedResult = DB_Utility.getRowCount();

        assertEquals(expectedResult,resultCount);
    }


    @Test
    public void testDB(){

        DB_Utility.runQuery("SELECT * FROM SPARTANS");


        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";



        DB_Utility.runQuery(query);
        DB_Utility.displayAllData();

        int expectedResult = DB_Utility.getRowCount();
        System.out.println("expectedResult = " + expectedResult);

    }

    @Test
    public void testIdResponse(){

        Response response = given()
                .log().all()
                .queryParam("gender","Female")
                .queryParam("nameContains", "a").
                        when()
                .get("/spartans/search")
                .prettyPeek();

        List<String> IDListFromResponse = response.jsonPath().getList("content.id",String.class );

        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";

        DB_Utility.runQuery(query);
        List<String> IdListFromDB = DB_Utility.getColumnDataAsList(1);

        assertEquals(IDListFromResponse.size(),IdListFromDB.size());

        assertEquals(IDListFromResponse,IdListFromDB);


    }


    @AfterAll
    public static void teardown(){

        RestAssured.reset();

        DB_Utility.destroy();
    }

}
