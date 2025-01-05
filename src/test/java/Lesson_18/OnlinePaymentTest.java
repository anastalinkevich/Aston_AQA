package Lesson_18;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import java.util.List;
import static io.qameta.allure.SeverityLevel.MINOR;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OnlinePaymentTest extends BaseTest{


    @Owner("Linkevich Anastasiya")
    @Severity(MINOR)
    @Tag("Задание по лекции 15")
    @DisplayName("Проверка наличия текста <Онлайн пополнение без комиссии>")
    @Test
    public void testText() {
        String actualText = onlinePayment.findText();
        String expectedText = "Онлайн пополнение без комиссии";
        assertEquals(expectedText, actualText, "Не удалось найти строку - <Онлайн пополнение без комиссии>");
    }

    @Severity(MINOR)
    @Tag("Задание по лекции 15")
    @DisplayName("Проверка лого Партнеров в 'Онлайн пополнение без комиссии'")
    @Test
    void logoPartners() {
        List<String> actualAltTexts = onlinePayment.getPartnersLogoAltTexts();
        List<String> expectedAltTexts = List.of("Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт");

        assertEquals(expectedAltTexts, actualAltTexts, "Список alt текстов не совпадает с ожидаемыми");
    }

    @Severity(MINOR)
    @Tag("Задание по лекции 15")
    @DisplayName("Проверка перехода по ссылке «Подробнее о сервисе»")
    @Test
    public void testLink() {
        onlinePayment.clickLink();
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        assertEquals(expectedUrl, driver.getCurrentUrl(), "Переход не произошёл по linkText 'Подробнее о сервисе'"); // Проверяем, что произошла навигация на нужную страницу
    }

    @Flaky
    @Severity(MINOR)
    @Tag("Задание по лекции 15")
    @DisplayName("Проверка на заполнение полей и подтверждения пополнения счёта")
    @Test
    public void testButton() {
        onlinePayment.setForm("297777777", "12.55");
        assertTrue(onlinePayment.getIframeConnect(), "Нажатие на кнопку <Продолжить> не произошло");
    }

// Тесты по лекции 16 "Тестирование с помощью Selenium WebDriver часть 2"
    @Severity(MINOR)
    @Tag("Задание по лекции 16")
    @DisplayName("Проверка плейсхолдеров <Услуги связи>")
    @Test
    void testPlaceholderServices() {
        assertEquals("Номер телефона", onlinePayment.getConnectPhone(), "Название в плейсхолдере не совпадает с 'Номер телефона'");         // Проверить значение
        assertEquals("Сумма", onlinePayment.setSum(), "Название в плейсхолдере не совпадает с 'Сумма'");         // Проверить значение
    }

    @Severity(MINOR)
    @Tag("Задание по лекции 16")
    @DisplayName("Проверка плейсхолдеров <Домашний интернет>")
    @Test
    public void testPlaceholderInternet(){
        onlinePayment.getClickInternet();
        assertEquals("Номер абонента", onlinePayment.getInternetPhone(), "Название в плейсхолдере не совпадает с 'Номер абонента'");
        assertEquals("Сумма", onlinePayment.getInternetSum(), "Название в плейсхолдере не совпадает с 'Сумма'");
    }

    @Severity(MINOR)
    @Tag("Задание по лекции 16")
    @DisplayName("Проверка плейсхолдеров <Рассрочка>")
    @Test
    public void testPlaceholderScore(){
        onlinePayment.getClickScore();
        assertEquals("Номер счета на 44", onlinePayment.getScoreInstalment(), "Название в плейсхолдере не совпадает с 'Номер счета на 44'");
        assertEquals("Сумма", onlinePayment.getInstalmentSum(), "Название в плейсхолдере не совпадает с 'Сумма'");
    }

    @Tag("Задание по лекции 16")
    @DisplayName("Проверка плейсхолдеров <Задолженность>")
    @Test
    public void testPlaceholderArrears(){
        onlinePayment.getClickArrears();
        assertEquals("Номер счета на 2073", onlinePayment.getScoreArrears(), "Название в плейсхолдере не совпадает с 'Номер счета на 2073'");
        assertEquals("Сумма", onlinePayment.getArrearsSum(), "Название в плейсхолдере не совпадает с 'Сумма'");
    }

//Задание 2 по Lesson_16
//Тест начал падать после оптимизации, но при дебаге проходит
    @Flaky
    @Severity(NORMAL)
    @Tag("Задание по лекции 16")
    @DisplayName("Проверка полей фрейма оплаты")
    @Description("Заполнение полей оплаты (номер телефона, сумма оплаты). Переход на фрейм дальнейшей оплаты. Проверка плейсхолдеров на фрейме, логотипы партеров.")
    @Test
    public void testFillFrame(){
        onlinePayment.setForm("297777777", "12.55");
//        onlinePayment.switchToFrame();

        onlinePayment.switchToFrame();

        assertEquals("12.55 BYN", onlinePayment.getSumFrame(), "Ожидаемая сумма и актуальная не совпадает.");
        assertEquals("Номер карты", onlinePayment.getCardNumber(), "Текст во фрейме не соответствует <Номер карты>");
        assertEquals("Оплата: Услуги связи Номер:375297777777", onlinePayment.getNumberPhoneText(), "Текст во фрейме не соответствует <Оплата: Услуги связи Номер:375297777777>");
        assertEquals("Срок действия", onlinePayment.getTimeInserted(), "Текст во фрейме не соответствует <Срок действия>");
        assertEquals("CVC", onlinePayment.getTestCVC(), "Текст во фрейме не соответствует <CVC>");
        assertEquals("Имя держателя (как на карте)", onlinePayment.getNameOwner(), "Текст во фрейме не соответствует <Имя держателя (на карте)>");
        assertEquals("Оплатить 12.55 BYN", onlinePayment.getButtonLocatorSum(), "Текст во фрейме не соответствует <Оплатить 10.55 BYN>");
        List<String> expectedLogoFrame = List.of(
                onlinePayment.imgURL + "visa-system.svg",
                onlinePayment.imgURL + "mastercard-system.svg",
                onlinePayment.imgURL + "belkart-system.svg",
                onlinePayment.imgURL + "maestro-system.svg",
                onlinePayment.imgURL + "mir-system-ru.svg");
        List<String> actualLogoFrame = onlinePayment.getPartnersLogoFrame();
        assertEquals(expectedLogoFrame, actualLogoFrame, "Логотипы на фрейме не совпадают с ожидаемыми");
    }
}
