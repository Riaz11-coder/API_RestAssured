package Day07_;

import Utility.LibraryUtil;
import org.junit.jupiter.api.Test;

import Utility.ConfigurationReader;
import Utility.DB_Utility;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LibraryApp_API_DB_Test {

    private static String libraryToken;

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("baseURL");
        RestAssured.basePath = "/rest/v1";
        DB_Utility.createConnection("library1");
        libraryToken = LibraryUtil.loginAndGetToken(ConfigurationReader.getProperty("library1.librarian_username"),
        ConfigurationReader.getProperty("library1.librarian_password"));
    }


    @Test
    public void test(){
        System.out.println("libraryToken = " + libraryToken);
    }

    @AfterAll
    public static void tearDown(){
        //closes connection to Database
        DB_Utility.destroy();
        //Rests all values
        RestAssured.reset();
    }

}
