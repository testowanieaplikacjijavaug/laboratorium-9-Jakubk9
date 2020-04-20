import org.easymock.EasyMock;
import org.easymock.MockType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

public class EasyMockVSMockitoTest {
    private Car myFerrari = mock(Car.class);
    private Car myLambo = EasyMock.createMock(MockType.NICE, Car.class);

    //verify poszczególnych wywołań w mockito
    //verify wszystkich wywołan w EasyMock

    @Test
    public void exampleMockitoTest (){
        when(myFerrari.needsFuel()).thenReturn(true);
        when(myFerrari.getEngineTemperature()).thenReturn(50.0);

        myFerrari.getEngineTemperature();
        myFerrari.needsFuel();

        verify(myFerrari).getEngineTemperature();
        verify(myFerrari).needsFuel();

        assertTrue(myFerrari.needsFuel());
        assertEquals(50.0,myFerrari.getEngineTemperature());
    }

    @Test
    public void exampleEasyMockTest() {
        EasyMock.expect(myLambo.getEngineTemperature()).andReturn(Double.MAX_VALUE);
        EasyMock.expect(myLambo.needsFuel()).andReturn(true);
        EasyMock.replay(myLambo);

        assertEquals(Double.MAX_VALUE, myLambo.getEngineTemperature());
        assertTrue(myLambo.needsFuel());

        EasyMock.verify(myLambo);
    }

    
}
