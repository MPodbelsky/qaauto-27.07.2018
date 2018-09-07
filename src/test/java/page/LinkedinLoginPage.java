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
                || getCurrentPageTitle().contains("LinkedIn: Log In or Sign Up")
                || getCurrentPageTitle().contains("LinkedIn: Einloggen oder Registrieren")
                && getCurrentPageUrl().contains("linkedin.com");
    }


    /**
     * Method that enters userEmail/userPass and click singIn button.
     * @param userEmail - String with user email.
     * @param userPass - String with user password.
     * @param <T> - Generic type to return corresponding pageObject.
     * @return either LinkedinLoginSubmitPage or LinkedinHomePage or LinkedinLoginPage pageObject.
     */
    public <T> T login(String userEmail, String userPass){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        singInButton.click();
        if (getCurrentPageUrl().contains("/uas/login-submit")){
            return (T) new LinkedinLoginSubmitPage(browser);
        }
        if (getCurrentPageUrl().contains("/feed")){
            return (T) new LinkedinHomePage(browser);
        }
        else{
            return (T) this;
        }
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