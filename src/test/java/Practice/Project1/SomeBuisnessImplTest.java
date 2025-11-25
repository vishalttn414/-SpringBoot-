package Practice.Project1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeBuisnessImplTest {

    @Test
    void test(){
        DataServiceStub dataServiceStub=new DataServiceStub();
        SomeBuisnessImpl someBuisness=new SomeBuisnessImpl(dataServiceStub);
        int max = someBuisness.findMax();
        assertEquals(25,max);
    }
}

class DataServiceStub implements DataService{

    @Override
    public int[] retrieveAllData()
    {
        return new int[]{25,15,5};
    }
}