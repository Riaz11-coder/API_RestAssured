package Day03_;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ExtractingDataFromResponseBody {

    @DisplayName("Getting movie info")
    @Test
    public void test1(){


        Response response =
         given()

                .baseUri("http://www.omdbapi.com/")
                .queryParam("apikey","26aa5b74")
                .queryParam("t","Boss Baby")
                .queryParam("Plot","full").
        when()
               .get();

        response.prettyPrint();
        System.out.println(response.statusCode());

        String title = response.path("Title");
        System.out.println("Title= "+ title);
        String year = response.path("Year");
        System.out.println("Year= "+year);
        String director = response.path("Director");
        System.out.println("Director= "+director);
        String actors = response.path("Actors");
        System.out.println(actors);
        ArrayList <String> ratings = response.path("Ratings");
        System.out.println(ratings);


    }

    @Test
    public void test2(){
        Response response =
                given()

                        .baseUri("http://www.omdbapi.com/")
                        .queryParam("apikey","26aa5b74")
                        .queryParam("t","Boss Baby")
                        .queryParam("Plot","full").
                when()
                        .get();
        JsonPath jp = response.jsonPath();
        String title = jp.getString("Title");
        System.out.println("title = " + title);
        int year = jp.getInt("Year");
        System.out.println("year = " + year);
        String director = jp.getString("Director");
        System.out.println("director = " + director);
        Map<String,Object> responseMap = jp.getMap("");
        System.out.println("responseMap = " + responseMap);
        
        
        Map<String,Object> singleResponse = jp.getMap("Ratings[0]");
        System.out.println("singleResponse = " + singleResponse);
        
        List<String> result = jp.getList("Ratings.Source");
        System.out.println("result = " + result);



    }
}
