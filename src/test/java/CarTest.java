import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CarTest {
    private Car myFerrari;

    @BeforeEach
    public void setUp() {
        myFerrari = mock(Car.class);
    }

    @Test
    public void test_instance_car(){
        assertTrue(myFerrari instanceof Car);
    }

    @Test
    public void test_default_behavior_needsFuel(){
        assertFalse(myFerrari.needsFuel(), "New test double should return False as boolean");
    }

    @Test
    public void test_default_behavior_temperature(){
        assertEquals(0.0, myFerrari.getEngineTemperature(), "New test double should return 0.0");
    }

    @Test
    public void test_stubbing_mock(){
        when(myFerrari.needsFuel()).thenReturn(true);
        assertTrue(myFerrari.needsFuel());
    }

    @Test
    public void test_exception(){
        when(myFerrari.needsFuel()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> {
            myFerrari.needsFuel();
        });
    }

    @Test
    public void testVerification(){
        myFerrari.driveTo("Kartuzy");
        myFerrari.needsFuel();

        verify(myFerrari).driveTo("Kartuzy");
        verify(myFerrari).needsFuel();
        assertFalse(myFerrari.needsFuel());
    }

    @Test
    public void stubTemperatureTest() {
        when(myFerrari.getEngineTemperature()).thenReturn(50.0);

        myFerrari.getEngineTemperature();

        verify(myFerrari).getEngineTemperature();

        assertEquals(50.0,myFerrari.getEngineTemperature());
    }

    @Test
    public void verifyUnusedStubTest() {
        when(myFerrari.getEngineTemperature()).thenReturn(50.0);

        verify(myFerrari,never()).getEngineTemperature();
    }

    @Test
    public void stubingOneFunctionFewTimesTest() {
        when(myFerrari.getEngineTemperature()).thenReturn(50.0);
        when(myFerrari.getEngineTemperature()).thenReturn(10.0);
        when(myFerrari.getEngineTemperature()).thenReturn(90.0);

        myFerrari.getEngineTemperature();

        verify(myFerrari).getEngineTemperature();

        assertEquals(90.0,myFerrari.getEngineTemperature());
    }

    @Test
    public void throwExpectionTest() {
        doThrow(new RuntimeException()).when(myFerrari)
                .getEngineTemperature();
        assertThrows(RuntimeException.class, () -> myFerrari.getEngineTemperature());
    }

    @AfterEach
    public void tearDown() {
        myFerrari = null;
    }

}