package Day01_Junit_Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
//Hamcrest assertion Library makes validation more readable
public class Practice_hamcrest {

    @Test
    public void test(){

       // assert 5+4 is 9

        int num1 = 5, num2 = 4;

        assertThat(num1 + num2,is(9));

        assertThat(num1 + num2,equalTo(9));

        assertThat(num1 + num2,is(equalTo(9)));

        //negative assertions

        assertThat(num1+num2,not(11));

        assertThat(num1+num2,is(not(11)));

        assertThat(num1+num2,not(equalTo(11)));






    }

    @Test
    public void nameAssertion(){

        String firstName = "Riaz ";
        String lastName = "Ahmed";
        String firstName1 = "Riaz ";

        assertThat(firstName+lastName,is("Riaz Ahmed"));

        assertThat(firstName+lastName,is(equalTo("Riaz Ahmed")));

        //Negative Assertion

        assertThat(firstName+lastName,not("Riaz Hamed"));

        assertThat(firstName+lastName,is(not("Riaz Hamed")));

        assertThat(firstName+lastName,not(equalTo("Riaz Hamed")));

        assertThat(firstName,equalToIgnoringCase("riaz "));

        assertThat(firstName1,equalToCompressingWhiteSpace("Riaz"));

        assertThat(firstName,endsWith("z "));

        assertThat(firstName,startsWith("Ri"));

        assertThat(firstName,containsString("ia"));
    }
    @DisplayName("Support for collection")
    @Test
    public void test2(){

        List<Integer> numList = Arrays.asList(11,22,33,44,55);

        assertThat(numList,hasSize(5));

        assertThat(numList.size(),is(5));

        assertThat(numList,hasItem(11));

        assertThat(numList,hasItems(11,33,55));

        assertThat(numList,contains(11,22,33,44,55));


    }




}
