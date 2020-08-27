package Day04_;

import POJO.Spartan;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;


public class Practice1 {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://52.86.72.131:8000";
        basePath = "/api";

    }
    @DisplayName("Post using Java_Faker")
    @Test
    public void testPost(){
        Faker faker = new Faker();

        String randomName = faker.name().firstName();
        String randomPhone = faker.phoneNumber().subscriberNumber(10);
        System.out.println("randomName = " + randomName);
        String myPostData = "{\n" +
                "  \"name\"  : \""+randomName+"\",\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"phone\": "+randomPhone+"\n" +
                "}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(myPostData).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .body("data.name",is(randomName));




    }
    @DisplayName("Post using external Json file")
    @Test
    public void testPostWithExternalFile(){

        File file1 = new File("spartan.json");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(file1).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .body("data.name",is("Martha"));

    }
    @DisplayName("Map post with jackson into Json")
    @Test
    public void jacksonMapTest(){
        //jackson library turns Map object into Json
        Map<String,Object> bodyMap = new LinkedHashMap<>();

        bodyMap.put("name","Adnan");
        bodyMap.put("gender","Male");
        bodyMap.put("phone",2345678901l);

        System.out.println("bodyMap = " + bodyMap);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .body("data.name",is("Adnan"));
    }
    @DisplayName("Post with POJO")
    @Test
    public void testWithPojo(){

        Spartan sp1 = new Spartan("Adnan","Male",7031231321l);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(sp1).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201);
    }
    @DisplayName("Put with Map and POJO/checked update with Get request")
    @Test
    public void testPutAndPatch(){
        String randomName  = new Faker().name().firstName();
        Map<String,Object> map1 = new LinkedHashMap<>();

        map1.put("name",randomName);
        map1.put("gender","Male");
        map1.put("phone",2345678901l);

        Spartan sp1 = new Spartan(randomName,"Male",1231231234l);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                //.body(map1)
                .body(sp1).
                when()
                .put("/spartans/{id}",99).
                then()
                .log().all()
                .statusCode(is(204));
        when()
                .get("/spartans/{id}",99).
                then()
                .log().all()
                .statusCode(200)
                .body("name", is(randomName));




    }
    @DisplayName("Patch request")
    @Test
    public void patchRequest(){
        String randomName = new Faker().name().firstName();
        Map<String,Object> map1 = new LinkedHashMap<>();
        map1.put("name",randomName);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(map1).
                when()
                .patch("/spartans/{id}",90).
                then()
                .log().all()
                .statusCode(204)
                ;

    }







    }
