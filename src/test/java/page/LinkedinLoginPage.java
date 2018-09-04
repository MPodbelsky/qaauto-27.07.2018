package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *Page Object class for LinkedinLoginPage.
 */
public class LinkedinLoginPage extends BasePage {
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

    /**
     * Method isLoaded for LinkedinLoginPage.
     * @return true if LinkedinLoginPage is loaded.
     */
    public boolean isLoaded(){
        return singInButton.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn: Войти или зарегистрироваться")
                && getCurrentPageUrl().contains("linkedin.com");
    }

    /**
     * Method for login with invalid credentials.
     * @param userEmail - user email.
     * @param userPass - user password.
     * @return LinkedinLoginSubmitPage if credentials are invalid.
     */
    public LinkedinLoginSubmitPage logInReturnLoginSubmitPage(String userEmail, String userPass){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        singInButton.click();
        return new LinkedinLoginSubmitPage(browser);
    }

    /**
     * Method for login with valid credentials.
     * @param userEmail - user email.
     * @param userPass - user password.
     * @return LinkedinHomePage if credentials are valid.
     */
    public LinkedinHomePage logInReturnLinkedinHomePage (String userEmail, String userPass){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        singInButton.click();
        return new LinkedinHomePage(browser);
    }

    /**
     * Method for login with empty fields (email, password or both).
     * @param userEmail - user email.
     * @param userPass - user password.
     * @return LinkedinLoginPage if one of the fields (email or password) are empty.
     */
    public LinkedinLoginPage logInReturnLinkedInLoginPage(String userEmail, String userPass){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        singInButton.click();
        return new LinkedinLoginPage(browser);
    }

    /**
     * Method for clicking on "Forgot password" link.
     * @return LinkedinRequestPasswordResetPage.
     */
    public LinkedinRequestPasswordResetPage clickForgotPasswordLink(){
        forgotPasswordLink.click();
        return new LinkedinRequestPasswordResetPage(browser);
    }
}