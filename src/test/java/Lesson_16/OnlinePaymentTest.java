package Lesson_16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OnlinePaymentTest {
    public static OnlinePayment onlinePayment;
    public static InfoServise infoServise;

    private static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));


    @BeforeAll
    static void setUpAll(){
        WebDriverManager.chromedriver().setup();
    }

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
        //onlinePayment = new OnlinePayment(driver);

    // Первый тест
    @DisplayName("Проверка наличия текста 'Онлайн пополнение без комиссии'")
    @Test
    public void firstTest() {
        //onlinePayment.findText();
        //String expected = "Онлайн пополнение\nбез комиссии";
        //Assertions.assertEquals(expected, actual, "Не удалось найти строку - Онлайн пополнение без комиссии");
    }

    // Второй тест
    @DisplayName("Проверка наличия логотипов платёжных систем")
    @Test
    public void findPartners(){
        onlinePayment.findImagePartners();

    }

    // Второй тест
    @DisplayName("Проверка лого Партеров")
    @Test
    void logo(){
        driver.findElement(By.className("pay__partners"));
    }

    // Третий тест
    @DisplayName("Проверка перехода по ссылке")
    @Test
    public void testLink() {

        onlinePayment.clickLink();
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        // Проверяем, что произошла навигация на нужную страницу
        Assertions.assertEquals(driver.getCurrentUrl(), expectedUrl, "Переход не произошёл по linkText 'Подробнее о сервисе'");

        // Возвращаемся на предыдущую страницу
        driver.navigate().back();
    }

    // Четвёртый тест

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
        // Найти элемент ввода текста
        WebElement inputPhone = driver.findElement(By.id("connection-phone"));
        // Получить значение атрибута 'placeholder'
        String placeholderValue = inputPhone.getAttribute("placeholder");
        // Проверить значение
        Assertions.assertEquals("Номер телефона", placeholderValue, "Название в плейсхолдере не совпадает с 'Номер телефона'");

        // Найти элемент ввода текста
        WebElement inputSum = driver.findElement(By.id("connection-phone"));
        // Получить значение атрибута 'placeholder'
        String placeholderValueSum = inputSum.getAttribute("placeholder");
        // Проверить значение
        Assertions.assertEquals("Номер телефона", placeholderValueSum, "Название в плейсхолдере не совпадает с 'Сумма'");
    }

    @DisplayName("Проверка плейсхолдеров <Домашний интернет>")
    @Test
        public void testPlaceholderInternet(){
        WebDriverWait waitElement = new WebDriverWait(driver, Duration.ofSeconds(4));
        //WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("select__header")));
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

    @DisplayName("Проверка плейсхолдеров Рассрочка")
    @Test
    public void testPlaceholderScore(){
        WebDriverWait waitElement = new WebDriverWait(driver, Duration.ofSeconds(4));
        //WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("select__header")));
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
        //WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("select__header")));
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
        Assertions.assertEquals("Номер счета на 2073", placeholderInternet, "Название в плейсхолдере не совпадает с 'Номер счета на 2073'");
        Assertions.assertEquals("Сумма", placeholderInternetSum, "Название в плейсхолдере не совпадает с 'Сумма'");
    }

    //Задание 2

    @DisplayName("Заполнение полей")
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

        waitFrame.until(ExpectedConditions.elementToBeClickable(By.className("app-wrapper__content")));
        WebElement iframe = driver.findElement(By.className("app-wrapper__content"));
        driver.switchTo().frame(iframe);

        WebElement phoneNumberFrame = driver.findElement(By.className("content ng-tns-c46-1"));
        //Assertions.assertTrue(submit.isDisplayed(), "Пополнение не произошло");


//        WebElement spanElement = driver.findElement(By.cssSelector("span[_ngcontent-yia-c62]"));
//        By.cssSelector("span[_ngcontent-yia-c62]"
//        String actualSum = spanElement.getText();

        //driver.switchTo().frame(driver.findElement(By.className("app-wrapper__content")));
        //driver.findElement(By.className("app-wrapper__content"));
        // Ожидание появления полей ввода
//        By onlinePaymentCreditCardForm = By.xpath("//div[@class='app-wrapper__content']");
//        WebElement bePaidFrame = wait.until(ExpectedConditions.elementToBeClickable(By.className("bepaid-iframe")));
//        wait.until(ExpectedConditions.elementToBeClickable(onlinePaymentCreditCardForm));
//        //driver.switchTo().frame(bePaidFrame);
//        WebElement inputNumberCard = waitFrame.until(ExpectedConditions.visibilityOfElementLocated(By.className("ng-tns-c46-1 ng-star-inserted")));

        // Извлечение и проверка значений placeholder
        //String placeholdeNumberCard = inputNumberCard.getAttribute("placeholder");

        // Утверждения
        //Assertions.assertEquals("Номер карты", placeholdeNumberCard, "Название в плейсхолдере не совпадает с 'Номер карты'");

        // Проверить, что введённая сумма содержится в тексте вместе с валютой "BYN"
//        Assertions.assertTrue(actualSum.contains("10") && actualSum.endsWith("BYN"),
//                "Введенная сумма и отображаемая не соответствуют друг другу.");
//
//
//        WebElement spanElement2 = driver.findElement(By.cssSelector("span[_ngcontent-rpj-c62]"));
//
//        //h1[contains(text(),’297777777’)]
    }

}


