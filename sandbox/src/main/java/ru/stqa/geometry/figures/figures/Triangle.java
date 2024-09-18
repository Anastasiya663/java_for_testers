package ru.stqa.geometry.figures.figures;

import javax.xml.transform.TransformerConfigurationException;
import java.util.Objects;

public record Triangle (
        double side1,
        double side2,
        double side3
) {

    public Triangle {
        if (side1 < 0 || side2 < 0 || side3 < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        } else if ((side1 + side2) < side3 || (side1 + side3) < side2 || (side2 + side3) < side1) {
            throw new IllegalArgumentException("Triangle doesn't exist");
        }
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.side1, triangle.side1) == 0 && Double.compare(this.side2, triangle.side2) == 0 && Double.compare(this.side3, triangle.side3) == 0)
                || (Double.compare(this.side1, triangle.side1) == 0 && Double.compare(this.side3, triangle.side2) == 0 && Double.compare(this.side2, triangle.side3) == 0)
                || (Double.compare(this.side3, triangle.side1) == 0 && Double.compare(this.side1, triangle.side2) == 0 && Double.compare(this.side2, triangle.side3) == 0)
                || (Double.compare(this.side3, triangle.side1) == 0 && Double.compare(this.side2, triangle.side2) == 0 && Double.compare(this.side1, triangle.side3) == 0)
                || (Double.compare(this.side2, triangle.side1) == 0 && Double.compare(this.side1, triangle.side2) == 0 && Double.compare(this.side3, triangle.side3) == 0)
                || (Double.compare(this.side2, triangle.side1) == 0 && Double.compare(this.side3, triangle.side2) == 0 && Double.compare(this.side1, triangle.side3) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(side1, side2, side3);
    }
}
