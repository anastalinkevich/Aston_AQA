package Lesson_18;

import io.qameta.allure.Step;
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
    final String imgURL = "https://checkout.bepaid.by/widget_v2/assets/images/payment-icons/card-types/"; // Переменная для проверки иконок на фрейме

// Локатор согласия с сookie
    @FindBy(xpath = "//*[@id='cookie-agree']")
    WebElement cookieAgree;

// Локаторы формы "Онлайн пополнение без комиссии"
    By textLocator = By.xpath("//div[@class='pay__wrapper']/h2");     // Локатор заголовка "Онлайн пополнение без комиссии"
    By partnersLocator = By.className("pay__partners"); // Локатор лого партнеров
    By linkText = By.linkText("Подробнее о сервисе");     // Локатор ссылки "Подробнее о сервисе"
    By connectPhone = By.id("connection-phone");     // Локатор плейсхолдера поля "Номер телефона". Меню "Услуги связи"
    By connectSum = By.id("connection-sum");     // Локатор плейсхолдера поля "Сумма". Меню "Услуги связи"
    By submitButton = By.xpath("//*[@id='pay-connection']/button");     // Локатор кнопки "Продолжить"

//Локаторы фрейма продолжения оформления оплаты
    @FindBy(xpath = "//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")
    WebElement iframeConnect;       // Локатор фрейма продолжения оформления оплаты
//    @FindBy(xpath = "//div[@class='app-wrapper__content']")
//    WebElement onlinePaymentForm;
    @FindBy(xpath = "//*[@class='bepaid-iframe']")
    WebElement newFrameConnect;

    By sumFrame = By.xpath("//div[@class='pay-description__cost']/span[1]");    // Локатор плейсхолдера. Строка "12.55 BYN"
    By numberCardFrame = By.xpath("//*[@class='ng-tns-c46-1 ng-star-inserted']"); // Локатор плейсхолдера. Поле "Номер карты"
    By numberPhoneText = By.xpath("//div[@class='pay-description__text']/span[1]"); // Локатор плейсхолдера. Строка "Оплата: Услуги связи Номер:375297777777"
    By timeInserted = By.xpath("//*[@class='ng-tns-c46-4 ng-star-inserted']");     // Локатор плейсхолдера. Поле "Срок действия"
    By testCVC = By.xpath("//*[@class='ng-tns-c46-5 ng-star-inserted']");     // Локатор плейсхолдера поля "CVC"
    By nameOwner = By.xpath("//*[@class='ng-tns-c46-3 ng-star-inserted']");     // Локатор плейсхолдера поля "Имя держателя (как на карте)"
    By buttonLocatorSum = By.xpath("//*[@class='colored disabled']"); // Локатор текста кнопки "Оплатить 12.55 BYN"
    By logoPartnersFrame = By.xpath("//div[@class='cards-brands ng-tns-c46-1']"); //Локатор лого партнеров на фрейме оплаты

//Локаторы полей в "Онлайн пополнение без комиссии"
    By menuSelect = By.className("select__header");     // Локатор нажатия на меню для вызова субменю
    By scoreMenu = By.xpath("//p[@class='select__option' and text()='Рассрочка']");     // Локатор перехода на субменю "Рассрочка"
    By homeInternet = By.xpath("//p[@class='select__option' and text()='Домашний интернет']");     // Локатор перехода на субменю "Домашний интернет"
    By arrears = By.xpath("//p[@class='select__option' and text()='Задолженность']");     // Локатор перехода на субменю "Задолженность"

// Локаторы субменю
    By internetPhone = By.id("internet-phone");     // Локатор поля "Номер абонента". Субменю "Домашний интернет"
    By internetSum = By.id("internet-sum");     // Локатор поля "Сумма". Субменю "Домашний интернет"
    By scoreInstalment = By.id("score-instalment"); // Локатор поля "Номер счета на 44". Субменю "Рассрочка"
    By instalmentSum = By.id("instalment-sum");     // Локатор поля "Сумма". Субменю "Рассрочка"
    By scoreArrears = By.id("score-arrears");     // Локатор поля "Номер счета на 2073". Субменю "Задолженность"
    By arrearsSum = By.id("arrears-sum");     // Локатор поля "Сумма". Субменю "Задолженность"

