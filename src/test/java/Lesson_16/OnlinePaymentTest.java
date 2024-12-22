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

// Перед всеми тестами
    @BeforeAll
    static void setUpAll(){
        WebDriverManager.chromedriver().setup();
    }
// Перед каждым тестом
    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/?hash-offset=70&hash-dur=1300#pay-section");
        driver.manage().window().maximize();
        WebElement cookieLocator = driver.findElement(By.xpath("//*[@id='cookie-agree']"));
        if(cookieLocator.isDisplayed()){
            cookieLocator.click();
        }
    }

// Первый тест Lesson_15
    @DisplayName("Проверка наличия текста 'Онлайн пополнение без комиссии'")
    @Test
    public void firstTest() {
        OnlinePayment actual = onlinePayment.findText();
        String expected = "Онлайн пополнение\nбез комиссии";
        Assertions.assertEquals(expected, actual, "Не удалось найти строку - Онлайн пополнение без комиссии");
    }
// Второй тест Lesson_15
    @DisplayName("Проверка наличия логотипов платёжных систем")
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
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Подробнее о сервисе")));
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
        String phone = "297777777";
        onlinePayment.inputPhone(phone);

        String sum = "10";
        onlinePayment.inputSum(sum);
        //driver.click();
        onlinePayment.clickButton();

    //Assertions.assertTrue(onlinePayment.clickButton().isDisplayed(), "Пополнение не произошло");
    }
    
// Тесты по лекции 17 "Тестирование с помощью Selenium WebDriver часть 2"

    @DisplayName("Проверка плейсхолдеров <Услуги связи>")
    @Test
    void testPlaceholderServices() {
        //WebElement until = wait.until(ExpectedConditions.elementToBeClickable(By.id("connection-phone")));
        WebElement inputPhone = driver.findElement(By.id("connection-phone"));
    // Получить значение атрибута 'placeholder'
        String placeholderValue = inputPhone.getAttribute("placeholder");
    // Проверить значение
        assertEquals("Номер телефона", placeholderValue, "Название в плейсхолдере не совпадает с 'Номер телефона'");

    // Найти элемент ввода текста
        WebElement inputSum = driver.findElement(By.id("connection-phone"));
    // Получить значение атрибута 'placeholder'
        String placeholderValueSum = inputSum.getAttribute("placeholder");
    // Проверить значение
        assertEquals("Номер телефона", placeholderValueSum, "Название в плейсхолдере не совпадает с 'Сумма'");
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

        WebElement menu = driver.findElement(By.className("select__header"));
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

    @DisplayName("Проверка плейсхолдеров Задолженность")
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

    @DisplayName("Заполнение полей и переход на фрейм")
    @Test
    public void testFillFrame(){
        WebDriverWait waitFrame = new WebDriverWait(driver, Duration.ofSeconds(4));
        WebElement numberPhone = waitFrame.until(ExpectedConditions.elementToBeClickable(By.id("connection-phone")));
        numberPhone.click();
        numberPhone.sendKeys("297777777");

        WebElement sumRub = waitFrame.until(ExpectedConditions.elementToBeClickable(By.id("connection-sum")));
        sumRub.click();
        sumRub.clear();
        sumRub.sendKeys("10.55");

        WebElement submit = driver.findElement(By.xpath("//*[@id='pay-connection']/button"));
        submit.click();

// Переходим на фрейм оплаты
        waitFrame.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='bepaid-iframe']"))); // By.xpath("//div[@class='app-wrapper__content']");
        WebElement iframe = driver.findElement(By.xpath("//*[@class='bepaid-iframe']"));//By.className("app-wrapper__content"
        driver.switchTo().frame(iframe);

// Проверить, что введённая сумма содержится в тексте вместе с валютой "BYN"
        WebElement phoneNumberFrame = driver.findElement(By.cssSelector(".ng-tns-c46-1.ng-star-inserted"));//"//div[@class='pay-description__cost']/span[1]"))
        String textFrame = phoneNumberFrame.getText();// By.className("pay-description__cost")
        Assertions.assertEquals("10.55 BYN", textFrame, "Текст во фрейме не совпадает с введенной суммой."); // Assertions.assertTrue(actualSum.contains("10") && actualSum.endsWith("BYN"),

//Проверка значение плейсхолдера на фрейме Номер карты
        WebElement labelText = driver.findElement(By.id("cc-number"));
        String text = labelText.getText();
        //Assertions.assertEquals("Номер карты", text, "Текст во фрейме не соответсвует Номер карты");

//Проверка значения плейсхоледра Срок действия
        WebElement timeDur = driver.findElement(By.xpath("//*[@class='ng-tns-c46-4 ng-star-inserted']"));

//Проверка надписи CVC
        WebElement testCVC = driver.findElement(By.xpath("//*[@class='ng-tns-c46-5 ng-star-inserted']"));

//Проверка надписи Имя держателя на карте
        WebElement nameOuner = driver.findElement(By.xpath("//*[@class='ng-tns-c46-3 ng-star-inserted']"));

        WebElement buttonLocatorSum = driver.findElement(By.xpath("//*[@class='ng-tns-c46-1 ng-star-inserted']"));

    }

}


