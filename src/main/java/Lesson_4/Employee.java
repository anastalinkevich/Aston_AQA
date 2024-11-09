package Lesson_4;

/* Задание 1. Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст.
   Конструктор класса должен заполнять эти поля при создании объекта.
   Внутри класса "Сотрудник" написать метод, который выводит информацию об объекте в консоль.
*/

public class Employee {
    private String name;
    private String position;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;

// Конструктор класса Employee
    Employee(String name, String position, String email, String phoneNumber, int salary, int age){
        this.name = name;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

// Метод класса Employee, который выводит информацию об объекте в консоль
    public void infoEmployee(){
        System.out.println("ФИО: " + name);
        System.out.println("Позиция: " + position);
        System.out.println("Email: " + email);
        System.out.println("Номер телефона: " + phoneNumber);
        System.out.println("Заработная плата: " + salary);
        System.out.println("Возраст: " + age);
        System.out.println();
    }
}

