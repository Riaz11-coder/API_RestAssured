package Day01_Junit_Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Simple_Test {

    @DisplayName("Simple Test")
    @Test
    public void nameTest(){

        String first = "Riaz";
        String last = "Ahmed";

        assertEquals("Riaz Ahmed",first+" "+last);

    }
}
