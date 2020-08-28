package Day06_LocationPOJO;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import POJO.Locations;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;


public class Locations_practice {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://54.160.106.84:1000";
        basePath = "/ords/hr";

    }

    @Test
    public void testLocation(){

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("location_id",1700)
                .log().all().
                when()
                .get("/locations/{location_id}")
                .prettyPeek();

      Locations c1 = response.as(Locations.class);
        System.out.println("c1 = " + c1);


    }

    @Test
    public void testLocations2() {


        Response response = get("locations").prettyPeek();

        List<String> addressList = response.jsonPath().getList("items.street_address");

        System.out.println("addressList = " + addressList);

        addressList.forEach(each -> System.out.println(each));


        List<Locations> locationList
                = response.jsonPath().getList("items", Locations.class);

        locationList.forEach(each -> System.out.println(each));

        assertThat(locationList, hasSize(23));

        List<Locations> usLocations = response.jsonPath().getList("items.findAll{it.country_id=='US'}",Locations.class);

        usLocations.forEach(each-> System.out.println(each));


    }

    @Test
    public void testAuth(){




    }





    }



