package Day07_;

import Utility.ConfigurationReader;
import Utility.DB_Utility;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class LibraryApp_API_DB_Test {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("baseURL");
        RestAssured.basePath = "/rest/v1";
        DB_Utility.createConnection("library1");
    }

    @AfterAll
    public static void tearDown(){
        //closes connection to Database
        DB_Utility.destroy();
        //Rests all values
        RestAssured.reset();
    }

}
