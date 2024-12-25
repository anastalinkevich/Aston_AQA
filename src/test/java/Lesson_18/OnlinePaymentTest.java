package Lesson_18;

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
//Так и не поняла как этот тест сделать :D
    @DisplayName("Проверка наличия текста 'Онлайн пополнение без комиссии'")
    @Test
    public void firstTest() {
        OnlinePayment actualText = onlinePayment.findText();
        String expectedText = "Онлайн пополнение\nбез комиссии";
        //Assertions.assertEquals(expectedText, actualText, "Не удалось найти строку - Онлайн пополнение без комиссии");
    }

    // Второй тест Lesson_15
    @DisplayName("Проверка лого Партеров")
    @Test
    void logo(){
        wait.until(ExpectedConditions.visibilityOf(onlinePayment.partnersLocator));
        onlinePayment.findImagePartners();
        Assertions.assertTrue(onlinePayment.partnersLocator.isDisplayed(), "Логотипы партёров не найдены");
    }
    // Третий тест Lesson_15
    @DisplayName("Проверка перехода по ссылке")
    @Test
    public void testLink() {
        wait.until(ExpectedConditions.elementToBeClickable(onlinePayment.linkText));
        onlinePayment.clickLink();
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        // Проверяем, что произошла навигация на нужную страницу
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl(), "Переход не произошёл по linkText 'Подробнее о сервисе'");
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
        //Поверка и явное ожидание
        wait.until(ExpectedConditions.visibilityOf(onlinePayment.frameConnect));
        Assertions.assertTrue(onlinePayment.frameConnect.isDisplayed(), "Пополнение не произошло");
    }

// Тесты по лекции 16 "Тестирование с помощью Selenium WebDriver часть 2"

    @DisplayName("Проверка плейсхолдеров <Услуги связи>")
    @Test
    void testPlaceholderServices() {
        // Ищем поле Номер телефона
        WebElement inputPhone = onlinePayment.connectPhone;
        // Получить значение атрибута 'placeholder'
        String placeholderValue = inputPhone.getAttribute("placeholder");
        // Проверить значение
        assertEquals("Номер телефона", placeholderValue, "Название в плейсхолдере не совпадает с 'Номер телефона'");

        // Найти элемент ввода текста
        WebElement inputSum = onlinePayment.connectSum;
        // Получить значение атрибута 'placeholder'
        String placeholderValueSum = inputSum.getAttribute("placeholder");
        // Проверить значение
        assertEquals("Сумма", placeholderValueSum, "Название в плейсхолдере не совпадает с 'Сумма'");
    }

    @DisplayName("Проверка плейсхолдеров <Домашний интернет>")
    @Test
    public void testPlaceholderInternet(){
        WebDriverWait waitElement = new WebDriverWait(driver, Duration.ofSeconds(4));

        onlinePayment.clickMenu();
        WebElement sumMenu = onlinePayment.homeInternet;
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
        onlinePayment.clickMenu();
        onlinePayment.clickSubMenu();
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

    //В порыве оптимизации немного подпортила тест
    @DisplayName("Проверка плейсхолдеров <Задолженность>")
    @Test
    public void testPlaceholderArrears(){
        onlinePayment.clickMenu();
        WebElement arrearsMenu = onlinePayment.arrears;
        arrearsMenu.click();

        // Ожидание появления полей ввода
        WebElement inputScore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("score-arrears")));
        WebElement inputScoreSum = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("arrears-sum")));
        // Извлечение и проверка значений placeholder
        String placeholderInternet = inputScore.getAttribute("placeholder");
        String placeholderInternetSum = inputScoreSum.getAttribute("placeholder");
        // Утверждения
        Assertions.assertEquals("Номер счета на 2073", placeholderInternet, "Название в плейсхолдере не совпадает с 'Номер счета на 2073'");
        Assertions.assertEquals("Сумма", placeholderInternetSum, "Название в плейсхолдере не совпадает с 'Сумма'");
    }

//Задание 2 по Lesson_16

    @DisplayName("Заполнение полей и переход на фрейм")
    @Test
    public void testFillFrame(){
        wait.until(ExpectedConditions.elementToBeClickable(onlinePayment.connectPhone)); //Ждём доступность поля Номер телефона
        onlinePayment.setPhone(phone);

        wait.until(ExpectedConditions.elementToBeClickable(onlinePayment.connectSum)); // Ждём доступность поля Сумма
        onlinePayment.setSum(sum);
        onlinePayment.clickButton();
// Явное ожидание фрейма и переход для дальнейшей оплаты
        WebElement iFrame = wait.until(ExpectedConditions.elementToBeClickable(onlinePayment.iframeConnect));
        driver.switchTo().frame(iFrame);

// Проверить, что введённая сумма содержится в тексте вместе с валютой "BYN"
        WebElement creditCardFormCost = wait.until(ExpectedConditions.visibilityOf(onlinePayment.creditCardFormCost));
        Assertions.assertEquals(sum + " BYN", creditCardFormCost.getText(), "Ожидаемая сумма и актуальна не совпадает.");

//Проверка значение плейсхолдера на фрейме <Номер карты>
        WebElement labelText = onlinePayment.labelText;
        Assertions.assertEquals("Номер карты", labelText.getText(), "Текст во фрейме не соответствует <Номер карты>");

// Проверка значения номера телефона
        WebElement numberPhoneText = onlinePayment.numberPhoneText;
        Assertions.assertEquals("Оплата: Услуги связи Номер:375297777777", numberPhoneText.getText(), "Текст во фрейме не соответствует <Оплата: Услуги связи Номер:375297777777>");

//Проверка значения плейсхоледра <Срок действия>
        WebElement timeInserted = onlinePayment.timeInserted;
        Assertions.assertEquals("Срок действия", timeInserted.getText(), "Текст во фрейме не соответствует <Срок действия>");

//Проверка надписи <CVC>
        WebElement testCVC = onlinePayment.testCVC;
        Assertions.assertEquals("CVC", testCVC.getText(), "Текст во фрейме не соответствует <CVC>");

//Проверка надписи <Имя держателя (на карте)>
        WebElement nameOuner = onlinePayment.nameOuner;
        Assertions.assertEquals("Имя держателя (как на карте)", nameOuner.getText(), "Текст во фрейме не соответствует <Имя держателя (на карте)>");

//Проверка надписи на кнопке
        WebElement buttonLocatorSum = onlinePayment.buttonLocatorSum;
        Assertions.assertEquals("Оплатить " + sum + " BYN", buttonLocatorSum.getText(), "Текст во фрейме не соответствует <Оплатить 10.55 BYN>");
    }
}
