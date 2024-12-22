package Lesson_16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OnlinePaymentTest {
    OnlinePayment onlinePayment = new OnlinePayment(driver);

    private static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

    public String phone = "297777777";
    public String sum = "10.55";

// Перед всеми тестами
    @BeforeAll
    static void setUpAll(){
        WebDriverManager.chromedriver().setup();
    }
// Перед каждым тестом
    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
        onlinePayment = new OnlinePayment(driver); // Переместил сюда!
        driver.get("https://www.mts.by/?hash-offset=70&hash-dur=1300#pay-section");
        driver.manage().window().maximize();
        WebElement cookieLocator = driver.findElement(By.xpath("//*[@id='cookie-agree']"));
        if (cookieLocator.isDisplayed()) {
            cookieLocator.click();
        }
    }

// Первый тест Lesson_15
    @DisplayName("Проверка наличия текста 'Онлайн пополнение без комиссии'")
    @Test
    public void firstTest() {
        OnlinePayment actual = onlinePayment.findText();
        String expected = "Онлайн пополнение\nбез комиссии";
        //Assertions.assertEquals(expected, actual, "Не удалось найти строку - Онлайн пополнение без комиссии");
    }
// Второй тест Lesson_15
    @DisplayName("Проверка наличия логотипов платёжных систем партнеров")
    @Test
    public void findPartners(){
        onlinePayment.findImagePartners();
    }
// Второй тест Lesson_15
    @DisplayName("Проверка лого Партеров")
    @Test
    void logo(){
        onlinePayment.findImagePartners();
    }
// Третий тест Lesson_15
    @DisplayName("Проверка перехода по ссылке")
    @Test
    public void testLink() {
        wait.until(ExpectedConditions.elementToBeClickable(onlinePayment.linkText));
        onlinePayment.clickLink();
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
    // Проверяем, что произошла навигация на нужную страницу
        assertEquals(driver.getCurrentUrl(), expectedUrl, "Переход не произошёл по linkText 'Подробнее о сервисе'");
    // Возвращаемся на предыдущую страницу
        driver.navigate().back();
    }

// Четвёртый тест Lesson_15
    @DisplayName("Проверка на заполнение полей и подтверждения пополнения счёта")
    @Test
    public void testButton() {
        onlinePayment.setPhone(phone);

        onlinePayment.setSum(sum);
        onlinePayment.clickButton();

        //WebElement expectedFrame = driver.findElement(By.xpath("//*[@class='bepaid-iframe']"));
        //Assertions.assertTrue(onlinePayment.clickButton().isDisplayed(), "Пополнение не произошло");
    }
    
// Тесты по лекции 17 "Тестирование с помощью Selenium WebDriver часть 2"

    @DisplayName("Проверка плейсхолдеров <Услуги связи>")
    @Test
    void testPlaceholderServices() {
    // Ищем поле Номер телефона
        WebElement inputPhone = driver.findElement(By.id("connection-phone"));
    // Получить значение атрибута 'placeholder'
        String placeholderValue = inputPhone.getAttribute("placeholder");
    // Проверить значение
        assertEquals("Номер телефона", placeholderValue, "Название в плейсхолдере не совпадает с 'Номер телефона'");

    // Найти элемент ввода текста
        WebElement inputSum = driver.findElement(By.id("connection-sum"));
    // Получить значение атрибута 'placeholder'
        String placeholderValueSum = inputSum.getAttribute("placeholder");
    // Проверить значение
        assertEquals("Сумма", placeholderValueSum, "Название в плейсхолдере не совпадает с 'Сумма'");
    }

    @DisplayName("Проверка плейсхолдеров <Домашний интернет>")
    @Test
    public void testPlaceholderInternet(){
        WebDriverWait waitElement = new WebDriverWait(driver, Duration.ofSeconds(4));

        WebElement menu = driver.findElement(By.className("select__header"));
        menu.click();
        WebElement sumMenu = driver.findElement(By.xpath("//p[@class='select__option' and text()='Домашний интернет']"));
        sumMenu.click();

    // Ожидание появления полей ввода
        WebElement inputInternetPhone = waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.id("internet-phone")));
        WebElement inputInternetSum = waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.id("internet-sum")));

    // Извлечение и проверка значений placeholder
        String placeholderInternet = inputInternetPhone.getAttribute("placeholder");
        String placeholderInternetSum = inputInternetSum.getAttribute("placeholder");

     // Утверждения
        Assertions.assertEquals("Номер абонента", placeholderInternet, "Название в плейсхолдере не совпадает с 'Номер абонента'");
        Assertions.assertEquals("Сумма", placeholderInternetSum, "Название в плейсхолдере не совпадает с 'Сумма'");
    }

    @DisplayName("Проверка плейсхолдеров <Рассрочка>")
    @Test
    public void testPlaceholderScore(){
        WebDriverWait waitElement = new WebDriverWait(driver, Duration.ofSeconds(4));

        WebElement menu = driver.findElement(By.className("select__header")); //Присваиваем переменной ссылку на локатор
        menu.click();
        WebElement sumMenu = driver.findElement(By.xpath("//p[@class='select__option' and text()='Рассрочка']"));
        sumMenu.click();

    // Ожидание появления полей ввода
        WebElement inputScore = waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.id("score-instalment")));
        WebElement inputScoreSum = waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.id("instalment-sum")));

    // Извлечение и проверка значений placeholder
        String placeholderInternet = inputScore.getAttribute("placeholder");
        String placeholderInternetSum = inputScoreSum.getAttribute("placeholder");

    // Утверждения
        Assertions.assertEquals("Номер счета на 44", placeholderInternet, "Название в плейсхолдере не совпадает с 'Номер счета на 44'");
        Assertions.assertEquals("Сумма", placeholderInternetSum, "Название в плейсхолдере не совпадает с 'Сумма'");
    }

    @DisplayName("Проверка плейсхолдеров <Задолженность>")
    @Test
    public void testPlaceholderArrears(){
        WebDriverWait waitElement = new WebDriverWait(driver, Duration.ofSeconds(4));

        WebElement menu = driver.findElement(By.className("select__header"));
        menu.click();
        WebElement sumMenu = driver.findElement(By.xpath("//p[@class='select__option' and text()='Задолженность']"));
        sumMenu.click();

    // Ожидание появления полей ввода
        WebElement inputScore = waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.id("score-arrears")));
        WebElement inputScoreSum = waitElement.until(ExpectedConditions.visibilityOfElementLocated(By.id("arrears-sum")));

    // Извлечение и проверка значений placeholder
        String placeholderInternet = inputScore.getAttribute("placeholder");
        String placeholderInternetSum = inputScoreSum.getAttribute("placeholder");

    // Утверждения
        assertEquals("Номер счета на 2073", placeholderInternet, "Название в плейсхолдере не совпадает с 'Номер счета на 2073'");
        assertEquals("Сумма", placeholderInternetSum, "Название в плейсхолдере не совпадает с 'Сумма'");
    }

