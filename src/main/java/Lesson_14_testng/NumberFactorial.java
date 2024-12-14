package Lesson_14_testng;

public class NumberFactorial {
    public static int calculateFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }

        int factorial = number;
        for (int i = (number - 1); i > 1; i--) {
            factorial *= i;
        }
        return factorial;
    }
}
