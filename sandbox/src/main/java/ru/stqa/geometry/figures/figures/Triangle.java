package ru.stqa.geometry.figures.figures;

public record Triangle (
        double side1,
        double side2,
        double side3
) {

    public static void printPerimeter(Triangle triangle) {
        System.out.printf("Периметр треугольника со сторонами %f, %f и %f = %f%n", triangle.side1, triangle.side2, triangle.side3, triangle.perimeterOfTriangle());
    }

    public static void printArea(Triangle triangle) {
        System.out.printf("Площадь треугольника со сторонами %f, %f и %f = %f%n", triangle.side1, triangle.side2, triangle.side3, triangle.areaOfTriangle());
    }

    public double perimeterOfTriangle() {
        return this.side1 + this.side2 + this.side3;
    }

    public double areaOfTriangle() {
        var halfOfPerimetr = (this.side1 + this.side2 + this.side3) / 2;
        return Math.sqrt(halfOfPerimetr*(halfOfPerimetr - this.side1) *
                (halfOfPerimetr - this.side2) * (halfOfPerimetr - this.side3));
    }

}
