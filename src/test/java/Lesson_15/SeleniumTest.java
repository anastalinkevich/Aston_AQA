package Lesson_15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class SeleniumTest {
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

    @BeforeAll
    static void setUpAll(){
        WebDriverManager.chromedriver().setup();
    }

    // Принимаем cookie
    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/?hash-offset=70&hash-dur=1300#pay-section");
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cookie-agree']")));
        WebElement cookieLocator = driver.findElement(By.xpath("//*[@id='cookie-agree']"));
        if(cookieLocator.isDisplayed()){
            cookieLocator.click();
        }
    }
//        By cookieLocator = By.xpath("//button[@class='btn btn_black cookie__ok']");
//
//        try {
//            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(cookieLocator));
//            cookieButton.click();
//            System.out.println("Приняли cookie.");
//        } catch (TimeoutException e) {
//            System.err.println("Не удалось принять cookie: Кнопка не появилась вовремя.");
//        } catch (NoSuchElementException e) {
//            System.err.println("Не удалось принять cookie: Кнопка не найдена.");
//        }

// Первый тест
    @DisplayName("Проверка наличия текста 'Онлайн пополнение без комиссии'")
    @Test
    void payTitle() {
        String expected = "Онлайн пополнение\nбез комиссии";
        WebElement actualLocator = driver.findElement(By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/h2"));
        String actual = actualLocator.getText();
        Assertions.assertEquals(expected, actual, "Название заголовка не совпадает");
    }

//        String expected = "Онлайн пополнение\nбез комиссии";
//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".pay h2")));
//        WebElement h2Element = driver.findElement(By.cssSelector(".pay h2"));
//        String h2Text = h2Element.getText().replaceAll("\n", "");// Не знаю как правильно записать локатор, когда текст разделен на две строки
//        //String actual = actualLocator.getText();
//        Assertions.assertEquals(expected, h2Text, "Не удалось найти строку - Онлайн пополнение без комиссии");

// Второй тест
    @DisplayName("Проверка наличия логотипов платёжных систем")
    @Test
    public void findImagePartners(){
//        WebElement partners = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pay__partners")));
//        Assertions.assertTrue(partners.isDisplayed(), "Логотипы партёров не найдены");
        driver.findElement(By.className("pay__partners"));
    }

// Третий тест
    @DisplayName("Проверка перехода по ссылке")
    @Test
    public void testLink() {
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String linkText = "Подробнее о сервисе";

    // Находим ссылку по тексту и кликаем на нее
    //wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
        WebElement link = driver.findElement(By.linkText(linkText));
        link.click();

    // Проверяем, что произошла навигация на нужную страницу
        Assertions.assertEquals(driver.getCurrentUrl(), expectedUrl, "Переход не произошёл по linkText 'Подробнее о сервисе'");

    // Возвращаемся на предыдущую страницу
        driver.navigate().back();
    }
// Четвертый тест
@DisplayName("Проверка на заполнение полей и подтверждения пополнения счёта")
@Test
public void testButton() {
    //wait.until(ExpectedConditions.elementToBeClickable(By.id("connection-phone")));
    WebElement numberPhone = driver.findElement(By.id("connection-phone"));
    numberPhone.click();
    numberPhone.sendKeys("297777777");

    //wait.until(ExpectedConditions.elementToBeClickable(By.id("connection-sum")));
    WebElement sumRub = driver.findElement(By.id("connection-sum"));
    sumRub.click();
    sumRub.sendKeys("10");

    //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='pay-connection']/button")));
    WebElement submit = driver.findElement(By.xpath("//*[@id='pay-connection']/button"));
    submit.click();

    Assertions.assertTrue(submit.isDisplayed(), "Пополнение не произошло");


//        WebElement numberPhone = driver.findElement(By.id("connection-phone"));
//        numberPhone.click();
//        numberPhone.sendKeys("297777777");
//
//        WebElement sumRub = driver.findElement(By.id("connection-sum"));
//        sumRub.click();
//        sumRub.sendKeys("10");
//
//        WebElement submit = driver.findElement(By.xpath("//*[@id='pay-connection']/button"));
//        submit.click();
}

    // Закрываем браузер
//    @AfterAll
//    public static void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

}
