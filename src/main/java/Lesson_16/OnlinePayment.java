package Lesson_16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OnlinePayment {
    public static WebDriver driver;

// Локаторы класса OnlinePayment
    By textLocator = By.xpath("//div[@class = 'pay__wrapper']/h2");
//    @FindBy(xpath = "//div[@class = 'pay__wrapper']/h2")
//    WebElement textLocator;
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
    @FindBy(xpath = "//div[@class='pay__wrapper']/h2")
        WebElement frameConnect;
    @FindBy(xpath = "//*[@class='bepaid-iframe']")
        WebElement iframeConnect;
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
        WebElement nameOwner;
    @FindBy(xpath = "//*[@class='colored disabled']")
        WebElement buttonLocatorSum;

//Локаторы полей на главной странице
    @FindBy(className = "select__header")
        WebElement menuSelect;
    @FindBy(xpath = "//p[@class='select__option' and text()='Рассрочка']")
        WebElement subMenu;
    @FindBy(xpath = "//p[@class='select__option' and text()='Домашний интернет']")
        WebElement homeInternet;
    @FindBy(xpath = "//p[@class='select__option' and text()='Задолженность']")
        WebElement arrears;

// Конструктор класса
    public OnlinePayment(WebDriver driver) {
        OnlinePayment.driver = driver;
    }
// Метод первого теста. Нахождение заголовка
    public String findText(){
        return driver.findElement(textLocator).getText().replaceAll("\n", " ");
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
//Метод для нажатия на меню
    public void clickMenu(){
        menuSelect.click();
    }
//Метод для субменю
    public void clickSubMenu(){
        subMenu.click();
    }
    public void clickArrears(){
        arrears.click();
    }
}
