package Lesson_14_junit_5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberFactorialTest {

    @BeforeAll
    public static void setUpAll() {
        System.out.println("Выполняется до запуска всех тестов");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("Выполняется перед каждым тестом");
    }

    @DisplayName("Позитивные проверки на получение факториала числа")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    public void testCalculateFactorial(int input) {
        // Ожидаемые результаты для различных входных значений
        int[] expectedResults = {0, 1, 2, 6, 24, 120};

        int result = NumberFactorial.calculateFactorial(input);
        Assertions.assertEquals(expectedResults[input], result);
    }

    @Test
    @Tag("negative_test")
    public void testNegative1() {
        // Метод .assertThrows ожидает исключение
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> NumberFactorial.calculateFactorial(-1));
    }

    @Test
    @Tag("negative_test")
    public void testNegative2() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> NumberFactorial.calculateFactorial(-200));
    }

    @DisplayName("Тест повторяется 3 раза")
    @RepeatedTest(3)
    public void testNegative3() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> NumberFactorial.calculateFactorial(-1000));
    }

    @Test
    @Disabled("Тест отключён для демонстрации аннотации")
    public void testNegative4() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> NumberFactorial.calculateFactorial(-50));
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("Выполняется после завершения всех тестов");
    }
}

