package Day09_;

import POJO.Spartan;
import Utility.DB_Utility;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.registerParser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.restassured.RestAssured.given;


public class SpartanRoleBaseAccessControlNegatives {


static RequestSpecification requestSpec;
static ResponseSpecification responseSpec;

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

        requestSpec = given()
                .auth().basic("user","user")
                .accept(ContentType.JSON)
                .log().all();

        ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder();

         responseSpec = resSpecBuilder.expectStatusCode(403)
                .expectContentType(ContentType.JSON)
                .expectHeader("date",is(notNullValue(String.class)))
                .log(LogDetail.ALL)
                .build();







    }

    @DisplayName("User should not be able to delete data")
    @Test
    public void testUserCanNotDeleteData(){

        given()
               .spec(requestSpec).

        when()
                .delete("/spartans/{id}",10).

        then()
                .log().all()
                .spec(responseSpec);





    }
    @DisplayName("User should not be able to update data")
    @Test
    public void testUserCanNotUpdateData(){


        Spartan obj1 = new Spartan("Mike","Male",7031231234l);
        given()
                .spec(requestSpec)
                .contentType(ContentType.JSON)
                .body(obj1).
                when()
                .put("/spartans/{id}",10).
                then()
               .spec(responseSpec);
    }
    @DisplayName("User should not be able to post data")
    @Test
    public void testUserCanNotPostData(){


    }

    @AfterAll
    public static void destroy(){
        DB_Utility.destroy();
    }

}
