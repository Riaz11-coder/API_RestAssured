package Day09_;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.registerParser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DataDriven {

    @ParameterizedTest(name = "iteration {index}")
    @CsvFileSource(resources = "/zip.csv", numLinesToSkip = 1)
    public void testZipCodeAPI(String state, String city, int zipCode) {

        /*System.out.println("state = " + state);
        System.out.println("city = " + city);
        System.out.println("zipCode = " + zipCode);

         */

        Response response =  given()
                .baseUri("http://api.zippopotam.us/us")
                .pathParam("state",state)
                .pathParam("city",city).
                when()
                .get("/{state}/{city}");

        JsonPath jp = response.jsonPath();

        System.out.println("state = " + jp.getString("'state abbreviation'"));

        System.out.println("city = " + jp.getString("'place name'"));

        assertThat(jp.getString("'state abbreviation'"),is(state));
        assertThat(jp.getString("'place name'"),is(city));

        List<String> zipCodeList = jp.getList("places.'post code'");

        zipCodeList.forEach(each-> System.out.println(each));

        System.out.println("zipCodeList = " + zipCodeList);

        assertThat(zipCodeList, hasSize(zipCode));

        System.out.println("Calling the size using Groovy = " + jp.getInt("places.size()"));

    }
}
