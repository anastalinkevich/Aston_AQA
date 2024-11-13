package Lesson_5;

public class Cat extends Animal{
    public int maxRunDistance = 200;
    public int maxSwimDistance = 0;
    private static int catCount = 0;
    public Bowl bowl;
    public boolean isCatFull; // поле для сытости кота

// Конструктор класса Cat, который наследует конструктор от родителя Animal.
// Увеличивает количество созданных котов на 1 и возвращает данные в переменную.
    public Cat(String name) {
        super(name);
        catCount++;
    }

//Второй конструктор, для кота который может кушать из миски
    public Cat(String name, Bowl bowl) {
        super(name);
        this.bowl = bowl;
        this.isCatFull = false; // все коты голодные изначально
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
            System.out.println(name + " пробежал(а) " + distance + " м.");
        } else {
            System.out.println(name + "  не может пробежать более " + maxRunDistance + " метров.");
        }
    }

// Метод класса Cat, который переопределен от родительского класса Animal.
// У метода есть ограничения в дистанции. Кот не умеет плавать
    public void swim(int distance){
        if (distance > maxSwimDistance) {
            System.out.println(name + " не умеет плавать.");
        }
    }

// Метод класса Cat, умение кота кушать из миски
    public void eatBowl(int amountEat){
        if (bowl.getFoodBowl() >= amountEat){
            bowl.decreaseFood(amountEat);
            System.out.println(name + " съел(а) " + amountEat + " единиц еды.");
            isCatFull = true;
        }else{
            int availableFood = bowl.getFoodBowl();
            bowl.decreaseFood(availableFood);
            System.out.println("В миске мало еды, наполните ее.");
            isCatFull = false;
        }
    }

 //Сытость кота
    public boolean isCatFull(){
        return isCatFull;
    }
}
