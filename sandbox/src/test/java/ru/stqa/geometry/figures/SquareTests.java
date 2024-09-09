package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.geometry.figures.figures.Square;

public class SquareTests {
    @Test
    void canCalculateArea() {
        var s = new Square(5);
        Assertions.assertEquals(25, s.area());
    }

    @Test
    void canCalculatePerimetr() {
        Assertions.assertEquals(20, new Square(5).perimetr());
    }
}
