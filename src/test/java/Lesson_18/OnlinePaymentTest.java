package Lesson_18;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class OnlinePaymentTest {
    OnlinePayment onlinePayment = new OnlinePayment(driver);
    private static WebDriver driver;

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
        Assertions.assertEquals(expectedText, actualText, "Не удалось найти строку - <Онлайн пополнение без комиссии>");
    }
//Дополнить проверку лого главной страницы
    @DisplayName("Проверка лого Партеров")
    @Test
    void logo(){
        WebElement actualLogo = onlinePayment.findImagePartners();
        Assertions.assertTrue(actualLogo.isDisplayed(), "Логотипы партнеров не найдены");
    }
    @DisplayName("Проверка перехода по ссылке")
    @Test
    public void testLink() {
        onlinePayment.clickLink();
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl(), "Переход не произошёл по linkText 'Подробнее о сервисе'"); // Проверяем, что произошла навигация на нужную страницу
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
        Assertions.assertEquals("Номер телефона", onlinePayment.getConnectPhone(), "Название в плейсхолдере не совпадает с 'Номер телефона'");         // Проверить значение
        Assertions.assertEquals("Сумма", onlinePayment.setSum(), "Название в плейсхолдере не совпадает с 'Сумма'");         // Проверить значение
    }

    @DisplayName("Проверка плейсхолдеров <Домашний интернет>")
    @Test
    public void testPlaceholderInternet(){
        onlinePayment.getClickInternet();
        Assertions.assertEquals("Номер абонента", onlinePayment.getInternetPhone(), "Название в плейсхолдере не совпадает с 'Номер абонента'");
        Assertions.assertEquals("Сумма", onlinePayment.getInternetSum(), "Название в плейсхолдере не совпадает с 'Сумма'");
    }

    @DisplayName("Проверка плейсхолдеров <Рассрочка>")
    @Test
    public void testPlaceholderScore(){
        onlinePayment.getClickScore();
        Assertions.assertEquals("Номер счета на 44", onlinePayment.getScoreInstalment(), "Название в плейсхолдере не совпадает с 'Номер счета на 44'");
        Assertions.assertEquals("Сумма", onlinePayment.getInstalmentSum(), "Название в плейсхолдере не совпадает с 'Сумма'");
    }

    @DisplayName("Проверка плейсхолдеров <Задолженность>")
    @Test
    public void testPlaceholderArrears(){
        onlinePayment.getClickArrears();
        Assertions.assertEquals("Номер счета на 2073", onlinePayment.getScoreArrears(), "Название в плейсхолдере не совпадает с 'Номер счета на 2073'");
        Assertions.assertEquals("Сумма", onlinePayment.getArrearsSum(), "Название в плейсхолдере не совпадает с 'Сумма'");
    }

//Задание 2 по Lesson_16
    @DisplayName("Заполнение полей и переход на фрейм")
    @Test
    public void testFillFrame(){
        onlinePayment.setForm("297777777", "12.55");
        onlinePayment.getIframeConnect();
        Assertions.assertEquals("12.55 BYN", onlinePayment.getSumFrame(), "Ожидаемая сумма и актуальная не совпадает.");
        Assertions.assertEquals("Номер карты", onlinePayment.getCardNumber(), "Текст во фрейме не соответствует <Номер карты>");
        Assertions.assertEquals("Оплата: Услуги связи Номер:375297777777", onlinePayment.getNumberPhoneText(), "Текст во фрейме не соответствует <Оплата: Услуги связи Номер:375297777777>");
        Assertions.assertEquals("Срок действия", onlinePayment.getTimeInserted(), "Текст во фрейме не соответствует <Срок действия>");
        Assertions.assertEquals("CVC", onlinePayment.getTestCVC(), "Текст во фрейме не соответствует <CVC>");
        Assertions.assertEquals("Имя держателя (как на карте)", onlinePayment.getNameOwner(), "Текст во фрейме не соответствует <Имя держателя (на карте)>");
        Assertions.assertEquals("Оплатить 12.55 BYN", onlinePayment.getButtonLocatorSum(), "Текст во фрейме не соответствует <Оплатить 10.55 BYN>");
        //Добавить проверку лого
    }
// Закрываем веб-драйвер после выполнения теста
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
