package Practice.Project1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SomeBuisnessImplMockTest {

//    @Test
//    void test(){
//        DataService dataServiceMock = mock(DataService.class);
//        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25,15,5});
//        SomeBuisnessImpl someBuisness=new SomeBuisnessImpl(dataServiceMock);
//        int max = someBuisness.findMax();
//        assertEquals(25,max);
//    }
    @Mock
    private DataService dataServiceMock;

    @InjectMocks
    private SomeBuisnessImpl buisnessImpl;

    @Test
    void test(){
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25,15,5});
        int max = buisnessImpl.findMax();
        assertEquals(25,max);
    }
}
