package Day09_;import POJO.Spartan;
import Utility.DB_Utility;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static POJO.Spartan.createRandomSpartanObject;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import POJO.Spartan;
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


public class SpartanPostRequestExtractingSpec {

    static RequestSpecification validPostRequestSpec;
    static ResponseSpecification validPostResponseSpec ;
    static Spartan randomSp = createRandomSpartanObject();

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://100.25.192.231";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

        validPostRequestSpec =  given()
                .auth().basic("admin","admin")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(randomSp)
                .log().all();

        ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder();
        validPostResponseSpec = resSpecBuilder.expectStatusCode(201)

                .expectHeader("date",is(notNullValue(String.class)))
                .expectBody("data.name",is(randomSp.getName()))
                .expectBody("data.gender",is(randomSp.getGender()))
                .expectBody("data.phone",is(randomSp.getPhone()))
                .expectBody("data.id",is(notNullValue())).build();


    }

    @DisplayName("Extracting the requestSpec and responseSpec practice")

    @Test
    public void test(){


        given()
                .spec(validPostRequestSpec).

        when()

                .post("/spartans").
        then()
                .spec(validPostResponseSpec);

    }

}
