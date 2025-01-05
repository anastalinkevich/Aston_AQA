package Lesson_18;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public abstract class BaseTest {
    protected OnlinePayment onlinePayment;
    protected static WebDriver driver;
    protected String baseUrlOnlinePay = "https://www.mts.by/?hash-offset=70&hash-dur=1300#pay-section";

    @Step("Открытие драйвера. Начало теста")
    @BeforeEach
    void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        onlinePayment = new OnlinePayment(driver);

        driver.get(baseUrlOnlinePay);
        driver.manage().window().maximize();

        onlinePayment.getCookieAgree();
//        WebElement cookieLocator = onlinePayment.cookieAgree;
//        if (cookieLocator.isDisplayed()) {
//            cookieLocator.click();
//        }
    }

    @Step("Закрытие драйвера. Конец теста")
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
