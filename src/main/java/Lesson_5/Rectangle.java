package Lesson_5;

public class Rectangle implements RectangleShape {
    private final double width;
    private final double height;
    private final String fillColor;
    private final String borderColor;

// Конструктор класса Rectangle
    public Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getWidth() {
        return width;
    }
    @Override
    public double getHeight(){
        return height;
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
        return "Прямоугольник";
    }
    public void infoRectangle(){
        System.out.println("Фигура: " + getName() + "\n" +
                "Периметр: " + String.format("%.1f", getPerimeter()) + "\n" +
                "Площадь: " + String.format("%.1f", getArea()) + "\n" +
                "Цвет фона: " + getFillColor() + "\n" +
                "Цвет границы: " + getBorderColor() + "\n");
    }
}
