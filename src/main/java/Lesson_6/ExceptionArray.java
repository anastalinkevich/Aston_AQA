package Lesson_6;

public class ExceptionArray {
    public static void myArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++){
                if(array.length != 4 || array[i].length != 4){ // Если стороны массива не имеют стороны 4х4, выбросит исключение.
                    throw new MyArraySizeException("Некорректный массив. Массив должен быть размером 4х4");
                }
            // Если массив нужного размера - проходим по массиву, переводим строки в int и суммируем значения.
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {         // Если в массиве имеется посторонний символ, будет брошено исключение.
                    throw new MyArrayDataException("Некорректный массив. Ошибка преобразования в ячейке [" + i + ", " + j + "]");
                }
            }
        }
    // Вывод суммы всех чисел из корректного массива.
        System.out.println("Корректный массив. Сумма всех элементов массива: " + sum);
    }
}
