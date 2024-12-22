package Lesson_16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OnlinePayment {
    private static WebDriver driver;
    public static InfoServise infoServise;

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
    }
// Метод первого теста. Нахождение заголовка
    public OnlinePayment findText(){
        driver.findElement(textLocator);
        return this;
    }

// Метод второго теста. Ищем лого партеров
    public OnlinePayment findImagePartners(){
        driver.findElement(partnersLocator);
        return this;
    }

//  Метод третьего теста. Проверяем переход по ссылке
    public InfoServise clickLink(){
        driver.findElement(linkText).click();
        return new InfoServise();
    }
// Метод ввода номера телефона на основную страницу
    public OnlinePayment inputPhone(String phone){
        driver.findElement(connectPhone).sendKeys();
        return this;
    }
// Метод ввода суммы на основной странице
    public OnlinePayment inputSum(String sum){
        driver.findElement(connectSum).sendKeys(sum);
        return this;
    }
//Метод нажатия кпоки Продолжить
    public OnlinePayment clickButton(){
        driver.findElement(submitButton).click();
        return this;
    }
}
