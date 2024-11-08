package Lesson_3;

import java.util.Arrays;

public class TasksClass {
    // 1. Метод printThreeWords(), который печатает
    // в столбец три слова: Orange, Banana, Apple
    public static void printThreeWords(String a, String b, String c) {
        System.out.println(a + "\n" + b + "\n" + c);
    }

    // 2. Метод checkSumSign(), который суммирует переменные и выводит на консоль
    // "Сумма позитивная", "Сумма отрицательная"
    public static void checkSumSign(int v, int m){
        int d = v + m;
        if(d >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3. Метод printColor()который принимает значение int
    // и выводит на консоль "Красный" или "Жёлтый" или "Зелёный"
    public static void printColor(int value) {
        if (value > 0 && value <= 100){
            System.out.println("Жёлтый");
        } else if (value <= 0) {
            System.out.println("Красный");
        } else {
            System.out.println("Зелёный");
        }
    }

    // 4. Метод compareNumber(), который принимает два значения int
    // и выводит в консоль "a >= b" или "a < b"
    public static void compareNumbers(int a, int b) {
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5. Метод, который вычисляет сумму чисел и если числа
    // в диапазоне от 10 до 20 и вывод true или false
    public static boolean sumNumbers(int a, int b){
        int sum = a + b;
        if (sum >= 10 && sum <= 20){
            System.out.println(true);
            return true;
        } else {
            System.out.println(false);
            return false;
        }
    }

    // 6. Метод, который определяет число и выводит
    // в консоль "Положительное число" или "Отрицательные число"
    public static void getNum(int num){
        if (num >= 0){
            System.out.println("Положительное число");
        } else {
            System.out.println("Отрицательное число");
        }
    }

    // 7. Метод, принимающий int и выводит
    // true если число отрицательное, false - положительное
    public static boolean getIntNum(int num2){
        if (num2 < 0){
            System.out.println(true);
            return true;
        } else {
            System.out.println(false);
            return false;
        }
    }

    // 8. Метод печатает в консоль строку указанное количество раз
    public static String repeatString(String word, int c){
        String result = word.repeat(c) ;
        System.out.println(result);
        return result;
    }

    //9. Метод, который вычисляет високосный год, если да - возвращает true, если нет - false
    public static boolean leapYear(int year) {
        if(year % 400 == 0){
            System.out.println(true);
            return true;
        }else if((year % 4 == 0 && year % 100 != 0)) {
            System.out.println(true);
            return true;
        }else{
            System.out.println(false);
            return false;
        }
    }

    // 10. Метод, который выводит массив, где заменяются значение с 1 на 0 и наоборот
    public static void replaceValuesInArray() {
        int[] numSs = {1, 1, 0, 0, 1, 1, 0, 0};
        for (int i = 0; i < numSs.length; i++) {
            if (numSs[i] == 0) {
                numSs[i] = 1;
            } else {
                numSs[i] = 0;
            }
        }
        System.out.println(Arrays.toString(numSs));
    }

    // 11. Метод, заполняющий массив числами от 1 до 100 с помощью цикла
    public static void fillAndPrintArray() {
        int[] nums = new int[100];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + 1;
        }
        System.out.println(Arrays.toString(nums));
    }

    // 12. Метод, который в заданном массиве, с помощью цикла умножает числа меньше 6 на 2
    public static void doubleValues() {
        int[] numW = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < numW.length; i++) {
            if (numW[i] < 6) {
                numW[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(numW));
    }

    // 13. Метод, который выводит квадратный двумерный массив и
    // с помощью циклов заполняет элементы по диагонали цифрой 1
    public static void createMatrix(int sizeArray) {
        int[][] arraySquare = new int[sizeArray][sizeArray];
        for (int i = 0; i < sizeArray; i++) {
            for (int j = 0; j < sizeArray; j++) {
                if (i == j || i + j == sizeArray - 1) {
                    arraySquare[i][j] = 1;
                } else {
                    arraySquare[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < sizeArray; i++) {
            System.out.println(Arrays.toString(arraySquare[i]));
        }
    }

    // 14. Метод, который выводит массив типа int и длинной len, где каждая ячейка равна initialValue
    public static void arrayR ( int len, int initialValue){
        int[] arrayOne = new int[len];
        Arrays.fill(arrayOne, initialValue);
        System.out.println(Arrays.toString(arrayOne));
    }

}
