package kalkulator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KalkulatorTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldCheckAddition() {
        //Given
        Kalkulator kalkulator = new Kalkulator();
        //when
        String expected = "7.0";
        String[] args = {"3+4="};
        //Then
        assertEquals(expected, kalkulator.oblicz(args));
    }

    @Test
    void shouldCheckSubtraction() {
        //Given
        Kalkulator kalkulator = new Kalkulator();
        //when
        String expected = "7.0";
        String[] args = {"10-3="};
        //Then
        assertEquals(expected, kalkulator.oblicz(args));
    }

    @Test
    void shouldCheckMultiplication() {
        //Given
        Kalkulator kalkulator = new Kalkulator();
        //when
        String expected = "7.0";
        String[] args = {"2*3.5="};
        //Then
        assertEquals(expected, kalkulator.oblicz(args));
    }

    @Test
    void shouldCheckDivision() {
        //Given
        Kalkulator kalkulator = new Kalkulator();
        //when
        String expected = "7.5";
        String[] args = {"15/2="};
        //Then
        assertEquals(expected, kalkulator.oblicz(args));
    }
}