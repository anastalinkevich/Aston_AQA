package Lesson_18;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OnlinePayment {
    public static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

    // Локаторы класса OnlinePayment
    @FindBy(xpath = "//div[@class='pay__wrapper']/h2")
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
    @FindBy(xpath = "//div[@class='pay__wrapper']/h2")
    WebElement frameConnect;
    @FindBy(xpath = "//*[@class='bepaid-iframe']")
    WebElement iframeConnect;
    @FindBy(xpath = "//div[@class='pay-description__cost']/span[1]")
    WebElement sumFrame; //Проверка суммы введенной на главной странице
    @FindBy(xpath = "//*[@class='ng-tns-c46-1 ng-star-inserted']")
    WebElement numberCardFrame; //Плейсхолдер фрейма Номер карты
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
    WebElement scoreMenu;
    @FindBy(xpath = "//p[@class='select__option' and text()='Домашний интернет']")
    WebElement homeInternet;
    @FindBy(xpath = "//p[@class='select__option' and text()='Задолженность']")
    WebElement arrears;

    @FindBy(id = "internet-phone")
    WebElement internetPhone;
    @FindBy(id = "internet-sum")
    WebElement internetSum;
    @FindBy(id = "score-instalment")
    WebElement scoreInstalment;
    @FindBy(id = "instalment-sum")
    WebElement instalmentSum;
    @FindBy(id = "score-arrears")
    WebElement scoreArrears;
    @FindBy(id = "arrears-sum")
    WebElement arrearsSum;

    // Конструктор класса
    public OnlinePayment(WebDriver driver) {
        PageFactory.initElements(driver, this);
        OnlinePayment.driver = driver;;
    }
    // Метод первого теста. Нахождение заголовка
    public String findText(){
        return textLocator.getText().replaceAll("\n", " ");
    }

    // Метод второго теста. Ищем лого партеров
    public WebElement findImagePartners(){
        wait.until(ExpectedConditions.visibilityOf(partnersLocator));
        return partnersLocator; // Возвращаем найденный элемент
    }

    //  Метод третьего теста. Проверяем переход по ссылке
    public void clickLink(){
        wait.until(ExpectedConditions.elementToBeClickable(linkText));
        linkText.click();
    }
    // Метод ввода номера телефона на основную страницу
//    public void setPhone(String phone){
//        connectPhone.sendKeys(phone);
//    }

    // Получить плейсхолдер ConnectionPhone
    public String getConnectPhone() {
        return connectPhone.getDomProperty("placeholder");
    }

    // Метод ввода суммы на основной странице
    public String setSum(){
        return connectSum.getDomProperty("placeholder");
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
//    public void clickSubMenu(){
//        subMenu.click();
//    }
    public void clickArrears(){
        arrears.click();
    }

    // Получить плейсхолдер InternetPhone
    public String getInternetPhone() {
        return internetPhone.getDomProperty("placeholder");
    }
    // Получить плейсхолдер InternetSum
    public String getInternetSum() {
        return internetSum.getDomProperty("placeholder");
    }
    // Получить плейсхолдер
    public String getScoreInstalment() {
        return scoreInstalment.getDomProperty("placeholder");
    }
    // Получить плейсхолдер
    public String getInstalmentSum() {
        return instalmentSum.getDomProperty("placeholder");
    }
    // Получить плейсхолдер
    public String getScoreArrears() {
        return scoreArrears.getDomProperty("placeholder");
    }
    // Получить плейсхолдер
    public String getArrearsSum() {
        return arrearsSum.getDomProperty("placeholder");
    }
    //Переход на субменю Домашний интернет
    public void getClickInternet(){
        clickMenu();
        homeInternet.click();
    }
    //Переход на субменю Рассрочка
    public void getClickScore(){
        clickMenu();
        scoreMenu.click();
    }
    //Переход на субменю <Задолженность>
    public void getClickArrears(){
        clickMenu();
        arrears.click();
    }
    //Заполнение полей формы на главной странице
    public void setForm(String phone, String cost){
        connectPhone.sendKeys(phone);
        connectSum.sendKeys(cost);
        submitButton.click();
    }
    //Получение текста внутри локатора оплаты с фрейма
    public String getSumFrame(){
        return sumFrame.getText();
    }
    //Получение текста внутри локатора Номера карты с фрейма
    public String getCardNumber(){
        return numberCardFrame.getText();
    }
    //Получение текста внутри локатора
    public String getNumberPhoneText(){
        return numberPhoneText.getText();
    }
    //Получение текста внутри локатора
    public String getTimeInserted(){
        return timeInserted.getText();
    }
    //Получение текста внутри локатора
    public String getTestCVC(){
        return testCVC.getText();
    }
    //Получение текста внутри локатора
    public String getNameOwner(){
        return nameOwner.getText();
    }
    //Получение текста внутри локатора
    public String getButtonLocatorSum(){
        return buttonLocatorSum.getText();
    }

    public void getIframeConnect(){
        WebElement iFrame = wait.until(ExpectedConditions.elementToBeClickable(iframeConnect)); // Явное ожидание фрейма и переход для дальнейшей оплаты
        driver.switchTo().frame(iFrame);
    }

}
