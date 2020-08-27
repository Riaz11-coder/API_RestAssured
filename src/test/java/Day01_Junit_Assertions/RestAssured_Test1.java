package Day01_Junit_Assertions;


import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssured_Test1 {

     String spartanBaseUrl = "http://52.86.72.131:8000";

    @Test
    public void test1(){

        Response response = get(spartanBaseUrl+"/api/hello");

        System.out.println(response.statusCode());



        System.out.println(response.statusLine());





        System.out.println("Today's Date is: " + response.header("Date"));


        System.out.println(response.header("Content-Type"));

        response.body().prettyPrint();


    }

    @Test
    public void testHello(){

        Response response = get(spartanBaseUrl+"/api/hello");

        assertEquals(response.statusCode(),200);

        assertEquals("text/plain;charset=UTF-8",response.contentType());

        assertEquals("17",response.header("Content-Length"));


    }
    @DisplayName("Testing the body")
    @Test
    public void testingHelloResponseBody(){

        Response response = get(spartanBaseUrl+"/api/hello");

        System.out.println(response.body().asString());

        String helloBody = response.asString();

        assertEquals("Hello from Sparta",helloBody);

        assertEquals(17, helloBody.length());


    }

    @DisplayName("Printing the body")
    @Test
    public void printingBody(){

        Response response = get(spartanBaseUrl+"/api/hello");

        response.prettyPrint();
        //prints out all the return info, Header, body, status line, content-length, date
        int statusCode = response.prettyPeek().statusCode();

        System.out.println("PRINTING ONLY THE STATUS CODE "+statusCode);
    }
}
