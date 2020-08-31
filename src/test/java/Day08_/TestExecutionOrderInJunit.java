package Day08_;

import org.junit.jupiter.api.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestExecutionOrderInJunit {
    @Order(1)
    @Test
    public void testB(){



    }

    @Order(2)
    @Test
    public void testA(){

    }
    @Order(3)
    @Test
    public void testZ(){

    }
    @Order(4)
    @Test
    public void testK(){

    }


}
