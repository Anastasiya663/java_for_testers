package ru.stqa.geometry.figures;

import ru.stqa.geometry.figures.figures.Rectangle;
import ru.stqa.geometry.figures.figures.Square;
import ru.stqa.geometry.figures.figures.Triangle;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
//        var squares = List.of(new Square(7.0), new Square(5.0),new Square(3.0));
//        for (Square square : squares) {
//            Square.printSquareArea(square);
//        }

//        Consumer<Square> print = (square) -> { //тот же код, что и выше, но в функциональном стиле
//            Square.printSquareArea(square);
//        };
//        squares.forEach(print); //будет применена ко всем элементам списка (Consumer принимает на вход параметры, но ничего не возвращает)

//        Square.printSquareArea(new Square(7.0));
//        Square.printSquareArea(new Square(5.0));
//        Square.printSquareArea(new Square(3.0));
//
//        Rectangle.printRectangleArea(3, 5);
//        Rectangle.printRectangleArea(9, 8);
//
//        Triangle.printPerimeter(new Triangle(5, 8, 6));
//        Triangle.printArea(new Triangle(6, 8, 10));
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0)); // функция - "генератор": ничего не принимает на вход,
                                                                                                // только возвращает/генерирует данные
        var squares = Stream.generate(randomSquare).limit(5);
        Consumer<Square> print = (square) -> {
           Square.printArea(square);
           Square.printArea(square);
       };
        squares.peek(Square::printArea).forEach(Square::printPerimeter);
    }
}
