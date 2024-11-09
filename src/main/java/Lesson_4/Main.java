package Lesson_4;

public class Main {
    public static void main(String[] args) {
    // Задание 2: Создать массив из 5 сотрудников.

    // С помощью конструктора класса Employee заполняются данные о сотрудниках в массив.
        Employee[] empArray = new Employee[5];
        empArray[0] = new Employee("Иванов Иван Сергеевич", "Разработчик", "ivan.ivanov@example.com", "+7(999)123-45-67", 100000, 31);
        empArray[1] = new Employee("Николаенко Василий Геориевич", "Менеджер", "vasil.nikola@example.com", "+7(888)987-65-4", 80000, 28);
        empArray[2] = new Employee("Васильева Мария Олеговна", "Бухгалтер", "maria.vasileva@example.com", "+7(777)654-32-10", 90000, 51);
        empArray[3] = new Employee("Пахомов Алексей Николаевич", "Администратор сети", "aleksey.pahom@example.com", "+7(666)432-21-09", 80000, 45);
        empArray[4] = new Employee("Ломоносова Ольга Витальевна", "Маркетолог", "olga.lomonosova@example.com", "+7(555)321-08-76", 85000, 34);

    // Цикл для вывода информации о сотрудниках в консоль.
        for (Employee employee : empArray) {
            employee.infoEmployee();
        }

    // Задание 3: Создать класс Park с внутренним классом, с помощью объектов которого можно хранить информацию
    // об аттракционах, времени их работы и стоимости.

    //  Объявление и создание внутреннего класса Attraction, с помощью создания внешнего класса Park.
        Park.Attraction attraction1 = new Park() .new Attraction("Американские горки", "10:00", "21:00", 6.7);
        Park.Attraction attraction2 = new Park() .new Attraction("Марс", "9:00", "20:00", 6.0);

    // С помощью метода внутреннего класса выводим информацию об аттракционах в консоль.
        attraction1.infoAttraction();
        attraction2.infoAttraction();
    }
}

