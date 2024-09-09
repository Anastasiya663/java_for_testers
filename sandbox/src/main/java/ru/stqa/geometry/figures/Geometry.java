package ru.stqa.geometry.figures;

import ru.stqa.geometry.figures.figures.Rectangle;
import ru.stqa.geometry.figures.figures.Square;
import ru.stqa.geometry.figures.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(7.0));
        Square.printSquareArea(new Square(5.0));
        Square.printSquareArea(new Square(3.0));

        Rectangle.printRectangleArea(3, 5);
        Rectangle.printRectangleArea(9, 8);

        Triangle.printPerimeter(new Triangle(5, 8, 6));
        Triangle.printArea(new Triangle(6, 8, 10));
    }
}
