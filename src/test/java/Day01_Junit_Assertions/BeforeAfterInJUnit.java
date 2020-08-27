package Day01_Junit_Assertions;

import org.junit.jupiter.api.*;

public class BeforeAfterInJUnit {



    @BeforeAll
    public static void setUp(){
        System.out.println("This runs once before All");
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("Running before each and every test");
    }

    @Test
    public void test1(){
        System.out.println("Test is running");
    }



    //@Disabled same as @Ignore
    @Test
    public void test2(){

        System.out.println("Test2 is running");
    }

    @AfterEach
    public void afterEachTest(){
        System.out.println("Runs after each test");
    }




    @AfterAll
    public static void tearDown(){
        System.out.println("This will clean up");
    }
}
