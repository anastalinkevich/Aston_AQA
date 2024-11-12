package Lesson_5;

public class Bowl {
    public int foodAmount;

// Конструктор класса
    public Bowl(int initFood){
        if(initFood < 0){
            System.out.println("Количество еды не может быть отрицательным");
        }
        this.foodAmount = initFood; // принимающий значение еды и возвращает количество в переменную foodAmount
    }

// Метод хранит сколько осталось еды в миске
    public int getFoodBowl() {
        return foodAmount;
    }

// Метод проверяющий, сколько осталось в миске и может ли он покушать. И уменьшает количество еды
    public void decreaseFood(int amount){
        if (amount < 0 || foodAmount < amount) {
            System.out.println("В миске недостаточно еды.");
        }
        foodAmount -= amount;
    }

// Метод добавления еды
    public void addFood(int amount){
        if(amount > 0) {
            System.out.println("Добавлено " + amount + " единиц еды.");
            foodAmount += amount;
            System.out.println("В миске стало: " + foodAmount);
        } else {
            System.out.println("Нельзя добавить отрицательное количество еды.");
        }
    }
}
