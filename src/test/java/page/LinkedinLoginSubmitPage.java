package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for LinkedinLoginSubmitPage.
 */
public class LinkedinLoginSubmitPage extends BasePage {
    @FindBy(xpath = "//*[@role='alert']")
    private WebElement alertBox;

    @FindBy (xpath = "//span[@id='session_password-login-error']")
    private WebElement userPasswordValidationText;

    @FindBy (xpath = "//span[@id='session_key-login-error']")
    private WebElement userEmailValidationText;

    /**
     * Constructor of LinkedinLoginSubmitPage class.
     * @param browser - WebDriver instance from test.
     */
    public LinkedinLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser,this);
        waitUntilElementIsVisible(alertBox,10);
    }

    /**
     * Method which get text from alertBox.
     * @return text from alertBox.
     */
    public String getAlertBoxText(){
        return alertBox.getText();
    }

    /**
     * Method which get text from getUserPasswordValidationText popup if user password is wrong.
     * @return text from getUserPasswordValidationText popup.
     */
    public String getUserPasswordValidationText(){
       return userPasswordValidationText.getText();
    }

    /**
     * Method which get text from getUserEmailValidationText popup if user email is wrong.
     * @return text from getUserEmailValidationText popup.
     */
    public String getUserEmailValidationText(){
        return userEmailValidationText.getText();
    }

    /**
     * Method isLoaded for LinkedinLoginSubmitPage.
     * @return true if LinkedinLoginSubmitPage is loaded.
     */
    public boolean isLoaded() {
        return alertBox.isDisplayed()
                && getCurrentPageTitle().contains("Войти в LinkedIn")
                && getCurrentPageUrl().contains("/uas/login-submit");
    }
}