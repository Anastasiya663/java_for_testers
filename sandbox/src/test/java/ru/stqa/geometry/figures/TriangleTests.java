package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.geometry.figures.figures.Triangle;

public class TriangleTests {

    @Test
    void canCalculateArea () {
        Assertions.assertEquals(24, new Triangle(6, 8, 10).areaOfTriangle());
    }

    @Test
    void canCalculatePerimeter () {
        Assertions.assertEquals(19, new Triangle(5, 8, 6).perimeterOfTriangle());
    }

    @Test
    void cannotCreateTriangleWithNegativeSide1() {
        try {
            new Triangle(-5.0, 3.0, 6.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ОК
        }
    }

    @Test
    void cannotCreateTriangleWithNegativeSide2() {
        try {
            new Triangle(5.0, -3.0, 6.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ОК
        }
    }

    @Test
    void cannotCreateTriangleWithNegativeSide3() {
        try {
            new Triangle(5.0, 3.0, -6.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ОК
        }
    }

    @Test
    void checkSumOfSides() {
        try {
            new Triangle(1.0, 2.0, 6.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ОК
        }
    }
}