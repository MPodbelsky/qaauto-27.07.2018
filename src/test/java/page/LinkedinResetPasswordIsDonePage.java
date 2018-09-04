package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for LinkedinResetPasswordIsDonePage.
 */
public class LinkedinResetPasswordIsDonePage extends BasePage {

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    /**
     * Constructor of LinkedinResetPasswordIsDonePage class.
     * @param browser - WebDriver instance from test.
     */
    public LinkedinResetPasswordIsDonePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(resetPasswordSubmitButton, 10);
    }

    /**
     * Method isLoaded for LinkedinResetPasswordIsDonePage.
     * @return true if LinkedinResetPasswordIsDonePage is loaded.
     */
    public boolean isLoaded() {
        return resetPasswordSubmitButton.isDisplayed()
                && getCurrentPageUrl().contains("/checkpoint/rp/password-reset-submit");
    }

    /**
     * Method clickReturnToLoginPage is clicked on the resetPasswordSubmitButton.
     * @return LinkedinLoginPage.
     */
    public LinkedinLoginPage clickReturnToLoginPage(){
        resetPasswordSubmitButton.click();
        return new LinkedinLoginPage(browser);
    }
}
