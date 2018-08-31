package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *Page Object class for LinkedinLoginPage.
 */
public class LinkedinLoginPage extends BasePage {
    //методы которые переходят между страницами вовращают новые страницы
    //инициализирует елементы и ищет в момент обращения
    //page Object PageFactory
    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement singInButton;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    /**
     * Constructor of LinkedinLoginPage class.
     * @param browser - WebDriver instance from test.
     */
    public LinkedinLoginPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(singInButton,10);
    }
    public boolean isLoaded(){
        return singInButton.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn: Войти или зарегистрироваться")
                && getCurrentPageUrl().contains("linkedin.com");
    }
    public LinkedinLoginSubmitPage logInReturnLoginSubmitPage(String userEmail, String userPass){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        singInButton.click();
        return new LinkedinLoginSubmitPage(browser);
    }
    public LinkedinHomePage logInReturnLinkedinHomePage (String userEmail, String userPass){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        singInButton.click();
        return new LinkedinHomePage(browser);
    }
    public LinkedinLoginPage logInReturnLinkedInLoginPage(String userEmail, String userPass){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        singInButton.click();
        return new LinkedinLoginPage(browser);
    }
    public LinkedinRequestPasswordResetPage clickForgotPasswordLink(){
        forgotPasswordLink.click();
        return new LinkedinRequestPasswordResetPage(browser);
    }
}