package Lesson_5;

public class Main {
    public static void main(String[] args) {
// Создаем два объекта класса Dog и Cat.
        Dog reks = new Dog("Рекс");
        Cat marusa = new Cat("Маруся");

// Применяем методы класса Dog, к ссылочной переменной reks.
        reks.run(150);
        reks.swim(8);

// Применяем методы класса Dog, к ссылочной переменной marusa.
        marusa.run(100);
        marusa.swim(20);

// Создаем объект миски и еще одного кота, но с другим конструктором.
// Наполняем миску кота.
        Bowl bowl = new Bowl(20);
        Cat mila = new Cat("Мила", bowl);

// Выводим на экран значения из методов и количество созданных животных, котов и собак.
        System.out.println("Количество созданных животных: " + Animal.getAnimalCount());
        System.out.println("Количество созданных собак: " + Dog.getDogCount());
        System.out.println("Количество созданных котов: " + Cat.getCatCount());

// Вызываем у нового кота метод, позволяющий кушать из миски.
        mila.eatBowl(5);
        System.out.println(mila.isCatFull());

        System.out.println();
// Создаем общую миску.
        Bowl bowlCats = new Bowl(30);

//Метод добавляющий в миску еду
        bowlCats.addFood(15);

// Создан и наполнен массив из котов, которые едят из одной миски.
        Cat[] arrayCat = new Cat[3];
        arrayCat[0] = new Cat("Арни", bowlCats);
        arrayCat[1] = new Cat("Рори", bowlCats);
        arrayCat[2] = new Cat("Мия", bowlCats);

//Цикл для массива котов, которые едят из одной миски.
//Каждый кот ест одинаковое количество еды.
        for (Cat cats : arrayCat) {
            cats.eatBowl(20);
        }

        System.out.println("\n" + "Информация о сытости котов:" + "\n");

//С помощью цикла выводиться информация о сытости котов: true или false
        for (Cat cats : arrayCat) {
            System.out.println(cats.name + ": " + cats.isCatFull());
        }
    }
}
