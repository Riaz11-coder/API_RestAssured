package Day07_;

import Utility.DB_Utility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MySQL_Test {

    @BeforeAll
    public static void initDB(){
        DB_Utility.createConnection("library1");
    }

    @Test
    public void testThingsOut(){

       DB_Utility.runQuery("SELECT * FROM books") ;
       DB_Utility.displayAllData();

    }

    @AfterAll
    public static void destroy(){
        DB_Utility.destroy();
    }
}
