package Lesson_5;

public class Cat extends Animal{
    public int maxRunDistance = 200;
    public int maxSwimDistance = 0;
    private static int catCount = 0;

    // Конструктор класса Cat, который наследует конструктор от родителя Animal.
    // Увеличивает количество созданных котов на 1 и возвращает данные в переменную.
    public Cat(String name) {
        super(name);
        catCount++;
    }

    // Метод, который хранит количество созданных котов
    public static int getCatCount(){
        return catCount;
    }

    // Метод класса Cat, который переопределен от родительского класса Animal.
    // У метода есть ограничения в дистанции, сколько кот может пробежать.
    public void run(int distance){
        if (distance <= maxRunDistance) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + "  не может пробежать более " + maxRunDistance + " метров.");
        }
    }

    // Метод класса Cat, который переопределен от родительского класса Animal.
    // У метода есть ограничения в дистанции. Кот не умеет плавать
    public void swim(int distance){
        if (distance > maxSwimDistance) {
            System.out.println(name + " не может проплыть более " + maxRunDistance + " метров.");
        }
    }
}
