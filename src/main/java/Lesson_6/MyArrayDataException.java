package Lesson_6;

// Создаём свое исключение, которое расширяет исключение NumberFormatException.

public class MyArrayDataException extends NumberFormatException {
    public MyArrayDataException(String message) {
        super(message);
    }
}
