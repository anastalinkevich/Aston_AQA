package Lesson_6;

// Создаём свое исключение, которое расширяет исключение ArrayIndexOutOfBoundsException.

public class MyArraySizeException extends ArrayIndexOutOfBoundsException {
    public MyArraySizeException(String message) {
        super(message);
    }
}