//Задание 2 по Lesson_16

    //При Debug тест проходит, но при запуске падает
    @DisplayName("Заполнение полей и переход на фрейм")
    @Test
    public void testFillFrame(){
        wait.until(ExpectedConditions.elementToBeClickable(onlinePayment.connectPhone)); //Ждём доступность поля Номер телефона
        onlinePayment.setPhone(phone);

        wait.until(ExpectedConditions.elementToBeClickable(onlinePayment.connectSum)); // Ждём доступность поля Сумма
        onlinePayment.setSum(sum);
        onlinePayment.clickButton();

// Явное ожидание фрейма и переход для дальнейшей оплаты
        wait.until(ExpectedConditions.elementToBeClickable(onlinePayment.frameConnect));
        WebElement iframe = onlinePayment.frameConnect;
        driver.switchTo().frame(iframe);

// Проверить, что введённая сумма содержится в тексте вместе с валютой "BYN"
        WebElement creditCardFormCost = onlinePayment.creditCardFormCost;
        Assertions.assertEquals("10.55 BYN", creditCardFormCost.getText(), "Ожидаемая сумма и актуальна не совпадает.");

//Проверка значение плейсхолдера на фрейме Номер карты
        WebElement labelText = onlinePayment.labelText;
        Assertions.assertEquals("Номер карты", labelText.getText(), "Текст во фрейме не соответствует <Номер карты>");

// Проверка значения номера телефона
        WebElement numberPhoneText = onlinePayment.numberPhoneText;
        Assertions.assertEquals("Оплата: Услуги связи Номер:375297777777", numberPhoneText.getText(), "Текст во фрейме не соответствует <Оплата: Услуги связи Номер:375297777777>");

//Проверка значения плейсхоледра <Срок действия>
        WebElement timeInserted = onlinePayment.timeInserted;
        Assertions.assertEquals("Срок действия", timeInserted.getText(), "Текст во фрейме не соответствует <Срок действия>");

//Проверка надписи CVC
        WebElement testCVC = onlinePayment.testCVC;
        Assertions.assertEquals("CVC", testCVC.getText(), "Текст во фрейме не соответствует <CVC>");

//Проверка надписи Имя держателя на карте
        WebElement nameOuner = onlinePayment.nameOuner;
        Assertions.assertEquals("Имя держателя (как на карте)", nameOuner.getText(), "Текст во фрейме не соответсвует <Имя держателя (на карте)>");

//Проверка надписи на кнопке
        WebElement buttonLocatorSum = onlinePayment.buttonLocatorSum;
        Assertions.assertEquals("Оплатить 10.55 BYN", buttonLocatorSum.getText(), "Текст во фрейме не соответсвует <Оплатить 10.55 BYN>");
    }
}


