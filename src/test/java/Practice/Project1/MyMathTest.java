package Practice.Project1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MyMathTest {

    @BeforeEach
    void test(){
         MyMath myMath=new MyMath();
         int expectedresult=5;
        assertEquals(expectedresult, myMath.sum(4,3));
        assertTrue(6>0,"Test passed");

    }

    @AfterEach
    void testSum1(){
        MyMath myMath=new MyMath();
        int expectedResult = 5;
        int actualResult = myMath.sum(2,3);
        assertEquals(expectedResult, actualResult, "Sum should be 5");
    }

    @Test
    void testSum2(){
        MyMath myMath=new MyMath();
        int expectedResult = 5;
        int actualResult = myMath.sum(2,4);
        assertEquals(expectedResult, actualResult, "Sum should be 5");
    }
}
