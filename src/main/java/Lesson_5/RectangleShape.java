package Lesson_5;

public interface RectangleShape {
    double getWidth();
    double getHeight();

// Дефолтный метод расчета периметра для прямоугольников
    default double getPerimeter() {
        return 2 * (getWidth() + getHeight());
    }

// Дефолтный метод расчета площади для прямоугольников
    default double getArea() {
        return getWidth() * getHeight();
    }

    String getFillColor();
    String getBorderColor();
    String getName();
}
