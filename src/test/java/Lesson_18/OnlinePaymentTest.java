package Lesson_18;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OnlinePaymentTest {
    OnlinePayment onlinePayment = new OnlinePayment(driver);
    private static WebDriver driver;
    final String imgURL = "https://checkout.bepaid.by/widget_v2/assets/images/payment-icons/card-types/";

// Перед всеми тестами
    @BeforeAll
    static void setUpAll(){
        WebDriverManager.chromedriver().setup();
    }
// Перед каждым тестом
    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
        onlinePayment = new OnlinePayment(driver);
        driver.get("https://www.mts.by/?hash-offset=70&hash-dur=1300#pay-section");
        driver.manage().window().maximize();
        WebElement cookieLocator = driver.findElement(By.xpath("//*[@id='cookie-agree']"));
        if (cookieLocator.isDisplayed()) {
            cookieLocator.click();
        }
    }
    @DisplayName("Проверка наличия текста <Онлайн пополнение без комиссии>")
    @Test
    public void firstTest() {
        String actualText = onlinePayment.findText();
        String expectedText = "Онлайн пополнение без комиссии";
        assertEquals(expectedText, actualText, "Не удалось найти строку - <Онлайн пополнение без комиссии>");
    }
//Дополнить проверку лого главной страницы
    @DisplayName("Проверка лого Партнеров в 'Онлайн пополнение без комиссии'")
    @Test
    void logoPartners() {
        List<String> actualAltTexts = onlinePayment.getPartnersLogoAltTexts();
        List<String> expectedAltTexts = List.of("Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт");

        assertEquals(expectedAltTexts, actualAltTexts, "Список alt текстов не совпадает с ожидаемыми");
    }

    @DisplayName("Проверка перехода по ссылке")
    @Test
    public void testLink() {
        onlinePayment.clickLink();
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        assertEquals(expectedUrl, driver.getCurrentUrl(), "Переход не произошёл по linkText 'Подробнее о сервисе'"); // Проверяем, что произошла навигация на нужную страницу
        driver.navigate().back();// Возвращаемся на предыдущую страницу
    }
    @DisplayName("Проверка на заполнение полей и подтверждения пополнения счёта")
    @Test
    public void testButton() {
        onlinePayment.setForm("297777777", "12.55");
        Assertions.assertTrue(onlinePayment.isFormDisplayed(), "Нажатие на кнопку <Продолжить> не произошло");
    }

// Тесты по лекции 16 "Тестирование с помощью Selenium WebDriver часть 2"
    @DisplayName("Проверка плейсхолдеров <Услуги связи>")
    @Test
    void testPlaceholderServices() {
        assertEquals("Номер телефона", onlinePayment.getConnectPhone(), "Название в плейсхолдере не совпадает с 'Номер телефона'");         // Проверить значение
        assertEquals("Сумма", onlinePayment.setSum(), "Название в плейсхолдере не совпадает с 'Сумма'");         // Проверить значение
    }

    @DisplayName("Проверка плейсхолдеров <Домашний интернет>")
    @Test
    public void testPlaceholderInternet(){
        onlinePayment.getClickInternet();
        assertEquals("Номер абонента", onlinePayment.getInternetPhone(), "Название в плейсхолдере не совпадает с 'Номер абонента'");
        assertEquals("Сумма", onlinePayment.getInternetSum(), "Название в плейсхолдере не совпадает с 'Сумма'");
    }

    @DisplayName("Проверка плейсхолдеров <Рассрочка>")
    @Test
    public void testPlaceholderScore(){
        onlinePayment.getClickScore();
        assertEquals("Номер счета на 44", onlinePayment.getScoreInstalment(), "Название в плейсхолдере не совпадает с 'Номер счета на 44'");
        assertEquals("Сумма", onlinePayment.getInstalmentSum(), "Название в плейсхолдере не совпадает с 'Сумма'");
    }

    @DisplayName("Проверка плейсхолдеров <Задолженность>")
    @Test
    public void testPlaceholderArrears(){
        onlinePayment.getClickArrears();
        assertEquals("Номер счета на 2073", onlinePayment.getScoreArrears(), "Название в плейсхолдере не совпадает с 'Номер счета на 2073'");
        assertEquals("Сумма", onlinePayment.getArrearsSum(), "Название в плейсхолдере не совпадает с 'Сумма'");
    }

//Задание 2 по Lesson_16
    @DisplayName("Проверка полей фрейма оплаты")
    @Test
    public void testFillFrame(){
        onlinePayment.setForm("297777777", "12.55");
        onlinePayment.getIframeConnect();
        assertEquals("12.55 BYN", onlinePayment.getSumFrame(), "Ожидаемая сумма и актуальная не совпадает.");
        assertEquals("Номер карты", onlinePayment.getCardNumber(), "Текст во фрейме не соответствует <Номер карты>");
        assertEquals("Оплата: Услуги связи Номер:375297777777", onlinePayment.getNumberPhoneText(), "Текст во фрейме не соответствует <Оплата: Услуги связи Номер:375297777777>");
        assertEquals("Срок действия", onlinePayment.getTimeInserted(), "Текст во фрейме не соответствует <Срок действия>");
        assertEquals("CVC", onlinePayment.getTestCVC(), "Текст во фрейме не соответствует <CVC>");
        assertEquals("Имя держателя (как на карте)", onlinePayment.getNameOwner(), "Текст во фрейме не соответствует <Имя держателя (на карте)>");
        assertEquals("Оплатить 12.55 BYN", onlinePayment.getButtonLocatorSum(), "Текст во фрейме не соответствует <Оплатить 10.55 BYN>");
        List<String> expectedTexts = List.of(
                imgURL + "visa-system.svg",
                imgURL + "mastercard-system.svg",
                imgURL + "belkart-system.svg",
                imgURL + "maestro-system.svg",
                imgURL + "mir-system-ru.svg");
        List<String> actualTexts = onlinePayment.getPartnersLogoFrame();
        Assertions.assertEquals(expectedTexts, actualTexts, "Логотипы на фрейме не совпадают с ожидаемыми");
    }
// Закрываем веб-драйвер после выполнения теста
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
