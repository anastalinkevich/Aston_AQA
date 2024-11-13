package Lesson_5;

public class Dog extends Animal{
    private int maxRunDistance = 500;
    private int maxSwimDistance = 10;
    private static int dogCount = 0;

// Конструктор класса Dog.
// Увеличивает количество созданных собак на 1 и возвращает данные в переменную.
    public Dog(String name){
        super(name);
        dogCount++;
    }
// Метод, который хранит количество созданных собак
    public static int getDogCount(){
        return dogCount;
    }

// Метод класса Dog, который переопределен от родительского класса Animal.
// У метода есть ограничения в дистанции, сколько собака может пробежать.
    @Override
    public void run(int distance){
        if (distance <= maxRunDistance) {
            System.out.println(name + " пробежал(а) " + distance + " м.");
        } else {
            System.out.println(name + "  не может пробежать более " + maxRunDistance + " метров.");
        }
    }

// Метод класса Dog, который переопределен от родительского класса Animal.
// У метода есть ограничения в дистанции, сколько собака может проплыть.
    @Override
    public void swim(int distance){
        if (distance <= maxSwimDistance) {
            System.out.println(name + " проплыл(а) " + distance + " м.");
        } else {
            System.out.println(name + "  не может проплыть более " + maxSwimDistance + " метров.");
        }
    }
}

