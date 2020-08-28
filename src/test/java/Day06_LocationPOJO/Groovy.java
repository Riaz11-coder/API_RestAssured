package Day06_LocationPOJO;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;


public class Groovy {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.174.216.245/" ;
        RestAssured.port = 1000 ;
        RestAssured.basePath = "ords/hr";

    }

    @DisplayName("Testing the /Employees endpoint")
    @Test
    public void testEmployees() {

        Response response = get("/employees");

        JsonPath jp = response.jsonPath();

        System.out.println("All ID's "+ jp.getList("items.employee_id"));

        System.out.println("First ID "+jp.getInt("items[0].employee_id"));

        System.out.println("Last ID "+jp.getInt("items[-1].employee_id"));

        System.out.println("List of ID's "+jp.getList("items[0..4].employee_id"));

        System.out.println("List of ID's "+jp.getList("items[0..10].employee_id"));

        System.out.println("All last names "+jp.getList("items[0..14].last_name"));

        String result = (jp.getString("items.find{it.employee_id == 105 }.last_name"));

        System.out.println("result = " + result);

        int result2 = jp.getInt("items.find{it.email == 'LDEHAAN'}.salary");

        System.out.println("result2 = " + result2);

        List<String> richPeople = jp.getList("items.findAll{it.salary > 15000}.first_name");
        System.out.println("richPeople = " + richPeople);

        List<String> phoneNumber = jp.getList("items.findAll{it.department_id == 90}.phone_number");

        System.out.println("phoneNumber = " + phoneNumber);

        int maxSalary = jp.getInt("items.max{it.salary}.salary");
        System.out.println("maxSalary = " + maxSalary);

        String richestGuy = jp.getString("items.max{it.salary}.first_name");
        System.out.println("richestGuy = " + richestGuy);
    }

   




    }