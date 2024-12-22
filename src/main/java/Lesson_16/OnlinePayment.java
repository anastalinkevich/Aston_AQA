package Lesson_16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OnlinePayment {
    private static WebDriver driver;

// Локаторы класса OnlinePayment
    @FindBy(xpath = "//div[@class = 'pay__wrapper']/h2")
    WebElement textLocator;
    @FindBy(className = "pay__partners")
        WebElement partnersLocator;
    @FindBy(linkText = "Подробнее о сервисе")
        WebElement linkText;
    @FindBy(id = "connection-phone")
        WebElement connectPhone;
    @FindBy(id = "connection-sum")
        WebElement connectSum;
    @FindBy(xpath = "//*[@id='pay-connection']/button")
        WebElement submitButton;

//Локаторы фрейма оплаты
    @FindBy(xpath = "//*[@class='bepaid-iframe']")
        WebElement frameConnect;
    @FindBy(xpath = "//div[@class='pay-description__cost']/span[1]")
        WebElement creditCardFormCost;
    @FindBy(xpath = "//*[@class='ng-tns-c46-1 ng-star-inserted']")
        WebElement labelText;
    @FindBy(xpath = "//div[@class='pay-description__text']/span[1]")
        WebElement numberPhoneText;
    @FindBy(xpath = "//*[@class='ng-tns-c46-4 ng-star-inserted']")
        WebElement timeInserted;
    @FindBy(xpath = "//*[@class='ng-tns-c46-5 ng-star-inserted']")
        WebElement testCVC;
    @FindBy(xpath = "//*[@class='ng-tns-c46-3 ng-star-inserted']")
        WebElement nameOuner;
    @FindBy(xpath = "//*[@class='colored disabled']")
        WebElement buttonLocatorSum;


// Конструктор класса
    public OnlinePayment(WebDriver driver) {
        PageFactory.initElements(driver, this);
        OnlinePayment.driver = driver;;
    }
// Метод первого теста. Нахождение заголовка
    public OnlinePayment findText(){
        textLocator.getText();
        return this;
    }

// Метод второго теста. Ищем лого партеров
    public void findImagePartners(){
        partnersLocator.isDisplayed();
    }

//  Метод третьего теста. Проверяем переход по ссылке
    public void clickLink(){
        linkText.click();
    }
// Метод ввода номера телефона на основную страницу
    public void setPhone(String phone){
        connectPhone.sendKeys(phone);
    }
// Метод ввода суммы на основной странице
    public void setSum(String sum){
        connectSum.sendKeys(sum);
    }
//Метод нажатия кпопки Продолжить
    public void clickButton(){
        submitButton.click();
    }

}
