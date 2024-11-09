package Lesson_5;

public class Main {
    public static void main(String[] args) {
        // Создаются два объекта класса Dog и Cat.
        Dog reks = new Dog("Рекс");
        Cat marusa = new Cat("Маруся");

        // Применяем методы класса Dog, к ссылочной переменной reks, и вводим аргумент в скобки.
        reks.run(150);
        reks.swim(8);

        // Применяем методы класса Dog, к ссылочной переменной marusa, и вводим аргумент в скобки.
        marusa.run(100);
        marusa.swim(20);

        // Выводим на экран значения из методов и количество созданных животных, котов и собак.
        System.out.println("Количество созданных животных: " + Animal.getAnimalCount());
        System.out.println("Количество созданных собак: " + Dog.getDogCount());
        System.out.println("Количество созданных котов: " + Cat.getCatCount());
    }
}
