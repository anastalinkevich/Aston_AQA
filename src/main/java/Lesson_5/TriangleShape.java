package Lesson_5;

public interface TriangleShape {
    double getSide();

// Дефолтный метод расчета периметра для равносторонних треугольников
    default double getPerimeter() {
        return getSide() * 3;
    }

// Дефолтный метод расчета площади для равносторонних треугольников
    default double getArea(){
        return (Math.pow(getSide(), 2) * Math.sqrt(3)) / 4;
    }

    String getFillColor();
    String getBorderColor();
    String getName();
}
