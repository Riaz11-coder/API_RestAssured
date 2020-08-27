package Day05_;

import POJO.Countries;
import POJO.Spartan;
import POJO.Spartan2;
import POJO.region;
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



public class HR_Practice {
    @BeforeAll
    public static void setUp() {
        baseURI = "http://54.174.216.245:1000";
        basePath = "/ords/hr";

    }

    @Test
    public void hrDataregions(){

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("region_id",1).
                when()
                .get("/regions/{region_id}").prettyPeek();

        //stores select POJO data
        region r1 = response.as(region.class);
        System.out.println("r1 = " + r1);






    }
    @Test
    public void testingRegionJsonArrayToPOJO(){

        Response response = given()
                .accept(ContentType.JSON).

                        when()
                .get("/regions/");


            JsonPath jp = response.jsonPath();


            System.out.println("jp = " + jp.getString("items[0].region_name"));

            System.out.println("jp = " +jp.getInt("items[2].region_id"));

            System.out.println("jp = " + jp.getString("items[0].links[0]"));

            List<String> allNames = jp.getList("items.region_name");
            System.out.println("allNames = " + allNames);

            allNames.forEach(each-> System.out.println(each));

            List<region> regionList = jp.getList("items",region.class);
            System.out.println("regionList = " + regionList);

            regionList.forEach(each-> System.out.println(each));


    }

    @Test
    public void hrDatabaseCountries(){

        Response response = given()
                .accept(ContentType.JSON).

                        when()
                .get("/countries/");


        JsonPath jp = response.jsonPath();

        System.out.println("jp = " +jp.getString("items[3].country_id"));

        List<String> allCountryNames = jp.getList("items.country_name");
        System.out.println("allCountryNames = " + allCountryNames);

        allCountryNames.forEach(each-> System.out.println(each));

        List<Countries> countryList = jp.getList("items",Countries.class);
        System.out.println("countryList = " + countryList);

        countryList.forEach(each-> System.out.println(each));
    }
}
