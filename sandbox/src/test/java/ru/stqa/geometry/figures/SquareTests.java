package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.geometry.figures.figures.Square;

public class SquareTests {
    @Test
    void canCalculateArea() {
        var s = new Square(4);
        Assertions.assertEquals(25, s.area());
        /*if (s.area() != 25.0) {
            throw new AssertionError(String.format("Expected %f, actual %f", 25.0, s.area()));
        }*/
    }

    @Test
    void canCalculatePerimetr() {
        Assertions.assertEquals(20, new Square(5).perimetr());
    }

    @Test
    void cannotCreateSquareWithNegativeSide() {
        try {
            new Square(-5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ОК
        }
    }

    @Test
    void testEquality() {
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        Assertions.assertEquals(s1, s2);
    }

    @Test
    void testNonEquality() {
        var s1 = new Square(5.0);
        var s2 = new Square(4.0);
        Assertions.assertNotEquals(s1, s2);
    }

    @Test
    void testFail () {
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        Assertions.assertTrue(s1.equals(s2));
    }
}
