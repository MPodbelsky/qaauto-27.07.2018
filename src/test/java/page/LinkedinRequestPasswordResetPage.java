package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for LinkedinRequestPasswordResetPage.
 */
public class LinkedinRequestPasswordResetPage extends BasePage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement userEmailField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    /**
     * Constructor of LinkedinRequestPasswordResetPage class.
     * @param browser - WebDriver instance from test.
     */
    public LinkedinRequestPasswordResetPage(WebDriver browser){
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    /**
     * Method isLoaded for LinkedinRequestPasswordResetPage.
     * @return true if LinkedinRequestPasswordResetPage is loaded.
     */
    public boolean isLoaded() {
        return findAccountButton.isDisplayed()
                && getCurrentPageTitle().contains("Изменить пароль | LinkedIn")
                && getCurrentPageUrl().contains("/uas/request-password-reset");
    }

    /**
     * Method which realised checking for user account to send email with reset password link.
     * @param userName - user email address.
     * @return LinkedinPasswordResetSubmitPage if checking is successfully.
     */
    public LinkedinPasswordResetSubmitPage findAccount(String userName) {
        BasePage.gMailService.connect();
        userEmailField.sendKeys(userName);
        findAccountButton.click();
        return new LinkedinPasswordResetSubmitPage(browser);
    }
}