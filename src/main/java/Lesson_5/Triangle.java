package Lesson_5;

public class Triangle implements TriangleShape {
    private final double side;
    private final String fillColor;
    private final String borderColor;

// Конструктор класса Triangle (равносторонний треугольник для упрощения примера)
    public Triangle(double side, String fillColor, String borderColor) {
        this.side = side;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getSide() {
        return side;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }

    @Override
    public String getName(){
        return "Равносторонний треугольник";
    }

    public void infoTriangle(){
        System.out.println("Фигура: " + getName() + "\n" +
                "Периметр: " + String.format("%.1f", getPerimeter()) + "\n" +
                "Площадь: " + String.format("%.1f", getArea()) + "\n" +
                "Цвет фона: " + getFillColor() + "\n" +
                "Цвет границы: " + getBorderColor() + "\n");
    }
}