// Конструктор класса OnlinePayment
    public OnlinePayment(WebDriver driver) {
        OnlinePayment.driver = driver;
    }

    @Step("Нахождение заголовка <Онлайн пополнение без комиссии>")
    public String findText(){
        return driver.findElement(textLocator).getText().replaceAll("\n", " ");
    }

    @Step("Получение списка альтернативных текстов логотипов партнеров в 'Онлайн пополнение без комиссии'")
    public List<String> getPartnersLogoAltTexts() {
        List<String> paymentLogos = new ArrayList<>();
        List<WebElement> images = driver.findElement(partnersLocator).findElements(By.tagName("img"));   // Находим все изображения внутри партнера
        for (WebElement img : images) {
            String altText = img.getAttribute("alt");                          // Получаем атрибут alt
            assert altText != null;
            if (!altText.isEmpty()) {                                                // Проверяем, что атрибут не пустой
                paymentLogos.add(altText);
            }
        }
        return paymentLogos;
    }

    @Step("Переход по ссылке <Подробнее о сервисе>")
    public void clickLink(){
        wait.until(ExpectedConditions.elementToBeClickable(linkText));
        driver.findElement(linkText).click();
    }
    @Step("Получить плейсхолдер поля <Номер телефона>. Меню <Услуги связи>")
    public String getConnectPhone() {
        return driver.findElement(connectPhone).getDomProperty("placeholder");
    }

    @Step("Получение плейсхолдера поля Сумма (Меню: Услуги связи)")
    public String setSum(){
        return driver.findElement(connectSum).getDomProperty("placeholder");
    }

    @Step("Клик на меню для вызова субменю")
    public void clickMenu(){
        driver.findElement(menuSelect).click();
    }

    @Step("Получить плейсхолдер поля <Номер абонента>. Субменю <Домашний интернет>")
    public String getInternetPhone() {
        return driver.findElement(internetPhone).getDomProperty("placeholder");
    }

    @Step("Получить плейсхолдер поля <Сумма>. Субменю <Домашний интернет>")
    public String getInternetSum() {
        return driver.findElement(internetSum).getDomProperty("placeholder");
    }

    @Step("Получить плейсхолдер поля <Номер счета на 44>. Субменю <Рассрочка>")
    public String getScoreInstalment() {
        return driver.findElement(scoreInstalment).getDomProperty("placeholder");
    }

    @Step("Получить плейсхолдер поля <Сумма>. Субменю <Рассрочка>")
    public String getInstalmentSum() {
        return driver.findElement(instalmentSum).getDomProperty("placeholder");
    }

    @Step("Получить плейсхолдер поля <Номер счета на 2073>. Субменю <Задолженность>")
    public String getScoreArrears() {
        return driver.findElement(scoreArrears).getDomProperty("placeholder");
    }

    @Step("Получить плейсхолдер поля <Сумма>. Субменю <Задолженность>")
    public String getArrearsSum() {
        return driver.findElement(arrearsSum).getDomProperty("placeholder");
    }

    @Step("Переход на субменю <Домашний интернет>")
    public void getClickInternet(){
        clickMenu();
        driver.findElement(homeInternet).click();
    }
    @Step("Переход на субменю <Рассрочка>")
    public void getClickScore(){
        clickMenu();
        driver.findElement(scoreMenu).click();
    }
    @Step("Переход на субменю <Задолженность>")
    public void getClickArrears(){
        clickMenu();
        driver.findElement(arrears).click();
    }
    @Step("Заполнение полей формы на главной странице")
    public void setForm(String phone, String cost){
        driver.findElement(connectPhone).sendKeys(phone);
        driver.findElement(connectSum).sendKeys(cost);
        driver.findElement(submitButton).click();
    }
    @Step("Проверка плейсхолдера. Строка <12.55 BYN>")
    public String getSumFrame(){
        return driver.findElement(sumFrame).getText();
    }

    @Step("Проверка плейсхолдера. Поле <Номер карты>")
    public String getCardNumber(){
        return driver.findElement(numberCardFrame).getText();
    }

    @Step("Проверка плейсхолдера. Строка <Оплата: Услуги связи Номер:375297777777>")
    public String getNumberPhoneText(){
        return driver.findElement(numberPhoneText).getText();
    }

    @Step("Проверка плейсхолдера. Поле <Срок действия>")
    public String getTimeInserted(){
        return driver.findElement(timeInserted).getText();
    }

    @Step("Проверка плейсхолдера. Поле <CVC>")
    public String getTestCVC(){
        return driver.findElement(testCVC).getText();
    }

    @Step("Проверка плейсхолдера. Поле <Имя держателя (как на карте)>")
    public String getNameOwner(){
        return driver.findElement(nameOwner).getText();
    }

    @Step("Проверка плейсхолдера кнопки <Оплатить 12.55 BYN>")
    public String getButtonLocatorSum(){
        return driver.findElement(buttonLocatorSum).getText();
    }

    @Step("Метод для получения списка логотипов партнеров во фрейме оплаты")
    public List<String> getPartnersLogoFrame() {
        List<String> paymentLogos = new ArrayList<>();
        WebElement element = driver.findElement(logoPartnersFrame);
        List<WebElement> images = element.findElements(By.tagName("img")); // Находим все изображения внутри элемента

        for (WebElement img : images) {
            String textSrc = img.getAttribute("src");
            if (textSrc != null && !textSrc.isEmpty()) {
                paymentLogos.add(textSrc);
            }
        }
        return paymentLogos;
    }

    @Step("Ожидание фрейма для продолжения оформления оплаты и переход на него")
    public boolean getIframeConnect(){
       // Явное ожидание фрейма и переход для дальнейшей оплаты
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameConnect));
        //WebElement formElement = wait.until(ExpectedConditions.visibilityOf(frameConnect));
        return driver.findElement(frameConnect).isDisplayed();
    }

    By frameConnect = By.xpath("//div[@class='pay__wrapper']/h2");

    @Step("Отображение фрейма продолжения оформления оплаты после нажатия кнопки \"Продолжить\"")
    public void switchToFrame(){
        // Инициализация WebElement с указанным локатором
        WebElement frameElement = driver.findElement(paymentIframe);

        // Ждем пока фрейм станет доступен и переключимся на него
        WebElement visibleFrame = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(frameElement));

        // Переключаем контекст на найденный фрейм
        driver.switchTo().frame(visibleFrame);

//        WebElement formElement = wait.until(ExpectedConditions.visibilityOf(frameConnect));
//        driver.switchTo().frame(formElement);
    }

    public void formToBeClickable(){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameConnect));
        wait.until(ExpectedConditions.elementToBeClickable(onlinePaymentForm));
    }
    private final By paymentIframe = By.className("bepaid-iframe");
    private final By onlinePaymentForm = By.xpath("//div[@class='app-wrapper__content']");
}
