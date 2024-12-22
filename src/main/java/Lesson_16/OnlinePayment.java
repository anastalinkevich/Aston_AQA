package Lesson_16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OnlinePayment {
    // Вебдрайвер и WebDriverWait
    private static WebDriver driver;
    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

    // Локаторы класса OnlinePayment
    //By cookieLocator = By.xpath("//button[@class='btn btn_black cookie__ok']");
    By textLocator = By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/h2");
    By partnersLocator = By.className("pay__partners");
    By linkText = By.linkText("Подробнее о сервисе");
    By connectPhone = By.id("connection-phone");
    By connectSum = By.id("connection-sum");
    By submitButton = By.xpath("//*[@id='pay-connection']/button");

    String text = "Онлайн пополнение без комиссии";

    // Конструктор класса
    public OnlinePayment(WebDriver driver) {
        this.driver = driver;

        if (!"Login".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the login page");
        }
    }
    // Метод первого теста
    public OnlinePayment findText(String text){
        driver.findElement(textLocator);
        return this;
    }

    // Метод второго теста
    public OnlinePayment findImagePartners(){
        driver.findElement(partnersLocator);
        return this;
    }

    //  Метод третьего теста
    public InfoServise clickLink(){
        driver.findElement(linkText).click();
        return new InfoServise();
    }

    public OnlinePayment inputPhone(String phone){
        driver.findElement(connectPhone).sendKeys();
        return this;
    }

    public OnlinePayment inputSum(String sum){
        driver.findElement(connectSum).sendKeys(sum);
        return this;
    }

    public OnlinePayment clickButton(){
        driver.findElement(submitButton).click();
        return this;
    }
}
