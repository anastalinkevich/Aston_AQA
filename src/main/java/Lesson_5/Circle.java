package Lesson_5;

public class Circle implements CircleShape {
    private final double radius;
    private final String fillColor;
    private final String borderColor;

// Конструктор класса Circle
    public Circle(double radius, String fillColor, String borderColor){
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getRadius() { // Переопределенный метод из интерфейса
        return radius;
    }

    @Override
    public String getFillColor() { // Возвращает цвет фона
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    } // Возвращает цвет границы

    @Override
    public String getName(){ // Возвращает имя фигуры
        return "Круг";
    }

// Метод собирает все данные о фигуре и выводит в консоль
    public void infoCircle(){
        System.out.println("Фигура: " + getName() + "\n" +
                "Периметр: " + String.format("%.1f", getPerimeter()) + "\n" +
                "Площадь: " + String.format("%.1f", getArea()) + "\n" +
                "Цвет фона: " + getFillColor() + "\n" +
                "Цвет границы: " + getBorderColor() + "\n");
    }
}
