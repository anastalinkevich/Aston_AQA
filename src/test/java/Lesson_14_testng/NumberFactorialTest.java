package Lesson_14_testng;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class NumberFactorialTest {

    @BeforeTest
    public static void setUpAll() {
        System.out.println("Выполняется до запуска всех тестов");
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("Выполняется перед каждым методом");
    }

    @DataProvider(name = "positiveNumbers") // Аннотация предоставляет данные для параметризованного тестового метода
    public Object[][] dataProvider() {
        return new Object[][] {
                {0, 0},
                {1, 1},
                {2, 2},
                {3, 6},
                {4, 24},
                {5, 120}
        };
    }

    @Test(dataProvider = "positiveNumbers", description = "Проверка с параметрами") // Аннотация использует @DataProvider и сравнивает значения
    void testPositive(int input, int expected) {
        int result = NumberFactorial.calculateFactorial(input);
        Assert.assertEquals(expected, result);
    }

    @Test(description = "Тест повторяется 3 раза", invocationCount = 3) // Задан аргумент, который повторяет тест 3 раза
    public void testNegative4() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> NumberFactorial.calculateFactorial(-1000));
    }

    @Test(enabled= false) // Тест отключён для демонстрации аннотации
    public void testNegative5() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> NumberFactorial.calculateFactorial(-50));
    }

    @Test(priority = 1) // Задан приоритет 1
    void testPositive2() {
        int result = NumberFactorial.calculateFactorial(7);
        Assert.assertTrue(result > 0);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class}) // Аргумент аннотации указывает, что ожидается исключение
    public void testNegative6() {
        NumberFactorial.calculateFactorial(-60);
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Выполняется после каждого метода");
    }

    @AfterTest
    public static void tearDownAll() {
        System.out.println("Выполняется после завершения всех тестов");
    }

}
