package ru.stqa.geometry.figures.figures;

public record Square (double side) { //record для сокращения записи свойств и конструктора

    public static void printSquareArea(Square s) {
        System.out.println(String.format("Площадь квадрата со стороной %f = %f", s.side, s.area()));
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
