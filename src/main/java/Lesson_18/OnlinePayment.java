package Lesson_18;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OnlinePayment {
    public static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

// Локаторы формы "Онлайн пополнение без комиссии"
    @FindBy(xpath = "//div[@class='pay__wrapper']/h2")
    WebElement textLocator;         // Локатор заголовка "Онлайн пополнение без комиссии"
    @FindBy(className = "pay__partners")
    WebElement partnersLocator;     // Локатор лого партнеров
    @FindBy(linkText = "Подробнее о сервисе")
    WebElement linkText;            // Локатор ссылки "Подробнее о сервисе"
    @FindBy(id = "connection-phone")
    WebElement connectPhone;        // Локатор плейсхолдера поля "Номер телефона". Меню "Услуги связи"
    @FindBy(id = "connection-sum")
    WebElement connectSum;          // Локатор плейсхолдера поля "Сумма". Меню "Услуги связи"
    @FindBy(xpath = "//*[@id='pay-connection']/button")
    WebElement submitButton;        // Локатор кнопки "Продолжить"

//Локаторы фрейма продолжения оформления оплаты
    @FindBy(xpath = "//*[@class='bepaid-iframe']")
    WebElement iframeConnect;       // Локатор фрейма продолжения оформления оплаты
    @FindBy(xpath = "//div[@class='pay-description__cost']/span[1]")
    WebElement sumFrame;            // Локатор плейсхолдера. Строка "12.55 BYN"
    @FindBy(xpath = "//*[@class='ng-tns-c46-1 ng-star-inserted']")
    WebElement numberCardFrame;     // Локатор плейсхолдера. Поле "Номер карты"
    @FindBy(xpath = "//div[@class='pay-description__text']/span[1]")
    WebElement numberPhoneText;     // Локатор плейсхолдера. Строка "Оплата: Услуги связи Номер:375297777777"
    @FindBy(xpath = "//*[@class='ng-tns-c46-4 ng-star-inserted']")
    WebElement timeInserted;        // Локатор плейсхолдера. Поле "Срок действия"
    @FindBy(xpath = "//*[@class='ng-tns-c46-5 ng-star-inserted']")
    WebElement testCVC;             // Локатор плейсхолдера поля "CVC"
    @FindBy(xpath = "//*[@class='ng-tns-c46-3 ng-star-inserted']")
    WebElement nameOwner;           // Локатор плейсхолдера поля "Имя держателя (как на карте)"
    @FindBy(xpath = "//*[@class='colored disabled']")
    WebElement buttonLocatorSum;    // Локатор текста кнопки "Оплатить 12.55 BYN"
    @FindBy(xpath = "//div[@class='cards-brands ng-tns-c46-1']")
    WebElement logoPartnersFrame;   //Локатор лого партнеров на фрейме оплаты

//Локаторы полей в "Онлайн пополнение без комиссии"
    @FindBy(className = "select__header")
    WebElement menuSelect;          // Локатор нажатия на меню для вызова субменю
    @FindBy(xpath = "//p[@class='select__option' and text()='Рассрочка']")
    WebElement scoreMenu;           // Локатор перехода на субменю "Рассрочка"
    @FindBy(xpath = "//p[@class='select__option' and text()='Домашний интернет']")
    WebElement homeInternet;        // Локатор перехода на субменю "Домашний интернет"
    @FindBy(xpath = "//p[@class='select__option' and text()='Задолженность']")
    WebElement arrears;             // Локатор перехода на субменю "Задолженность"

// Локаторы субменю
    @FindBy(id = "internet-phone")
    WebElement internetPhone;       // Локатор поля "Номер абонента". Субменю "Домашний интернет"
    @FindBy(id = "internet-sum")
    WebElement internetSum;         // Локатор поля "Сумма". Субменю "Домашний интернет"
    @FindBy(id = "score-instalment")
    WebElement scoreInstalment;     // Локатор поля "Номер счета на 44". Субменю "Рассрочка"
    @FindBy(id = "instalment-sum")
    WebElement instalmentSum;       // Локатор поля "Сумма". Субменю "Рассрочка"
    @FindBy(id = "score-arrears")
    WebElement scoreArrears;        // Локатор поля "Номер счета на 2073". Субменю "Задолженность"
    @FindBy(id = "arrears-sum")
    WebElement arrearsSum;          // Локатор поля "Сумма". Субменю "Задолженность"

// Конструктор класса OnlinePayment
    public OnlinePayment(WebDriver driver) {
        PageFactory.initElements(driver, this);
        OnlinePayment.driver = driver;;
    }
// Нахождение заголовка "Онлайн пополнение без комиссии"
    public String findText(){
        return textLocator.getText().replaceAll("\n", " ");
    }
// Метод для получения списка альтернативных текстов логотипов партнеров в 'Онлайн пополнение без комиссии'
    public List<String> getPartnersLogoAltTexts() {
        List<String> paymentLogos = new ArrayList<>();
        List<WebElement> images = partnersLocator.findElements(By.tagName("img"));   // Находим все изображения внутри партнера
        for (WebElement img : images) {
            String altText = img.getAttribute("alt");                          // Получаем атрибут alt
            if (!altText.isEmpty()) {                                                // Проверяем, что атрибут не пустой
                paymentLogos.add(altText);
            }
        }
        return paymentLogos;
    }
// Проверка лого партеров на главной странице
//    public WebElement findImagePartners(){
//        wait.until(ExpectedConditions.visibilityOf(partnersLocator));
//        return partnersLocator; // Возвращаем найденный элемент
//    }
// Проверяем переход по ссылке "Подробнее о сервисе"
    public void clickLink(){
        wait.until(ExpectedConditions.elementToBeClickable(linkText));
        linkText.click();
    }
// Получить плейсхолдер поля "Номер телефона". Меню "Услуги связи"
    public String getConnectPhone() {
        return connectPhone.getDomProperty("placeholder");
    }
// Получить плейсхолдер поля "Сумма". Меню "Услуги связи"
    public String setSum(){
        return connectSum.getDomProperty("placeholder");
    }
// Метод для нажатия на меню для вызова субменю
    public void clickMenu(){
        menuSelect.click();
    }
// Получить плейсхолдер поля "Номер абонента". Субменю "Домашний интернет"
    public String getInternetPhone() {
        return internetPhone.getDomProperty("placeholder");
    }
// Получить плейсхолдер поля "Сумма". Субменю "Домашний интернет"
    public String getInternetSum() {
        return internetSum.getDomProperty("placeholder");
    }
// Получить плейсхолдер поля "Номер счета на 44". Субменю "Рассрочка"
    public String getScoreInstalment() {
        return scoreInstalment.getDomProperty("placeholder");
    }
// Получить плейсхолдер поля "Сумма". Субменю "Рассрочка"
    public String getInstalmentSum() {
        return instalmentSum.getDomProperty("placeholder");
    }
// Получить плейсхолдер поля "Номер счета на 2073". Субменю "Задолженность"
    public String getScoreArrears() {
        return scoreArrears.getDomProperty("placeholder");
    }
// Получить плейсхолдер поля "Сумма". Субменю "Задолженность"
    public String getArrearsSum() {
        return arrearsSum.getDomProperty("placeholder");
    }
// Переход на субменю "Домашний интернет"
    public void getClickInternet(){
        clickMenu();
        homeInternet.click();
    }
// Переход на субменю "Рассрочка"
    public void getClickScore(){
        clickMenu();
        scoreMenu.click();
    }
// Переход на субменю "Задолженность"
    public void getClickArrears(){
        clickMenu();
        arrears.click();
    }
// Заполнение полей формы на главной странице
    public void setForm(String phone, String cost){
        connectPhone.sendKeys(phone);
        connectSum.sendKeys(cost);
        submitButton.click();
    }
// Проверка плейсхолдера. Строка "12.55 BYN"
    public String getSumFrame(){
        return sumFrame.getText();
    }
// Проверка плейсхолдера. Поле "Номер карты"
    public String getCardNumber(){
        return numberCardFrame.getText();
    }
// Проверка плейсхолдера. Строка "Оплата: Услуги связи Номер:375297777777"
    public String getNumberPhoneText(){
        return numberPhoneText.getText();
    }
// Проверка плейсхолдера. Поле "Срок действия"
    public String getTimeInserted(){
        return timeInserted.getText();
    }
// Проверка плейсхолдера. Поле "CVC"
    public String getTestCVC(){
        return testCVC.getText();
    }
// Проверка плейсхолдера. Поле "Имя держателя (как на карте)"
    public String getNameOwner(){
        return nameOwner.getText();
    }
// Проверка плейсхолдера кнопки "Оплатить 12.55 BYN"
    public String getButtonLocatorSum(){
        return buttonLocatorSum.getText();
    }
// Метод для получения списка логотипов партнеров во фрейме оплаты
    public List<String> getPartnersLogoFrame() {
        List<String> paymentLogos = new ArrayList<>();
        WebElement element = logoPartnersFrame;
        List<WebElement> images = element.findElements(By.tagName("img")); // Находим все изображения внутри элемента

        for (WebElement img : images) {
            String textSrc = img.getAttribute("src");
            if (textSrc != null && !textSrc.isEmpty()) {
                paymentLogos.add(textSrc);
            }
        }
        return paymentLogos;
    }
// Ожидание фрейма для продолжения оформления оплаты и переход на него
    public void getIframeConnect(){
        WebElement iFrame = wait.until(ExpectedConditions.elementToBeClickable(iframeConnect)); // Явное ожидание фрейма и переход для дальнейшей оплаты
        driver.switchTo().frame(iFrame);
    }
// Отображение фрейма продолжения оформления оплаты после нажатия кнопки "Продолжить"
    public boolean isFormDisplayed(){
        return iframeConnect.isDisplayed();
    }
}
