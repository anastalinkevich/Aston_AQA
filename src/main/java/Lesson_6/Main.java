package Lesson_6;

public class Main {
    public static void main(String[] args) {
        String[][] correctArray = {{"1", "2", "4", "5"},
                {"5", "12", "4", "2"},
                {"9", "0", "1", "1"},
                {"7", "2", "8", "7"}};
        String[][] inCorrectArray = {{"9", "2", "6",},
                {"5", "12", "4"},
                {"9", "0", "1"},
                {"0", "2", "8"}};
        String[][] inCorrectArrayInt = {{"1", "2", "5", "2"},
                {"5", "two", "4", "2"},
                {"1", "0", "6", "1"},
                {"7", "2", "2", "7"}};

    // Вызов метода, где массив имеет правильный размер и данные.
        try {
            ExceptionArray.myArray(correctArray);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

    // Вызов метода, когда массив имеет неправильный размер.
        try {
            ExceptionArray.myArray(inCorrectArray);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

    // Вызов метода, когда в массиве есть неправильные данные.
        try {
            ExceptionArray.myArray(inCorrectArrayInt);
        }catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }
}
