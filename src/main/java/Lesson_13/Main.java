package Lesson_13;

import java.util.*;

public class Main {
    public static void main(String[] args) {
// 1 Задание
        System.out.println("1 задание\n");

        String[] words = {"яблоко", "банан", "груша", "апельсин", "банан", "персик", // Создаем массив со словами (некоторые слова повторяются)
                "слива", "виноград", "яблоко", "лимон", "яблоко", "виноград", "банан"};

        Set<String> uniqueWords = new HashSet<>(words.length); // Используем HashSet для получения списка уникальных слов
        uniqueWords.addAll(Arrays.asList(words));

        System.out.println("Уникальные слова:"); // Выводим на консоль все уникальные слова
        for (String word : uniqueWords) {
            System.out.println(word);
        }

        Map<String, Integer> wordCount = new HashMap<>(); // Используем HashMap для подсчета каждого слова
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // С помощью цикла выводим ключ (название фрукта) и значение (количество раз, которое он встречается)
        System.out.println("\nКоличество встречаемости каждого слова:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

// 2 Задание
        System.out.println("\n2 задание\n");
        PhoneBook book = new PhoneBook(); // Создаем объект класса PhoneBook

        book.add("Иванов", "+375-29-123-4567");
        book.add("Петров", "+375-29-234-5678");
        book.add("Сидоров", "+375-29-345-6789");
        book.add("Иванов", "+375-29-987-6543");  // Добавление ещё одной фамилии Иванов

        PhoneBook.get("Иванов"); // Ожидаемый результат: [+375-29-123-4567, +375-29-987-6543]
        PhoneBook.get("Петров"); // Ожидаемый результат: [+375-29-234-5678]
        PhoneBook.get("Куликов"); // Ожидаемый результат: Не найдено.
    }
}
