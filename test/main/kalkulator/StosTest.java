package test.kalkulator;

import main.kalkulator.Stos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StosTest {
    @Test
    void shouldCheckEmptyStack() {
        //Given
        Stos stos = new Stos();
        //when

        //Then
        Assertions.assertTrue(stos.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, () -> stos.top());
        assertThrows(IndexOutOfBoundsException.class, () -> stos.pop());
    }

    @Test
    void shouldCheckStackTop() {
        //Given
        Stos stos = new Stos();
        //When
        stos.push("1");
        stos.push("2");
        String expected = "3";
        stos.push(expected);
        //Then
        Assertions.assertEquals(expected, stos.top());
        Assertions.assertEquals(expected, stos.top());
        Assertions.assertEquals(expected, stos.top());
        Assertions.assertFalse(stos.isEmpty());
    }

    @Test
    void shouldCheckStackPopWithOneElement() {
        //Given
        Stos stos = new Stos();
        //when
        String expected = "3";
        stos.push(expected);
        //Then
        assertSame(expected, stos.pop());
        Assertions.assertTrue(stos.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, () -> stos.pop());
    }

    @Test
    void shouldCheckStackPopWithThreeElements() {
        //Given
        Stos stos = new Stos();
        //when
        String first = "1";
        String second = "2";
        String third = "3";
        stos.push(first);
        stos.push(second);
        stos.push(third);
        //Then
        assertSame(third, stos.pop());
        assertSame(second, stos.pop());
        assertSame(first, stos.pop());
        Assertions.assertTrue(stos.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, () -> stos.pop());
    }

    @Test
    void shouldCheckNullPOP() {
        //Given
        Stos stos = new Stos();
        //when
        String expected = null;
        stos.push(expected);
        //Then
        assertNull(stos.pop());
        Assertions.assertTrue(stos.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, () -> stos.pop());
    }

    @Test
    void shouldCheckStackWorksFineAfterExcepiton() {
        //Given
        Stos stos = new Stos();
        //when
        try {
            stos.pop();
        } catch (IndexOutOfBoundsException e) {
        }
        String expected = "1";
        stos.push(expected);
        //Then
        assertSame(expected, stos.pop());
        Assertions.assertTrue(stos.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, () -> stos.pop());
    }
}