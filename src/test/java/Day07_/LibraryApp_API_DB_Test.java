package Day07_;
import static org.hamcrest.Matchers.*;

import Utility.LibraryUtil;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import Utility.ConfigurationReader;
import Utility.DB_Utility;
import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LibraryApp_API_DB_Test {

    private static String libraryToken;

    @BeforeAll
    public static void setUp(){

        String active_environment = ConfigurationReader.getProperty("active_env");
        libraryToken = LibraryUtil.setUpRestAssuredAndDB_forEnv(active_environment);
    }


    @Test
    public void test(){
        System.out.println("libraryToken = " + libraryToken);

        DB_Utility.runQuery("SELECT count(*) FROM books");
        String bookCount = DB_Utility.getColumnDataAtRow(1,1);

        System.out.println("bookCount = " + bookCount);

        DB_Utility.runQuery("SELECT count(*) FROM users");
        String userCount = DB_Utility.getColumnDataAtRow(1,1);

        System.out.println("userCount = " + userCount);

        DB_Utility.runQuery("SELECT count(*) FROM book_borrow WHERE is_returned=false");
        String borrowedBookCount = DB_Utility.getColumnDataAtRow(1,1);

        System.out.println("borrowedBookCount = " + borrowedBookCount);

        given()
                .log().all()
                .header("x-library-token",libraryToken).
                when()
                .get("/dashboard_stats").
                then()
                .body("book_count",is(bookCount))
                .body("users",is(userCount))
                .body("borrowed_books",is(borrowedBookCount));




    }

    @AfterAll
    public static void tearDown(){
        //closes connection to Database
        DB_Utility.destroy();
        //Rests all values
        RestAssured.reset();
    }

}
