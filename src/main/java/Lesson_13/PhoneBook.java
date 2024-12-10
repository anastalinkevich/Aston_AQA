package Lesson_13;

import java.util.*;

public class PhoneBook {
    public static Map<String, ArrayList<String>> phoneBook = new HashMap<>();

    public void add(String surname, String number){
        if (phoneBook.containsKey(surname)){    // Проверяем, есть ли уже запись с указанным фамилией в телефонной книге
            phoneBook.get(surname).add(number); // Если запись есть, то добавляем номер телефона в список номеров этой фамилии
        } else {                                // Если записи фамилии нет, то создаем новый список номеров и добавляем его в телефонную книгу
            ArrayList<String> numbers = new ArrayList<>();
            numbers.add(number);
            phoneBook.put(surname, numbers);
        }
    }

    public static void get(String surname){
        if (phoneBook.containsKey(surname)){ // Проверяем, есть ли запись с указанным фамилией в телефонной книге
            // Если запись есть, то выводим фамилию и список номера(ов) телефона этой фамилии
            System.out.println("Фамилия: " + surname + ", номер(а) телефона: " + phoneBook.get(surname));
        } else {
            System.out.println("Не найдено.");
        }
    }
}
