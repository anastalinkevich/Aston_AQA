package Lesson_4;

    // Задание 3: Создать класс Park с внутренним классом, с помощью объектов которого можно хранить информацию
    // об аттракционах, времени их работы и стоимости.

    // Публичный класс Park
public class Park{
    // Вложенный (inner) класс Attraction, который хранит информацию об аттракционах,
    // времени их работы и стоимости.
    class Attraction{
        private String name;
        private String openTime;
        private String closeTime;
        private double cost;

    // Конструктор внутреннего класса Attraction.
        public Attraction(String name, String openTime, String closeTime, double cost){
            this.name = name;
            this.openTime = openTime;
            this.closeTime = closeTime;
            this.cost = cost;
        }

    // Метод внутреннего класса Attraction, выводящий в консоль информацию об аттракционах.
        public void infoAttraction(){
            System.out.println("Название: " + name);
            System.out.println("Начало работы: " + openTime);
            System.out.println("Время закрытия: " + closeTime);
            System.out.println("Стоимость: " + cost + "\n");
        }
    }
}
