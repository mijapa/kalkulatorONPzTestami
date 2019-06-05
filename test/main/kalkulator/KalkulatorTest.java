package main.kalkulator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class KalkulatorTest {
    @Test
    void shouldCheckProperEnding() {
        //Given
        Kalkulator kalkulator = new Kalkulator();
        //when
        String[] args = {"1+1"};
        //Then
        assertThrows(IllegalArgumentException.class, () -> kalkulator.oblicz(args));
    }

    @Test
    void shouldCheckEmptyArg() {
        //Given
        Kalkulator kalkulator = new Kalkulator();
        //when
        String[] args = {""};
        //Then
        assertThrows(IllegalArgumentException.class, () -> kalkulator.oblicz(args));
    }

    @Test
    void shouldCheckNullInput() {
        //Given
        Kalkulator kalkulator = new Kalkulator();
        //when
        String[] args = null;
        //Then
        assertThrows(IllegalArgumentException.class, () -> kalkulator.oblicz(args));
    }

    @Test
    void shouldCheckSomeOfEverything() {
        //Given
        Kalkulator kalkulator = new Kalkulator();
        //when
        String expected = "11.25";
        String[] args = {"1+2-3/4*5="};
        //Then
        Assertions.assertEquals(expected, kalkulator.oblicz(args));
    }

    @Nested
    class Multiplication {
        @Test
        void shouldCheckSimpleMultiplication() {
            //Given
            Kalkulator kalkulator = new Kalkulator();
            //when
            String expected = "7.0";
            String[] args = {"2*3.5="};
            //Then
            Assertions.assertEquals(expected, kalkulator.oblicz(args));
        }

        @Test
        void shouldCheckComplexMultiplication() {
            //Given
            Kalkulator kalkulator = new Kalkulator();
            //when
            String expected = "362880.0";
            String[] args = {"1*2*3*4*5*6*7*8*9="};
            //Then
            Assertions.assertEquals(expected, kalkulator.oblicz(args));
        }
    }

    @Nested
    class Division {
        @Test
        void shouldCheckSimpleDivision() {
            //Given
            Kalkulator kalkulator = new Kalkulator();
            //when
            String expected = "7.5";
            String[] args = {"15/2="};
            //Then
            Assertions.assertEquals(expected, kalkulator.oblicz(args));
        }

        @Test
        void shouldCheckComplexDivision() {
            //Given
            Kalkulator kalkulator = new Kalkulator();
            //when
            String expected = "1.5625";
            String[] args = {"100/8/4/2/1="};
            //Then
            Assertions.assertEquals(expected, kalkulator.oblicz(args));
        }
    }

    @Nested
    class Subtraction {
        @Test
        void shouldCheckSimpleSubtraction() {
            //Given
            Kalkulator kalkulator = new Kalkulator();
            //when
            String expected = "7.0";
            String[] args = {"10-3="};
            //Then
            Assertions.assertEquals(expected, kalkulator.oblicz(args));
        }

        @Test
        void shouldCheckComplexSubtraction() {
            //Given
            Kalkulator kalkulator = new Kalkulator();
            //when
            String expected = "-43.0";
            String[] args = {"1-2-3-4-5-6-7-8-9="};
            //Then
            Assertions.assertEquals(expected, kalkulator.oblicz(args));
        }
    }

    @Nested
    class Addition {
        @Test
        void shouldCheckSimpleAddition() {
            //Given
            Kalkulator kalkulator = new Kalkulator();
            //when
            String expected = "7.0";
            String[] args = {"3+4="};
            //Then
            Assertions.assertEquals(expected, kalkulator.oblicz(args));
        }

        @Test
        void shouldCheckComplexAddition() {
            //Given
            Kalkulator kalkulator = new Kalkulator();
            //when
            String expected = "45.0";
            String[] args = {"1+2+3+4+5+6+7+8+9="};
            //Then
            Assertions.assertEquals(expected, kalkulator.oblicz(args));
        }
    }
}