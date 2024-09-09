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
}