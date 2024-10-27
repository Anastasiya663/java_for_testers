package ru.stqa.geometry.figures.figures;

public record Square (double side) {//record для сокращения записи свойств и конструктора

    public Square {
        if (side < 0) {
            throw new IllegalArgumentException("Square side should be non-negative");
        }
    }

    public static void printArea(Square s) {
        System.out.println(String.format("Площадь квадрата со стороной %f = %f", s.side, s.area()));
    }

    public static void printPerimeter(Square s) {
        System.out.println(String.format("Периметр квадрата со стороной %f = %f", s.side, s.perimetr()));
    }

    /*public static double area(double a) {
        return a * a;
    }

    public static double perimetr(double a) {
        return 4 * a;
    }*/

    //теперь данные берутся не из параметров функции, а из свойств объекта
    public double area() {
        return this.side * this.side;
    }

    public double perimetr() {
        return 4 * this.side;
    }
}
