package Lesson_5;

public interface CircleShape {
    double getRadius();

// Дефолтный метод расчета периметра для всех кругов
    default double getPerimeter() {
        return 2 * Math.PI * getRadius();
    }

// Дефолтный метод расчета площади для всех кругов
    default double getArea() {
        return Math.PI * Math.pow(getRadius(), 2);
    }

    String getFillColor(); // Метод возвращает цвет фона
    String getBorderColor(); // Метод возвращает цвет границы
    String getName(); // Метод возвращает имя
}
